package com.mohsolehuddin.vehicle_reservation.feature.auth;

import com.mohsolehuddin.vehicle_reservation.feature.auth.signin.SignInRequest;
import com.mohsolehuddin.vehicle_reservation.feature.auth.signin.SignInResponse;
import com.mohsolehuddin.vehicle_reservation.feature.auth.signup.SignUpRequest;

public interface AuthService {
    void signup(SignUpRequest signupRequest);

    SignInResponse signin(SignInRequest signinRequest);

    void changePassword(String userId, String oldPassword, String newPassword);
}
