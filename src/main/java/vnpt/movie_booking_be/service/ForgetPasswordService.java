package vnpt.movie_booking_be.service;


import org.springframework.http.ResponseEntity;

public interface ForgetPasswordService {
    ResponseEntity<String> verifyMail(String email);

    ResponseEntity<String> verifyOtp(Integer otp, String email);

    ResponseEntity<String> changePassword(String email, String newPassword);
}
