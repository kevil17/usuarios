package com.eds.monolitica.users.repositories.data;

import com.eds.monolitica.users.domain.entities.UserDetail;
import com.eds.monolitica.users.domain.entities.UserRol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRolRepository extends JpaRepository<UserRol,Integer> {
}
