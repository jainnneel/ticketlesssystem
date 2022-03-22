package com.ticketless.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.ticketless.requestdto.OrderCancellationDto;
import com.ticketless.requestdto.OrderPaymentDto;
import com.ticketless.requestdto.OrderRequestDto;
import com.ticketless.requestdto.VisitorRequestDto;
import com.ticketless.resposedto.BookingDetailsResponse;
import com.ticketless.resposedto.BookingDetailsResponseDto;
import com.ticketless.resposedto.BookingResponseDto;
import com.ticketless.resposedto.OrderCancelResponseDto;
import com.ticketless.resposedto.OrderReviewDto;
import com.ticketless.resposedto.OrderUpdateResponseDto;

@Service
public interface BookingService {

    BookingResponseDto bookingService(OrderRequestDto orderRequestDto, UserDetails userDetails);

    OrderUpdateResponseDto updateBooking(OrderPaymentDto orderDto);

    OrderCancelResponseDto orderCancelRequest(OrderCancellationDto cancellationDto,UserDetails userDetails);

    List<BookingDetailsResponseDto> getAllUserOrders(String username);

    List<OrderCancelResponseDto> getAllCancelOreders(String username);

    BookingDetailsResponse getDetails(String bookingId);

    boolean approveTicket(String bookingId);

    OrderReviewDto getBooKingInfo(String bookingId);

    OrderUpdateResponseDto getBookingReceit(String bookingId);

    Long getAllBookings(VisitorRequestDto requestDto);

}
