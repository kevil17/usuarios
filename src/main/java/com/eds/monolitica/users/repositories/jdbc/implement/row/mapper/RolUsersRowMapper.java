package com.eds.monolitica.users.repositories.jdbc.implement.row.mapper;

import com.eds.monolitica.users.dto.UserForRolDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RolUsersRowMapper implements RowMapper<UserForRolDTO> {
    @Override
    public UserForRolDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new UserForRolDTO(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getBoolean("active"),
                rs.getString("user_name"),
                rs.getString("password"),
                rs.getString("email")
        );
    }
}
