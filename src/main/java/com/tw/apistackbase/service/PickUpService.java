package com.tw.apistackbase.service;

import com.tw.apistackbase.repository.PickUpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class PickUpService {

    @Autowired
    PickUpRepository pickUpRepository;


}
