package com.eds.monolitica.users.domain.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Entity
@Table(name = "user_rol")
@SQLDelete(sql = "UPDATE user_rol SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
public class UserRol {
    @Id
    @SequenceGenerator(name = "user_rol_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_rol_sequence")
    private Integer id;

    private boolean active;
    @CreatedDate
    @Column(updatable = false, columnDefinition = "timestamp without time zone DEFAULT CURRENT_TIMESTAMP")
    private Date created_at;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rol_id")
    private Rol rol;
    @Column(columnDefinition = "BOOLEAN NOT NULL DEFAULT '0'")
    private boolean deleted;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User users;

    public UserRol() {
    }

    public UserRol(Integer id) {
        this.id = id;
    }

    public UserRol(Integer id, boolean active, Date created_at, Rol rol, User user) {
        this.id = id;
        this.active = active;
        this.created_at = created_at;
        this.rol = rol;
        this.users = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
