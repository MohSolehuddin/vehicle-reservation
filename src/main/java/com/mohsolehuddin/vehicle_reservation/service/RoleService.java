package com.mohsolehuddin.vehicle_reservation.service;

import com.mohsolehuddin.vehicle_reservation.entity.Role;

public interface RoleService {
    Role getOrSaveRole(Role role);
}
