package com.github.netcracker2017team.project.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Alex Ivchenko
 */
@Setter
@Getter
@Entity
@Table(name = "instructions")
public class Instruction extends AbstractEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "step_id")
    private Step step;

    @Column(name = "message")
    private String message;
}
