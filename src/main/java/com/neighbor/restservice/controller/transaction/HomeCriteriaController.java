package com.neighbor.restservice.controller.transaction;

import com.neighbor.model.transaction.HomeCriteria;
import com.neighbor.service.transaction.HomeCriteriaService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/home_criteria")
public class HomeCriteriaController {

    private final HomeCriteriaService homeCriteriaService;

    @Autowired
    public HomeCriteriaController(HomeCriteriaService homeCriteriaService) {
        this.homeCriteriaService = homeCriteriaService;
    }
//
//    @ApiOperation(value = "Get user from access token.")
//    @RequestMapping(method = RequestMethod.GET)
//    public ResponseEntity<User> get() {
//        return new ResponseEntity<>(userService.get(), HttpStatus.OK);
//    }

    @ApiOperation(value = "Create a new homeCriteria")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<HomeCriteria> createNewTransactionInvitation(@RequestBody HomeCriteria homeCriteria) {
        return new ResponseEntity<>(homeCriteriaService.createNewHomeCriteria(homeCriteria), HttpStatus.CREATED);
    }

}
