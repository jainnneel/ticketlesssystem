package com.ticketless.requestdto;

public class OrderPaymentDto {

    private String orderId;
    
    private String paymentId;

    public OrderPaymentDto() {
        super();
    }

    public OrderPaymentDto(String orderId, String paymentId) {
        super();
        this.orderId = orderId;
        this.paymentId = paymentId;
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

    @Override
    public String toString() {
        return "OrderPaymentDto [orderId=" + orderId + ", paymentId=" + paymentId + "]";
    }
    
}
