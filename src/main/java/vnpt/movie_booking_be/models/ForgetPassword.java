package vnpt.movie_booking_be.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"user"})
@ToString(exclude = {"user"})
@Builder
public class ForgetPassword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int otp;

    private Date expirationTime;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
}
