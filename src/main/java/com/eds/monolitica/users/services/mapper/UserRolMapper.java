package com.eds.monolitica.users.services.mapper;

import com.eds.monolitica.users.domain.entities.Rol;
import com.eds.monolitica.users.domain.entities.User;
import com.eds.monolitica.users.domain.entities.UserDetail;
import com.eds.monolitica.users.domain.entities.UserRol;
import com.eds.monolitica.users.dto.UserDetailDTO;
import com.eds.monolitica.users.dto.UserRolDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRolMapper {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RolMapper rolMapper;
    public UserRolDTO toDto(UserRol userRol, boolean mapp) {
        UserRolDTO userRolDTO = new UserRolDTO();
        userRolDTO.setId(userRol.getId());
        userRolDTO.setActive(userRol.isActive());
        userRolDTO.setCreatedAt(userRol.getCreated_at());
        if (mapp){
            userRolDTO.setUserDTO(userMapper.toDto(userRol.getUsers()));
            userRolDTO.setRolDTO(rolMapper.toDto(userRol.getRol()));
        }
        return userRolDTO;
    }

    public UserRol toEntity(UserRolDTO userRolDTO, User user, Rol rol) {
        UserRol userRol = new UserRol();
        userRol.setActive(userRolDTO.isActive());
        userRol.setCreated_at(userRolDTO.getCreatedAt());
        userRol.setUsers(user);
        userRol.setRol(rol);
        return userRol;
    }
}
