package com.tw.apistackbase.service;

import com.tw.apistackbase.core.Company;
import com.tw.apistackbase.repository.CompanyRepository;
import com.tw.apistackbase.response.Responses;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    public static final String NO_COMPANY_WAS_DELETED = "No Company was deleted!";
    public static final String COMPANY_WAS_DELETED = "Company was deleted!";
    @Autowired
    private CompanyRepository companyRepository;

    public Iterable<Company> list(Integer page, Integer pageSize) {
        return companyRepository.findAll(PageRequest.of(page-1,pageSize));
    }

    public List<Company> getCompany(String name) {
        if (name == null) {
            return companyRepository.findAll();
        }
        return companyRepository.findByFirstnameLike(name);
    }

    public String deleteCompanyById(Long id) throws NotFoundException{
        if ( companyRepository.findById(id).isPresent()) {
            companyRepository.deleteById(id);
            return COMPANY_WAS_DELETED;
        }
        throw new NotFoundException(NO_COMPANY_WAS_DELETED);
    }

    public Company add(Company company) {
            companyRepository.save(company);
            return company;
    }

    public Company modifyCompany(Long id, Company company) throws NotFoundException{
        if ( companyRepository.findById(id).isPresent()) {
            Company company1 = companyRepository.getOne(id);
            company1.setName(company.getName());
            companyRepository.save(company1);
            return company1;
        } else {
            throw new NotFoundException("NO COMPANY WAS MODIFIED");
        }
    }
}
