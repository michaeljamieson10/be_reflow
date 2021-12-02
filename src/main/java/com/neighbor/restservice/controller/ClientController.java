package com.neighbor.restservice.controller;

import com.neighbor.model.Agent;
import com.neighbor.model.Client;
import com.neighbor.service.AgentService;
import com.neighbor.service.ClientService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/client")
public class ClientController {


    private final ClientService clientService;
    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }
//
//    @ApiOperation(value = "Get user from access token.")
//    @RequestMapping(method = RequestMethod.GET)
//    public ResponseEntity<User> get() {
//        return new ResponseEntity<>(userService.get(), HttpStatus.OK);
//    }

    @ApiOperation(value = "Create a new client.")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Client> newUser(@RequestBody Client client) {
        return new ResponseEntity<>(clientService.createNewClient(client), HttpStatus.CREATED);
    }

}
