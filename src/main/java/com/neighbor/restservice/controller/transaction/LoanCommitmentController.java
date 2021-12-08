package com.neighbor.restservice.controller.transaction;

import com.neighbor.model.transaction.LoanCommitment;
import com.neighbor.service.transaction.LoanCommitmentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/loan_commitment")
public class LoanCommitmentController {

    private final LoanCommitmentService loanCommitmentService;

    @Autowired
    public LoanCommitmentController(LoanCommitmentService loanCommitmentService) {
        this.loanCommitmentService = loanCommitmentService;
    }
//
//    @ApiOperation(value = "Get user from access token.")
//    @RequestMapping(method = RequestMethod.GET)
//    public ResponseEntity<User> get() {
//        return new ResponseEntity<>(userService.get(), HttpStatus.OK);
//    }

    @ApiOperation(value = "Create a new LoanCommitment")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<LoanCommitment> createLoanCommitment(@RequestBody LoanCommitment loanCommitment) {
        return new ResponseEntity<>(loanCommitmentService.createNewLoanCommitment(loanCommitment), HttpStatus.CREATED);
    }

}
