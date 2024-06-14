package vnpt.movie_booking_be;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;
import vnpt.movie_booking_be.models.*;
import vnpt.movie_booking_be.repository.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class MovieBookingBeApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieBookingBeApplication.class, args);
    }

}
