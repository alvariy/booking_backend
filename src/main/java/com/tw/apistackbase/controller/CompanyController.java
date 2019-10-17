package com.tw.apistackbase.controller;

import com.tw.apistackbase.core.Company;
import com.tw.apistackbase.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    @Autowired
    CompanyRepository companyRepository;
    @GetMapping(value = "/all" ,produces = {"application/json"})
    public Iterable<Company> list(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5") Integer pageSize) {
        return companyRepository.findAll(PageRequest.of(page - 1 ,pageSize, Sort.by("name").descending()));
    }

    @GetMapping(produces = {"application/json"})
    public List<Company> getCompanyLikeName(@RequestParam(required = false) String name) {
        if(name == null)
        {
            return  companyRepository.findAll();
        }
        return companyRepository.findByFirstnameLike(name);
    }

    @DeleteMapping(value = "/{id}", produces = {"application/json"})
    public ResponseEntity deleteCompanyById(@PathVariable Long id){
        if ( companyRepository.findById(id).isPresent()) {
            companyRepository.deleteById(id);
            return new ResponseEntity<>(companyRepository.findAll(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

     @DeleteMapping(produces = {"application/json"})
     public ResponseEntity  deleteCompanyById(){
       companyRepository.deleteAll();
       return new ResponseEntity<>(companyRepository.findAll(), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}" , produces = {"application/json"})
    public ResponseEntity modifyCompany(@PathVariable Long id, @RequestBody Company company){
        if ( companyRepository.findById(id).isPresent()) {
            Company company1 = companyRepository.getOne(id);
            company1.setName(company.getName());
            company1.setCompanyProfile(company.getCompanyProfile());
            company1.setEmployees(company.getEmployees());
            companyRepository.save(company1);
            return new ResponseEntity<>(companyRepository.findAll(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(produces = {"application/json"})
    public ResponseEntity<Company> add(@RequestBody Company company) {
        return new ResponseEntity<>(companyRepository.save(company), HttpStatus.CREATED);
    }
}
