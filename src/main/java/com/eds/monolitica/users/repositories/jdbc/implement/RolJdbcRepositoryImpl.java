package com.eds.monolitica.users.repositories.jdbc.implement;

import com.eds.monolitica.users.dto.UserDTO;
import com.eds.monolitica.users.dto.UserForRolDTO;
import com.eds.monolitica.users.repositories.jdbc.RolJdbcRepository;
import com.eds.monolitica.users.repositories.jdbc.implement.row.mapper.RolUsersRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class RolJdbcRepositoryImpl implements RolJdbcRepository {
    private final JdbcTemplate jdbcTemplate;

    public RolJdbcRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<UserForRolDTO> listUsersByRolId(Integer rolId) {
        var sql = """
                SELECT u.id,r.name,ur.active, u.user_name, u.password, u.email
                                FROM users u
                                INNER JOIN user_rol ur on u.id = ur.user_id
                				INNER JOIN rol r on ur.rol_id = r.id
                                WHERE ur.rol_id=?;
                """;
        return jdbcTemplate.query(sql, new RolUsersRowMapper(), rolId);
    }
}
