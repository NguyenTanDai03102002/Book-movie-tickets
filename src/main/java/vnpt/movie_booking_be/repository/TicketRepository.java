package vnpt.movie_booking_be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vnpt.movie_booking_be.models.Ticket;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    @Query(value = "SELECT * FROM Ticket WHERE YEAR(order_time) = ?1",nativeQuery = true)
    List<Ticket> findAllByYear(int year);
}
