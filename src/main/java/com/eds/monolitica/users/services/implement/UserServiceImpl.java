package com.eds.monolitica.users.services.implement;

import com.eds.monolitica.users.domain.entities.User;
import com.eds.monolitica.users.domain.entities.UserDetail;
import com.eds.monolitica.users.dto.UserDTO;
import com.eds.monolitica.users.dto.UserDetailDTO;
import com.eds.monolitica.users.exceptions.NotFoundException;
import com.eds.monolitica.users.repositories.data.UserDetailRepository;
import com.eds.monolitica.users.repositories.data.UserRepository;
import com.eds.monolitica.users.services.IUserService;
import com.eds.monolitica.users.services.mapper.UserDetailMapper;
import com.eds.monolitica.users.services.mapper.UserMapper;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;
    private final UserDetailRepository userDetailRepository;
    private final UserMapper userMapper;
    private final UserDetailMapper userDetailMapper;

    public UserServiceImpl(UserRepository userRepository, UserDetailRepository userDetailRepository, UserMapper userMapper, UserDetailMapper userDetailMapper) {
        this.userRepository = userRepository;
        this.userDetailRepository = userDetailRepository;
        this.userMapper = userMapper;

        this.userDetailMapper = userDetailMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> listUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> listUsersDetailed() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDtoDetailed).collect(Collectors.toList());
    }

    @Override
    public UserDTO save(UserDTO dto) {
        User user = userRepository.save(userMapper.toEntity(dto));
        if(dto.getUserDetailDTO()!=null){
            UserDetail userDetail = userDetailRepository.save(userDetailMapper.toEntity(dto.getUserDetailDTO(),user));
        }
        return userMapper.toDto(user);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserDTO> getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user==null){
            throw new NotFoundException("Usuario",id);
        }
        return user.map(userMapper::toDtoDetailed);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDTO update(UserDTO userDTO) {
        if(userDTO.getUserDetailDTO()!=null){
            UserDetail example= new UserDetail(userDTO.getUserDetailDTO().getId());
            Optional<UserDetail> optionalDetail = userDetailRepository.findOne(Example.of(example));
            if(optionalDetail.isEmpty()){
                throw new NotFoundException("Detalles del usuario", example.getId());
            }
            UserDetailDTO userDetailDTO = userDTO.getUserDetailDTO();
            UserDetail userDetail = optionalDetail.get();
            userDetail.setFirstName(userDetailDTO.getFirstName());
            userDetail.setLastName(userDetailDTO.getLastName());
            userDetail.setBirthDay(userDetailDTO.getBirthDay());
            userDetail.setAge(userDetailDTO.getAge());
            userDetailRepository.save(userDetail);
        }
        User user = userRepository.save(userMapper.toEntity(userDTO));
        return userMapper.toDto(user);
    }

}
