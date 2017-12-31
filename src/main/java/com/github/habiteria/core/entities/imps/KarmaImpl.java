package com.github.habiteria.core.entities.imps;

import com.github.habiteria.core.entities.AbstractEntity;
import com.github.habiteria.core.entities.Karma;
import com.github.habiteria.core.entities.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Alex Ivchenko
 */
@Setter
@Getter
@Entity
@Table(name = "karma")
public class KarmaImpl extends AbstractEntity implements Karma {
    @JoinColumn(name = "owner_id", nullable = false, updatable = false)
    @OneToOne(targetEntity = UserImpl.class)
    private User owner;

    @Column(name = "value")
    private Integer value;

    @Column(name = "actual_time")
    private LocalDateTime actualTime;

    @Override
    public void update(Integer value, LocalDateTime time) {
        this.value = value;
        this.actualTime = time;
    }
}
