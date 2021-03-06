package com.neighbor.restservice.controller.transaction;

import com.neighbor.model.transaction.FinalWalkthrough;
import com.neighbor.service.transaction.FinalWalkthroughService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/final_walkthrough")
public class FinalWalkthoughController {
    private final FinalWalkthroughService finalWalkthroughService;

    @Autowired
    public FinalWalkthoughController(FinalWalkthroughService finalWalkthroughService) {
        this.finalWalkthroughService = finalWalkthroughService;
    }
//
//    @ApiOperation(value = "Get user from access token.")
//    @RequestMapping(method = RequestMethod.GET)
//    public ResponseEntity<User> get() {
//        return new ResponseEntity<>(userService.get(), HttpStatus.OK);
//    }

    @ApiOperation(value = "Create a new FinalWalkthrough")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<FinalWalkthrough> createNewFinalWalkthrough(@RequestBody FinalWalkthrough finalWalkthrough) {
        return new ResponseEntity<>(finalWalkthroughService.createNewFinalWalkthrough(finalWalkthrough), HttpStatus.CREATED);
    }
}
