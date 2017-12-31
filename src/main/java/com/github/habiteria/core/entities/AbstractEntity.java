package com.github.habiteria.core.entities;

import lombok.ToString;

import javax.persistence.*;

/**
 * @author Alex Ivchenko
 */
@ToString(of = "id")
@MappedSuperclass
public abstract class AbstractEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
