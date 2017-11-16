package com.github.habiteria.core.entities;

import com.github.habiteria.core.entities.imps.UserImpl;
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
    @OneToOne(targetEntity = UserImpl.class)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "points")
    private int points;

    @Column(name = "hearts")
    private int hearts;
}
