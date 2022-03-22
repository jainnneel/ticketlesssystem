package com.ticketless.requestdto;

public class VisitorRequestDto {

    private String visitdate;
    
    private String placeId;

    public VisitorRequestDto() {
        super();
        // TODO Auto-generated constructor stub
    }

    public VisitorRequestDto(String visitdate, String placeId) {
        super();
        this.visitdate = visitdate;
        this.placeId = placeId;
    }

    public String getVisitdate() {
        return visitdate;
    }

    public void setVisitdate(String visitdate) {
        this.visitdate = visitdate;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    @Override
    public String toString() {
        return "VisitorRequestDto [visitdate=" + visitdate + ", placeId=" + placeId + "]";
    }
    
}
