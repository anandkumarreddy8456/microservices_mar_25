package com.anand.mapper;

import com.anand.entity.Booking;
import com.anand.payload.BookingDto;

public class BookingMapper {
    public static BookingDto toBookingdto(Booking booking){
        BookingDto bookingDto=new BookingDto();
        bookingDto.setId(booking.getId());
        bookingDto.setCustomerId(booking.getCustomerId());
        bookingDto.setBookingStatus(booking.getBookingStatus());
        bookingDto.setStartTime(booking.getStartTime());
        bookingDto.setEndTime(booking.getEndTime());
        bookingDto.setOnlineAppId(booking.getOnlineAppId());
        bookingDto.setServiceIds(booking.getServiceIds());
        return bookingDto;
    }
}
