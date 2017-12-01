package com.github.habiteria.core.entities.imps;

import com.github.habiteria.core.entities.AbstractEntity;
import com.github.habiteria.core.entities.Habit;
import com.github.habiteria.core.entities.User;
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
    @ManyToOne(targetEntity = UserImpl.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User owner;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Embedded
    private ScheduleImpl schedule;
}
