package vnpt.movie_booking_be.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vnpt.movie_booking_be.dto.response.NewResponse;
import vnpt.movie_booking_be.mapper.NewMapper;
import vnpt.movie_booking_be.models.New;
import vnpt.movie_booking_be.repository.NewRepository;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class NewServiceImpl implements NewService{

    @Autowired
    private NewRepository newRepository;

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private NewMapper newMapper;

    @Override
    public List<NewResponse> getAll() {
        List<New> newList = newRepository.findAll();
        return newList.stream().map(n -> newMapper.toNewResponse(n))
                .collect(Collectors.toList());
    }

    public Map<String, Object> uploadImage(MultipartFile file) {
        try {
            return this.cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        } catch (IOException io) {
            throw new RuntimeException("Image upload failed", io);
        }
    }

    @Override
    public void createNew(String title, String content, MultipartFile file) {
        String imageUrl = null;
        if (file != null && !file.isEmpty()) {
            Map<String, Object> uploadImg = uploadImage(file);
            imageUrl = (String) uploadImg.get("url");
        }

        newRepository.save(New.builder()
                        .title(title)
                        .content(content)
                        .imageUrl(imageUrl)
                        .created(new Date())
                .build());
    }

    @Override
    public void DeleteNew(int newId) {
        New n = newRepository.findById(newId).orElseThrow(() -> new RuntimeException("new not found"));
        newRepository.delete(n);
    }

    @Override
    public void updateNew(int newId, String title, String content, MultipartFile file) {
        New n = newRepository.findById(newId).orElseThrow(() -> new RuntimeException("new not found"));
        if(title != null){
            n.setTitle(title);
        }
        if(content != null){
            n.setContent(content);
        }

        if (file != null && !file.isEmpty()) {
            Map<String, Object> uploadImg = uploadImage(file);
            String imageUrl = (String) uploadImg.get("url");
            n.setImageUrl(imageUrl);
        }

        newRepository.save(n);
    }


}
