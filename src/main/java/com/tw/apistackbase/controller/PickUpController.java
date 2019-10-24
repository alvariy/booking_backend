package com.tw.apistackbase.controller;

import com.tw.apistackbase.core.PickUp;
import com.tw.apistackbase.service.PickUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pickups")
public class PickUpController {

    @Autowired
    private PickUpService pickUpService;

    @PostMapping(produces = {"application/json"})
    public PickUp add(@RequestBody PickUp pickUp) {
        return pickUpService.addBookingPickUp(pickUp);
    }

}
