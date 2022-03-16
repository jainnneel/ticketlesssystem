package com.ticketless.resposedto;

import java.sql.Date;
import java.sql.Time;

public class BookingDetailsResponseDto {
    private String orderId;

    private String paymentId;

    private String amount;

    private Time bookingTime;

    private Date bookingDate;
    
    private String visitDate;
    
    private int adultQnt;
    
    private int childQnt;
    
    private Long bookingId;

    public BookingDetailsResponseDto() {
        super();
    }

    public BookingDetailsResponseDto(String orderId, String paymentId, String amount, Time bookingTime,
            Date bookingDate) {
        super();
        this.orderId = orderId;
        this.paymentId = paymentId;
        this.amount = amount;
        this.bookingTime = bookingTime;
        this.bookingDate = bookingDate;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Time getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(Time bookingTime) {
        this.bookingTime = bookingTime;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }
    
    public String getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }

    public int getAdultQnt() {
        return adultQnt;
    }

    public void setAdultQnt(int adultQnt) {
        this.adultQnt = adultQnt;
    }

    public int getChildQnt() {
        return childQnt;
    }

    public void setChildQnt(int childQnt) {
        this.childQnt = childQnt;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    @Override
    public String toString() {
        return "BookingDetailsResponseDto [orderId=" + orderId + ", paymentId=" + paymentId + ", amount=" + amount
                + ", bookingTime=" + bookingTime + ", bookingDate=" + bookingDate + ", visitDate=" + visitDate
                + ", adultQnt=" + adultQnt + ", childQnt=" + childQnt + "]";
    }

}
