package com.mohsolehuddin.vehicle_reservation.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.mohsolehuddin.vehicle_reservation.entity.AppUser;

public interface UserService extends UserDetailsService {
    AppUser loadUserByUserId(String id);
}
