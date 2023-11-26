package com.eds.monolitica.users.domain.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.List;

@Entity
@Table(name = "rol")
@SQLDelete(sql = "UPDATE rol SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
public class Rol {
    @Id
    @SequenceGenerator(name = "rol_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rol_sequence")
    private Integer id;
    private String name;
    @OneToMany(mappedBy = "rol", cascade = CascadeType.REMOVE)
    private List<UserRol> userRolList;
    @Column(columnDefinition = "BOOLEAN NOT NULL DEFAULT '0'")
    private boolean deleted;
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

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
