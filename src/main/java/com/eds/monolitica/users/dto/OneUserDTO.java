package com.eds.monolitica.users.dto;

import com.eds.monolitica.users.domain.entities.UserDetail;
import com.eds.monolitica.users.domain.entities.UserRol;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;
@Getter
@Setter
@ToString
public class OneUserDTO {
    private Long id;
    private String userName;
    private String password;
    private String email;
    private UserDetail userDetail;
    //private List<UserRol> userRolList;
    public OneUserDTO() {
    }

    public OneUserDTO(Long id) {
        this.id = id;
    }

}
