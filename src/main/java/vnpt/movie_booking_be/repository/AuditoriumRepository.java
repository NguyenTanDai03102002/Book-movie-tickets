package vnpt.movie_booking_be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD
import org.springframework.data.jpa.repository.Query;
=======
>>>>>>> 5b16deb64ee75b07ddf34a11fcbd5bef5619ff79
import vnpt.movie_booking_be.models.Auditorium;
import vnpt.movie_booking_be.models.Cinema;

import java.util.List;

public interface AuditoriumRepository extends JpaRepository<Auditorium, Integer> {
    Auditorium findByName(String name);
    List<Auditorium> findAuditoriumsByCinema(Cinema cinema);
<<<<<<< HEAD
    @Query("SELECT s.name FROM Auditorium s WHERE s.id = ?1")
    String findAuditoriumIdById(int seatId);
=======
>>>>>>> 5b16deb64ee75b07ddf34a11fcbd5bef5619ff79
}
