package vnpt.movie_booking_be;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import vnpt.movie_booking_be.models.*;
import vnpt.movie_booking_be.repository.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Component
public class DataInitializer implements CommandLineRunner {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MembershipRepository membershipRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private CinemaRepository cinemaRepository;

    @Autowired
    private AuditoriumRepository auditoriumRepository;

    @Autowired
    private FactorRepository factorRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private ScreeningRepository screeningRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        if(roleRepository.findByName("ADMIN") != null){
                return;
        }

        //MEMBERSHIP
        membershipRepository.save(Membership.builder().name("Đồng").description("Đồng").discount_rate(0.0f).build());
        membershipRepository.save(Membership.builder().name("Bạc").description("Bạc").discount_rate(0.05f).build());
        membershipRepository.save(Membership.builder().name("Vàng").description("vàng").discount_rate(0.1f).build());

        //ROLE
        Role adminRole = roleRepository.save(Role.builder().name("ADMIN").build());
        Role userRole = roleRepository.save(Role.builder().name("USER").build());
        Set<Role> roles = new HashSet<>();
        roles.add(adminRole);
        roles.add(userRole);
        //ADMIN
        userRepository.save(User.builder().name("admin").email("admin@gmail.com").phone("0943946242")
                    .password(passwordEncoder.encode("admin")).enabled(true).code(null).roles(roles).build());

        //GENRE
        Genre actionGenre = genreRepository.save(Genre.builder().name("Hành động").build());
        Genre adventureGenre = genreRepository.save(Genre.builder().name("Phiêu lưu").build());
        genreRepository.save(Genre.builder().name("Hoạt hình").build());
        genreRepository.save(Genre.builder().name("Bí ẩn").build());
        genreRepository.save(Genre.builder().name("Hài hước").build());
        genreRepository.save(Genre.builder().name("Tội phạm").build());
        genreRepository.save(Genre.builder().name("Gia đình").build());
        genreRepository.save(Genre.builder().name("Fantasia").build());
        genreRepository.save(Genre.builder().name("Kinh dị").build());
        genreRepository.save(Genre.builder().name("Lãng mạn").build());
        genreRepository.save(Genre.builder().name("Khoa học viễn tưởng").build());
        genreRepository.save(Genre.builder().name("Thần thoại").build());
        genreRepository.save(Genre.builder().name("Thể thao").build());
        genreRepository.save(Genre.builder().name("Chiến tranh").build());
        genreRepository.save(Genre.builder().name("Xã hội").build());
        genreRepository.save(Genre.builder().name("Tâm lý").build());
        genreRepository.save(Genre.builder().name("Trinh thám").build());
        genreRepository.save(Genre.builder().name("Thám hiểm").build());
        genreRepository.save(Genre.builder().name("Võ thuật").build());

        //MOVIE
        Set<String> casts = new HashSet<>();
        casts.add("Sandra Bullock");
        casts.add("Channing Tatum");
        casts.add("Daniel Radcliffe");

        Set<Genre> genreSet = new HashSet<>();
        genreSet.add(actionGenre);
        genreSet.add(adventureGenre);

        Movie movie1 = movieRepository.save(Movie.builder()
                .title("The Lost City ")
                .description("Bộ phim hành động hay The Lost City gây ấn tượng với khán giả nhờ vào khung cảnh thiên nhiên" +
                        " hùng vĩ và những màn hành động đầy ấn tượng. Câu chuyện xoay quanh vị" +
                        " tác giả tài năng Loretta Sage, người đã dành cả cuộc đời của mình để khám" +
                        " phá những địa điểm kỳ lạ và viết sách. Trong chuyến quảng bá sách của mình," +
                        " cô bị một tỷ phú kỳ quái bắt cóc và ép buộc chỉ đường đến một thành phố bí ẩn," +
                        " nơi ẩn chứa một kho báu.")
                .director("Aaron và Adam Nee")
                .casts(casts)
                .duration(112)
                .genres(genreSet)
                .rating((float) 4.5)
                .release_date(LocalDate.of(2024,5,1))
                .end_date(LocalDate.of(2024,6,30))
                .build());

        //CINEMA
        Cinema cinema1 = cinemaRepository.save(Cinema.builder()
                .name("CGV Lê Lợi")
                .address(Address.builder()
                        .street("123 Lê Lợi")
                        .ward("Bến Thành")
                        .district("Quận 1")
                        .city("Hồ Chí Minh")
                        .build())
                .auditoriums(new HashSet<>())
                .build());

