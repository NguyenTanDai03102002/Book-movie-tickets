package vnpt.movie_booking_be;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import vnpt.movie_booking_be.models.*;
import vnpt.movie_booking_be.repository.*;

import java.time.LocalDate;
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
private VourcherRepository voucherRepository;
    @Autowired
    private ScreeningRepository screeningRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private NewRepository newRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        if(roleRepository.findByName("ADMIN") != null){
                return;
        }

        //MEMBERSHIP
        membershipRepository.save(Membership.builder().name("Đồng").description("Đồng").discount_rate(0.0).rankprice(0).build());
        membershipRepository.save(Membership.builder().name("Bạc").description("Bạc").discount_rate(0.05).rankprice(300000).build());
        membershipRepository.save(Membership.builder().name("Vàng").description("vàng").discount_rate(0.1).rankprice(500000).build());

        //ROLE
        Role adminRole = roleRepository.save(Role.builder().name("ADMIN").build());
        Role userRole = roleRepository.save(Role.builder().name("USER").build());
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);

        Set<Role> userRoles = new HashSet<>();
        userRoles.add(userRole);
        //ADMIN
        userRepository.save(User.builder().name("admin").email("admin@gmail.com").phone("0943946242")
                    .password(passwordEncoder.encode("admin")).enabled(true).code(null).roles(adminRoles).build());
        userRepository.save(User.builder().name("dai").email("ndai6618@gmail.com").phone("0943946242")
                .password(passwordEncoder.encode("dai")).enabled(true).code(null).roles(userRoles).build());
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
        Genre khvt = genreRepository.save(Genre.builder().name("Khoa học viễn tưởng").build());
        genreRepository.save(Genre.builder().name("Thần thoại").build());
        genreRepository.save(Genre.builder().name("Thể thao").build());
        genreRepository.save(Genre.builder().name("Chiến tranh").build());
        genreRepository.save(Genre.builder().name("Xã hội").build());
        genreRepository.save(Genre.builder().name("Tâm lý").build());
        genreRepository.save(Genre.builder().name("Trinh thám").build());
        genreRepository.save(Genre.builder().name("Thám hiểm").build());
        genreRepository.save(Genre.builder().name("Võ thuật").build());

        //MOVIE
        Set<String> casts1 = new HashSet<>();
        casts1.add("Lee Jung Jae");
        casts1.add("Park Hae Soo");
        casts1.add("Wi Ha Joon");

        Set<Genre> genreSet1 = new HashSet<>();
        genreSet1.add(actionGenre);
        genreSet1.add(adventureGenre);

        Movie movie1 = movieRepository.save(Movie.builder()
                .title("TRÒ CHƠI CON MỰC")
                .description("Squid Game – Trò Chơi Con Mực (tên tiếng Hàn 오징어 게임) " +
                        "là một series Hàn Quốc trên Netflix được lấy cảm hứng từ một trò " +
                        "chơi quen thuộc dành cho trẻ em ở Hàn Quốc ngày xưa. Vào những năm 1980," +
                        " đây là một trong những trò giải trí phổ biến thường được chơi trên khoảng " +
                        "sân trống với những hình thù được vẽ trên đất. Squid Game – Trò Chơi Con Mực " +
                        "(tên tiếng Hàn 오징어 게임) là một series Hàn Quốc trên Netflix được lấy cảm hứng " +
                        "từ một trò chơi quen thuộc dành cho trẻ em ở Hàn Quốc ngày xưa. Vào những năm " +
                        "1980, đây là một trong những trò giải trí phổ biến thường được chơi trên khoảng " +
                        "sân trống với những hình thù được vẽ trên đất.")
                .director("Hwang Dong-hyuk")
                .casts(casts1)
                .duration(112)
                .genres(genreSet1)
                .rating((float) 4.5)
                .image("http://res.cloudinary.com/do9bojdku/image/upload/v1717242487/b8up5j8i6j0hrlwayxx2.jpg")
                .trailer("http://res.cloudinary.com/do9bojdku/video/upload/v1717242492/jgjfnbhjt2hjake9hgm0.mp4")
                .release_date(LocalDate.of(2024,5,1))
                .end_date(LocalDate.of(2024,6,30))
                        .isActive(true)
                .build());

        Set<String> casts2 = new HashSet<>();
        casts2.add("Dwayne Johnson");
        casts2.add("Naomie Harris");
        casts2.add("Malin Åkerman");
        casts2.add("Joe Manganiello");
        casts2.add("Jake Lacy");
        casts2.add("Marley Shelton");
        casts2.add("Jeffrey Dean Morgan");

        Set<Genre> genreSet2 = new HashSet<>();
        genreSet2.add(khvt);
        Movie movie2 = movieRepository.save(Movie.builder()
                .title("SIÊU THÚ CUỒNG NỘ")
                .description("Bộ phim kể về nhà sinh vật học Davis Okoye có mối liên kết khăng khít " +
                        "với George - một chú gorilla lưng bạc được anh chăm sóc từ nhỏ. Một thí nghiệm " +
                        "đột biến ngoài ý muốn đã làm biến đổi gorilla thành quái vật khổng lồ hung hăng" +
                        ". Gorilla cùng những sinh vật bị biến đổi khác đã tấn công con người và đẩy nhân" +
                        " loại đứng trước hiểm họa diệt vong. Liệu Davis Okoye sẽ làm gì để giành chiến " +
                        "thắng, không chỉ để ngăn chặn một thảm họa toàn cầu, mà còn để cứu gorilla đã " +
                        "từng là bạn tốt của mình?")
                .director("Brad Peyton")
                .casts(casts2)
                .duration(112)
                .genres(genreSet2)
                .rating((float) 5.5)
                .image("http://res.cloudinary.com/do9bojdku/image/upload/v1717243220/l0frpo7yaggxzj1oznob.jpg")
                .trailer("http://res.cloudinary.com/do9bojdku/video/upload/v1717243224/cmqkux5zxbxrmrio6bnu.mp4")
                .release_date(LocalDate.of(2024,5,1))
                .end_date(LocalDate.of(2024,6,30))
                        .isActive(true)
                .build());

        Set<String> casts3 = new HashSet<>();
        casts3.add("Lee Jung Jae");
        casts3.add("Park Hae Soo");
        casts3.add("Wi Ha Joon");

        Set<Genre> genreSet3 = new HashSet<>();
        genreSet3.add(actionGenre);
        genreSet3.add(adventureGenre);
        Movie movie3 = movieRepository.save(Movie.builder()
                .title("TRÒ CHƠI CON MỰC")
                .description("Squid Game – Trò Chơi Con Mực (tên tiếng Hàn 오징어 게임) " +
                        "là một series Hàn Quốc trên Netflix được lấy cảm hứng từ một trò " +
                        "chơi quen thuộc dành cho trẻ em ở Hàn Quốc ngày xưa. Vào những năm 1980," +
                        " đây là một trong những trò giải trí phổ biến thường được chơi trên khoảng " +
                        "sân trống với những hình thù được vẽ trên đất. Squid Game – Trò Chơi Con Mực " +
                        "(tên tiếng Hàn 오징어 게임) là một series Hàn Quốc trên Netflix được lấy cảm hứng " +
                        "từ một trò chơi quen thuộc dành cho trẻ em ở Hàn Quốc ngày xưa. Vào những năm " +
                        "1980, đây là một trong những trò giải trí phổ biến thường được chơi trên khoảng " +
                        "sân trống với những hình thù được vẽ trên đất.")
                .director("Hwang Dong-hyuk")
                .casts(casts1)
                .duration(112)
                .genres(genreSet1)
                .rating((float) 4.5)
                .image("http://res.cloudinary.com/do9bojdku/image/upload/v1717242487/b8up5j8i6j0hrlwayxx2.jpg")
                .trailer("http://res.cloudinary.com/do9bojdku/video/upload/v1717242492/jgjfnbhjt2hjake9hgm0.mp4")
                .release_date(LocalDate.of(2024,10,10))
                .end_date(LocalDate.of(2024,12,12))
                .isActive(true)
                .build());

        Set<String> casts4 = new HashSet<>();
        casts4.add("Dwayne Johnson");
        casts4.add("Naomie Harris");
        casts4.add("Malin Åkerman");
        casts4.add("Joe Manganiello");
        casts4.add("Jake Lacy");
        casts4.add("Marley Shelton");
        casts4.add("Jeffrey Dean Morgan");
        Set<Genre> genreSet4 = new HashSet<>();
        genreSet4.add(khvt);
        Movie movie4 = movieRepository.save(Movie.builder()
                .title("SIÊU THÚ CUỒNG NỘ")
                .description("Bộ phim kể về nhà sinh vật học Davis Okoye có mối liên kết khăng khít " +
                        "với George - một chú gorilla lưng bạc được anh chăm sóc từ nhỏ. Một thí nghiệm " +
                        "đột biến ngoài ý muốn đã làm biến đổi gorilla thành quái vật khổng lồ hung hăng" +
                        ". Gorilla cùng những sinh vật bị biến đổi khác đã tấn công con người và đẩy nhân" +
                        " loại đứng trước hiểm họa diệt vong. Liệu Davis Okoye sẽ làm gì để giành chiến " +
                        "thắng, không chỉ để ngăn chặn một thảm họa toàn cầu, mà còn để cứu gorilla đã " +
                        "từng là bạn tốt của mình?")
                .director("Brad Peyton")
                .casts(casts2)
                .duration(112)
                .genres(genreSet2)
                .rating((float) 5.5)
                .image("http://res.cloudinary.com/do9bojdku/image/upload/v1717243220/l0frpo7yaggxzj1oznob.jpg")
                .trailer("http://res.cloudinary.com/do9bojdku/video/upload/v1717243224/cmqkux5zxbxrmrio6bnu.mp4")
                .release_date(LocalDate.of(2024,10,10))
                .end_date(LocalDate.of(2024,12,12))
                .isActive(true)
                .build());

        // Insert data for vourcher
        Date startDateTime = new GregorianCalendar(2024, Calendar.JUNE, 24).getTime();
        Date endDateTime = new GregorianCalendar(2024, Calendar.JULY, 24).getTime();
        double[] discounts = {0.05, 0.1, 0.2, 0.15, 0.25};

        for (double discount : discounts) {
          //  Vourcher vourcher = new Vourcher(
            Vourcher vourcher = new Vourcher(startDateTime, endDateTime, 50, discount, "mã giảm giá");
            voucherRepository.save(vourcher);
        }

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
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 45.500).row_Seat("A").seatType(SeatType.normal).auditorium(auditorium1).build());
        }
        for(int i = 11 ;i<=20;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 44.500).row_Seat("B").seatType(SeatType.vip).auditorium(auditorium1).build());
        }
        for(int i = 21 ;i<=30;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 43.500).row_Seat("C").seatType(SeatType.sweetBox).auditorium(auditorium1).build());
        }

        Auditorium auditorium2 = Auditorium.builder().name("Phòng 2").cinema(cinema1).build();

        for(int i =1 ;i<=10;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 45.500).row_Seat("A").seatType(SeatType.normal).auditorium(auditorium2).build());
        }
        for(int i = 11 ;i<=20;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 44.500).row_Seat("B").seatType(SeatType.vip).auditorium(auditorium2).build());
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
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 45.500).row_Seat("A").seatType(SeatType.normal).auditorium(auditorium3).build());
        }
        for(int i = 11 ;i<=20;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 44.500).row_Seat("B").seatType(SeatType.vip).auditorium(auditorium3).build());
        }
        for(int i = 21 ;i<=30;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 43.500).row_Seat("C").seatType(SeatType.sweetBox).auditorium(auditorium3).build());
        }
        Auditorium auditorium4 = auditoriumRepository.save(Auditorium.builder().name("Phòng 2").cinema(cinema2).build());

        for(int i =1 ;i<=10;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 45.500).row_Seat("A").seatType(SeatType.normal).auditorium(auditorium4).build());
        }
        for(int i = 11 ;i<=20;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 44.500).row_Seat("B").seatType(SeatType.vip).auditorium(auditorium4).build());
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
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 45.500).row_Seat("A").seatType(SeatType.normal).auditorium(auditorium5).build());
        }
        for(int i = 11 ;i<=20;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 44.500).row_Seat("B").seatType(SeatType.vip).auditorium(auditorium5).build());
        }
        for(int i = 21 ;i<=30;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 43.500).row_Seat("C").seatType(SeatType.sweetBox).auditorium(auditorium5).build());
        }
        Auditorium auditorium6 = auditoriumRepository.save(Auditorium.builder().name("Phòng 2").cinema(cinema3).build());
        for(int i =1 ;i<=10;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 45.500).row_Seat("A").seatType(SeatType.normal).auditorium(auditorium6).build());
        }
        for(int i = 11 ;i<=20;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 44.500).row_Seat("B").seatType(SeatType.vip).auditorium(auditorium6).build());
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
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 45.500).row_Seat("A").seatType(SeatType.normal).auditorium(auditorium7).build());
        }
        for(int i = 11 ;i<=20;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 44.500).row_Seat("B").seatType(SeatType.vip).auditorium(auditorium7).build());
        }
        for(int i = 21 ;i<=30;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 43.500).row_Seat("C").seatType(SeatType.sweetBox).auditorium(auditorium7).build());
        }
        Auditorium auditorium8 = auditoriumRepository.save(Auditorium.builder().name("Phòng 2").cinema(cinema4).build());
        for(int i =1 ;i<=10;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 45.500).row_Seat("A").seatType(SeatType.normal).auditorium(auditorium8).build());
        }
        for(int i = 11 ;i<=20;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 44.500).row_Seat("B").seatType(SeatType.vip).auditorium(auditorium8).build());
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
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 45.500).row_Seat("A").seatType(SeatType.normal).auditorium(auditorium9).build());
        }
        for(int i = 11 ;i<=20;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 44.500).row_Seat("B").seatType(SeatType.vip).auditorium(auditorium9).build());
        }
        for(int i = 21 ;i<=30;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 43.500).row_Seat("C").seatType(SeatType.sweetBox).auditorium(auditorium9).build());
        }
        Auditorium auditorium10 = auditoriumRepository.save(Auditorium.builder().name("Phòng 2").cinema(cinema5).build());
        for(int i =1 ;i<=10;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 45.500).row_Seat("A").seatType(SeatType.normal).auditorium(auditorium10).build());
        }
        for(int i = 11 ;i<=20;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 44.500).row_Seat("B").seatType(SeatType.vip).auditorium(auditorium10).build());
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
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 45.500).row_Seat("A").seatType(SeatType.normal).auditorium(auditorium11).build());
        }
        for(int i = 11 ;i<=20;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 44.500).row_Seat("B").seatType(SeatType.vip).auditorium(auditorium11).build());
        }
        for(int i = 21 ;i<=30;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 43.500).row_Seat("C").seatType(SeatType.sweetBox).auditorium(auditorium11).build());
        }
        Auditorium auditorium12 = auditoriumRepository.save(Auditorium.builder().name("Phòng 2").cinema(cinema6).build());
        for(int i =1 ;i<=10;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 45.500).row_Seat("A").seatType(SeatType.normal).auditorium(auditorium12).build());
        }
        for(int i = 11 ;i<=20;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 44.500).row_Seat("B").seatType(SeatType.vip).auditorium(auditorium12).build());
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
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 45.500).row_Seat("A").seatType(SeatType.normal).auditorium(auditorium13).build());
        }
        for(int i = 11 ;i<=20;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 44.500).row_Seat("B").seatType(SeatType.vip).auditorium(auditorium13).build());
        }
        for(int i = 21 ;i<=30;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 43.500).row_Seat("C").seatType(SeatType.sweetBox).auditorium(auditorium13).build());
        }
        Auditorium auditorium14 = auditoriumRepository.save(Auditorium.builder().name("Phòng 2").cinema(cinema7).build());
        for(int i =1 ;i<=10;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 45.500).row_Seat("A").seatType(SeatType.normal).auditorium(auditorium14).build());
        }
        for(int i = 11 ;i<=20;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 44.500).row_Seat("B").seatType(SeatType.vip).auditorium(auditorium14).build());
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
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 45.500).row_Seat("A").seatType(SeatType.normal).auditorium(auditorium15).build());
        }
        for(int i = 11 ;i<=20;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 44.500).row_Seat("B").seatType(SeatType.vip).auditorium(auditorium15).build());
        }
        for(int i = 21 ;i<=30;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 43.500).row_Seat("C").seatType(SeatType.sweetBox).auditorium(auditorium15).build());
        }
        Auditorium auditorium16 = auditoriumRepository.save(Auditorium.builder().name("Phòng 2").cinema(cinema8).build());
        for(int i =1 ;i<=10;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 45.500).row_Seat("A").seatType(SeatType.normal).auditorium(auditorium16).build());
        }
        for(int i = 11 ;i<=20;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 44.500).row_Seat("B").seatType(SeatType.vip).auditorium(auditorium16).build());
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
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 45.500).row_Seat("A").seatType(SeatType.normal).auditorium(auditorium17).build());
        }
        for(int i = 11 ;i<=20;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 44.500).row_Seat("B").seatType(SeatType.vip).auditorium(auditorium17).build());
        }
        for(int i = 21 ;i<=30;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 43.500).row_Seat("C").seatType(SeatType.sweetBox).auditorium(auditorium17).build());
        }
        Auditorium auditorium18 = auditoriumRepository.save(Auditorium.builder().name("Phòng 2").cinema(cinema9).build());
        for(int i =1 ;i<=10;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 45.500).row_Seat("A").seatType(SeatType.normal).auditorium(auditorium18).build());
        }
        for(int i = 11 ;i<=20;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 44.500).row_Seat("B").seatType(SeatType.vip).auditorium(auditorium18).build());
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
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 45.500).row_Seat("A").seatType(SeatType.normal).auditorium(auditorium19).build());
        }
        for(int i = 11 ;i<=20;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 44.500).row_Seat("B").seatType(SeatType.vip).auditorium(auditorium19).build());
        }
        for(int i = 21 ;i<=30;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 43.500).row_Seat("C").seatType(SeatType.sweetBox).auditorium(auditorium19).build());
        }
        Auditorium auditorium20 = auditoriumRepository.save(Auditorium.builder().name("Phòng 2").cinema(cinema10).build());
        for(int i =1 ;i<=10;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 45.500).row_Seat("A").seatType(SeatType.normal).auditorium(auditorium20).build());
        }
        for(int i = 11 ;i<=20;i++){
            seatRepository.save(Seat.builder().number_Seat(i).price((float) 44.500).row_Seat("B").seatType(SeatType.vip).auditorium(auditorium20).build());
        }
        //Factor
        List<Cinema> cinemas = Arrays.asList(cinema1, cinema2, cinema3, cinema4, cinema5, cinema6, cinema7, cinema8, cinema9, cinema10);
        for (Cinema cinema : cinemas) {
            factorRepository.save(Factor.builder().dayType(DayType.WEEKDAY).factor((float) 1).cinema(cinema).build());
            factorRepository.save(Factor.builder().dayType(DayType.WEEKEND).factor((float) 1.5).cinema(cinema).build());
            factorRepository.save(Factor.builder().dayType(DayType.HOLIDAY).factor((float) 2).cinema(cinema).build());
        }

        screeningRepository.save(Screening.builder()
                .date(LocalDate.of(2024, 6, 30))
                .start(LocalTime.of(10, 30))
                .auditorium(auditorium1)
                .movie(movie1)
                .build());

        screeningRepository.save(Screening.builder()
                .date(LocalDate.of(2024, 6, 30))
                .start(LocalTime.of(14, 30))
                .auditorium(auditorium1)
                .movie(movie2)
                .build());


        newRepository.save(New.builder()
                        .title("Hồng Đào, Thùy Tiên đóng phim kinh dị")
                        .content("Thùy Tiên xem phim điện ảnh đầu tay là cơ hội để thử thách bản thân " +
                                "với dòng phim tâm lý - kinh dị, thể loại cô yêu thích. Cô từng trải qua " +
                                "nhiều khóa học diễn xuất, trong đó có lớp do nghệ sĩ Lê Khanh hướng dẫn " +
                                "năm 2023. Theo hoa hậu, nhờ được học bài bản, cô nắm thủ thuật phân tích " +
                                "tâm lý nhân vật, ứng biến tình huống.Tôi từng áp lực khi diễn cảnh khóc vì " +
                                "vốn không quen thể hiện cảm xúc trước đám đông. Sau này, được cô Lê " +
                                "Khanh dạy, tôi hiểu cách đồng cảm nhân vật, từ đó làm chủ cảm xúc, dễ " +
                                "khóc hơn trên phim trường, cô nói.")
                        .imageUrl("http://res.cloudinary.com/do9bojdku/image/upload/v1719036397/z4j5kpyyxge2sgffqutx.jpg")
                        .created(new Date())
                .build());
    }

}
