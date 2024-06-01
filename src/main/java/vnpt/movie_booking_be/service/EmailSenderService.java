package vnpt.movie_booking_be.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String to, String subject, String body) {
        try{
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom("KichHoatTaiKhoann@gmail.com");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body,true);

            mailSender.send(message);
        }catch (Exception e) {
            throw new RuntimeException(e.getCause().toString());
        }

    }
}
