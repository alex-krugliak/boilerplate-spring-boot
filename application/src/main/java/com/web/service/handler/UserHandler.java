package com.web.service.handler;

import com.web.controller.wrapper.UserView;
import com.web.model.User;
import com.web.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.servlet.ServletRequest;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by alex on 26.02.18.
 */
@Service
public class UserHandler {

    private static final Logger logger = LoggerFactory.getLogger(UserHandler.class);

    @Autowired
    UserService userService;

    public List<UserView> getAllUsers(ServletRequest request) {
        Integer page = Integer.valueOf(request.getParameter("page"));
        Integer pageSize = Integer.valueOf(request.getParameter("size"));
        Sort.Direction dir = Sort.Direction.fromString(request.getParameter("dir"));

        Page<User> users = userService.getAllUser(page, pageSize, dir);
        List<UserView> userList = users.map(this::convertUser).getContent();
        Stream<UserView> usersStream = userList.stream();
        logger.debug("Start Users stream handling");
        return usersStream
                .filter(u -> "Vasia3".equals(u.getName()))
                .peek(user -> logger.info("User name - " + user.getName())).collect(Collectors.toList());

    }

    private UserView convertUser(User user) {
        UserView userView = new UserView();
        userView.setName(user.getName());
        userView.setEmail(user.getEmail());
        userView.setPassword(user.getPassword());
        return userView;
    }
}
