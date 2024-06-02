package vnpt.movie_booking_be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vnpt.movie_booking_be.models.Auditorium;
import vnpt.movie_booking_be.models.Seat;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat,Integer> {
    List<Seat> findSeatsByAuditorium(Auditorium auditorium);
}
