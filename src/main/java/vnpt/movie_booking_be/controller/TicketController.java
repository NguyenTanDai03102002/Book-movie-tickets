package vnpt.movie_booking_be.controller;

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
@Autowired
    private TicketService ticketService;



    @Autowired
    private VNPayServiceimpl vnpayservice;
  //  @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/tickets/GetAll")
    public ResponseEntity<List<TicketResponse>> getAllTickets() {
        List<TicketResponse> tickets = ticketService.getAllTickets();
        return ResponseEntity.ok(tickets);
    }
    @GetMapping("/TicketbyUserID/{userId}")
    public ResponseEntity<List<TicketResponse>> getTicketsByUserId(@PathVariable int userId) {
        List<TicketResponse> tickets = vnpayservice.getTicketsByUserId(userId);
        return ResponseEntity.ok(tickets);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/getTotalByYear")
    public List<TicketResponse> getTicketByYear(@RequestParam int year) {
        return ticketService.getTicketByYear(year);
    }
}
