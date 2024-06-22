package vnpt.movie_booking_be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vnpt.movie_booking_be.models.New;

public interface NewRepository extends JpaRepository<New,Integer> {
}
