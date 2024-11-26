package com.crm.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "stop")
public class Stop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long stopId;

    private String stopName;

    @ManyToMany(mappedBy = "stops") // Reference the 'stops' field in Bus
    private Set<Bus> buses = new HashSet<>();
}
