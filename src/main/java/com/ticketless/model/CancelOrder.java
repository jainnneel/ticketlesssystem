package com.ticketless.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class CancelOrder {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long cancelId;
    
    private int adultQnt;
    
    private int childQnt;
    
    private String refundAmount;
    
    private String accountNo;
    
    private String ifscCode;
    
    private String upiId;
    
    private String cancelCharge;
    
    private String refundMode;
    
    private String status;
    
    @ManyToOne
    private Booking booking;
    
    @ManyToOne
    private Users users;
    
    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public CancelOrder() {
        super();
    }

    public CancelOrder(Long cancelId, int adultQnt, int childQnt, String accountNo, String ifscCode, String upiId,
            String cancelCharge, String refundMode) {
        super();
        this.cancelId = cancelId;
        this.adultQnt = adultQnt;
        this.childQnt = childQnt;
        this.accountNo = accountNo;
        this.ifscCode = ifscCode;
        this.upiId = upiId;
        this.cancelCharge = cancelCharge;
        this.refundMode = refundMode;
    }

    public Long getCancelId() {
        return cancelId;
    }

    public void setCancelId(Long cancelId) {
        this.cancelId = cancelId;
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

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getUpiId() {
        return upiId;
    }

    public void setUpiId(String upiId) {
        this.upiId = upiId;
    }

    public String getCancelCharge() {
        return cancelCharge;
    }

    public void setCancelCharge(String cancelCharge) {
        this.cancelCharge = cancelCharge;
    }

    public String getRefundMode() {
        return refundMode;
    }

    public void setRefundMode(String refundMode) {
        this.refundMode = refundMode;
    }

    public String getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(String refundAmount) {
        this.refundAmount = refundAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CancelOrder [cancelId=" + cancelId + ", adultQnt=" + adultQnt + ", childQnt=" + childQnt
                + ", refundAmount=" + refundAmount + ", accountNo=" + accountNo + ", ifscCode=" + ifscCode + ", upiId="
                + upiId + ", cancelCharge=" + cancelCharge + ", refundMode=" + refundMode + "]";
    }
    
}
