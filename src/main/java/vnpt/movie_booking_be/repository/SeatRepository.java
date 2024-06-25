package vnpt.movie_booking_be.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import vnpt.movie_booking_be.dto.request.TicketRequest;
import vnpt.movie_booking_be.models.Auditorium;
import vnpt.movie_booking_be.models.Seat;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat,Integer> {
    List<Seat> findSeatsByAuditorium(Auditorium auditorium);

    @Query("SELECT s.number_Seat FROM Seat s WHERE s.id = ?1")
    Integer findNumberSeatById(int seatId);

    // Lấy ra hàng ghế từ ID ghế
    @Query("SELECT s.row_Seat FROM Seat s WHERE s.id = ?1")
    String findRowSeatById(int seatId);

    // Lấy ra ID của phòng chiếu từ ID ghế
    @Query("SELECT s.auditorium.id FROM Seat s WHERE s.id = ?1")
    Integer findAuditoriumIdById(int seatId);
    @Query("SELECT s.id FROM Seat s WHERE s.id = ?1")
    List<Integer> findSeatIdById(int seatId);

    List<Seat> findByAuditorium(Auditorium auditorium);


//    @Query("SELECT new vnpt.movie_booking_be.dto.request.TicketRequest(" +
//            "t.total, mov.title, scr.date, scr.start, scr.auditorium.name, CONCAT(seat.row_Seat, seat.number_Seat), usr.name)" +
//            " FROM Ticket t " +
//            " JOIN t.screening scr " +
//            " JOIN scr.auditorium aud " +
//            " JOIN t.movie mov " +
//            " JOIN t.user usr " +
//            " JOIN t.seats seat " +
//            " WHERE t.id = :ticketId AND t.user.id = :userId")
//    TicketRequest findTicketDetailsByTicketIdAndUserId(int ticketId, int userId);

}
