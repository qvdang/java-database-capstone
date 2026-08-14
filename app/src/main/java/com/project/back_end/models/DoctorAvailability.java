package com.project.back_end.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalTime;

@Entity
@Table(name = "doctor_available_times")
public class DoctorAvailability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "day_of_week", columnDefinition = "tinyint check (day_of_week between 1 and 7)")
    private Integer dayOfWeek;

    @Column(name = "begin_at", columnDefinition = "time(0)")
    private LocalTime beginAt;

    @Column(name = "end_at", columnDefinition = "time(0)")
    private LocalTime endAt;
}
