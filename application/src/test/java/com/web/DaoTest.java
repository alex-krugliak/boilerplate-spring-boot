package com.web;

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
