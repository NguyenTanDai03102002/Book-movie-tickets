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


    @Query("SELECT new vnpt.movie_booking_be.dto.response.TicketResponse(u.name, t.movie.id, m.title,t.qrcode, t.screening.id, sc.date, sc.start, a.id, a.name, t.total, s.row_Seat, s.number_Seat) " +
            "FROM Ticket t " +
            "JOIN t.user u " +
            "JOIN t.movie m " +
            "JOIN t.seats s " +
            "JOIN t.screening sc " +
            "JOIN sc.auditorium a " +
            "WHERE u.id = :userId")
    List<TicketResponse> findUserTickets(@Param("userId") int userId);


}
