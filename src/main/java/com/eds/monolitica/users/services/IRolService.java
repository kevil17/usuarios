package com.eds.monolitica.users.services;

import com.eds.monolitica.users.dto.RolDTO;
import com.eds.monolitica.users.dto.UserDTO;
import com.eds.monolitica.users.dto.UserForRolDTO;

import java.util.List;
import java.util.Optional;

public interface IRolService {
    List<RolDTO> listRolDetailed();

    List<RolDTO> listUsers();

    Optional<RolDTO> getRolById(Integer rolId);

    RolDTO save(RolDTO dto);

    void delete(Integer rolId);

    List<UserForRolDTO> listUsersByRolId(Integer rolId);
}
