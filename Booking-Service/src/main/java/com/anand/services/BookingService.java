package com.anand.services;

import com.anand.domain.BookingStatus;
import com.anand.entity.OnlineAppReport;
import com.anand.payload.BookingRequest;
import com.anand.entity.Booking;
import com.anand.payload.OnlineAppDto;
import com.anand.payload.ServiceDto;
import com.anand.payload.UserDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface BookingService {
    Booking createBooking(BookingRequest bookingRequest, UserDto userDto, OnlineAppDto onlineAppDto, Set<ServiceDto> serviceDto) throws Exception;
    List<Booking> getBookingsByCustomerID(Long customerId);
    List<Booking> getBookingsByOnlineAppId(Long onlineAppId);
    Booking getBookingById(Long id) throws Exception;
    Booking updateBookingById(BookingStatus bookingStatus, Long id) throws Exception;
    List<Booking> getBookingsByDate(LocalDate localDateTime, Long onlineAPpId) throws Exception;
    OnlineAppReport getOnlineAppReport(Long onlineAppId);


}
