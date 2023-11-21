package com.eds.monolitica.users.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRolDTO {
    private Integer id;
    private boolean active;
    private Date createdAt;
    private UserDTO userDTO;
    private RolDTO rolDTO;

    public UserRolDTO(Integer id) {
        this.id = id;
    }
}
