package com.roadTransport.RTTransport.model;

public class TransportResponse {

    private String message;
    private long otp;

    public long getOtp() {
        return otp;
    }

    public void setOtp(long otp) {
        this.otp = otp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "DriverDetailsResponse{" +
                "message='" + message + '\'' +
                ", otp=" + otp +
                '}';
    }

}
