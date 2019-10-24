package com.tw.apistackbase.service;

import com.tw.apistackbase.core.PickUp;
import com.tw.apistackbase.repository.PickUpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class PickUpService {

    @Autowired
    private PickUpRepository pickUpRepository;

    public PickUp addBookingPickUp(PickUp pickUp) {
        pickUpRepository.save(pickUp);
        return pickUp;
    }
}
