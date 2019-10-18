package com.tw.apistackbase.service;

import com.tw.apistackbase.core.Company;
import com.tw.apistackbase.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    @Autowired
    CompanyRepository companyRepository;

    public Iterable<Company> list(Integer page, Integer pageSize) {
        return companyRepository.findAll(PageRequest.of(page-1,pageSize));
    }

    public List<Company> getAll() {
        return companyRepository.findAll();
    }

    public List<Company> getCompanyLikeName(String name) {
        if(name == null) {
            return companyRepository.findAll();
        }
        return companyRepository.findByFirstnameLike(name);
    }

    public Boolean deleteCompanyById(Long id){
        if ( companyRepository.findById(id).isPresent()) {
            companyRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Company add(Company company) {
        companyRepository.save(company);
        return company;
    }

    public Boolean deleteAllCompany(){
        companyRepository.deleteAll();
        if (companyRepository.findAll().isEmpty()){
            return true;
        }
        return false;

    }

    public Company modifyCompany(Long id, Company company){
        //declare a variable, proper naming
        if ( companyRepository.findById(id).isPresent()) {
            Company company1 = companyRepository.getOne(id);
            company1.setName(company.getName());
            companyRepository.save(company1);
            return company1;
        } else {
            return null;
        }
    }


}
