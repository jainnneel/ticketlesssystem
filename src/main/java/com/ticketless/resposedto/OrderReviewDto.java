package com.ticketless.resposedto;

public class OrderReviewDto {


    PlaceResponseDto placeResponseDto;
    
    BookingDetailsResponseDto detailsResponseDto;

    public OrderReviewDto() {
        super();
        // TODO Auto-generated constructor stub
    }

    public OrderReviewDto(PlaceResponseDto placeResponseDto, BookingDetailsResponseDto detailsResponseDto) {
        super();
        this.placeResponseDto = placeResponseDto;
        this.detailsResponseDto = detailsResponseDto;
    }

    public PlaceResponseDto getPlaceResponseDto() {
        return placeResponseDto;
    }

    public void setPlaceResponseDto(PlaceResponseDto placeResponseDto) {
        this.placeResponseDto = placeResponseDto;
    }

    public BookingDetailsResponseDto getDetailsResponseDto() {
        return detailsResponseDto;
    }

    public void setDetailsResponseDto(BookingDetailsResponseDto detailsResponseDto) {
        this.detailsResponseDto = detailsResponseDto;
    }

    @Override
    public String toString() {
        return "OrderReviewDto [placeResponseDto=" + placeResponseDto + ", detailsResponseDto=" + detailsResponseDto
                + "]";
    }
    
}
