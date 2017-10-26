package com.github.netcracker2017team.project.domain.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * @author Alex Ivchenko
 */
@Setter
@Getter
@ToString
@Entity
@Table(name = "distributor_goal")
public class DistributorCreatedGoal extends Goal {

    @ManyToOne
    @JoinColumn(name = "distributor_owner_id", nullable = false)
    private Distributor distributor;

    @Column(name = "points")
    private int points;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;
}
