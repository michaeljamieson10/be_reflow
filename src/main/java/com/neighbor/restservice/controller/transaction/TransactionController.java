package com.neighbor.restservice.controller.transaction;

import com.neighbor.model.transaction.Transaction;
import com.neighbor.model.transaction.TransactionInvitation;
import com.neighbor.service.transaction.TransactionInvitationService;
import com.neighbor.service.transaction.TransactionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController( TransactionService transactionService) {
        this.transactionService = transactionService;
    }
//
//    @ApiOperation(value = "Get user from access token.")
//    @RequestMapping(method = RequestMethod.GET)
//    public ResponseEntity<User> get() {
//        return new ResponseEntity<>(userService.get(), HttpStatus.OK);
//    }

    @ApiOperation(value = "Create a new Transaction")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Transaction> createNewTransactionInvitation(@RequestBody Transaction transaction) {
        return new ResponseEntity<>(transactionService.createNewTransaction(transaction), HttpStatus.CREATED);
    }

}
