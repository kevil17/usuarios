package com.eds.monolitica.users.services.implement;

import com.eds.monolitica.users.domain.entities.User;
import com.eds.monolitica.users.dto.UserDTO;
import com.eds.monolitica.users.exceptions.NotFoundException;
import com.eds.monolitica.users.repositories.data.UserRepository;
import com.eds.monolitica.users.services.IUserService;
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
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
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

}
