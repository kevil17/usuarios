package com.eds.monolitica.users.services;

import com.eds.monolitica.users.dto.UserRolDTO;

import java.util.List;
import java.util.Optional;

public interface IUserRolService {
    List<UserRolDTO> listDetailUserRol();

    Optional<UserRolDTO> getUserRolById(Integer id);

    UserRolDTO save(UserRolDTO dto);

    UserRolDTO update(UserRolDTO userDTO);

    void delete(Integer id);
}
