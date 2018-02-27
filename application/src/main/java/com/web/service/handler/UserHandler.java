package com.web.service.handler;

import com.web.controller.wrapper.UserView;
import com.web.model.User;
import com.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.servlet.ServletRequest;

/**
 * Created by alex on 26.02.18.
 */
@Service
public class UserHandler {

    @Autowired
    UserService userService;

    public Page<UserView> getAllUsers(ServletRequest request) {
        Integer page = Integer.valueOf(request.getParameter("page"));
        Integer pageSize = Integer.valueOf(request.getParameter("size"));
        Sort.Direction dir = Sort.Direction.fromString(request.getParameter("dir"));

        Page<User> users = userService.getAllUser(page, pageSize, dir);
        return users.map(this::convertUser);
    }

    private UserView convertUser(User user) {
        UserView userView = new UserView();
        userView.setName(user.getName());
        userView.setEmail(user.getEmail());
        userView.setPassword(user.getPassword());
        return userView;
    }
}
