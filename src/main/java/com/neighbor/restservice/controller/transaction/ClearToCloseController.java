package com.neighbor.restservice.controller.transaction;

import com.neighbor.model.transaction.ClearToClose;
import com.neighbor.service.transaction.ClearToCloseService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/clear_to_close")
public class ClearToCloseController {
    private final ClearToCloseService clearToCloseService;

    @Autowired
    public ClearToCloseController(ClearToCloseService clearToCloseService) {
        this.clearToCloseService = clearToCloseService;
    }
//
//    @ApiOperation(value = "Get user from access token.")
//    @RequestMapping(method = RequestMethod.GET)
//    public ResponseEntity<User> get() {
//        return new ResponseEntity<>(userService.get(), HttpStatus.OK);
//    }

    @ApiOperation(value = "Create a new ClearToClose")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ClearToClose> createNewClearToClose(@RequestBody ClearToClose clearToClose) {
        return new ResponseEntity<>(clearToCloseService.createNewClearToClose(clearToClose), HttpStatus.CREATED);
    }
}
