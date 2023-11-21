package com.eds.monolitica.users.services.mapper;

import com.eds.monolitica.users.domain.entities.User;
import com.eds.monolitica.users.domain.entities.UserDetail;
import com.eds.monolitica.users.dto.UserDTO;
import com.eds.monolitica.users.dto.UserDetailDTO;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements CustomMapper<UserDTO, User> {

    @Override
    public UserDTO toDto(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUserName(user.getUserName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        return userDTO;
    }
    public UserDTO toDtoDetailed(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUserName(user.getUserName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());

        if (user.getUserDetail() != null) {
            UserDetailDTO userDetailDTO = new UserDetailDTO();
            UserDetail userDetail = user.getUserDetail();
            userDetailDTO.setId(userDetail.getId());
            userDetailDTO.setFirstName(userDetail.getFirstName());
            userDetailDTO.setLastName(userDetail.getLastName());
            userDetailDTO.setAge(userDetail.getAge());
            userDetailDTO.setBirthDay(userDetail.getBirthDay());
            userDTO.setUserDetailDTO(userDetailDTO);
        }else{
            userDTO.setUserDetailDTO(null);
        }
        return userDTO;
    }
    @Override
    public User toEntity(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setUserName(userDTO.getUserName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        return user;
    }
}
