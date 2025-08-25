package com.mohsolehuddin.vehicle_reservation.entity;

import com.mohsolehuddin.vehicle_reservation.constant.ERole;
import com.mohsolehuddin.vehicle_reservation.constant.PathDB;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = PathDB.ROLE)
public class Role{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private ERole role;
}
