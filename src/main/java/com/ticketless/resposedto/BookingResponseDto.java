package com.ticketless.resposedto;

public class BookingResponseDto {

    private String orderId;
    
    private String price;

    public BookingResponseDto() {
        super();
    }

    public BookingResponseDto(String orderId, String price) {
        super();
        this.orderId = orderId;
        this.price = price;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "BookingResponseDto [orderId=" + orderId + ", price=" + price + "]";
    }
    
}
