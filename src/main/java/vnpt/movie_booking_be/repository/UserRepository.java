package vnpt.movie_booking_be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vnpt.movie_booking_be.models.Role;
import vnpt.movie_booking_be.models.User;

import java.util.List;

import java.time.LocalTime;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findByEmail(String email);

    User findByCode(String code);

    List<User> findByRolesContains(Role role);
    @Query(value = "SELECT s.name FROM User s WHERE s.id = ?1")
    String findusernameById(int id);

}
