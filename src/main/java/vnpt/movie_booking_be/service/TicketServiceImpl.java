package vnpt.movie_booking_be.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vnpt.movie_booking_be.dto.response.TicketResponse;
import vnpt.movie_booking_be.mapper.TicketMapper;
import vnpt.movie_booking_be.models.Ticket;
import vnpt.movie_booking_be.repository.TicketRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService{

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private TicketMapper ticketMapper;

    @Override
    public List<TicketResponse> getTicketByYear(int year) {
        List<Ticket> ticketList = ticketRepository.findAllByYear(year);
        return ticketList.stream().map(ticket -> ticketMapper.toTicketResponse(ticket))
                .collect(Collectors.toList());
    }
    @Override
    public List<TicketResponse> getAllTickets() {
        List<Ticket> tickets = ticketRepository.findAll();
        return tickets.stream()
                .map(ticketMapper::toTicketResponse)
                .collect(Collectors.toList());
    }
}
