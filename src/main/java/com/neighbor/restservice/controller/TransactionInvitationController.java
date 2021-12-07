package com.neighbor.restservice.controller;

import com.neighbor.model.Agent;
import com.neighbor.model.TransactionInvitation;
import com.neighbor.service.AgentService;
import com.neighbor.service.TransactionInvitationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/transaction_invitation")
public class TransactionInvitationController {

    private final TransactionInvitationService transactionInvitationService;

    @Autowired
    public TransactionInvitationController(TransactionInvitationService transactionInvitationService) {
        this.transactionInvitationService = transactionInvitationService;
    }
//
//    @ApiOperation(value = "Get user from access token.")
//    @RequestMapping(method = RequestMethod.GET)
//    public ResponseEntity<User> get() {
//        return new ResponseEntity<>(userService.get(), HttpStatus.OK);
//    }

    @ApiOperation(value = "Create a new Transaction invitation.")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<TransactionInvitation> createNewTransactionInvitation(@RequestBody TransactionInvitation transactionInvitation) {
        return new ResponseEntity<>(transactionInvitationService.createNewTransactionInvitation(transactionInvitation), HttpStatus.CREATED);
    }

}
