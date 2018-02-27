package com.web.service;

import com.web.model.Role;
import com.web.model.RoleEnum;
import com.web.model.User;
import com.web.persistent.RoleRepository;
import com.web.persistent.UserRepository;
import com.web.service.mapper.UserMapper;
import com.web.service.wrapper.UserWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Created by alex on 14.02.18.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public List<User> getUsersByName(String name) {
        List<User> users = userRepository.findByName(name);
        return users;
    }

    @Transactional
    public Page<User> getAllUser(int page, int pageSize, Sort.Direction direction) {
        return userRepository.findAll(new PageRequest(page, pageSize, direction, "name"));
    }

    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public User getUserByNameJdbcTemplate(String name) {
        User testUser = userRepository.save(fillTestUser(name));

        Role roleAdmin = new Role();
        roleAdmin.setName(RoleEnum.ADMIN.name());
        roleAdmin.setUser(testUser);
        roleRepository.save(roleAdmin);

        //Test jdbcTemplate and JPA inside same transaction
        List<UserWrapper> userWrappers = jdbcTemplate.query("select u.user_id, u.name, u.email  from _user as u  left join role as r on u.user_id = r.user_id " +
                        " where u.name = ?",
                new UserMapper(), name);

        return userRepository.findByEmail(userWrappers.get(0).getEmail());
    }

    private User fillTestUser(String userName) {
        User user = new User();
        user.setName(userName);
        user.setEmail(UUID.randomUUID().toString() + "_test@test.com");
        user.setPassword("2");

        return user;
    }
}
