package vnpt.movie_booking_be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vnpt.movie_booking_be.models.Vourcher;

public interface VourcherRepository extends JpaRepository<Vourcher, Integer> {
}
