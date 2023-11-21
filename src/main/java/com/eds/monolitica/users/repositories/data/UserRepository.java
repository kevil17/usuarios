package com.eds.monolitica.users.repositories.data;

import com.eds.monolitica.users.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,Long> {
    @Query("SELECT u FROM User u Where u.id=?1")
    User findByUid(Long id);
}
