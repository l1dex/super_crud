package ru.zalupa_org.super_crud.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role",nullable = false)
    private Role role;


    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL,
        orphanRemoval = true,fetch = FetchType.EAGER)
    private List<Music> musicList;

    public List<Music> getMusicList() {
        return Optional.ofNullable(musicList).orElse(Collections.emptyList());
    }

    Customer(){

    }

    public Customer(String login, String password, List<Music> musicList) {
        this.login = login;
        this.password = password;
        this.musicList = musicList;
    }
}
