package com.web.controller;


import com.web.controller.wrapper.UserView;
import com.web.service.handler.UserHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletRequest;
import java.util.List;


/**
 * Created by alex on 13.02.18.
 */
@Controller
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private UserHandler userHandler;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @ResponseBody
    public List<UserView> getUsers(ServletRequest request) {
        return userHandler.getAllUsers(request);
    }
}
