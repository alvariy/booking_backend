package com.tw.apistackbase.repository;

import com.tw.apistackbase.core.PickUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PickUpRepository extends JpaRepository<PickUp, Long> {

}
