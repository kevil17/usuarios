package com.eds.monolitica.users.services.mapper;

import com.eds.monolitica.users.domain.entities.Rol;
import com.eds.monolitica.users.domain.entities.User;
import com.eds.monolitica.users.dto.RolDTO;
import com.eds.monolitica.users.dto.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class RolMapper {
    public RolDTO toDto(Rol rol) {
        RolDTO rolDTO = new RolDTO();
        rolDTO.setId(rol.getId());
        rolDTO.setName(rol.getName());
        return rolDTO;
    }
    public Rol toEntity(RolDTO rolDTO) {
        Rol rol = new Rol();
        rol.setId(rolDTO.getId());
        rol.setName(rolDTO.getName());
        return rol;
    }
}
