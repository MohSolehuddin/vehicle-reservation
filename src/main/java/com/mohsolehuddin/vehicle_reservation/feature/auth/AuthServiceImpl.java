package com.mohsolehuddin.vehicle_reservation.feature.auth;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.mohsolehuddin.vehicle_reservation.constant.ERole;
import com.mohsolehuddin.vehicle_reservation.constant.Message;
import com.mohsolehuddin.vehicle_reservation.entity.AppUser;
import com.mohsolehuddin.vehicle_reservation.entity.Role;
import com.mohsolehuddin.vehicle_reservation.entity.User;
import com.mohsolehuddin.vehicle_reservation.feature.auth.signin.SignInRequest;
import com.mohsolehuddin.vehicle_reservation.feature.auth.signin.SignInResponse;
import com.mohsolehuddin.vehicle_reservation.feature.auth.signup.SignUpRequest;
import com.mohsolehuddin.vehicle_reservation.repository.UserRepository;
import com.mohsolehuddin.vehicle_reservation.security.JwtUtil;
import com.mohsolehuddin.vehicle_reservation.service.RoleService;
import com.mohsolehuddin.vehicle_reservation.util.ValidationUtil;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    // private final UserProfileService userProfileService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final ValidationUtil validationUtil;

    @Value("${app.vehicle-reservation.email-super-admin}")
    private String emailSuperAdmin;

    @Value("${app.vehicle-reservation.password-admin}")
    private String passwordSuperAdmin;

    @PostConstruct
    private void initAdmin() {
        Optional<User> admin = userRepository.findByEmail(emailSuperAdmin);
        if (admin.isPresent()) {
            return;
        }

        Role role = roleService.getOrSaveRole(Role.builder().role(ERole.SUPER_ADMIN).build());

        User user = User.builder()
                .email(emailSuperAdmin)
                .password(passwordEncoder.encode(passwordSuperAdmin))
                .role(role)
                .build();
        userRepository.saveAndFlush(user);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void signup(SignUpRequest signupRequest) {
        if (userRepository.findByEmailAndDeletedDateIsNull(signupRequest.email()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");
        }
        validationUtil.validate(signupRequest);
        Role role = roleService.getOrSaveRole(Role.builder().role(ERole.ADMIN).build());

        User user = User.builder()
                .email(signupRequest.email())
                .password(passwordEncoder.encode(signupRequest.password()))
                .role(role)
                .build();
        userRepository.saveAndFlush(user);
    }

    @Override
    public SignInResponse signin(SignInRequest signinRequest) {
        validationUtil.validate(signinRequest);
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    signinRequest.email(),
                    signinRequest.password()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            AppUser appUser = (AppUser) authentication.getPrincipal();
            String token = jwtUtil.generateToken(appUser);

            String name = null;

            return new SignInResponse(
                    token,
                    appUser.getId(),
                    name
            );
        } catch (BadCredentialsException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email or password");
        }
    }
    @Override
    public void changePassword(String userId, String oldPassword, String newPassword) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, Message.DATA_NOT_FOUND));
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Last password is incorrect");
        }

        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedPassword);
        userRepository.saveAndFlush(user);
    }
}
