package com.eds.monolitica.users.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class RolDTO {
    private Integer id;
    private String name;

    public RolDTO() {
    }

    public RolDTO(Integer id) {
        this.id = id;
    }

}
