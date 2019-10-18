package com.tw.apistackbase.controller;


import com.tw.apistackbase.core.Company;
import com.tw.apistackbase.service.CompanyService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {


    @Autowired
    private
    CompanyService companyService;

    @GetMapping(value = "/all", produces = {"application/json"})
    public Iterable<Company> list(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5") Integer pageSize) throws NotFoundException {
        return companyService.list(page, pageSize);
    }

    @GetMapping(produces = {"application/json"})
    public List<Company> getCompany(@RequestParam(required = false) String name) throws NotFoundException{
        return companyService.getCompany(name);
    }

    @PostMapping(produces = {"application/json"})
    public Company add(@RequestBody Company company) {
        return companyService.add(company);
    }

    @DeleteMapping(value = "/{id}", produces = {"application/json"})
    public String deleteCompanyById(@PathVariable Long id) throws NotFoundException {
        return companyService.deleteCompanyById(id);
    }

    @PutMapping(value = "/{id}", produces = {"application/json"})
    public Company modifyCompany(@PathVariable Long id, @RequestBody Company company) throws NotFoundException {
        return companyService.modifyCompany(id, company);

    }
}
