package com.eds.monolitica.users.repositories.data;

import com.eds.monolitica.users.domain.entities.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RolRepository extends JpaRepository<Rol,Integer> {
    @Query("SELECT u FROM Rol u Where u.id=?1")
    Rol findByUid(Integer id);
}
