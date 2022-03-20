package com.ticketless.model;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private Users users;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private Place place;
    
    private String visitDate;
    
    private String orderId;
    
    private String paymentId;
    
    private String amount;
    
    private Time bookingTime;
    
    private Date bookingDate;
    
    private int adultQnt;
    
    private int childQnt;
    
    private boolean visited;
    
    private boolean cancelled;
    
    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    @OneToOne(cascade = CascadeType.ALL)
    private QrCode qrCode;
    
    @OneToMany(mappedBy = "booking",fetch = FetchType.LAZY)
    private List<CancelOrder> cancelOrders;

    public Booking() {
        super();
    }

    public Booking(Long bookingId, Users users, Place place, String orderId, String paymentId, String amount,
            Time bookingTime, Date bookingDate) {
        super();
        this.bookingId = bookingId;
        this.users = users;
        this.place = place;
        this.orderId = orderId;
        this.paymentId = paymentId;
        this.amount = amount;
        this.bookingTime = bookingTime;
        this.bookingDate = bookingDate;
    }

    public List<CancelOrder> getCancelOrders() {
        return cancelOrders;
    }

    public void setCancelOrders(List<CancelOrder> cancelOrders) {
        this.cancelOrders = cancelOrders;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
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

    public QrCode getQrCode() {
        return qrCode;
    }

    public void setQrCode(QrCode qrCode) {
        this.qrCode = qrCode;
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

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    @Override
    public String toString() {
        return "Booking [bookingId=" + bookingId + ", users=" + users + ", place=" + place + ", visitDate=" + visitDate
                + ", orderId=" + orderId + ", paymentId=" + paymentId + ", amount=" + amount + ", bookingTime="
                + bookingTime + ", bookingDate=" + bookingDate + ", adultQnt=" + adultQnt + ", childQnt=" + childQnt
                + ", qrCode=" + qrCode + "]";
    }

}
