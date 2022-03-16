package com.ticketless.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ticketless.resposedto.BookingDetailsResponse;
import com.ticketless.resposedto.BookingDetailsResponseDto;
import com.ticketless.resposedto.OrderCancelResponseDto;
import com.ticketless.resposedto.ResponseEntity;
import com.ticketless.service.BookingService;

@RestController
public class UsersController {

    
    @Autowired
    private BookingService bookingService;
    
    @GetMapping(value = "/secure/users")
    public ResponseEntity userCheck(@RequestParam("mobile") String mobile) {
        System.out.println("neel jain");
        return new ResponseEntity();
    }
    
    @GetMapping(value = "/secure/myorders",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity myOrder(@AuthenticationPrincipal UserDetails userDetails) {
        ResponseEntity entity = new ResponseEntity();
        List<BookingDetailsResponseDto> bookingDetailsResponseDtos = bookingService.getAllUserOrders(userDetails.getUsername());
        entity.setData(bookingDetailsResponseDtos);
        entity.setHttpStatus(HttpStatus.OK);
        entity.setStatus("success");
        return entity;
    }
    
    @GetMapping(value = "/secure/mycancel",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity mycancel(@AuthenticationPrincipal UserDetails userDetails) {
        ResponseEntity entity = new ResponseEntity();
        List<OrderCancelResponseDto> cancelResponseDtos = bookingService.getAllCancelOreders(userDetails.getUsername());
        entity.setData(cancelResponseDtos);
        entity.setHttpStatus(HttpStatus.OK);
        entity.setStatus("success");
        return entity;
    }
    
    @GetMapping(value = "/check/details/{bookingId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getBookingDetails(@PathVariable("bookingId") String bookingId) {
        ResponseEntity responseEntity = new ResponseEntity();
        BookingDetailsResponse bookingDetailsResponse =   bookingService.getDetails(bookingId);
        responseEntity.setData(bookingDetailsResponse);
        if (bookingDetailsResponse!=null) {
            responseEntity.setStatus("success");
        }else {
            responseEntity.setStatus("failed");
        }
        responseEntity.setHttpStatus(HttpStatus.OK);
        return responseEntity;
    }
    
    @GetMapping(value = "/check/approve/{bookingId}")
    public ResponseEntity approveBooking(@PathVariable("bookingId") String bookingId) {
        ResponseEntity responseEntity = new ResponseEntity();
        
        if(bookingService.approveTicket(bookingId)) {
            responseEntity.setStatus("success");
            responseEntity.setHttpStatus(HttpStatus.OK);
            responseEntity.setData("approved");
        }else {
            responseEntity.setStatus("failed");
        }
        
        return responseEntity;
    }
}


























