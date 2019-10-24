package com.tw.apistackbase.core;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PickUp {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long wayBillNumber;

    private String pickUpTime;

    public PickUp() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWayBillNumber() {
        return wayBillNumber;
    }

    public void setWayBillNumber(Long wayBillNumber) {
        this.wayBillNumber = wayBillNumber;
    }

    public String getPickUpTime() {
        return pickUpTime;
    }

    public void setPickUpTime(String pickUpTime) {
        this.pickUpTime = pickUpTime;
    }

}
