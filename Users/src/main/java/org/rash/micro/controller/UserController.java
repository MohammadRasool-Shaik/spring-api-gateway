package org.rash.micro.controller;

import lombok.extern.slf4j.Slf4j;
import org.rash.micro.entity.User;
import org.rash.micro.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
@Slf4j
public class UserController {

    @Autowired
    protected UserRepository userRepository;

    @GetMapping("/")
    public @ResponseBody
    List<User> fetchUsers() {
        log.info("fetch all users");
        return userRepository.findAll();
    }

    @GetMapping(value = "/{userName}")
    public User getByUserName(@PathVariable("userName") String userName) {
        log.info("fetch By user name " + userName);
        Optional<User> user = userRepository.findByUserName(userName);
        user.orElseThrow(() -> new UsernameNotFoundException(userName + " Not Found"));
        return user.get();
    }

}
