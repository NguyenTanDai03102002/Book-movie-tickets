package vnpt.movie_booking_be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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
//    @Query("SELECT s.auditorium.id FROM Seat s WHERE s.id = ?1")
//    Integer findAuditoriumIdById(int seatId);
}
