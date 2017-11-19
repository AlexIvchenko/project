package com.github.habiteria.core.entities.imps;

import com.github.habiteria.core.entities.AbstractEntity;
import com.github.habiteria.core.entities.Player;
import com.github.habiteria.core.entities.User;
import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode(of = "user", callSuper = false)
public class PlayerImpl extends AbstractEntity implements Player {
    @OneToOne(targetEntity = UserImpl.class)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "points")
    private int points;

    @Column(name = "health")
    private int health;
}
