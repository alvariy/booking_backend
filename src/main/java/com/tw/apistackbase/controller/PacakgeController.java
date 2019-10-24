package com.tw.apistackbase.controller;

import com.tw.apistackbase.core.Packages;
import com.tw.apistackbase.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/packages")
public class PacakgeController {

    @Autowired
    PackageService packageService;

    @PostMapping(produces = {"application/json"})
    public Packages add(@RequestBody Packages packages) {
        return packageService.addPackage(packages);
    }

    @PutMapping(value = "/{wayBillNumber}", produces = {"application/json"})
    public Packages modifyPackage(@PathVariable Long wayBillNumber, @RequestBody Packages packages){
        return packageService.modifyPackage(wayBillNumber, packages);
    }

    @GetMapping(produces = {"application/json"})
    public List<Packages> getCompany(){
        return packageService.getAllPackages();
    }
}
