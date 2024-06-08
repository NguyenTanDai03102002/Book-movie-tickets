package vnpt.movie_booking_be.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = {"address","membership","roles","tickets","reviews"})
@ToString(exclude = {"address","membership","roles","tickets","reviews"})
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    
    private String email;
    
    private String phone;

    private String password;

    private Boolean enabled;

    private String code;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    private Membership membership;
    
    @ManyToMany(fetch = FetchType.EAGER ,cascade = CascadeType.ALL)
	@JoinTable(name ="users_roles" ,
			joinColumns = @JoinColumn(name = "user_id") , 
			inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
    private List<Ticket> tickets = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user",orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    private ForgetPassword forgetPassword;
}
