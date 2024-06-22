package vnpt.movie_booking_be.service;

import org.springframework.web.multipart.MultipartFile;
import vnpt.movie_booking_be.dto.response.NewResponse;

import java.util.List;

public interface NewService {
    void createNew(String title, String content, MultipartFile file);

    List<NewResponse> getAll();

    void DeleteNew(int newId);

    void updateNew(int newId, String title, String content, MultipartFile file);
}
