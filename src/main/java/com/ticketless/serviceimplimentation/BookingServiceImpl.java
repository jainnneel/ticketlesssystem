package com.ticketless.serviceimplimentation;

import java.io.ByteArrayOutputStream;
import java.sql.Date;
import java.sql.Time;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.razorpay.Order;
import com.razorpay.Payment;
import com.razorpay.RazorpayClient;
import com.ticketless.model.Booking;
import com.ticketless.model.CancelOrder;
import com.ticketless.model.Place;
import com.ticketless.model.QrCode;
import com.ticketless.model.Users;
import com.ticketless.repository.BookingRepo;
import com.ticketless.repository.CancelRepo;
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
import com.ticketless.resposedto.PlaceResponseDto;
import com.ticketless.resposedto.QrResponseDto;
import com.ticketless.service.BookingService;
import com.ticketless.service.PlaceService;
import com.ticketless.service.UsersService;

@Component
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepo bookingRepo;

    @Autowired
    private UsersService usersService;

    @Autowired
    private PlaceService placeService;

    @Autowired
    private CancelRepo cancelRepo;

    @Override
    public BookingResponseDto bookingService(OrderRequestDto orderRequestDto, UserDetails userDetails) {
        Place place = placeExistOrNot(orderRequestDto.getPlaceId());
        if (place != null) {
            double amount = Double.valueOf(place.getPriceAdult()) * Double.valueOf(orderRequestDto.getAdultQnt())
                    + Double.valueOf(place.getPriceChild()) * Double.valueOf(orderRequestDto.getChildQnt());
            Order order = createOrder(String.valueOf(amount));
            if (order != null) {
                Booking booking = new Booking();
                booking.setAmount(String.valueOf(amount));
                booking.setBookingDate(Date.valueOf(LocalDate.now()));
                booking.setBookingTime(Time.valueOf(LocalTime.now()));
                booking.setOrderId(order.get("id"));
                booking.setUsers(usersService.getUserByMobile(userDetails.getUsername()));
                booking.setPlace(place);
                booking.setAdultQnt(orderRequestDto.getAdultQnt());
                booking.setChildQnt(orderRequestDto.getChildQnt());
                booking.setVisitDate(orderRequestDto.getVisitDate());
                booking.setCancelled(false);
                Booking save = bookingRepo.save(booking);
                BookingResponseDto bookingResponseDto = new BookingResponseDto();
                bookingResponseDto.setOrderId(order.get("id"));
                bookingResponseDto.setPrice(String.valueOf(amount));
                bookingResponseDto.setBookingId(String.valueOf(save.getBookingId()));
                return bookingResponseDto;
            }
        }
        return null;
    }

    private Place placeExistOrNot(Long placeId) {
        Place placeById = placeService.getPlaceEnById(placeId);
        if (placeById != null) {
            return placeById;
        }
        return null;
    }

    private Order createOrder(String amt) {
        try {
            Double amount = Double.valueOf(amt) * Double.valueOf(100);
            RazorpayClient razorpayClient = new RazorpayClient("rzp_test_9B8AXwdinhrVTx", "aeRfMEbWIgA3mA2fOsY7LU0G");
            JSONObject options = new JSONObject();
            options.put("amount", amount);
            options.put("currency", "INR");
            Order order = razorpayClient.Orders.create(options);
            return order;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public OrderUpdateResponseDto updateBooking(OrderPaymentDto orderDto) {
        try {
            OrderUpdateResponseDto orderUpdateResponseDto = new OrderUpdateResponseDto();
            Booking booking = bookingRepo.findByorderId(orderDto.getOrderId());
            if (booking != null) {
                RazorpayClient razorpayClient = new RazorpayClient("rzp_test_9B8AXwdinhrVTx",
                        "aeRfMEbWIgA3mA2fOsY7LU0G");
                Payment payment = razorpayClient.Payments.fetch(orderDto.getPaymentId());
                System.out.println(Long.valueOf(booking.getAmount().split("\\.")[0]) * 100 == Long.valueOf(payment.get("amount").toString().trim()));
                
                System.out.println(payment.get("amount").toString().trim());
                System.out.println(payment.get("order_id").toString() .trim());
                System.out.println(payment.get("status").toString().trim());
                
                if (payment != null && Long.valueOf(booking.getAmount().split("\\.")[0]) * 100 == Long.valueOf(payment.get("amount").toString().trim())
                        && payment.get("order_id").toString().trim().equals(orderDto.getOrderId())
                        && payment.get("status").toString().trim().equals("captured")) {
                booking.setBookingDate(Date.valueOf(LocalDate.now()));
                booking.setBookingTime(Time.valueOf(LocalTime.now()));
                booking.setPaymentId(orderDto.getPaymentId());
                QrCode code = new QrCode();
                code.setQrId(String.valueOf(Instant.now().toEpochMilli()) + booking.getUsers().getMobileNo());
                code.setQrUrl(qrCodeGenerator(code.getQrId()));
                booking.setQrCode(code);
                bookingRepo.save(booking);
                orderUpdateResponseDto.setPlaceResponseDto(placeService.getPlaceById(booking.getPlace().getPlaceId()));
                orderUpdateResponseDto.setDetailsResponseDto(bookingDetailsDtoConvertor(booking));
                orderUpdateResponseDto.setQrResponseDto(new QrResponseDto(code.getQrUrl()));
                return orderUpdateResponseDto;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String qrCodeGenerator(String qrId) {

        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(qrId, BarcodeFormat.QR_CODE, 200, 200);
            ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
            MatrixToImageConfig con = new MatrixToImageConfig(0xFF000002, 0xFFFFC041);
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream, con);
            byte[] pngData = pngOutputStream.toByteArray();
            return AwsService.uploadimage(qrId, pngData);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private BookingDetailsResponseDto bookingDetailsDtoConvertor(Booking booking) {
        BookingDetailsResponseDto bookingDetailsResponseDto = new BookingDetailsResponseDto();
        bookingDetailsResponseDto.setAmount(booking.getAmount());
        bookingDetailsResponseDto.setBookingDate(booking.getBookingDate());
        bookingDetailsResponseDto.setBookingTime(booking.getBookingTime());
        bookingDetailsResponseDto.setVisitDate(booking.getVisitDate());
        bookingDetailsResponseDto.setOrderId(booking.getOrderId());
        bookingDetailsResponseDto.setPaymentId(booking.getPaymentId());
        bookingDetailsResponseDto.setChildQnt(booking.getChildQnt());
        bookingDetailsResponseDto.setAdultQnt(booking.getAdultQnt());
        bookingDetailsResponseDto.setBookingId(booking.getBookingId());
        bookingDetailsResponseDto.setPlaceResponseDto(placeService.getPlaceById(booking.getPlace().getPlaceId()));
        bookingDetailsResponseDto.setCancelled(booking.isCancelled());
        bookingDetailsResponseDto.setCompleted(booking.getPaymentId() != null);
        if(booking.getQrCode() != null) bookingDetailsResponseDto.setQrResponseDto(new QrResponseDto(booking.getQrCode().getQrUrl()));
        DateTimeFormatter format = new DateTimeFormatterBuilder()
                .appendPattern("dd-MM-yyyy")
                .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
                .parseDefaulting(ChronoField.MILLI_OF_SECOND, 0)
                .toFormatter();
        String bookingDate = booking.getVisitDate();
        if(LocalDate.now().isBefore(LocalDate.parse(bookingDate, format))) {
            bookingDetailsResponseDto.setCanCancel(true);
        }
        
        return bookingDetailsResponseDto;
    }

    @Override
    public OrderCancelResponseDto orderCancelRequest(OrderCancellationDto cancellationDto, UserDetails userDetails) {

        try {
            Optional<Booking> booking = bookingRepo.findById(cancellationDto.getBookingId());

            if (booking.isPresent()) {
                Booking booking2 = booking.get();
                Place place = booking2.getPlace();
                if (userDetails.getUsername().equals(booking2.getUsers().getMobileNo())
                        && booking2.getAdultQnt() >= cancellationDto.getAdultQnt()
                        && booking2.getChildQnt() >= cancellationDto.getChildQnt()) {
                    CancelOrder cancelOrder = new CancelOrder();
                    cancelOrder.setAdultQnt(cancellationDto.getAdultQnt());
                    cancelOrder.setChildQnt(cancellationDto.getChildQnt());
                    cancelOrder.setStatus("In Progress");
                    if (cancellationDto.getRefundMode().equals("Account")) {
                        cancelOrder.setAccountNo(cancellationDto.getAccountNo());
                        cancelOrder.setIfscCode(cancellationDto.getIfscCode());
                    } else {
                        cancelOrder.setUpiId(cancellationDto.getUpiId());
                    }
                    cancelOrder.setBooking(booking2);
                    cancelOrder.setUsers(usersService.getUserByMobile(userDetails.getUsername()));
                    Double refundAmount = (Double.valueOf(cancellationDto.getAdultQnt())
                            * Double.valueOf(place.getPriceAdult()))
                            + (Double.valueOf(cancellationDto.getChildQnt()) * Double.valueOf(place.getPriceChild()))
                            - Double.valueOf((cancellationDto.getAdultQnt() + cancellationDto.getChildQnt()) * 10);

                    booking2.setAdultQnt(booking2.getAdultQnt() - cancellationDto.getAdultQnt());
                    booking2.setChildQnt(booking2.getChildQnt() - cancellationDto.getChildQnt());
                    booking2.setAmount(String.valueOf(Double.valueOf(booking2.getAmount())
                            - (Double.valueOf(cancellationDto.getAdultQnt()) * Double.valueOf(place.getPriceAdult()))
                            + (Double.valueOf(cancellationDto.getChildQnt()) * Double.valueOf(place.getPriceChild()))));
                    
                    if(booking2.getAdultQnt() + booking2.getChildQnt() == 0) {
                        booking2.setCancelled(true);
                    }
                    
                    bookingRepo.save(booking2);

                    cancelOrder.setRefundMode(cancellationDto.getRefundMode());
                    cancelOrder.setRefundAmount(String.valueOf(refundAmount));
                    cancelOrder.setCancelCharge(String.valueOf(
                            Double.valueOf((cancellationDto.getAdultQnt() + cancellationDto.getChildQnt()) * 10)));

                    cancelRepo.save(cancelOrder);

                    return calcelOrderDtoConvertor(cancelOrder);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private OrderCancelResponseDto calcelOrderDtoConvertor(CancelOrder cancelOrder) {
        OrderCancelResponseDto cancelResponseDto = new OrderCancelResponseDto();
        cancelResponseDto.setRefundAmount(String.valueOf(cancelOrder.getRefundAmount()));
        cancelResponseDto.setAccountNumber(cancelOrder.getAccountNo());
        cancelResponseDto.setAdultQntCancel(String.valueOf(cancelOrder.getAdultQnt()));
        cancelResponseDto.setChildQntCancel(String.valueOf(cancelOrder.getChildQnt()));
        cancelResponseDto.setIfscCode(cancelOrder.getIfscCode());
        cancelResponseDto.setRefundMode(cancelOrder.getRefundMode());
        cancelResponseDto.setUpiId(cancelOrder.getUpiId());
        cancelResponseDto.setCancelCharge(cancelOrder.getCancelCharge());
        cancelResponseDto.setCancelId(cancelOrder.getCancelId());
        cancelResponseDto.setStatus(cancelOrder.getStatus());
        cancelResponseDto.setBookingId(cancelOrder.getBooking().getBookingId());
        return cancelResponseDto;
    }

    @Override
    public List<BookingDetailsResponseDto> getAllUserOrders(String username) {

        List<BookingDetailsResponseDto> bookingDetailsResponseDtos = new ArrayList<>();

        try {
            List<Booking> bookings = bookingRepo.getUserOrders(username);

            for (Booking booking : bookings) {
                bookingDetailsResponseDtos.add(bookingDetailsDtoConvertor(booking));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bookingDetailsResponseDtos;
    }

    @Override
    public List<OrderCancelResponseDto> getAllCancelOreders(String username) {

        List<OrderCancelResponseDto> cancelResponseDtos = new ArrayList<>();
        try {
            Users users = usersService.getUserByMobile(username);
            List<CancelOrder> cancelOrders = cancelRepo.findByusers(users);

            for (CancelOrder cancelOrder : cancelOrders) {
                cancelResponseDtos.add(calcelOrderDtoConvertor(cancelOrder));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cancelResponseDtos;
    }

    @Override
    public BookingDetailsResponse getDetails(String bookingId) {

        BookingDetailsResponse bookingDetailsResponse = new BookingDetailsResponse();

        Optional<Booking> booking = bookingRepo.findByQRId(bookingId);

        if (booking.isPresent()) {
            Booking booking2 = booking.get();
            bookingDetailsResponse.setAdultQnt(booking2.getAdultQnt());
            bookingDetailsResponse.setAmountPaid(booking2.getAmount());
            bookingDetailsResponse.setBookingId(booking2.getBookingId()+"");
            bookingDetailsResponse.setChildQnt(booking2.getChildQnt());
            bookingDetailsResponse.setPaymentId(booking2.getPaymentId());
            bookingDetailsResponse.setTotalQnt(booking2.getAdultQnt() + booking2.getChildQnt());
            bookingDetailsResponse.setVisitDate(booking2.getVisitDate());
            bookingDetailsResponse.setVisited(booking2.isVisited());
            return bookingDetailsResponse;
        }

        return null;
    }

    @Override
    public boolean approveTicket(String bookingId) {
        try {
            Optional<Booking> booking = bookingRepo.findById(Long.valueOf(bookingId));
            if (booking.isPresent()) {
                booking.get().setVisited(true);
            }
            bookingRepo.save(booking.get());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public OrderReviewDto getBooKingInfo(String bookingId) {
        OrderReviewDto dto = new OrderReviewDto();
        Optional<Booking> booking =  bookingRepo.findById(Long.valueOf(bookingId));
        if(booking.isPresent()) {
            BookingDetailsResponseDto bookingDetailsResponse = bookingDetailsDtoConvertor(booking.get());
            PlaceResponseDto placeResponseDto = convertorPlaceDto(booking.get().getPlace());
            dto.setDetailsResponseDto(bookingDetailsResponse);
            dto.setPlaceResponseDto(placeResponseDto);
            return dto;
        }
        return null;
    }
    
    private PlaceResponseDto convertorPlaceDto(Place place) {
        PlaceResponseDto placeResponseDto = new PlaceResponseDto();
        placeResponseDto.setCity(place.getCity());
        placeResponseDto.setEndTime(place.getEndTime());
        placeResponseDto.setLatitude(place.getLatitude());
        placeResponseDto.setLongitude(place.getLongitude());
        placeResponseDto.setPicUrl(place.getPicUrl());
        placeResponseDto.setPlaceDescription(place.getPlaceDescription());
        placeResponseDto.setPlaceId(place.getPlaceId());
        placeResponseDto.setPlaceName(place.getPlaceName());
        placeResponseDto.setPriceAdult(place.getPriceAdult());
        placeResponseDto.setPriceChild(place.getPriceChild());
        placeResponseDto.setStartTime(place.getStartTime());
        placeResponseDto.setState(place.getState());
        return placeResponseDto;
    }

    @Override
    public OrderUpdateResponseDto getBookingReceit(String bookingId) {
        OrderUpdateResponseDto dto = new OrderUpdateResponseDto();
        Optional<Booking> booking = bookingRepo.findById(Long.valueOf(bookingId));
        if(booking.isPresent()) {
            QrResponseDto dto2 = new QrResponseDto();
            dto2.setQrUrl(booking.get().getQrCode().getQrUrl());
            dto.setPlaceResponseDto(placeService.getPlaceById(booking.get().getPlace().getPlaceId()));
            dto.setDetailsResponseDto(bookingDetailsDtoConvertor(booking.get()));
            dto.setQrResponseDto(dto2);
            return dto;
        }
        return null;
    }

    @Override
    public Long getAllBookings(VisitorRequestDto requestDto) {
        List<Booking> bookings =  bookingRepo.getAllBooking(Long.valueOf(requestDto.getPlaceId()),requestDto.getVisitdate());
        return Long.valueOf(bookings.size());
    }

}

























