package vnpt.movie_booking_be.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vnpt.movie_booking_be.service.ForgetPasswordService;

@RestController
@RequestMapping("/forget")
@CrossOrigin("*")
public class ForgetPasswordController {

    @Autowired
    private ForgetPasswordService forgetPasswordService;

    @PostMapping("/verifyMail")
    public ResponseEntity<String> verifyMail(@RequestParam String email) {
        return forgetPasswordService.verifyMail(email);
    }

    @PostMapping("/verifyOtp")
    public ResponseEntity<String> verifyOtp(@RequestParam Integer otp,@RequestParam String email) {
        return forgetPasswordService.verifyOtp(otp,email);
    }

    @PostMapping("/changePassword")
    public ResponseEntity<String> ChangePassword(@RequestParam String email,@RequestParam String newPassword) {
        return forgetPasswordService.changePassword(email,newPassword);
    }
}
