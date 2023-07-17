package org.lupasm.hm.p2p.model;

import java.io.Serializable;

public class Patient implements Serializable {

    private static final Long serialVersionID=1L;

    private Integer id;

    private String name;

    private String insuranceProvider;

    private Double copay;

    private Double amountToBePayed;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getInsuranceProvider() {
        return insuranceProvider;
    }

    public Double getCopay() {
        return copay;
    }

    public Double getAmountToBePayed() {
        return amountToBePayed;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInsuranceProvider(String insuranceProvider) {
        this.insuranceProvider = insuranceProvider;
    }

    public void setCopay(Double copay) {
        this.copay = copay;
    }

    public void setAmountToBePayed(Double amountToBePayed) {
        this.amountToBePayed = amountToBePayed;
    }
}
