package com.github.netcracker2017team.project.domain.model;

import lombok.*;

import javax.persistence.*;

/**
 * @author Alex Ivchenko
 */
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "`user`")
public @Data class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    @Column(name = "`password`")
    private String password;
}
