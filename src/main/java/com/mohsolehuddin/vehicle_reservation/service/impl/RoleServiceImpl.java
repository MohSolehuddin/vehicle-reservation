package com.mohsolehuddin.vehicle_reservation.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mohsolehuddin.vehicle_reservation.entity.Role;
import com.mohsolehuddin.vehicle_reservation.repository.RoleRepository;
import com.mohsolehuddin.vehicle_reservation.service.RoleService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role getOrSaveRole(Role role) {
        Optional<Role> findRole = roleRepository.findByRole(role.getRole());
        return findRole.orElseGet(() -> roleRepository.saveAndFlush(role));
    }
}
