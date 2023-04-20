package com.project.MovieEventService.rabbitmq;

import java.util.Date;

public class SeatsDTO {

    private String seatNumber;
    private double price;
    private Date dateOfBooking;
    private String transactionId;

    public SeatsDTO() {
    }

    public SeatsDTO(String seatNumber, double price, Date dateOfBooking, String transactionId) {
        this.seatNumber = seatNumber;
        this.price = price;
        this.dateOfBooking = dateOfBooking;
        this.transactionId = transactionId;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getDateOfBooking() {
        return dateOfBooking;
    }

    public void setDateOfBooking(Date dateOfBooking) {
        this.dateOfBooking = dateOfBooking;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    @Override
    public String toString() {
        return "SeatsDTO{" +
                "seatNumber='" + seatNumber + '\'' +
                ", price=" + price +
                ", dateOfBooking=" + dateOfBooking +
                ", transactionId='" + transactionId + '\'' +
                '}';
    }
}
