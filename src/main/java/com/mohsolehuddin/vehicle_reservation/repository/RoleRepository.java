package com.mohsolehuddin.vehicle_reservation.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mohsolehuddin.vehicle_reservation.constant.ERole;
import com.mohsolehuddin.vehicle_reservation.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    Optional<Role> findByRole(ERole name);
}
