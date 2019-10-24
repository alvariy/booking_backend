package com.tw.apistackbase.service;

import com.tw.apistackbase.core.Packages;
import com.tw.apistackbase.repository.PackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PackageService {

    @Autowired
    private PackageRepository packageRepository;

    public Packages addPackage(Packages packages)
    {
        packageRepository.save(packages);
        return  packages;
    }

    public List<Packages> getAllPackages() {
        return  packageRepository.findAll();
    }
}
