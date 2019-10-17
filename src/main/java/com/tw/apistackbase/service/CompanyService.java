package com.tw.apistackbase.service;

import com.tw.apistackbase.core.Company;
import com.tw.apistackbase.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    @Autowired
    CompanyRepository companyRepository;

    public List<Company> findAll()
    {
        return companyRepository.findAll();
    }

    public Page<Company> findAll(Integer page, Integer pageSize)
    {
        return companyRepository.findAll(PageRequest.of(page - 1 ,pageSize, Sort.by("name").descending()));
    }

    public Company save(Company company) {
        return companyRepository.save(company);
    }

    public List<Company> findByFirstnameLike(String name) {
        return companyRepository.findByFirstnameLike(name);
    }

    public Optional<Company> findById(Long id) {
        return companyRepository.findById(id);
    }

    public void deleteById(Long id) {
        companyRepository.deleteById(id);
    }

    public void deleteAll() {
        companyRepository.deleteAll();
    }

    public Company getOne(Long id) {
        return companyRepository.getOne(id);
    }
}
