package ru.zalupa_org.super_crud.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@Entity
@Table(name = "user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "login", nullable = false, unique = true)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,
        orphanRemoval = true)
    private Set<Music> musicList;

    User(){}

    public User(Long id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

}
