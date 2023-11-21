package com.eds.monolitica.users.services.mapper;

import com.eds.monolitica.users.domain.entities.User;
import com.eds.monolitica.users.domain.entities.UserDetail;
import com.eds.monolitica.users.dto.UserDetailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDetailMapper{
    @Autowired
    private UserMapper userMapper;
    public UserDetailDTO toDto(UserDetail userDetail, boolean mapUser) {
        UserDetailDTO userDetailDTO = new UserDetailDTO();
        userDetailDTO.setId(userDetail.getId());
        userDetailDTO.setFirstName(userDetail.getFirstName());
        userDetailDTO.setLastName(userDetail.getLastName());
        userDetailDTO.setAge(userDetail.getAge());
        userDetailDTO.setBirthDay(userDetail.getBirthDay());
        if (mapUser){
            userDetailDTO.setUserDTO(userMapper.toDto(userDetail.getUser()));
        }
        return userDetailDTO;
    }

    public UserDetail toEntity(UserDetailDTO userDetailDTO, User user) {
        UserDetail userDetail = new UserDetail();
        userDetail.setFirstName(userDetailDTO.getFirstName());
        userDetail.setLastName(userDetailDTO.getLastName());
        userDetail.setAge(userDetailDTO.getAge());
        userDetail.setBirthDay(userDetailDTO.getBirthDay());
        userDetail.setUser(user);
        return userDetail;
    }
}
