package vnpt.movie_booking_be.service;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vnpt.movie_booking_be.dto.request.UserCreationRequest;
import vnpt.movie_booking_be.dto.response.UserResponse;
import vnpt.movie_booking_be.mapper.UserMapper;
import vnpt.movie_booking_be.models.Membership;
import vnpt.movie_booking_be.models.Role;
import vnpt.movie_booking_be.models.User;
import vnpt.movie_booking_be.repository.MembershipRepository;
import vnpt.movie_booking_be.repository.RoleRepository;
import vnpt.movie_booking_be.repository.UserRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MembershipRepository membershipRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private EmailSenderService emailSenderService;

    @Override
    public void createUser(UserCreationRequest request) {
        User user = userRepository.findByEmail(request.getEmail());
        if(user == null) {
            Membership membership = membershipRepository.findByName("Đồng");
            Role userRole = roleRepository.findByName("USER");
            user = userMapper.toUser(request);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setMembership(membership);
            user.setRoles(Collections.singleton(userRole));
            user.setEnabled(false);
            user.setCode(UUID.randomUUID().toString());
            userRepository.save(user);

            Send(user);

        }else{
            throw new RuntimeException("User already exists");
        }
    }

    private void Send(User user){
        String subject = "Account Verfication";
        String content = "Dear [[name]],<br>" + "Please click the link below to verify your registration:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>" + "Thank you,<br>" + "Admin";

        String url = "http://localhost:8070";
        String siteUrl = url + "/user/verify?code=" + user.getCode();
        content = content.replace("[[name]]", user.getName());
        content =content.replace("[[URL]]",siteUrl);
        emailSenderService.sendEmail(user.getEmail(), subject,content);
    }

    @Override
    public String verifyUser(String code) {
        User user = userRepository.findByCode(code);
        if(user == null) {
            throw new RuntimeException("User does not exist");
        }else{
            user.setCode(null);
            user.setEnabled(true);
            userRepository.save(user);
            return "Bạn đã xác minh tài khoản thành công";
        }

    }

    @Override
    public void updateUser(int userid, UserCreationRequest request) {
        User user = getUser(userid);
        userMapper.updateUser(user,request);
        userRepository.save(user);
    }

    @Override
    public User getUser(int userid) {
        return userRepository.findById(userid).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> userMapper.userToUserResponse(user))
                .collect(Collectors.toList());
    }


}
