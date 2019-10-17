package com.tw.apistackbase.controller;

import com.tw.apistackbase.core.Company;
import com.tw.apistackbase.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    @Autowired
    CompanyService companyService;
    @GetMapping(value = "/all" ,produces = {"application/json"})
    public Iterable<Company> list(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5") Integer pageSize) {
        return companyService.findAll(page,pageSize);
    }

    @PostMapping(produces = {"application/json"})
    public ResponseEntity<Company> add(@RequestBody Company company) {
        return new ResponseEntity<>(companyService.save(company), HttpStatus.CREATED);
    }

    @GetMapping(produces = {"application/json"})
    public List<Company> getCompanyLikeName(@RequestParam(required = false) String name) {
        if(name == null)
        {
            return  companyService.findAll();
        }
        return companyService.findByFirstnameLike(name);
    }

    @DeleteMapping(value = "/{id}", produces = {"application/json"})
    public ResponseEntity deleteCompanyById(@PathVariable Long id){
        if ( companyService.findById(id).isPresent()) {
            companyService.deleteById(id);
            return new ResponseEntity<>(companyService.findAll(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

     @DeleteMapping(produces = {"application/json"})
     public ResponseEntity  deleteCompanyById(){
       companyService.deleteAll();
       return new ResponseEntity<>(companyService.findAll(), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}" , produces = {"application/json"})
    public ResponseEntity modifyCompany(@PathVariable Long id, @RequestBody Company company){
        if ( companyService.findById(id).isPresent()) {
            Company company1 = companyService.getOne(id);
            company1.setName(company.getName());
            company1.setCompanyProfile(company.getCompanyProfile());
            company1.setEmployees(company.getEmployees());
            companyService.save(company1);
            return new ResponseEntity<>(companyService.findAll(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
