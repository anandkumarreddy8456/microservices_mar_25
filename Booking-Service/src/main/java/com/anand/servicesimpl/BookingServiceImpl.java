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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    @Override
    public Booking createBooking(BookingRequest bookingRequest,
                                 UserDto userDto,
                                 OnlineAppDto onlineAppDto,
                                 Set<ServiceDto> serviceDto) throws Exception {
        int totalDuration=serviceDto.stream().mapToInt(ServiceDto::getDuration).sum();
        LocalDateTime startTime=bookingRequest.getStartTime();
        LocalDateTime endTime=startTime.plusMinutes(totalDuration);
        Boolean istime=isTimeSlotAvailable(onlineAppDto,startTime,endTime);
        int totalAmount=serviceDto.stream().mapToInt(ServiceDto::getPrice).sum();
        Set<Long> longSet=serviceDto.stream().map(ServiceDto::getId).collect(Collectors.toSet());

        Booking booking=new Booking();
        booking.setCustomerId(userDto.getId());
        booking.setOnlineAppId(onlineAppDto.getId());
        booking.setServiceIds(longSet);
        booking.setBookingStatus(BookingStatus.PENDING);
        booking.setStartTime(startTime);
        booking.setEndTime(endTime);
        booking.setTotalPrice(totalAmount);
        return bookingRepository.save(booking);
    }

    @Override
    public List<Booking> getBookingsByCustomerID(Long customerId) {
        return bookingRepository.findByCustomerId(customerId);
    }
    public Boolean isTimeSlotAvailable(OnlineAppDto onlineAppDto,LocalDateTime bookingStartTime,LocalDateTime bookingEndTIme) throws Exception {
        List<Booking> existingBookings=getBookingsByOnlineAppId(onlineAppDto.getId());
        LocalDateTime openTime=onlineAppDto.getOpenTime().atDate(bookingStartTime.toLocalDate());
        LocalDateTime closeTime=onlineAppDto.getCloseTime().atDate(bookingEndTIme.toLocalDate());
        if(bookingStartTime.isBefore(openTime) || bookingEndTIme.isAfter(closeTime)){
            throw  new Exception(" Booking time must be within the time");

        }
        for(Booking existngBooking:existingBookings){
            LocalDateTime existingBookingStartTime=existngBooking.getStartTime();
            LocalDateTime existingBookingEndTime=existngBooking.getStartTime();
            if(bookingStartTime.isBefore(existingBookingEndTime) && bookingEndTIme.isAfter(existingBookingStartTime) ){
                throw new Exception("slot not Available, Choose Different time.");
            }
            if(bookingStartTime.isEqual(existingBookingStartTime) || bookingEndTIme.isEqual(existingBookingEndTime)){
                throw new Exception("slot not Available, Choose Different time.");

            }
        }
        return true;

    }
    @Override
    public List<Booking> getBookingsByOnlineAppId(Long onlineAppId) {
        return bookingRepository.findByOnlineAppId(onlineAppId);
    }

    @Override
    public Booking getBookingById(Long id) throws Exception {
        Booking booking=bookingRepository.findById(id).orElse(null);
        if(booking == null){
            throw new Exception("Booking  not Found");
        }
        return booking;
    }

    @Override
    public Booking updateBookingById( Long id,BookingStatus bookingStatus) throws Exception {
        Booking booking=getBookingById(id);
        booking.setBookingStatus(bookingStatus);
        return booking;
    }

    @Override
    public List<Booking> getBookingsByDate(LocalDate localDateTime, Long onlineAPpId)  {
        List<Booking> bookingList=getBookingsByOnlineAppId(onlineAPpId);
        if(localDateTime == null){
            return bookingList;
        }
        return bookingList.stream().filter(booking -> isSameDate(booking.getStartTime(),localDateTime) || isSameDate(booking.getEndTime(),localDateTime)).collect(Collectors.toList());
    }

    private boolean isSameDate(LocalDateTime startTime, LocalDate localDateTime) {
        return startTime.toLocalDate().isEqual(localDateTime);
    }

    @Override
    public OnlineAppReport getOnlineAppReport(Long onlineAppId) {
        List<Booking> bookingList=getBookingsByOnlineAppId(onlineAppId);
        Double totalEarnings=bookingList.stream().mapToDouble(Booking::getTotalPrice).sum();
        int totalBooking=bookingList.size();
        List<Booking> cancelBooking=bookingList.stream().filter(booking -> booking.getBookingStatus().equals(BookingStatus.CANCELLED)).toList();
        double totalRefund=bookingList.stream().mapToDouble(Booking::getTotalPrice).sum();
        OnlineAppReport report=new OnlineAppReport();
        report.setOnlineAppId(onlineAppId);
        report.setCancelBookings(cancelBooking.size());
        report.setTotalBookings(totalBooking);
        report.setTotalEarnings(totalEarnings);
        report.setTotalRefund(totalRefund);
        return report;
    }
}
