package vnpt.movie_booking_be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vnpt.movie_booking_be.models.Screening;

import java.util.List;

public interface ScreeningRepository extends JpaRepository<Screening,Integer> {
    @Query(value = "SELECT s.* FROM screening s JOIN auditorium a ON s.auditorium_id = a.id " +
            "JOIN cinema c ON a.cinema_id = c.id JOIN address ar ON c.address_id = ar.id " +
            "WHERE ar.city = ?1 AND s.movie_id = ?2",nativeQuery = true)
    List<Screening> findScreeningsByCityAndMovie(String city, int movieId);
}
