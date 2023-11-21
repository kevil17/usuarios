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
public class UserDetailDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
    private Date birthDay;
    private UserDTO userDTO;

    public UserDetailDTO(Long id) {
        this.id = id;
    }
}
