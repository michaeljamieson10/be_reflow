package com.neighbor.restservice.controller;

import com.neighbor.model.Agent;
import com.neighbor.model.User;
import com.neighbor.model.UserRegistration;
import com.neighbor.service.AgentService;
import com.neighbor.service.AgentServiceImpl;
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
@RequestMapping("/v1/agent")
public class AgentController {

    private final AgentService agentService;

    @Autowired
    public AgentController(AgentService agentService) {
        this.agentService = agentService;
    }

    @ApiOperation(value = "Get agent from user from access token.")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Agent> get() {
        return new ResponseEntity<>(agentService.get(), HttpStatus.OK);
    }

    @ApiOperation(value = "Create a new agent.")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Agent> newUser(@RequestBody Agent agent) {
        return new ResponseEntity<>(agentService.createNewAgent(agent), HttpStatus.CREATED);
    }

}
