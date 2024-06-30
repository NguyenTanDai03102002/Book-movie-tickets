package vnpt.movie_booking_be.mapper;

import org.mapstruct.Mapper;
import vnpt.movie_booking_be.dto.response.MembershipResponse;
import vnpt.movie_booking_be.models.Membership;



@Mapper(componentModel = "spring")
public interface MembershipMapper {
    MembershipResponse toMembershipResponse(Membership membership);
}
