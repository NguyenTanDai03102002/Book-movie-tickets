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

<<<<<<< HEAD
=======
//    @Bean
//        CommandLineRunner run(RoleRepository roleRepository, MembershipRepository membershipRepository,
//                              GenreRepository genreRepository, MovieRepository movieRepository, UserRepository userRepository){
//        return args -> {
//            if(roleRepository.findByName("ADMIN") != null){
//                return;
//            }
//            Role adminRole = Role.builder()
//                    .name("ADMIN")
//                    .build();
//            adminRole = roleRepository.save(adminRole);
//
//
////            Role roleadmin = roleRepository.save(new Role("ADMIN"));
////            Role roleUser =roleRepository.save(new Role("USER"));
//
//            Set<Role> roles = new HashSet<>();
//            roles.add(adminRole);
//
//            userRepository.save(User.builder()
//                            .name("dai")
//                            .email("dai@gmail.com")
//                            .phone("302932")
//                            .password("123456")
//                            .roles(roles)
//                    .build());
////            roles.add(roleUser);
////            User user = new User(1,"dai","abc","032321","123",roles);
////            userRepository.save(user);
//
//            membershipRepository.save(Membership.builder()
//                    .name("Đồng")
//                    .description("Đồng")
//                    .discount_rate(0.0f)
//                    .build());
//            membershipRepository.save(Membership.builder()
//                    .name("Bạc")
//                    .description("Bạc")
//                    .discount_rate(0.05f)
//                    .build());
//            membershipRepository.save(Membership.builder()
//                    .name("Vàng")
//                    .description("vàng")
//                    .discount_rate(0.1f)
//                    .build());
//
//            // Tạo và lưu các đối tượng Genre với id
//            Genre actionGenre = genreRepository.save(Genre.builder().id(1).name("Hành động").build());
//            Genre adventureGenre = genreRepository.save(Genre.builder().id(2).name("Phiêu lưu").build());
//            genreRepository.save(Genre.builder().id(3).name("Hoạt hình").build());
//            genreRepository.save(Genre.builder().id(4).name("Bí ẩn").build());
//            genreRepository.save(Genre.builder().id(5).name("Hài hước").build());
//            genreRepository.save(Genre.builder().id(6).name("Tội phạm").build());
//            genreRepository.save(Genre.builder().id(7).name("Gia đình").build());
//            genreRepository.save(Genre.builder().id(8).name("Fantasia").build());
//            genreRepository.save(Genre.builder().id(9).name("Kinh dị").build());
//            genreRepository.save(Genre.builder().id(10).name("Lãng mạn").build());
//            genreRepository.save(Genre.builder().id(11).name("Khoa học viễn tưởng").build());
//            genreRepository.save(Genre.builder().id(12).name("Thần thoại").build());
//            genreRepository.save(Genre.builder().id(13).name("Thể thao").build());
//            genreRepository.save(Genre.builder().id(14).name("Chiến tranh").build());
//            genreRepository.save(Genre.builder().id(15).name("Xã hội").build());
//            genreRepository.save(Genre.builder().id(16).name("Tâm lý").build());
//            genreRepository.save(Genre.builder().id(17).name("Trinh thám").build());
//            genreRepository.save(Genre.builder().id(18).name("Thám hiểm").build());
//            genreRepository.save(Genre.builder().id(19).name("Võ thuật").build());
//
//
//                    Set<String> casts = new HashSet<>();
//            casts.add("Sandra Bullock");
//            casts.add("Channing Tatum");
//            casts.add("Daniel Radcliffe");
//
//            // Sử dụng các đối tượng Genre đã tạo và lưu trước đó
//            Set<Genre> genreSet = new HashSet<>();
//            genreSet.add(actionGenre);
//            genreSet.add(adventureGenre);
//
//            Movie movie = new Movie(1,"The Lost City","Bộ phim hành động hay The Lost City gây ấn tượng với khán giả nhờ vào khung cảnh thiên nhiên" +
//                    " hùng vĩ và những màn hành động đầy ấn tượng. Câu chuyện xoay quanh vị" +
//                    " tác giả tài năng Loretta Sage, người đã dành cả cuộc đời của mình để khám" +
//                    " phá những địa điểm kỳ lạ và viết sách. Trong chuyến quảng bá sách của mình," +
//                    " cô bị một tỷ phú kỳ quái bắt cóc và ép buộc chỉ đường đến một thành phố bí ẩn," +
//                    " nơi ẩn chứa một kho báu.","Aaron và Adam Nee",casts,112,genreSet);
//
////            Movie movie1 = Movie.builder()
////                    .title("The Lost City")
////                    .description("Bộ phim hành động hay The Lost City gây ấn tượng với khán giả nhờ vào khung cảnh thiên nhiên" +
////                            " hùng vĩ và những màn hành động đầy ấn tượng. Câu chuyện xoay quanh vị" +
////                            " tác giả tài năng Loretta Sage, người đã dành cả cuộc đời của mình để khám" +
////                            " phá những địa điểm kỳ lạ và viết sách. Trong chuyến quảng bá sách của mình," +
////                            " cô bị một tỷ phú kỳ quái bắt cóc và ép buộc chỉ đường đến một thành phố bí ẩn," +
////                            " nơi ẩn chứa một kho báu.")
////                    .director("Aaron và Adam Nee")
////                    .casts(casts)
////                    .duration(112)
////                    .genres(genreSet)
////                    .build();
//            movieRepository.save(movie);
////            movieRepository.save(movie1);
//        };
//    }
>>>>>>> 5b16deb64ee75b07ddf34a11fcbd5bef5619ff79
}
