package com.github.netcracker2017team.project.domain.model.template;

import com.github.netcracker2017team.project.domain.model.Distributor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * @author Alex Ivchenko
 */
@Setter
@Getter
@ToString
@Entity
@DiscriminatorValue("distributor")
public class DistributorContinuation extends ContinuationTemplate {

    @ManyToOne
    @JoinColumn(name = "owner")
    private Distributor owner;

    @Column(name = "points")
    private Integer points;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;
}