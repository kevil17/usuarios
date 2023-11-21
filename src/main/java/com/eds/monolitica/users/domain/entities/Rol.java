package com.eds.monolitica.users.domain.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "rol")
public class Rol {
    @Id
    @SequenceGenerator(name = "rol_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rol_sequence")
    private Integer id;
    private String name;
    @OneToMany(mappedBy = "rol", cascade = CascadeType.REMOVE)
    private List<UserRol> userRolList;

    public Rol() {
    }

    public Rol(Integer id) {
        this.id = id;
    }

    public Rol(Integer id, String name, List<UserRol> userRolList) {
        this.id = id;
        this.name = name;
        this.userRolList = userRolList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserRol> getUserRolList() {
        return userRolList;
    }

    public void setUserRolList(List<UserRol> userRolList) {
        this.userRolList = userRolList;
    }
}
