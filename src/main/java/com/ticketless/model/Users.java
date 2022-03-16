package com.ticketless.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    
    private String userName;
    
    private String mobileNo;
    
    private Date dateOfJoining;
    
    @OneToMany(mappedBy = "users",fetch = FetchType.LAZY)
    private List<Booking> userBookings;

    @OneToMany(mappedBy = "users", fetch = FetchType.LAZY)
    private List<CancelOrder> cancelOrders;
        
    public Users() {
        super();
    }

    public Users(Long userId, String userName, String mobileNo, Date dateOfJoining) {
        super();
        this.userId = userId;
        this.userName = userName;
        this.mobileNo = mobileNo;
        this.dateOfJoining = dateOfJoining;
    }

    public List<Booking> getUserBookings() {
        return userBookings;
    }

    public void setUserBookings(List<Booking> userBookings) {
        this.userBookings = userBookings;
    }

    public List<CancelOrder> getCancelOrders() {
        return cancelOrders;
    }

    public void setCancelOrders(List<CancelOrder> cancelOrders) {
        this.cancelOrders = cancelOrders;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public Date getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(Date dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    @Override
    public String toString() {
        return "Users [userId=" + userId + ", userName=" + userName + ", mobileNo=" + mobileNo + ", dateOfJoining="
                + dateOfJoining + "]";
    }
    
    
    
}
