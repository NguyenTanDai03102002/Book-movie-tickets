package vnpt.movie_booking_be.service;

import org.springframework.http.ResponseEntity;
import vnpt.movie_booking_be.dto.request.UserCreationRequest;
import vnpt.movie_booking_be.dto.response.UserResponse;
import vnpt.movie_booking_be.models.User;

import java.util.List;

public interface UserService {
    void createUser(UserCreationRequest request);

    void updateUser(int userid, UserCreationRequest request);

    User getUser(int userid);

    List<UserResponse> getAllUsers();

    String verifyUser(String code);

    void disableAccount(int userId);

    void unDisableAccount(int userId);
}
