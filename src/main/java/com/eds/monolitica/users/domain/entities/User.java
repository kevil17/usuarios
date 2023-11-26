package com.eds.monolitica.users.domain.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
@SQLDelete(sql = "UPDATE users SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
public class User {
    @Id
    @SequenceGenerator(name = "user_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    private Long id;
    @Column(name = "user_name")
    private String userName;
    private String password;
    private String email;
    @CreatedDate
    @Column(updatable = false, columnDefinition = "timestamp without time zone DEFAULT CURRENT_TIMESTAMP")
    private Date creation_date;
    @OneToOne(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private UserDetail userDetail;

    @OneToMany(mappedBy = "users", cascade = CascadeType.REMOVE)
    private List<UserRol> userRolList;
    @Column(columnDefinition = "BOOLEAN NOT NULL DEFAULT '0'")
    private boolean deleted;
    public User(String userName, String password, String email, Date createdAt) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.creation_date = createdAt;
    }

    public User() {
    }

    public User(Long id) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreatedAt() {
        return creation_date;
    }

    public void setCreatedAt(Date createdAt) {
        this.creation_date = createdAt;
    }

    public UserDetail getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetail userDetail) {
        this.userDetail = userDetail;
    }

    public Date getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(Date creation_date) {
        this.creation_date = creation_date;
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
