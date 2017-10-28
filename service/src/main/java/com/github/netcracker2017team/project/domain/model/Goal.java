package com.github.netcracker2017team.project.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * @author Alex Ivchenko
 */
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "goals")
@Entity
@DiscriminatorColumn(name = "type")
public abstract class Goal extends AbstractEntity {
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @Enumerated
    @Column(name = "termination_status")
    private TerminationStatus terminationStatus;

    @Column(name = "start_time")
    private LocalDate startTime;

    public enum Status {
        WAIT_FOR_ACCEPT, ACCEPTED, RESOLVED
    }
}
