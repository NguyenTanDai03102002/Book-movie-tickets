package vnpt.movie_booking_be.dto.response;

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
     String password;
     AddressResponse address;
     MembershipResponse membership;
     Set<RoleResponse> roles;
}
