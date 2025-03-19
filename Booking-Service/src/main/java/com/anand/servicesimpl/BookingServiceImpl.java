package com.anand.servicesimpl;

import com.anand.domain.BookingStatus;
import com.anand.entity.Booking;
import com.anand.entity.OnlineAppReport;
import com.anand.payload.BookingRequest;
import com.anand.payload.OnlineAppDto;
import com.anand.payload.ServiceDto;
import com.anand.payload.UserDto;
import com.anand.repository.BookingRepository;
import com.anand.services.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    @Override
    public Booking createBooking(BookingRequest bookingRequest,
                                 UserDto userDto,
                                 OnlineAppDto onlineAppDto,
                                 Set<ServiceDto> serviceDto) {
//        int totalDuration=
        return null;
    }

    @Override
    public List<Booking> getBookingsByCustomerID(Long customerId) {
        return List.of();
    }

    @Override
    public List<Booking> getBookingsByOnlineAppId(Long onlineAppId) {
        return List.of();
    }

    @Override
    public Booking getBookingById(Long id) {
        return null;
    }

    @Override
    public Booking updateBookingById(BookingStatus bookingStatus, Long id) {
        return null;
    }

    @Override
    public List<Booking> getBookingsByDate(LocalDateTime localDateTime, Long onlineAPpId) {
        return List.of();
    }

    @Override
    public OnlineAppReport getOnlineAppReport(Long onlineAppId) {
        return null;
    }
}
