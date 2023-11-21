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
public class UserDTO {
    private Long id;
    private String userName;
    private String password;
    private String email;

    private UserDetailDTO userDetailDTO;
    public UserDTO() {
    }

    public UserDTO(Long id) {
        this.id = id;
    }

    public UserDTO(Long id, String userName, String password, String email) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    public void setUserDetailDTO(UserDetailDTO userDetailDTO) {
        this.userDetailDTO = userDetailDTO;
    }
}
