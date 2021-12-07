package com.neighbor.restservice.controller.transaction;

import com.neighbor.model.transaction.Appraisal;
import com.neighbor.model.transaction.HomeCriteria;
import com.neighbor.service.Transaction.AppraisalService;
import com.neighbor.service.Transaction.HomeCriteriaService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/appraisal")
public class AppraisalController {

    private final AppraisalService appraisalService;

    @Autowired
    public AppraisalController(AppraisalService appraisalService) {
        this.appraisalService = appraisalService;
    }
//
//    @ApiOperation(value = "Get user from access token.")
//    @RequestMapping(method = RequestMethod.GET)
//    public ResponseEntity<User> get() {
//        return new ResponseEntity<>(userService.get(), HttpStatus.OK);
//    }

    @ApiOperation(value = "Create a new Appraisal")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Appraisal> createNewAppraisal(@RequestBody Appraisal appraisal) {
        return new ResponseEntity<>(appraisalService.createNewAppraisal(appraisal), HttpStatus.CREATED);
    }

}
