package com.eds.monolitica.users.services.mapper;

import com.eds.monolitica.users.domain.entities.User;
import com.eds.monolitica.users.domain.entities.UserDetail;
import com.eds.monolitica.users.dto.UserDetailDTO;

public interface CustomMapper <DTO, E> {
    DTO toDto(E e);
    E toEntity(DTO dto);

}
