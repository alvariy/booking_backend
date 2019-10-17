package com.tw.apistackbase.controller;


import com.tw.apistackbase.core.Company;
import com.tw.apistackbase.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    @Autowired
    private
    CompanyService companyService;

    @GetMapping(value = "/all" ,produces = {"application/json"})
    public ResponseEntity<Iterable<Company>> list(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5") Integer pageSize) {
        if(ResponseEntity.ok(companyService.list(page,pageSize)).getStatusCode() == HttpStatus.OK) {
            return new ResponseEntity<>(companyService.list(page,pageSize), HttpStatus.OK);
        }
        return new ResponseEntity<>(companyService.list(page,pageSize), HttpStatus.BAD_GATEWAY);

    }

    @PostMapping(produces = {"application/json"})
    public ResponseEntity add(@RequestBody Company company) {

        if(ResponseEntity.ok(companyService.add(company)).getStatusCode() == HttpStatus.OK)
        {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(produces = {"application/json"})
    public ResponseEntity<java.util.List<Company>> getCompanyLikeName(@RequestParam(required = false) String name) {
        if(companyService.getCompanyLikeName(name).isEmpty())
        {
            return new ResponseEntity<>(companyService.getCompanyLikeName(name), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(companyService.getCompanyLikeName(name), HttpStatus.OK);
        }
    }

    @DeleteMapping(value = "/{id}", produces = {"application/json"})
    public ResponseEntity deleteCompanyById(@PathVariable Long id){
        if (!companyService.deleteCompanyById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

     @DeleteMapping(produces = {"application/json"})
     public ResponseEntity  deleteAllCompany(){
         if (companyService.deleteAllCompany()) {
             return new ResponseEntity<>(HttpStatus.OK);
         }
         return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "/{id}" , produces = {"application/json"})
    public ResponseEntity modifyCompany(@PathVariable Long id, @RequestBody Company company){
        if (companyService.modifyCompany(id, company) == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
