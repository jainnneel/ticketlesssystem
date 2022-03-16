package com.ticketless.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ticketless.requestdto.OrderCancellationDto;
import com.ticketless.requestdto.OrderPaymentDto;
import com.ticketless.requestdto.OrderRequestDto;
import com.ticketless.resposedto.BookingResponseDto;
import com.ticketless.resposedto.OrderCancelResponseDto;
import com.ticketless.resposedto.OrderUpdateResponseDto;
import com.ticketless.resposedto.ResponseEntity;
import com.ticketless.service.BookingService;

@RestController
public class PlaceOrederController {
    
    @Autowired
    private BookingService bookingService;
    
    @PostMapping(value = "/secure/order",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity placeOrder(@RequestBody OrderRequestDto orderRequestDto,@AuthenticationPrincipal UserDetails userDetails) {
        ResponseEntity responseEntity = new ResponseEntity();
        BookingResponseDto bookingResponseDto = bookingService.bookingService(orderRequestDto,userDetails);
        responseEntity.setData(bookingResponseDto);
        responseEntity.setHttpStatus(HttpStatus.OK);
        
        if(bookingResponseDto != null) responseEntity.setStatus("success");
        else responseEntity.setStatus("failed");
        
        return responseEntity;
    }
    
    @PostMapping(value = "/secure/confirm",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity comfirmPayment(@RequestBody OrderPaymentDto orderDto) {
        ResponseEntity responseEntity = new ResponseEntity();
        OrderUpdateResponseDto updateBooking = bookingService.updateBooking(orderDto);
        if (updateBooking!=null) {
            responseEntity.setData(updateBooking);
            responseEntity.setHttpStatus(HttpStatus.OK);
            responseEntity.setStatus("success");
        }else {
            responseEntity.setStatus("failed");
        }
        return responseEntity;
    }
    
    @PostMapping(value = "/secure/cancel",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity cancelPayment(@RequestBody OrderCancellationDto cancellationDto,  @AuthenticationPrincipal UserDetails userDetails) {
        ResponseEntity entity = new ResponseEntity();
        OrderCancelResponseDto cancellationResponse =  bookingService.orderCancelRequest(cancellationDto,userDetails);
        entity.setData(cancellationResponse);
        entity.setHttpStatus(HttpStatus.OK);
        if(cancellationResponse!=null) entity.setStatus("success");
        else entity.setStatus("failed");
        return entity;
    }
    

}



































