package vnpt.movie_booking_be.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import vnpt.movie_booking_be.jwt.JwtAuthFilter;
import vnpt.movie_booking_be.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers(

         "/",
//
                                        "/getticketbyusser",
"/TicketbyUserID/**",
"/tickets/GetAll",
                                "/getAll",
                                "/removeTicketIdFromSeats/**",
                                "/submitOrder",
                                "/vnpay-payment",
                                "/vnpay-payment-app",
                                "/seat/getSeatsById/**",
                                "/genre/get/**","/address/getAddressHasCinema","/address/cities","/address/districts","/address/wards","/address/streets",
                                "/cinema/get/**",
                                "/factor/getAllFactorByCinema/**",
                                "/auditorium/get/**",
                                "/screening/getScreeningByCityAndMovie","/screening/getScreeningByCinema","/screening/getAll","/screening/getScreeningByAuditorium",
                                "/userLogin","/user/createUser",
                                "/user/verify/**","/movie/getAll",
                                "/review/get/**","/forget/verifyMail","/forget/verifyOtp","/forget/changePassword",
                                "/new/getAll").permitAll()
                                .anyRequest().authenticated()
                )
//                .authorizeRequests(authorize ->
//                        authorize
//                                .antMatchers("/").permitAll()
//                                .antMatchers("/seat/getSeatsById/**").permitAll()
//                                .antMatchers("/").permitAll()
//                                .anyRequest().authenticated()
//                )

                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
