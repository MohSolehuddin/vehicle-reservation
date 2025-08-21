package com.mohsolehuddin.vehicle_reservation.entity;

import com.mohsolehuddin.vehicle_reservation.constant.EGender;
import com.mohsolehuddin.vehicle_reservation.constant.PathDB;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.sql.Date;

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

