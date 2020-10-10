package ru.zalupa_org.super_crud.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "customer")
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "login", nullable = false, unique = true)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL,
        orphanRemoval = true)
    private List<Music> musicList;

    @Enumerated(value = EnumType.STRING)
    @Column
    private Role role;

    public Customer(){

    }

    public Customer(String login, String password, List<Music> musicList) {
        this.login = login;
        this.password = password;
        this.musicList = musicList;
    }
}
