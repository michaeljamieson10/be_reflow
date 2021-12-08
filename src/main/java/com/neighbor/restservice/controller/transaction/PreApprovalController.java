package com.neighbor.restservice.controller.transaction;

import com.neighbor.model.transaction.PreApproval;
import com.neighbor.service.transaction.PreApprovalService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/pre_approval")
public class PreApprovalController {

    private final PreApprovalService preApprovalService;

    @Autowired
    public PreApprovalController(PreApprovalService preApprovalService) {
        this.preApprovalService = preApprovalService;
    }
//
//    @ApiOperation(value = "Get user from access token.")
//    @RequestMapping(method = RequestMethod.GET)
//    public ResponseEntity<User> get() {
//        return new ResponseEntity<>(userService.get(), HttpStatus.OK);
//    }

    @ApiOperation(value = "Create a new Pre Approval invitation.")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<PreApproval> createNewPreApproval(@RequestBody PreApproval preApproval) {
        return new ResponseEntity<>(preApprovalService.createNewPreApproval(preApproval), HttpStatus.CREATED);
    }

}
