package com.ticketless.resposedto;

public class BookingDetailsResponse {

    private String bookingId;
    
    private int adultQnt;
    
    private int childQnt;
    
    private int totalQnt;
    
    private String amountPaid;
    
    private String visitDate;
    
    private String paymentId;
    
    private boolean visited;

    public BookingDetailsResponse() {
        super();
    }

    public BookingDetailsResponse(String bookingId, int adultQnt, int childQnt, int totalQnt, String amountPaid,
            String visitDate, String paymentId) {
        super();
        this.bookingId = bookingId;
        this.adultQnt = adultQnt;
        this.childQnt = childQnt;
        this.totalQnt = totalQnt;
        this.amountPaid = amountPaid;
        this.visitDate = visitDate;
        this.paymentId = paymentId;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
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

    public int getTotalQnt() {
        return totalQnt;
    }

    public void setTotalQnt(int totalQnt) {
        this.totalQnt = totalQnt;
    }

    public String getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(String amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    @Override
    public String toString() {
        return "BookingDetailsResponse [bookingId=" + bookingId + ", adultQnt=" + adultQnt + ", childQnt=" + childQnt
                + ", totalQnt=" + totalQnt + ", amountPaid=" + amountPaid + ", visitDate=" + visitDate + ", paymentId="
                + paymentId + "]";
    }
}
