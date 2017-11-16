package com.github.habiteria.core.entities.imps;

import com.github.habiteria.core.entities.*;
import lombok.*;

import javax.persistence.*;

/**
 * @author Alex Ivchenko
 */
@Getter
@Setter
@Entity
@Table(name = "habits")
@ToString(of = "name", callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"owner", "name"}, callSuper = false)
public class HabitImpl extends AbstractEntity implements Habit {
    @ManyToOne(targetEntity = UserImpl.class)
    @JoinColumn(name = "user_id", nullable = false)
    private User owner;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToOne(cascade = CascadeType.ALL, optional = false, targetEntity = ScheduleImpl.class)
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;
}
