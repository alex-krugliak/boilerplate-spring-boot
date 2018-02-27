package com.web.persistent;

import com.web.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by alex on 13.02.18.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select distinct u from User u where u.name = ?1")
    List<User> findByName(String name);

    @Query("select u from User u left join fetch u.roles where u.email = ?1")
    User findByEmail(String name);

}
