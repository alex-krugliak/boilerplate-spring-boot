package com.web;

import com.web.model.Role;
import com.web.model.RoleEnum;
import com.web.model.User;
import com.web.persistent.RoleRepository;
import com.web.persistent.UserRepository;
import com.web.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by alex on 13.02.18.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class DaoTest {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void setUserRole() {
        Role role = new Role();
        role.setName(RoleEnum.ADMIN.name());

        List<User> users = userService.getUsersByName("Vasia");
        role.setUser(users.get(0));

        roleRepository.save(role);
    }

    @Test
    public void createUser() {
        User user = new User();
        user.setName("Petia");
        user.setEmail("test4@test.com");
        user.setPassword("2");
        user = userService.save(user);

        Role roleAdmin = new Role();
        roleAdmin.setName(RoleEnum.ADMIN.name());
        roleAdmin.setUser(user);
        roleRepository.save(roleAdmin);

        Role roleUser = new Role();
        roleUser.setName(RoleEnum.USER.name());
        roleUser.setUser(user);
        roleRepository.save(roleUser);

    }

    @Test
    public void getUser() {
        User user = userRepository.findByEmail("test4@test.com");
        Assert.assertEquals(user.getName(), "Petia");
    }

    @Test
    public void createAndGetUserJdbcTemplate() {
        User user = userService.getUserByNameJdbcTemplate("Vasia3");
        Assert.assertNotNull(user);
    }
}
