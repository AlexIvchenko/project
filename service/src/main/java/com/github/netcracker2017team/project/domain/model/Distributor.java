package com.github.netcracker2017team.project.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Alex Ivchenko
 */
@Setter
@Getter
@ToString
@Entity
@Table(name = "distributor")
public class Distributor extends AbstractEntity {

    @Column(name = "url", nullable = false, unique = true)
    private String url;

    @Column(name = "password", nullable = false, unique = true)
    private String password;
}
