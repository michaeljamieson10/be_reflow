package com.neighbor.restservice.controller.transaction;

import com.neighbor.model.transaction.ContractsSigned;
import com.neighbor.service.transaction.ContractsSignedService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/contracts_signed")
public class ContractsSignedController {
    private final ContractsSignedService contractsSignedService;

    @Autowired
    public ContractsSignedController(ContractsSignedService contractsSignedService) {
        this.contractsSignedService = contractsSignedService;
    }

    @ApiOperation(value = "Create a new ContractsSigned")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ContractsSigned> createNewContractsSigned(@RequestBody ContractsSigned contractsSigned) {
        return new ResponseEntity<>(contractsSignedService.createNewContractsSigned(contractsSigned), HttpStatus.CREATED);
    }

}
