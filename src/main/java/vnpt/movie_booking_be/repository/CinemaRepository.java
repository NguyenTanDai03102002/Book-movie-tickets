package vnpt.movie_booking_be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vnpt.movie_booking_be.models.Cinema;

import java.util.List;

public interface CinemaRepository extends JpaRepository<Cinema, Integer> {
    Cinema findByName(String name);

    @Query(value = "SELECT c.* FROM cinema c JOIN address a ON c.address_id = a.id WHERE a.city = ?1",nativeQuery = true)
    List<Cinema> findCinemasByAddress(String city);
}