        Auditorium auditorium1 = Auditorium.builder().name("Phòng 1").cinema(cinema1).build();
        for(int i =1 ;i<=10;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 45.500).row_Seat("A").auditorium(auditorium1).build());
        }
        for(int i = 11 ;i<=20;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 44.500).row_Seat("B").auditorium(auditorium1).build());
        }
        for(int i = 21 ;i<=30;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 43.500).row_Seat("C").auditorium(auditorium1).build());
        }

        Auditorium auditorium2 = Auditorium.builder().name("Phòng 2").cinema(cinema1).build();

        for(int i =1 ;i<=10;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 45.500).row_Seat("A").auditorium(auditorium2).build());
        }
        for(int i = 11 ;i<=20;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 44.500).row_Seat("B").auditorium(auditorium2).build());
        }
        auditoriumRepository.save(auditorium1);
        auditoriumRepository.save(auditorium2);

        cinema1.getAuditoriums().add(auditorium1);
        cinema1.getAuditoriums().add(auditorium2);

        cinemaRepository.save(cinema1);

        Cinema cinema2 = cinemaRepository.save(Cinema.builder()
                .name("CGV Nguyễn Trãi")
                .address(Address.builder()
                        .street("456 Nguyễn Trãi")
                        .ward("Nguyễn Cư Trinh")
                        .district("Quận 1")
                        .city("Hồ Chí Minh")
                        .build())
                .build());
        Auditorium auditorium3 = auditoriumRepository.save(Auditorium.builder().name("Phòng 1").cinema(cinema2).build());
        for(int i =1 ;i<=10;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 45.500).row_Seat("A").auditorium(auditorium3).build());
        }
        for(int i = 11 ;i<=20;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 44.500).row_Seat("B").auditorium(auditorium3).build());
        }
        for(int i = 21 ;i<=30;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 43.500).row_Seat("C").auditorium(auditorium3).build());
        }
        Auditorium auditorium4 = auditoriumRepository.save(Auditorium.builder().name("Phòng 2").cinema(cinema2).build());

        for(int i =1 ;i<=10;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 45.500).row_Seat("A").auditorium(auditorium4).build());
        }
        for(int i = 11 ;i<=20;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 44.500).row_Seat("B").auditorium(auditorium4).build());
        }
        Cinema cinema3 = cinemaRepository.save(Cinema.builder()
                .name("CGV Hai Bà Trưng")
                .address(Address.builder()
                        .street("789 Hai Bà Trưng")
                        .ward("Lý Thái Tổ")
                        .district("Quận Hoàn Kiếm")
                        .city("Hà Nội")
                        .build())
                .build());
        Auditorium auditorium5 = auditoriumRepository.save(Auditorium.builder().name("Phòng 1").cinema(cinema3).build());
        for(int i =1 ;i<=10;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 45.500).row_Seat("A").auditorium(auditorium5).build());
        }
        for(int i = 11 ;i<=20;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 44.500).row_Seat("B").auditorium(auditorium5).build());
        }
        for(int i = 21 ;i<=30;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 43.500).row_Seat("C").auditorium(auditorium5).build());
        }
        Auditorium auditorium6 = auditoriumRepository.save(Auditorium.builder().name("Phòng 2").cinema(cinema3).build());
        for(int i =1 ;i<=10;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 45.500).row_Seat("A").auditorium(auditorium6).build());
        }
        for(int i = 11 ;i<=20;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 44.500).row_Seat("B").auditorium(auditorium6).build());
        }
        Cinema cinema4 = cinemaRepository.save(Cinema.builder()
                .name("CGV Trần Hưng Đạo")
                .address(Address.builder()
                        .street("101 Trần Hưng Đạo")
                        .ward("Cửa Nam")
                        .district("Quận Hoàn Kiếm")
                        .city("Hà Nội")
                        .build())
                .build());
        Auditorium auditorium7 = auditoriumRepository.save(Auditorium.builder().name("Phòng 1").cinema(cinema4).build());
        for(int i =1 ;i<=10;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 45.500).row_Seat("A").auditorium(auditorium7).build());
        }
        for(int i = 11 ;i<=20;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 44.500).row_Seat("B").auditorium(auditorium7).build());
        }
        for(int i = 21 ;i<=30;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 43.500).row_Seat("C").auditorium(auditorium7).build());
        }
        Auditorium auditorium8 = auditoriumRepository.save(Auditorium.builder().name("Phòng 2").cinema(cinema4).build());
        for(int i =1 ;i<=10;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 45.500).row_Seat("A").auditorium(auditorium8).build());
        }
        for(int i = 11 ;i<=20;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 44.500).row_Seat("B").auditorium(auditorium8).build());
        }
        Cinema cinema5 = cinemaRepository.save(Cinema.builder()
                .name("CGV Cách Mạng Tháng 8")
                .address(Address.builder()
                        .street("789 Cách Mạng Tháng 8")
                        .ward("An Thới")
                        .district("Bình Thủy")
                        .city("Cần Thơ")
                        .build())
                .build());
        Auditorium auditorium9 = auditoriumRepository.save(Auditorium.builder().name("Phòng 1").cinema(cinema5).build());
        for(int i =1 ;i<=10;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 45.500).row_Seat("A").auditorium(auditorium9).build());
        }
        for(int i = 11 ;i<=20;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 44.500).row_Seat("B").auditorium(auditorium9).build());
        }
        for(int i = 21 ;i<=30;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 43.500).row_Seat("C").auditorium(auditorium9).build());
        }
        Auditorium auditorium10 = auditoriumRepository.save(Auditorium.builder().name("Phòng 2").cinema(cinema5).build());
        for(int i =1 ;i<=10;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 45.500).row_Seat("A").auditorium(auditorium10).build());
        }
        for(int i = 11 ;i<=20;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 44.500).row_Seat("B").auditorium(auditorium10).build());
        }
        Cinema cinema6 = cinemaRepository.save(Cinema.builder()
                .name("CGV Lê Lợi Cần Thơ")
                .address(Address.builder()
                        .street("123 Lê Lợi")
                        .ward("Cái Khế")
                        .district("Ninh Kiều")
                        .city("Cần Thơ")
                        .build())
                .build());
        Auditorium auditorium11 = auditoriumRepository.save(Auditorium.builder().name("Phòng 1").cinema(cinema6).build());
        for(int i =1 ;i<=10;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 45.500).row_Seat("A").auditorium(auditorium11).build());
        }
        for(int i = 11 ;i<=20;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 44.500).row_Seat("B").auditorium(auditorium11).build());
        }
        for(int i = 21 ;i<=30;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 43.500).row_Seat("C").auditorium(auditorium11).build());
        }
        Auditorium auditorium12 = auditoriumRepository.save(Auditorium.builder().name("Phòng 2").cinema(cinema6).build());
        for(int i =1 ;i<=10;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 45.500).row_Seat("A").auditorium(auditorium12).build());
        }
        for(int i = 11 ;i<=20;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 44.500).row_Seat("B").auditorium(auditorium12).build());
        }
        Cinema cinema7 = cinemaRepository.save(Cinema.builder()
                .name("CGV Lê Duẩn")
                .address(Address.builder()
                        .street("101 Lê Duẩn")
                        .ward("Hải Châu 1")
                        .district("Hải Châu")
                        .city("Đà Nẵng")
                        .build())
                .build());
        Auditorium auditorium13 = auditoriumRepository.save(Auditorium.builder().name("Phòng 1").cinema(cinema7).build());
        for(int i =1 ;i<=10;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 45.500).row_Seat("A").auditorium(auditorium13).build());
        }
        for(int i = 11 ;i<=20;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 44.500).row_Seat("B").auditorium(auditorium13).build());
        }
        for(int i = 21 ;i<=30;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 43.500).row_Seat("C").auditorium(auditorium13).build());
        }
        Auditorium auditorium14 = auditoriumRepository.save(Auditorium.builder().name("Phòng 2").cinema(cinema7).build());
        for(int i =1 ;i<=10;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 45.500).row_Seat("A").auditorium(auditorium14).build());
        }
        for(int i = 11 ;i<=20;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 44.500).row_Seat("B").auditorium(auditorium14).build());
        }
        Cinema cinema8 = cinemaRepository.save(Cinema.builder()
                .name("CGV Nguyễn Văn Linh")
                .address(Address.builder()
                        .street("123 Nguyễn Văn Linh")
                        .ward("Thạc Gián")
                        .district("Thanh Khê")
                        .city("Đà Nẵng")
                        .build())
                .build());
        Auditorium auditorium15 = auditoriumRepository.save(Auditorium.builder().name("Phòng 1").cinema(cinema8).build());
        for(int i =1 ;i<=10;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 45.500).row_Seat("A").auditorium(auditorium15).build());
        }
        for(int i = 11 ;i<=20;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 44.500).row_Seat("B").auditorium(auditorium15).build());
        }
        for(int i = 21 ;i<=30;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 43.500).row_Seat("C").auditorium(auditorium15).build());
        }
        Auditorium auditorium16 = auditoriumRepository.save(Auditorium.builder().name("Phòng 2").cinema(cinema8).build());
        for(int i =1 ;i<=10;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 45.500).row_Seat("A").auditorium(auditorium16).build());
        }
        for(int i = 11 ;i<=20;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 44.500).row_Seat("B").auditorium(auditorium16).build());
        }
        Cinema cinema9 = cinemaRepository.save(Cinema.builder()
                .name("CGV Trần Phú")
                .address(Address.builder()
                        .street("102 Trần Phú")
                        .ward("Lạch Tray")
                        .district("Ngô Quyền")
                        .city("Hải Phòng")
                        .build())
                .build());
        Auditorium auditorium17 = auditoriumRepository.save(Auditorium.builder().name("Phòng 1").cinema(cinema9).build());
        for(int i =1 ;i<=10;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 45.500).row_Seat("A").auditorium(auditorium17).build());
        }
        for(int i = 11 ;i<=20;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 44.500).row_Seat("B").auditorium(auditorium17).build());
        }
        for(int i = 21 ;i<=30;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 43.500).row_Seat("C").auditorium(auditorium17).build());
        }
        Auditorium auditorium18 = auditoriumRepository.save(Auditorium.builder().name("Phòng 2").cinema(cinema9).build());
        for(int i =1 ;i<=10;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 45.500).row_Seat("A").auditorium(auditorium18).build());
        }
        for(int i = 11 ;i<=20;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 44.500).row_Seat("B").auditorium(auditorium18).build());
        }
        Cinema cinema10 = cinemaRepository.save(Cinema.builder()
                .name("CGV Điện Biên Phủ")
                .address(Address.builder()
                        .street("789 Điện Biên Phủ")
                        .ward("Máy Tơ")
                        .district("Ngô Quyền")
                        .city("Hải Phòng")
                        .build())
                .build());
        Auditorium auditorium19 = auditoriumRepository.save(Auditorium.builder().name("Phòng 1").cinema(cinema10).build());
        for(int i =1 ;i<=10;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 45.500).row_Seat("A").auditorium(auditorium19).build());
        }
        for(int i = 11 ;i<=20;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 44.500).row_Seat("B").auditorium(auditorium19).build());
        }
        for(int i = 21 ;i<=30;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 43.500).row_Seat("C").auditorium(auditorium19).build());
        }
        Auditorium auditorium20 = auditoriumRepository.save(Auditorium.builder().name("Phòng 2").cinema(cinema10).build());
        for(int i =1 ;i<=10;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 45.500).row_Seat("A").auditorium(auditorium20).build());
        }
        for(int i = 11 ;i<=20;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 44.500).row_Seat("B").auditorium(auditorium20).build());
        }
        //Factor
        List<Cinema> cinemas = Arrays.asList(cinema1, cinema2, cinema3, cinema4, cinema5, cinema6, cinema7, cinema8, cinema9, cinema10);
        for (Cinema cinema : cinemas) {
            factorRepository.save(Factor.builder().dayType(DayType.WEEKDAY).factor((float) 1).cinema(cinema).build());
            factorRepository.save(Factor.builder().dayType(DayType.WEEKEND).factor((float) 1.5).cinema(cinema).build());
            factorRepository.save(Factor.builder().dayType(DayType.HOLIDAY).factor((float) 2).cinema(cinema).build());
        }

        screeningRepository.save(Screening.builder()
                .date(LocalDate.of(2024, 6, 5))
                .start(LocalTime.of(10, 30))
                .auditorium(auditorium1)
                .movie(movie1)
                .build());
    }
}
