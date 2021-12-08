package com.neighbor.restservice.controller.transaction;

import com.neighbor.model.transaction.Closing;
import com.neighbor.service.transaction.ClosingService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/closing")
public class ClosingController {
    private final ClosingService closingService;

    @Autowired
    public ClosingController(ClosingService closingService) {
        this.closingService = closingService;
    }
//
//    @ApiOperation(value = "Get user from access token.")
//    @RequestMapping(method = RequestMethod.GET)
//    public ResponseEntity<User> get() {
//        return new ResponseEntity<>(userService.get(), HttpStatus.OK);
//    }

    @ApiOperation(value = "Create a new Closing")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Closing> createNewClosing(@RequestBody Closing closing) {
        return new ResponseEntity<>(closingService.createNewClosing(closing), HttpStatus.CREATED);
    }
}
