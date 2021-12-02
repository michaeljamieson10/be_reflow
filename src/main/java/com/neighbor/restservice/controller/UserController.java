package com.neighbor.restservice.controller;

import com.neighbor.model.User;
import com.neighbor.model.UserRegistration;
import com.neighbor.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "Get user from access token.")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<User> get() {
        return new ResponseEntity<>(userService.get(), HttpStatus.OK);
    }

    @ApiOperation(value = "Create a new user.")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<User> newUser(@RequestBody UserRegistration userRegistration) {
        return new ResponseEntity<>(userService.createNewUser(userRegistration), HttpStatus.CREATED);
    }

}
