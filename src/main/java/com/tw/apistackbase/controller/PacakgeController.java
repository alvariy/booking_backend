package com.tw.apistackbase.controller;

import com.tw.apistackbase.core.Packages;
import com.tw.apistackbase.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/packages")
public class PacakgeController {

    @Autowired
    PackageService packageService;

    @PostMapping(produces = {"application/json"})
    public Packages add(@RequestBody Packages packages) {
        return packageService.addPackage(packages);
    }
}
