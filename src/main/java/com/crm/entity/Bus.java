package com.crm.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name="bus")
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long busId;

    private String busName;

    @ManyToMany
    @JoinTable(
            name = "bus_stop", // Name of the join table
            joinColumns = @JoinColumn(name = "bus_id"), // Foreign key for Bus
            inverseJoinColumns = @JoinColumn(name = "stop_id") // Foreign key for Stop
    )
    private Set<Stop> stops = new HashSet<>();
}
