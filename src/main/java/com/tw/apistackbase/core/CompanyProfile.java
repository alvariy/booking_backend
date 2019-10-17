package com.tw.apistackbase.core;

import javax.persistence.*;

@Entity
public class CompanyProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String certId;

    public CompanyProfile() {
    }

    public CompanyProfile(Long id, String certId, Integer registeredCapital) {
        this.id = id;
        this.certId = certId;
        this.registeredCapital = registeredCapital;
    }

    private Integer registeredCapital;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCertId() {
        return certId;
    }

    public void setCertId(String certId) {
        this.certId = certId;
    }


    public Integer getRegisteredCapital() {
        return registeredCapital;
    }

    public void setRegisteredCapital(Integer registeredCapital) {
        this.registeredCapital = registeredCapital;
    }

}
