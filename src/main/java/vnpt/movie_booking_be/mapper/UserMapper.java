package vnpt.movie_booking_be.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import vnpt.movie_booking_be.dto.request.UserCreationRequest;
import vnpt.movie_booking_be.dto.response.MembershipResponse;
import vnpt.movie_booking_be.dto.response.UserResponse;
import vnpt.movie_booking_be.models.Membership;
import vnpt.movie_booking_be.models.User;


@Mapper(componentModel = "spring")
public interface UserMapper {

    MembershipResponse toMembershipResponse(Membership membership);
    User toUser(UserCreationRequest request);

    void updateUser(@MappingTarget  User user, UserCreationRequest request);
    @Mapping(source = "totalprice", target = "totalprice")
    UserResponse userToUserResponse(User user);
}
