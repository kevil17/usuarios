package com.eds.monolitica.users.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserForRolDTO {
    private Long id;
    private String rolName;
    private Boolean active;
    private String userName;
    private String password;
    private String email;

    public UserForRolDTO() {
    }

    public UserForRolDTO(Long id) {
        this.id = id;
    }

    public UserForRolDTO(Long id, String rolName,Boolean active, String userName, String password, String email) {
        this.id = id;
        this.rolName=rolName;
        this.active=active;
        this.userName = userName;
        this.password = password;
        this.email = email;
    }
}
