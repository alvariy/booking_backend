package com.tw.apistackbase.repository;

import com.tw.apistackbase.core.Packages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PackageRepository extends JpaRepository<Packages, Long> {

    @Query("Select c from Packages c where c.wayBillNumber = :wayBillNumber")
    Packages findByName(@Param("wayBillNumber") Long wayBillNumber);
}
