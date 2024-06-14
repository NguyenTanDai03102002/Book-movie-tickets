package vnpt.movie_booking_be.config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {
    @Bean
    public Cloudinary getCloudinary() {
        Map<String, Object> config = new HashMap<>();
        config.put("cloud_name", "do9bojdku");
        config.put("api_key", "949824695166918");
        config.put("api_secret", "cXZp8UJwFvIQi5D0HtK94MqOASA");
        config.put("secure", true);
        return new Cloudinary(config);
    }
}
