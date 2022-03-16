package com.ticketless.requestdto;

public class OrderCancellationDto {

    private Long bookingId;
    
    private int adultQnt;
    
    private int childQnt;
    
    private String accountNo;
    
    private String ifscCode;
    
    private String upiId;
    
    private String refundMode;

    public OrderCancellationDto() {
        super();
    }

    public OrderCancellationDto(Long bookingId, int adultQnt, int childQnt) {
        super();
        this.bookingId = bookingId;
        this.adultQnt = adultQnt;
        this.childQnt = childQnt;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
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

    public String getRefundMode() {
        return refundMode;
    }

    public void setRefundMode(String refundMode) {
        this.refundMode = refundMode;
    }

    @Override
    public String toString() {
        return "OrderCancellationDto [bookingId=" + bookingId + ", adultQnt=" + adultQnt + ", childQnt=" + childQnt
                + ", accountNo=" + accountNo + ", ifscCode=" + ifscCode + ", upiId=" + upiId + ", refundMode="
                + refundMode + "]";
    }
}
