package vnpt.movie_booking_be.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vnpt.movie_booking_be.models.Genre;


public interface GenreRepository extends JpaRepository<Genre,Integer> {
    Genre findByName(String name);

    @Query(value = "SELECT * FROM genre g WHERE LOWER(g.name) LIKE LOWER(CONCAT(?1,'%'))",nativeQuery = true)
    Page<Genre> findByKeyword(String keyword, Pageable pageable);
}
