package com.github.habiteria.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * @author Alex Ivchenko
 */
@Getter
@Setter
@Entity
@Table(name = "visitors")
public class Visitor extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "date")
    private LocalDate date = LocalDate.now();

    public Visitor() {
    }

    public Visitor(User user) {
        this.user = user;
    }
}
