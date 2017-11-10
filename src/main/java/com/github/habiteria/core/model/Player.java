package com.github.habiteria.core.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Alex Ivchenko
 */
@Getter
@Setter
@Entity
@Table(name = "players")
public class Player extends AbstractEntity {
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "points")
    private int points;

    @Column(name = "hearts")
    private int hearts;
}
