package vnpt.movie_booking_be.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vnpt.movie_booking_be.dto.request.UserCreationRequest;
import vnpt.movie_booking_be.dto.response.UserResponse;
import vnpt.movie_booking_be.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/getall")
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
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
    public void getUser(@PathVariable int userid,@RequestBody UserCreationRequest request) {
        userService.updateUser(userid,request);
    }
}
