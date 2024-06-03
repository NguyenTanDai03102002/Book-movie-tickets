package vnpt.movie_booking_be.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vnpt.movie_booking_be.dto.response.SeatResponse;
import vnpt.movie_booking_be.mapper.SeatMapper;
import vnpt.movie_booking_be.models.Auditorium;
import vnpt.movie_booking_be.models.Seat;
import vnpt.movie_booking_be.repository.AuditoriumRepository;
import vnpt.movie_booking_be.repository.SeatRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeatServiceImpl implements SeatService{
    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private AuditoriumRepository auditoriumRepository;

    @Autowired
    private SeatMapper seatMapper;

    @Override
    public List<SeatResponse> getSeatsById(int cinemaId) {
        Auditorium auditorium = auditoriumRepository.findById(cinemaId).orElseThrow(()->new RuntimeException("Auditorium not found"));
        List<Seat> seatList = seatRepository.findSeatsByAuditorium(auditorium);
        return seatList.stream().map(seat -> seatMapper.toSeatResponse(seat)).collect(Collectors.toList());
    }
}
