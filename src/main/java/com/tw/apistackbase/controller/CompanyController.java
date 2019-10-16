package com.tw.apistackbase.controller;

import com.tw.apistackbase.core.Company;
import com.tw.apistackbase.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    @Autowired
    CompanyRepository companyRepository;

    List<Company> companyList = new ArrayList<>();

    @GetMapping(produces = {"application/json"})
    public Iterable<Company> list() {
        return companyRepository.findAll();
    }

    @GetMapping(value = "/{name}" ,produces = {"application/json"})
    public Company getCompanyByName(@PathVariable String name) {
        return companyRepository.findOneByName(name);
    }

    @DeleteMapping(value = "/{id}", produces = {"application/json"})
    public void deleteCompanyById(@PathVariable Long id){
        companyRepository.deleteById(id);
    }

     @DeleteMapping(produces = {"application/json"})
     public void deleteCompanyById(){
       companyRepository.deleteAll();
    }

    @PutMapping(value = "/{id}" , produces = {"application/json"})
    public void modifyCompany(@PathVariable Long id, @RequestBody Company company){
        Company company1 = companyRepository.getOne(id);
        company1.setName(company.getName());
        company1.setCompanyProfile(company.getCompanyProfile());
        company1.setEmployees(company.getEmployees());
        companyRepository.save(company1);

    }

    @PostMapping(produces = {"application/json"})
    public Company add(@RequestBody Company company) {
        return companyRepository.save(company);
    }
}
