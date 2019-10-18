package com.tw.apistackbase.controller;


import com.tw.apistackbase.core.Company;
import com.tw.apistackbase.service.CompanyService;
import javassist.NotFoundException;
import javassist.tools.web.BadHttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private static final String CANNOT_GET_NON_EXISTING_COMPANY = "Company Not Found!";
    public static final String CANNOT_ADD_COMPANY = "Company was not Added";
    public static final String CANNOT_DELETE_COMPANY = "Company cannot be deleted";
    public static final String CANNOT_UPDATE_COMPANY = "Cannot Update Company";

    @Autowired
    private
    CompanyService companyService;

    @GetMapping(value = "/all" ,produces = {"application/json"})
    public ResponseEntity<Iterable<Company>> list(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5") Integer pageSize) throws NotFoundException {
        if(ResponseEntity.ok(companyService.list(page,pageSize)).getStatusCode() == HttpStatus.OK) {
            return new ResponseEntity<>(companyService.list(page,pageSize), HttpStatus.OK);
        }
        throw new NotFoundException(CANNOT_GET_NON_EXISTING_COMPANY);

    }

    @GetMapping(produces = {"application/json"})
    public List<Company> getCompany(@RequestParam(required = false) String name){
        return companyService.getCompany(name);
    }

    @PostMapping(produces = {"application/json"})
    public Company add(@RequestBody Company company)  {

        return companyService.add(company);
    }

    @DeleteMapping(value = "/{id}", produces = {"application/json"})
    public String deleteCompanyById(@PathVariable Long id) throws NotFoundException{
      return companyService.deleteCompanyById(id);
    }

    @PutMapping(value = "/{id}" , produces = {"application/json"})
    public Company modifyCompany(@PathVariable Long id, @RequestBody Company company) throws NotFoundException{
      return companyService.modifyCompany(id, company);

    }
}
