package vnpt.movie_booking_be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vnpt.movie_booking_be.models.ForgetPassword;
import vnpt.movie_booking_be.models.User;

public interface ForgetPasswordRepository extends JpaRepository<ForgetPassword, Integer> {
    ForgetPassword findByUser(User user);

    @Query(value = "SELECT * FROM forget_password WHERE otp = ?1 AND user_id = ?2", nativeQuery = true)
    ForgetPassword findByOtpAndUser(Integer otp, Integer userId);

}
