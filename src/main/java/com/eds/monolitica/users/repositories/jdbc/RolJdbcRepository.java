package com.eds.monolitica.users.repositories.jdbc;

import com.eds.monolitica.users.dto.UserDTO;
import com.eds.monolitica.users.dto.UserForRolDTO;

import java.util.List;

public interface RolJdbcRepository {
    List<UserForRolDTO> listUsersByRolId(Integer rolId);
}
