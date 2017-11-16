package com.github.habiteria.core.entities;

import com.github.habiteria.core.entities.imps.UserImpl;
import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode(of = {"user", "date"}, callSuper = false)
public class Visitor extends AbstractEntity {

    @ManyToOne(targetEntity = UserImpl.class)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "date")
    private LocalDate date = LocalDate.now();

    public Visitor() {
    }

    public Visitor(User user) {
        this.user = user;
    }
}
