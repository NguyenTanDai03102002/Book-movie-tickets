package vnpt.movie_booking_be.controller;

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

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/getTotalByYear")
    public List<TicketResponse> getTicketByYear(@RequestParam int year) {
        return ticketService.getTicketByYear(year);
    }
}
