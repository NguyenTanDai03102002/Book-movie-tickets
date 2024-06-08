package vnpt.movie_booking_be.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vnpt.movie_booking_be.models.ForgetPassword;
import vnpt.movie_booking_be.models.User;
import vnpt.movie_booking_be.repository.ForgetPasswordRepository;
import vnpt.movie_booking_be.repository.UserRepository;

import java.beans.Encoder;
import java.time.Instant;
import java.util.Date;
import java.util.Random;

@Service
public class ForgetPasswordServiceImpl implements ForgetPasswordService{

    @Autowired
    private ForgetPasswordRepository forgetPasswordRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Integer otpGenerator(){
        Random rand = new Random();
        return rand.nextInt(100_000,999_999);
    }

    @Override
    public ResponseEntity<String> verifyMail(String email) {
        User user = userRepository.findByEmail(email);
        int otp = otpGenerator();
        if(user == null) {
            return ResponseEntity.badRequest().body("email not registered for account");
        }
        ForgetPassword forgetPassword = forgetPasswordRepository.findByUser(user);
        if(forgetPassword != null) {
            return ResponseEntity.badRequest().body("please check your email, otp sent");
        }else{
            forgetPasswordRepository.save(ForgetPassword.builder()
                            .otp(otp)
                            .expirationTime(new Date(System.currentTimeMillis() + 70 * 1000))
                            .user(user)
                    .build());
            Send(user,otp);
            return ResponseEntity.ok("OTP sent for verification");
        }
    }

    private void Send(User user,int otp){
        String subject = "OTP for Forget Password request";
        String content = "This is the OTP for your Forget password request : " + otp;

        emailSenderService.sendEmail(user.getEmail(), subject,content);
    }


    @Override
    public ResponseEntity<String> verifyOtp(Integer otp, String email) {
        User user = userRepository.findByEmail(email);
        if(user == null) {
            return ResponseEntity.badRequest().body("email not registered for account");
        }

        ForgetPassword forgetPassword = forgetPasswordRepository.findByOtpAndUser(otp,user.getId());
        if(forgetPassword != null) {
            if(forgetPassword.getExpirationTime().before(Date.from(Instant.now()))){
                forgetPasswordRepository.deleteById(forgetPassword.getId());
                return ResponseEntity.badRequest().body("OTP has expired");
            }
        }else{
            return ResponseEntity.badRequest().body("OTP is not correct");
        }


        return ResponseEntity.ok("OTP verified");
    }

    @Override
    public ResponseEntity<String> changePassword(String email, String newPassword) {
        User user = userRepository.findByEmail(email);
        if(user != null) {
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
            return ResponseEntity.ok("Password changed successfully");
        }else{
            return ResponseEntity.badRequest().body("user not found");
        }
    }

}
