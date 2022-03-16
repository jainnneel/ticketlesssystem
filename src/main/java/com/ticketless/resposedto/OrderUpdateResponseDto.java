package com.ticketless.resposedto;

public class OrderUpdateResponseDto {

    PlaceResponseDto placeResponseDto;
    
    BookingDetailsResponseDto detailsResponseDto;
    
    QrResponseDto qrResponseDto;

    public OrderUpdateResponseDto() {
        super();
    }

    public OrderUpdateResponseDto(PlaceResponseDto placeResponseDto, BookingDetailsResponseDto detailsResponseDto,
            QrResponseDto qrResponseDto) {
        super();
        this.placeResponseDto = placeResponseDto;
        this.detailsResponseDto = detailsResponseDto;
        this.qrResponseDto = qrResponseDto;
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

    public QrResponseDto getQrResponseDto() {
        return qrResponseDto;
    }

    public void setQrResponseDto(QrResponseDto qrResponseDto) {
        this.qrResponseDto = qrResponseDto;
    }

    @Override
    public String toString() {
        return "OrderUpdateResponseDto [placeResponseDto=" + placeResponseDto + ", detailsResponseDto="
                + detailsResponseDto + ", qrResponseDto=" + qrResponseDto + "]";
    }
    
    
}
