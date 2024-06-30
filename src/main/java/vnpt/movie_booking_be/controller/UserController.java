package vnpt.movie_booking_be.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vnpt.movie_booking_be.dto.request.UserCreationRequest;
import vnpt.movie_booking_be.dto.response.UserResponse;
import vnpt.movie_booking_be.mapper.UserMapper;
import vnpt.movie_booking_be.models.Membership;
import vnpt.movie_booking_be.models.User;
import vnpt.movie_booking_be.repository.MembershipRepository;
import vnpt.movie_booking_be.service.UserService;
import vnpt.movie_booking_be.service.VNPayServiceimpl;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private MembershipRepository membershipRepository;
@Autowired
    VNPayServiceimpl vnPayServiceimpl;
    @Autowired
    private UserMapper userMapper;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> userResponses = userService.getAllUsers();

        // Kiểm tra và cập nhật Membership của từng User
        for (UserResponse userResponse : userResponses) {
            // Lấy thông tin User từ UserService
            User user = userService.getUser(userResponse.getId());
            vnPayServiceimpl.updateUserMembershipByRankPrice(user.getId(), 0);
        }
        List<UserResponse> userResponsess = userService.getAllUsers();
        return new ResponseEntity<>(userResponsess, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable("userId") int userId) {
        User user = userService.getUser(userId);

        // Kiểm tra và cập nhật Membership của User
        if (user != null) {
            vnPayServiceimpl.updateUserMembershipByRankPrice(user.getId(), 0);
            // Lấy total price của User
            }
        User userr = userService.getUser(userId);
        // Chuyển đổi User thành UserResponse để trả về
        UserResponse userResponse = userMapper.userToUserResponse(userr);

        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }
    @PostMapping("/createUser")
    public void createUser(@RequestBody UserCreationRequest request) {
        userService.createUser(request);
    }

    @GetMapping("/verify")
    public String verifyUser(@RequestParam("code") String code) {
        return userService.verifyUser(code);
    }

    @PutMapping("/updateUser/{userid}")
    public void updateUser(@PathVariable int userid, @RequestBody UserCreationRequest request) {
        userService.updateUser(userid, request);
        // Cập nhật Membership dựa trên totalprice mới
        User user = userService.getUser(userid);
        vnPayServiceimpl.updateUserMembershipByRankPriceAfterEdit(userid, user.getTotalprice());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/disableAccount/{userId}")
    public void disableAccount(@PathVariable int userId) {
        userService.disableAccount(userId);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/unDisableAccount/{userId}")
    public void unDisableAccount(@PathVariable int userId) {
        userService.unDisableAccount(userId);
    }

}
