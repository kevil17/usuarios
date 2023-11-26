package com.eds.monolitica.users.services;

import com.eds.monolitica.users.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<UserDTO> listUsers();
    List<UserDTO> listUsersDetailed();
    UserDTO save(UserDTO dto);
    Optional<UserDTO> getUserById(Long id);
    void delete(Long id);

    UserDTO update(UserDTO userDTO);
}
