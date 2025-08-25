package com.mohsolehuddin.vehicle_reservation.entity;

import java.sql.Date;

import com.mohsolehuddin.vehicle_reservation.constant.EGender;
import com.mohsolehuddin.vehicle_reservation.constant.PathDB;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = PathDB.USER_PROFILE)
public class UserProfile extends AuditEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, name = "date_of_birth")
    private Date dateOfBirth;

    @Column(nullable = false)
    private EGender gender;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}

