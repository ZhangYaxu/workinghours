package org.springboot.dao;

import org.springboot.jdo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by goldendba@gmail.com on 2017/2/4.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByLoginName(String loginName);
}
