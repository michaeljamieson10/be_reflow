package com.neighbor.restservice.controller.transaction;

import com.neighbor.model.Agent;
import com.neighbor.model.transaction.Transaction;
import com.neighbor.service.transaction.TransactionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController( TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @ApiOperation(value = "Get transaction from id.")
    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<Transaction> get(@PathVariable("id") int transactionId) {
        return new ResponseEntity<>(transactionService.getTransactionById(transactionId), HttpStatus.OK);
    }

    @ApiOperation(value = "Create a new Transaction")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Transaction> createNewTransactionInvitation(@RequestBody Transaction transaction) {
        return new ResponseEntity<>(transactionService.createNewTransaction(transaction), HttpStatus.CREATED);
    }
    @ApiOperation("Get list transactions by either by agent or client")
    @RequestMapping(method = RequestMethod.GET, value="/list")
    public ResponseEntity<List<Transaction>> getTransactionsListByAgent(@RequestParam(name="agent_id") int agentId){
        return new ResponseEntity<>(transactionService.getTransactionsListByAgent(agentId), HttpStatus.OK);
    }

}
