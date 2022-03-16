package com.ticketless.requestdto;

public class OrderRequestDto {

    private Long placeId;
    
    private int adultQnt;
    
    private int childQnt;
    
    private String visitDate;

    public OrderRequestDto() {
        super();
    }

    public OrderRequestDto(Long placeId, int adultQnt, int childQnt) {
        super();
        this.placeId = placeId;
        this.adultQnt = adultQnt;
        this.childQnt = childQnt;
    }

    public Long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
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

    public String getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }

    @Override
    public String toString() {
        return "OrderRequestDto [placeId=" + placeId + ", adultQnt=" + adultQnt + ", childQnt=" + childQnt
                + ", visitDate=" + visitDate + "]";
    }
    
}
