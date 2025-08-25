package com.mohsolehuddin.vehicle_reservation.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mohsolehuddin.vehicle_reservation.entity.AppUser;
import com.mohsolehuddin.vehicle_reservation.entity.User;
import com.mohsolehuddin.vehicle_reservation.repository.UserRepository;
import com.mohsolehuddin.vehicle_reservation.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public AppUser loadUserByUserId(String id) {
        User userCredential = userRepository.findById(id).orElseThrow(()-> new UsernameNotFoundException("Invalid credential"));
        return AppUser.builder()
                .id(userCredential.getId())
                .email(userCredential.getEmail())
                .password(userCredential.getPassword())
                .role(userCredential.getRole().getRole())
                .build();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User userCredential = userRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("Invalid credential"));
        return AppUser.builder()
                .id(userCredential.getId())
                .email(userCredential.getEmail())
                .password(userCredential.getPassword())
                .role(userCredential.getRole().getRole())
                .build();
    }
}
