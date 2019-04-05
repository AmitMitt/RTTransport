package com.roadTransport.RTTransport.model;

public class TransportRequest {

    private String transportName;
    private String transportRegistrationNumber;
    private String createdDate;
    private String modifiedDate;
    private String transportLocation;
    private String transportOwnerName;
    private String transportOwnerMobileNumber;
    private long totalVehicles;
    private long totalLoadVehicles;
    private long totalPersonalVehicles;
    private long totalBuses;
    private String ownerImage;
    private String transportLogo;
    private String transportLicenceImage;
    private long ownerAadhaarNumber;
    private String ownerPanCardNumber;
    private String ownerAadhaarCardImage;
    private String ownerPanCardImage;
    private boolean status;
    private long otherVehicles;

    public long getOtherVehicles() {
        return otherVehicles;
    }

    public void setOtherVehicles(long otherVehicles) {
        this.otherVehicles = otherVehicles;
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

    public String getTransportOwnerMobileNumber() {
        return transportOwnerMobileNumber;
    }

    public void setTransportOwnerMobileNumber(String transportOwnerMobileNumber) {
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TransportRequest{" +
                "transportName='" + transportName + '\'' +
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
                ", status=" + status +
                '}';
    }
}
