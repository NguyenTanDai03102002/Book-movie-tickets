package vnpt.movie_booking_be.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vnpt.movie_booking_be.dto.response.TicketResponse;
import vnpt.movie_booking_be.models.Ticket;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    @Query("SELECT t.total FROM Ticket t WHERE t.id = :ticketId")
    Integer findTotalByTicketId(@Param("ticketId") int ticketId);

    @Query("SELECT t.orderTime FROM Ticket t WHERE t.id = :ticketId")
    Date findOrderTimeByTicketId(@Param("ticketId") int ticketId);

    @Query("SELECT t.status FROM Ticket t WHERE t.id = :ticketId")
    Integer findStatusByTicketId(@Param("ticketId") int ticketId);

    @Query("SELECT t.user.id FROM Ticket t WHERE t.id = :ticketId")
    Integer findUserIdByTicketId(@Param("ticketId") int ticketId);

    @Query("SELECT t.movie.id FROM Ticket t WHERE t.id = :ticketId")
    Integer findMovieIdByTicketId(@Param("ticketId") int ticketId);

    @Query("SELECT t.screening.id FROM Ticket t WHERE t.id = :ticketId")
    Integer findScreeningIdByTicketId(@Param("ticketId") int ticketId);

    @Modifying
    @Transactional
    @Query("UPDATE Ticket t SET t.qrcode = :qr WHERE t.id = :ticketId")
    void updateQRCodeByTicketId(@Param("ticketId") int ticketId, @Param("qr") String qr);
    @Query("SELECT t FROM Ticket t WHERE t.user.id = :userId")
    List<Ticket> findByUserId(@Param("userId") int userId);

    @Query("select t.total  from Ticket t where t.id =:ticketTd ")
    Integer findTotalByTicketTd(@Param("ticketTd") Integer ticketTd);




    @Query(value = "SELECT * FROM Ticket WHERE YEAR(order_time) = ?1",nativeQuery = true)
    List<Ticket> findAllByYear(int year);

    @Query("SELECT s.movie, COALESCE(SUM(t.total), 0) FROM Screening s LEFT JOIN Ticket t ON s.id = t.screening.id GROUP BY s.movie")
    List<Object[]> getTotalAmountGroupedByMovie();

    @Query("SELECT t FROM Ticket t JOIN t.screening s JOIN s.auditorium a JOIN a.cinema c WHERE c.id = :cinemaId AND YEAR(s.date) = :year")
    List<Ticket> getTicketTotal(int cinemaId, int year);
}
