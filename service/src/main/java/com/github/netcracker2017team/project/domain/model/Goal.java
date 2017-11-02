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
    @JoinColumn(name = "doer_id", nullable = false)
    private User doer;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status = Status.NEW;

    @Enumerated
    @Column(name = "termination_status")
    private TerminationStatus terminationStatus;

    @Column(name = "start_time")
    private LocalDate startTime;

    public void publish() {
        if (status == Status.NEW) {
            status = Status.PUBLISHED;
        } else {
            throw new IllegalStateException();
        }
    }

    public void accept() {
        if (status == Status.PUBLISHED) {
            status = Status.ACCEPTED;
        } else {
            throw new IllegalStateException();
        }
    }

    public enum Status {
        NEW, PUBLISHED, ACCEPTED, RESOLVED
    }
}
