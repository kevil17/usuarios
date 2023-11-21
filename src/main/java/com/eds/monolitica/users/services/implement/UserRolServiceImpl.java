package com.eds.monolitica.users.services.implement;

import com.eds.monolitica.users.domain.entities.Rol;
import com.eds.monolitica.users.domain.entities.User;
import com.eds.monolitica.users.domain.entities.UserDetail;
import com.eds.monolitica.users.domain.entities.UserRol;
import com.eds.monolitica.users.dto.RolDTO;
import com.eds.monolitica.users.dto.UserDTO;
import com.eds.monolitica.users.dto.UserRolDTO;
import com.eds.monolitica.users.exceptions.NotFoundException;
import com.eds.monolitica.users.repositories.data.RolRepository;
import com.eds.monolitica.users.repositories.data.UserRepository;
import com.eds.monolitica.users.repositories.data.UserRolRepository;
import com.eds.monolitica.users.services.IUserRolService;
import com.eds.monolitica.users.services.mapper.UserRolMapper;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserRolServiceImpl implements IUserRolService {
    private final UserRolRepository userRolRepository;
    private final UserRolMapper userRolMapper;
    private final UserRepository userRepository;
    private final RolRepository rolRepository;

    public UserRolServiceImpl(UserRolRepository userRolRepository, UserRolMapper userRolMapper, UserRepository userRepository, RolRepository rolRepository) {
        this.userRolRepository = userRolRepository;
        this.userRolMapper = userRolMapper;
        this.userRepository = userRepository;
        this.rolRepository = rolRepository;
    }

    @Override
    public List<UserRolDTO> listDetailUserRol() {
        return userRolRepository.findAll()
                .stream()
                .map(elem -> userRolMapper.toDto(elem,false)).collect(Collectors.toList());
    }

    @Override
    public Optional<UserRolDTO> getUserRolById(Integer id) {
        Optional<UserRol> userRol = userRolRepository.findById(id);
        if(userRol==null){
            throw new NotFoundException("Usuario",id.longValue());
        }
        return userRol.map(elem ->userRolMapper.toDto(elem,true));
    }

    @Override
    public UserRolDTO save(UserRolDTO dto) {
        UserDTO userDTO = dto.getUserDTO();
        RolDTO rolDTO = dto.getRolDTO();
        if (userDTO == null || rolDTO == null){
            throw new NotFoundException("NECESTIAS UN USUARIO Y UN ROL",null);
        }
        User user = userRepository.findByUid(userDTO.getId());
        Rol rol = rolRepository.findByUid(rolDTO.getId());
        if(user==null){
            throw new NotFoundException("USUARIO NO ENCONTRADO EN DB",null);
        }
        if(rol==null){
            throw new NotFoundException("ROL NO ENCONTRADO EN DB",null);
        }
        UserRol userRol = userRolRepository.save(userRolMapper.toEntity(dto,user,rol));
        return userRolMapper.toDto(userRol,true);
    }

    @Override
    public UserRolDTO update(UserRolDTO userDTO) {
        UserRol example= new UserRol(userDTO.getId());
        Optional<UserRol> optional = userRolRepository.findById(userDTO.getId());
        if(optional.isEmpty()){
            throw new NotFoundException("No existe este UserRol", userDTO.getId().longValue());
        }
        UserRol userRol = optional.get();
        userRol.setActive(userDTO.isActive());
        userRol.setCreated_at(userDTO.getCreatedAt());
        userRolRepository.save(userRol);
        return userRolMapper.toDto(userRol,true);
    }

    @Override
    public void delete(Integer id) {
        userRolRepository.deleteById(id);
    }
}
