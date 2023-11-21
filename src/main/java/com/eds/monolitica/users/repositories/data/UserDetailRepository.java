package com.eds.monolitica.users.repositories.data;

import com.eds.monolitica.users.domain.entities.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailRepository extends JpaRepository<UserDetail,Long> {

}
