package com.eds.monolitica.users.services.implement;

import com.eds.monolitica.users.domain.entities.Rol;
import com.eds.monolitica.users.dto.RolDTO;
import com.eds.monolitica.users.dto.UserDTO;
import com.eds.monolitica.users.dto.UserForRolDTO;
import com.eds.monolitica.users.exceptions.NotFoundException;
import com.eds.monolitica.users.repositories.data.RolRepository;
import com.eds.monolitica.users.repositories.jdbc.RolJdbcRepository;
import com.eds.monolitica.users.repositories.jdbc.implement.RolJdbcRepositoryImpl;
import com.eds.monolitica.users.services.IRolService;
import com.eds.monolitica.users.services.mapper.RolMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class RolServiceImpl implements IRolService {
    private final RolRepository rolRepository;
    private final RolMapper rolMapper;
    private RolJdbcRepository rolJdbcRepository;
    public RolServiceImpl(RolRepository rolRepository, RolMapper rolMapper) {
        this.rolRepository = rolRepository;
        this.rolMapper = rolMapper;
    }
    @Autowired
    public void setRolJdbcRepository(RolJdbcRepository rolJdbcRepository) {
        this.rolJdbcRepository = rolJdbcRepository;
    }
    @Override
    @Transactional(readOnly = true)
    public List<RolDTO> listRolDetailed() {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<RolDTO> listUsers() {
        return rolRepository.findAll()
                .stream()
                .map(rolMapper::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RolDTO> getRolById(Integer rolId) {
        Optional<Rol> rol = rolRepository.findById(rolId);
        if(rol==null){
            throw new NotFoundException("Usuario",rolId.longValue());
        }
        return rol.map(rolMapper::toDto);
    }

    @Override
    public RolDTO save(RolDTO dto) {
        Rol rol = rolRepository.save(rolMapper.toEntity(dto));
        return rolMapper.toDto(rol);
    }

    @Override
    public void delete(Integer rolId) {
        rolRepository.deleteById(rolId);
    }

    @Override
    public List<UserForRolDTO> listUsersByRolId(Integer rolId) {
        return rolJdbcRepository.listUsersByRolId(rolId);
    }
}
