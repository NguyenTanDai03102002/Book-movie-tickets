package vnpt.movie_booking_be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vnpt.movie_booking_be.models.Cinema;
import vnpt.movie_booking_be.models.Factor;

import java.util.List;

public interface FactorRepository extends JpaRepository<Factor,Integer> {
    List<Factor> findFactorByCinema(Cinema cinema);
}
