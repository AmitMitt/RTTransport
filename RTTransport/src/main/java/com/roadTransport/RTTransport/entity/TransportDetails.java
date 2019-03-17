package com.roadTransport.RTTransport.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table
@Entity
public class TransportDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;

    @Column
    @NotNull
    private String transportName;

    @Column(unique = true)
    @NotNull
    private String transportRegistrationNumber;

    @Column
    private String createdDate;

    @Column
    private String modifiedDate;

    @Column
    @NotNull
    private String transportLocation;

    @Column
    @NotNull
    private String transportOwnerName;

    @Column(unique = true, length = 10)
    @NotNull
    private long transportOwnerMobileNumber;

    @Column
    private long totalVehicles;

    @Column
    private long totalLoadVehicles;

    @Column
    private long totalPersonalVehicles;

    @Column
    private long totalBuses;

    @Column(columnDefinition = "Text")
    private String ownerImage;

    @Column(columnDefinition = "Text")
    private String transportLogo;

    @Column(columnDefinition = "Text")
    @NotNull
    private String transportLicenceImage;

    @Column
    @NotNull
    private long ownerAadhaarNumber;

    @Column
    @NotNull
    private String ownerPanCardNumber;

    @Column(columnDefinition = "Text")
    @NotNull
    private String ownerAadhaarCardImage;

    @Column(columnDefinition = "Text")
    @NotNull
    private String ownerPanCardImage;

    @Column
    private boolean kyc;

    @Column
    private boolean status;

    @Column
    private long otherVehicles;

    public long getOtherVehicles() {
        return otherVehicles;
    }

    public void setOtherVehicles(long otherVehicles) {
        this.otherVehicles = otherVehicles;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTransportName() {
        return transportName;
    }

    public void setTransportName(String transportName) {
        this.transportName = transportName;
    }

    public String getTransportRegistrationNumber() {
        return transportRegistrationNumber;
    }

    public void setTransportRegistrationNumber(String transportRegistrationNumber) {
        this.transportRegistrationNumber = transportRegistrationNumber;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getTransportLocation() {
        return transportLocation;
    }

    public void setTransportLocation(String transportLocation) {
        this.transportLocation = transportLocation;
    }

    public String getTransportOwnerName() {
        return transportOwnerName;
    }

    public void setTransportOwnerName(String transportOwnerName) {
        this.transportOwnerName = transportOwnerName;
    }

    public long getTransportOwnerMobileNumber() {
        return transportOwnerMobileNumber;
    }

    public void setTransportOwnerMobileNumber(long transportOwnerMobileNumber) {
        this.transportOwnerMobileNumber = transportOwnerMobileNumber;
    }

    public long getTotalVehicles() {
        return totalVehicles;
    }

    public void setTotalVehicles(long totalVehicles) {
        this.totalVehicles = totalVehicles;
    }

    public long getTotalLoadVehicles() {
        return totalLoadVehicles;
    }

    public void setTotalLoadVehicles(long totalLoadVehicles) {
        this.totalLoadVehicles = totalLoadVehicles;
    }

    public long getTotalPersonalVehicles() {
        return totalPersonalVehicles;
    }

    public void setTotalPersonalVehicles(long totalPersonalVehicles) {
        this.totalPersonalVehicles = totalPersonalVehicles;
    }

    public long getTotalBuses() {
        return totalBuses;
    }

    public void setTotalBuses(long totalBuses) {
        this.totalBuses = totalBuses;
    }

    public String getOwnerImage() {
        return ownerImage;
    }

    public void setOwnerImage(String ownerImage) {
        this.ownerImage = ownerImage;
    }

    public String getTransportLogo() {
        return transportLogo;
    }

    public void setTransportLogo(String transportLogo) {
        this.transportLogo = transportLogo;
    }

    public String getTransportLicenceImage() {
        return transportLicenceImage;
    }

    public void setTransportLicenceImage(String transportLicenceImage) {
        this.transportLicenceImage = transportLicenceImage;
    }

    public long getOwnerAadhaarNumber() {
        return ownerAadhaarNumber;
    }

    public void setOwnerAadhaarNumber(long ownerAadhaarNumber) {
        this.ownerAadhaarNumber = ownerAadhaarNumber;
    }

    public String getOwnerPanCardNumber() {
        return ownerPanCardNumber;
    }

    public void setOwnerPanCardNumber(String ownerPanCardNumber) {
        this.ownerPanCardNumber = ownerPanCardNumber;
    }

    public String getOwnerAadhaarCardImage() {
        return ownerAadhaarCardImage;
    }

    public void setOwnerAadhaarCardImage(String ownerAadhaarCardImage) {
        this.ownerAadhaarCardImage = ownerAadhaarCardImage;
    }

    public String getOwnerPanCardImage() {
        return ownerPanCardImage;
    }

    public void setOwnerPanCardImage(String ownerPanCardImage) {
        this.ownerPanCardImage = ownerPanCardImage;
    }

    public boolean isKyc() {
        return kyc;
    }

    public void setKyc(boolean kyc) {
        this.kyc = kyc;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TransportDetails{" +
                "id=" + id +
                ", transportName='" + transportName + '\'' +
                ", transportRegistrationNumber='" + transportRegistrationNumber + '\'' +
                ", createdDate='" + createdDate + '\'' +
                ", modifiedDate='" + modifiedDate + '\'' +
                ", transportLocation='" + transportLocation + '\'' +
                ", transportOwnerName='" + transportOwnerName + '\'' +
                ", transportOwnerMobileNumber=" + transportOwnerMobileNumber +
                ", totalVehicles=" + totalVehicles +
                ", totalLoadVehicles=" + totalLoadVehicles +
                ", totalPersonalVehicles=" + totalPersonalVehicles +
                ", totalBuses=" + totalBuses +
                ", ownerImage='" + ownerImage + '\'' +
                ", transportLogo='" + transportLogo + '\'' +
                ", transportLicenceImage='" + transportLicenceImage + '\'' +
                ", ownerAadhaarNumber=" + ownerAadhaarNumber +
                ", ownerPanCardNumber='" + ownerPanCardNumber + '\'' +
                ", ownerAadhaarCardImage='" + ownerAadhaarCardImage + '\'' +
                ", ownerPanCardImage='" + ownerPanCardImage + '\'' +
                ", kyc=" + kyc +
                ", status=" + status +
                ", otherVehicles=" + otherVehicles +
                '}';
    }
}
