package vnpt.movie_booking_be.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
<<<<<<< HEAD
import java.util.HashSet;
import java.util.Set;
=======
>>>>>>> 5b16deb64ee75b07ddf34a11fcbd5bef5619ff79


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
<<<<<<< HEAD
//@EqualsAndHashCode(exclude = {"seat","screening","user"})
//@ToString(exclude = {"seat","screening","user"})
@EqualsAndHashCode(exclude = {"seat","screening","user", "movies"})
@ToString(exclude = {"seat","screening","user", "movies"})
=======
@EqualsAndHashCode(exclude = {"seat","screening","user"})
@ToString(exclude = {"seat","screening","user"})
>>>>>>> 5b16deb64ee75b07ddf34a11fcbd5bef5619ff79
@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private int total;
    
<<<<<<< HEAD
    private Date orderTime;  // tụư getdatetime now
    
    private int status;  // mới thnah toán xong tự set =0
    private String qrcode;
    @Enumerated(EnumType.STRING)
	private PaymentMethod paymentMethod;

    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Seat> seats = new HashSet<>();

=======
    private Date orderTime;
    
    private int status;
    
    @Enumerated(EnumType.STRING)
	private PaymentMethod paymentMethod;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seat_id")
    private Seat seat;
    
>>>>>>> 5b16deb64ee75b07ddf34a11fcbd5bef5619ff79
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "screening_id")
    private Screening screening;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
<<<<<<< HEAD

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private Movie movie;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Set<Seat> getSeats() {
        return seats;
    }

    public void setSeats(Set<Seat> seats) {
        this.seats = seats;
    }

    public Screening getScreening() {
        return screening;
    }

    public void setScreening(Screening screening) {
        this.screening = screening;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
=======
    
    
>>>>>>> 5b16deb64ee75b07ddf34a11fcbd5bef5619ff79
}
