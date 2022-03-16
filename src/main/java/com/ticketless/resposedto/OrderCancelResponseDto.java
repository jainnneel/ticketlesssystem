package com.ticketless.resposedto;

public class OrderCancelResponseDto {

    private Long cancelId;
    
    private String refundAmount;
    
    private String refundMode;
    
    private String upiId;
    
    private String accountNumber;
    
    private String ifscCode;
    
    private String cancelCharge;
    
    private String adultQntCancel;
    
    private String childQntCancel;
    
    private String status;

    private Long bookingId;
    
    public OrderCancelResponseDto() {
        super();
    }

    public OrderCancelResponseDto(Long cancelId, String refundAmount, String refundMode, String upiId,
            String accountNumber, String ifscCode) {
        super();
        this.cancelId = cancelId;
        this.refundAmount = refundAmount;
        this.refundMode = refundMode;
        this.upiId = upiId;
        this.accountNumber = accountNumber;
        this.ifscCode = ifscCode;
    }

    public Long getCancelId() {
        return cancelId;
    }

    public void setCancelId(Long cancelId) {
        this.cancelId = cancelId;
    }

    public String getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(String refundAmount) {
        this.refundAmount = refundAmount;
    }

    public String getRefundMode() {
        return refundMode;
    }

    public void setRefundMode(String refundMode) {
        this.refundMode = refundMode;
    }

    public String getUpiId() {
        return upiId;
    }

    public void setUpiId(String upiId) {
        this.upiId = upiId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getCancelCharge() {
        return cancelCharge;
    }

    public void setCancelCharge(String cancelCharge) {
        this.cancelCharge = cancelCharge;
    }

    public String getAdultQntCancel() {
        return adultQntCancel;
    }

    public void setAdultQntCancel(String adultQntCancel) {
        this.adultQntCancel = adultQntCancel;
    }

    public String getChildQntCancel() {
        return childQntCancel;
    }

    public void setChildQntCancel(String childQntCancel) {
        this.childQntCancel = childQntCancel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    @Override
    public String toString() {
        return "OrderCancelResponseDto [cancelId=" + cancelId + ", refundAmount=" + refundAmount + ", refundMode="
                + refundMode + ", upiId=" + upiId + ", accountNumber=" + accountNumber + ", ifscCode=" + ifscCode
                + ", cancelCharge=" + cancelCharge + ", adultQntCancel=" + adultQntCancel + ", childQntCancel="
                + childQntCancel + "]";
    }

}
