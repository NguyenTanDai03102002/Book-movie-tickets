package vnpt.movie_booking_be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vnpt.movie_booking_be.models.Auditorium;
import vnpt.movie_booking_be.models.Cinema;

import java.util.List;

public interface AuditoriumRepository extends JpaRepository<Auditorium, Integer> {

    @Query(value = "SELECT * FROM Auditorium WHERE cinema_id = ?1 AND name = ?2",nativeQuery = true)
    Auditorium findByNameOfCinema(int cinemaId, String name);
    List<Auditorium> findAuditoriumsByCinema(Cinema cinema);
    @Query("SELECT s.name FROM Auditorium s WHERE s.id = ?1")
    String findAuditoriumIdById(int seatId);
}
