package com.anand.controller;

import com.anand.domain.BookingStatus;
import com.anand.entity.Booking;
import com.anand.entity.OnlineAppReport;
import com.anand.mapper.BookingMapper;
import com.anand.payload.*;
import com.anand.services.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/booking")
public class BookingController {

    private  final BookingService bookingService;
    @PostMapping("/create")
    public ResponseEntity<Booking> createBooking(@RequestParam Long onlineAppId, @RequestBody BookingRequest booking) throws Exception {
        UserDto userDto=new UserDto();
        userDto.setId(1L);

        OnlineAppDto onlineAppDto=new OnlineAppDto();
        onlineAppDto.setId(onlineAppId);
        onlineAppDto.setOpenTime(LocalTime.of(10, 0)); ;
        onlineAppDto.setCloseTime(onlineAppDto.getOpenTime().plusHours(12));
        Set<ServiceDto> dtos=new HashSet<>();
        ServiceDto serviceDto1=new ServiceDto();
        serviceDto1.setId(1L);
        serviceDto1.setName("Watching");
        serviceDto1.setPrice(123);
        serviceDto1.setDuration(30);
        dtos.add(serviceDto1);
        return new ResponseEntity<>(bookingService.createBooking(booking,userDto,onlineAppDto,dtos), HttpStatus.CREATED);
    }
    @GetMapping("/getBookingByCustomer")
    public ResponseEntity<Set<BookingDto>> getBookingByCustomer(@RequestParam Long id){

        List<Booking> bookings=bookingService.getBookingsByCustomerID(id);

        return new ResponseEntity<>(getBookingDtos(bookings),HttpStatus.OK);
    }
    @GetMapping("/getBookingByOnline")
    public ResponseEntity<Set<BookingDto>> getBookingByOnline(@RequestParam Long id){

        List<Booking> bookings=bookingService.getBookingsByOnlineAppId(id);

        return new ResponseEntity<>(getBookingDtos(bookings),HttpStatus.OK);
    }

    private Set<BookingDto> getBookingDtos(List<Booking> bookings){
        return bookings.stream().map(BookingMapper::toBookingdto).collect(Collectors.toSet());
    }
    @GetMapping("/getBookingById")
    public ResponseEntity<BookingDto> getBookingById(@RequestParam Long id) throws Exception {
        return new ResponseEntity<>(BookingMapper.toBookingdto(bookingService.getBookingById(id)),HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<BookingDto> updateBookingStatus(@RequestParam Long id, @RequestParam BookingStatus bookingStatus) throws Exception {
        return new ResponseEntity<>(BookingMapper.toBookingdto(bookingService.updateBookingById(id,bookingStatus)),HttpStatus.OK);
    }
    @GetMapping("/getBookSlot")
    public ResponseEntity<List<BookingSlotDto>> getBookSlot(@RequestParam Long id, @RequestParam(required = false) LocalDate localDate) throws Exception {
        return new ResponseEntity<>(bookingService.getBookingsByDate(localDate, id).stream().map(booking -> {
                    BookingSlotDto bookingSlotDto = new BookingSlotDto();
                    bookingSlotDto.setStartTime(booking.getStartTime());
                    bookingSlotDto.setEndTime(booking.getEndTime());
                    return bookingSlotDto;
                }
        ).toList(),HttpStatus.OK);

    }
    @GetMapping("/bookingReport")
    public ResponseEntity<OnlineAppReport> getOnlineReport() throws Exception {

       return new ResponseEntity<>(bookingService.getOnlineAppReport(1L),HttpStatus.OK);

    }
}
