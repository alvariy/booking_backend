package com.tw.apistackbase.core;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Packages {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long wayBillNumber;

    private String recipients;

    private String phone;

    private String weight;

    private String pickUdDate;

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

    public String getRecipients() {
        return recipients;
    }

    public void setRecipients(String recipients) {
        this.recipients = recipients;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getPickUdDate() {
        return pickUdDate;
    }

    public void setPickUdDate(String pickUdDate) {
        this.pickUdDate = pickUdDate;
    }

    public Packages() {
    }
}
