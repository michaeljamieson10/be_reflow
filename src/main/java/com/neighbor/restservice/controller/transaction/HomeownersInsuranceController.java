package com.neighbor.restservice.controller.transaction;

import com.neighbor.model.transaction.HomeownersInsuance;
import com.neighbor.service.transaction.HomeownersInsuranceService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/homeowners_insurance")
public class HomeownersInsuranceController {
    private final HomeownersInsuranceService homeownersInsuranceService;

    @Autowired
    public HomeownersInsuranceController(HomeownersInsuranceService homeownersInsuranceService) {
        this.homeownersInsuranceService = homeownersInsuranceService;
    }
//
//    @ApiOperation(value = "Get user from access token.")
//    @RequestMapping(method = RequestMethod.GET)
//    public ResponseEntity<User> get() {
//        return new ResponseEntity<>(userService.get(), HttpStatus.OK);
//    }

    @ApiOperation(value = "Create a new HomeownersInsuance")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<HomeownersInsuance> createNewHomeownersInsurance(@RequestBody HomeownersInsuance homeInspection) {
        return new ResponseEntity<>(homeownersInsuranceService.createHomeInspection(homeInspection), HttpStatus.CREATED);
    }
}
