package com.eds.monolitica.users.services.implement;

import com.eds.monolitica.users.domain.entities.User;
import com.eds.monolitica.users.domain.entities.UserDetail;
import com.eds.monolitica.users.dto.UserDTO;
import com.eds.monolitica.users.dto.UserDetailDTO;
import com.eds.monolitica.users.exceptions.NotFoundException;
import com.eds.monolitica.users.repositories.data.UserDetailRepository;
import com.eds.monolitica.users.repositories.data.UserRepository;
import com.eds.monolitica.users.services.IUserDetailService;
import com.eds.monolitica.users.services.mapper.UserDetailMapper;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserDetailImpl implements IUserDetailService {

    private final UserDetailRepository userDetailRepository;
    private final UserDetailMapper userDetailMapper;
    private final UserRepository userRepository;

    public UserDetailImpl(UserDetailRepository userDetailRepository, UserDetailMapper userDetailMapper, UserRepository userRepository) {
        this.userDetailRepository = userDetailRepository;
        this.userDetailMapper = userDetailMapper;
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDetailDTO> listDetailUsers() {
        return userDetailRepository.findAll()
                .stream()
                .map(elem -> userDetailMapper.toDto(elem,true)).collect(Collectors.toList());
    }

    @Override
    public UserDetailDTO save(UserDetailDTO dto) {
        UserDTO userDTO = dto.getUserDTO();
        if (userDTO == null){
            throw new NotFoundException("USUARIO",null);
        }
        User user = userRepository.findByUid(userDTO.getId());
        if(user==null){
            throw new NotFoundException("USUARIO",null);
        }
        UserDetail userDetail = userDetailRepository.save(userDetailMapper.toEntity(dto,user));
        return userDetailMapper.toDto(userDetail,true);
    }
    @Override
    public UserDetailDTO update(UserDetailDTO dto) {
        UserDetail example= new UserDetail(dto.getId());
        Optional<UserDetail> optionalDetail = userDetailRepository.findOne(Example.of(example));
        if(optionalDetail.isEmpty()){
            throw new NotFoundException("Detalles del usuario", dto.getId());
        }
        UserDetail userDetail = optionalDetail.get();
        userDetail.setFirstName(dto.getFirstName());
        userDetail.setLastName(dto.getLastName());
        userDetail.setBirthDay(dto.getBirthDay());
        userDetail.setAge(dto.getAge());
        userDetailRepository.save(userDetail);
        return userDetailMapper.toDto(userDetail,true);
    }
    @Override
    public Optional<UserDetailDTO> getUserById(Long id) {
        Optional<UserDetail> userDetail = userDetailRepository.findById(id);
        if(userDetail==null){
            throw new NotFoundException("Usuario",id);
        }
        return userDetail.map(elem ->userDetailMapper.toDto(elem,true));
    }

    @Override
    public void delete(Long id) {
        userDetailRepository.deleteById(id);
    }
}
