package vnpt.movie_booking_be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vnpt.movie_booking_be.models.Auditorium;
import vnpt.movie_booking_be.models.Screening;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ScreeningRepository extends JpaRepository<Screening,Integer> {

    @Query(value = "SELECT s.* FROM screening s JOIN auditorium a ON s.auditorium_id = a.id " +
            "JOIN cinema c ON c.id = a.cinema_id WHERE c.id = ?1" ,nativeQuery = true)
    List<Screening> findScreeningsByCinema(int cinemaId);


    @Query(value = "SELECT s.* FROM screening s JOIN auditorium a ON s.auditorium_id = a.id " +
            "JOIN cinema c ON a.cinema_id = c.id JOIN address ar ON c.address_id = ar.id " +
            "WHERE ar.city = ?1 AND s.movie_id = ?2",nativeQuery = true)
    List<Screening> findScreeningsByCityAndMovie(String city, int movieId);

    @Query(value = "SELECT s.date FROM Screening s WHERE s.id = ?1")
    LocalDate findDateById(int screeningId);

    @Query(value = "SELECT s.start FROM Screening s WHERE s.id = ?1")
    LocalTime findStartTimeById(int screeningId);


    List<Screening> findScreeningByAuditorium(Auditorium auditorium);
}
