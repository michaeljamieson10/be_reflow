package com.neighbor.restservice.controller.transaction;

import com.neighbor.model.transaction.HomeInspection;
import com.neighbor.service.transaction.HomeInspectionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/home_inspection")
public class HomeInspectionController {
    private final HomeInspectionService homeInspectionService;

    @Autowired
    public HomeInspectionController(HomeInspectionService homeInspectionService) {
        this.homeInspectionService = homeInspectionService;
    }
//
//    @ApiOperation(value = "Get user from access token.")
//    @RequestMapping(method = RequestMethod.GET)
//    public ResponseEntity<User> get() {
//        return new ResponseEntity<>(userService.get(), HttpStatus.OK);
//    }

    @ApiOperation(value = "Create a new homeInspection")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<HomeInspection> createNewHomeInspection(@RequestBody HomeInspection homeInspection) {
        return new ResponseEntity<>(homeInspectionService.createHomeInspection(homeInspection), HttpStatus.CREATED);
    }

}
