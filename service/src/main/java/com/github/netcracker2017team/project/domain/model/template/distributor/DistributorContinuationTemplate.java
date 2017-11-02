package com.github.netcracker2017team.project.domain.model.template.distributor;

import com.github.netcracker2017team.project.domain.model.Distributor;
import com.github.netcracker2017team.project.domain.model.template.ContinuationTemplate;
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
@ToString(callSuper = true, exclude = "owner")
@Entity
@DiscriminatorValue("distributor")
public class DistributorContinuationTemplate extends ContinuationTemplate {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "distributor_owner_id")
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
