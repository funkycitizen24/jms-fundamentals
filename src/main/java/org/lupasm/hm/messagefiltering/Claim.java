package org.lupasm.hm.messagefiltering;

import java.io.Serializable;

public class Claim implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer hospitalID;
    private String doctorName;
    private String doctorType;
    private String insuranceProvider;
    private Double claimAmount;

    public Integer getHospitalID() {
        return hospitalID;
    }

    public Claim(Integer hospitalID, String doctorName, String doctorType, String insuranceProvider, Double claimAmount) {
        this.hospitalID = hospitalID;
        this.doctorName = doctorName;
        this.doctorType = doctorType;
        this.insuranceProvider = insuranceProvider;
        this.claimAmount = claimAmount;
    }

    public void setHospitalID(Integer hospitalID) {
        this.hospitalID = hospitalID;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorType() {
        return doctorType;
    }

    public void setDoctorType(String doctorType) {
        this.doctorType = doctorType;
    }

    public String getInsuranceProvider() {
        return insuranceProvider;
    }

    public void setInsuranceProvider(String insuranceProvider) {
        this.insuranceProvider = insuranceProvider;
    }

    public Double getClaimAmount() {
        return claimAmount;
    }

    public void setClaimAmount(Double claimAmount) {
        this.claimAmount = claimAmount;
    }
}
