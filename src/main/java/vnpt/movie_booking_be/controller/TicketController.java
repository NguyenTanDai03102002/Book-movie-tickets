package vnpt.movie_booking_be.controller;

<<<<<<< HEAD
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import vnpt.movie_booking_be.dto.request.TicketRequest;
import vnpt.movie_booking_be.dto.response.TicketResponse;
import vnpt.movie_booking_be.models.Ticket;
import vnpt.movie_booking_be.repository.SeatRepository;
import vnpt.movie_booking_be.service.VNPayServiceimpl;
import vnpt.movie_booking_be.service.TicketService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@CrossOrigin("*")
@RestController
public class TicketController {

    private TicketService ticketService;
    private VNPayServiceimpl vnPayService;
    @Autowired
            private SeatRepository seatRepository;

@GetMapping("/getticketbyusser")
public ResponseEntity<List<TicketResponse>> getUserTickets(@RequestParam("userid") int userId) {
    List<TicketResponse> ticketResponses = vnPayService.getUserTickets(userId);
    if (ticketResponses.isEmpty()) {
        return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(ticketResponses);
}
=======
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vnpt.movie_booking_be.dto.response.TicketResponse;
import vnpt.movie_booking_be.service.TicketService;

import java.util.List;

@RestController
@RequestMapping("/ticket")
@CrossOrigin("*")
public class TicketController {

    @Autowired
    private TicketService ticketService;

>>>>>>> 5b16deb64ee75b07ddf34a11fcbd5bef5619ff79
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/getTotalByYear")
    public List<TicketResponse> getTicketByYear(@RequestParam int year) {
        return ticketService.getTicketByYear(year);
    }
}
