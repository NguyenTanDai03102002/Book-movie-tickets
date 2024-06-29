package vnpt.movie_booking_be.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vnpt.movie_booking_be.dto.response.MovieTotalResponse;
import vnpt.movie_booking_be.dto.response.TicketTotalResponse;
import vnpt.movie_booking_be.mapper.MovieMapper;
import vnpt.movie_booking_be.models.Movie;
import vnpt.movie_booking_be.models.Ticket;
import vnpt.movie_booking_be.repository.TicketRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/profit")
@CrossOrigin("*")
public class ProfitController {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private MovieMapper movieMapper;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/total-by-movie")
    public List<MovieTotalResponse> getTotalAmountGroupedByMovie() {
        List<Object[]> results = ticketRepository.getTotalAmountGroupedByMovie();
        return results.stream()
                .map(result -> new MovieTotalResponse(movieMapper.movieToMovieResponse((Movie) result[0]), (Long) result[1]))
                .collect(Collectors.toList());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/ticket-total")
    public List<TicketTotalResponse> getTicketTotal(@RequestParam int cinemaId,@RequestParam int year){
        List<Ticket> ticketList = ticketRepository.getTicketTotal(cinemaId,year);
        return ticketList.stream()
                .map(ticket -> new TicketTotalResponse(ticket.getId(),ticket.getTotal(),ticket.getOrderTime()))
                .collect(Collectors.toList());
    }

}
