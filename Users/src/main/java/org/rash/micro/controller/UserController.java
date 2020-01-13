package org.rash.micro.controller;

import org.rash.micro.repository.UserRepository;
import org.rash.micro.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    protected UserRepository userRepository;

    @GetMapping("/")
    public @ResponseBody
    List<User> fetchUsers() {
        logger.info("fetch all users");
        return userRepository.findAll();
    }

    @GetMapping(value = "/user")
    public String getUser() {
        return "Welcome user";
    }

    @GetMapping(value = "/admin")
    public String getAdmin() {
        return "Welcome Admin";
    }

}
