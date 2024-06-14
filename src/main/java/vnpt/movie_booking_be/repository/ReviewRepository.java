package vnpt.movie_booking_be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vnpt.movie_booking_be.models.Movie;
import vnpt.movie_booking_be.models.Review;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findByMovie(Movie movie);
}
