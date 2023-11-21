package com.eds.monolitica.users.services;


import com.eds.monolitica.users.dto.UserDetailDTO;

import java.util.List;
import java.util.Optional;

public interface IUserDetailService {
    List<UserDetailDTO> listDetailUsers();
    UserDetailDTO save(UserDetailDTO dto);

    UserDetailDTO update(UserDetailDTO dto);

    Optional<UserDetailDTO> getUserById(Long id);
    void delete(Long id);
}
