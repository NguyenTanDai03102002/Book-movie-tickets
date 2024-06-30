package vnpt.movie_booking_be.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
     int id;
     String name;
     String email;
     String phone;
     int totalprice; // Đảm bảo trường này có trong lớp UserResponse
     @JsonIgnore
     String password;
     AddressResponse address;
     MembershipResponse membership;
     Set<RoleResponse> roles;
}
