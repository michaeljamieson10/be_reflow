package com.neighbor.restservice.controller.transaction;

import com.neighbor.model.transaction.AcceptedOffer;
import com.neighbor.model.transaction.HomeCriteria;
import com.neighbor.service.Transaction.AcceptedOfferService;
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
@RequestMapping("/v1/accepted_offer")
public class AcceptedOfferController {

    private final AcceptedOfferService acceptedOfferService;

    @Autowired
    public AcceptedOfferController(AcceptedOfferService acceptedOfferService) {
        this.acceptedOfferService = acceptedOfferService;
    }
//
//    @ApiOperation(value = "Get user from access token.")
//    @RequestMapping(method = RequestMethod.GET)
//    public ResponseEntity<User> get() {
//        return new ResponseEntity<>(userService.get(), HttpStatus.OK);
//    }

    @ApiOperation(value = "Create a new homeCriteria")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<AcceptedOffer> createNewTransactionInvitation(@RequestBody AcceptedOffer acceptedOffer) {
        return new ResponseEntity<>(acceptedOfferService.createAcceptedOffer(acceptedOffer), HttpStatus.CREATED);
    }

}
