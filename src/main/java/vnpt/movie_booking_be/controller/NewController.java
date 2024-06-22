package vnpt.movie_booking_be.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vnpt.movie_booking_be.dto.response.NewResponse;
import vnpt.movie_booking_be.service.NewService;

import java.util.List;

@RestController
@RequestMapping("/new")
@CrossOrigin("*")
public class NewController {

    @Autowired
    private NewService newService;

    @GetMapping("/getAll")
    public List<NewResponse> getAll(){
        return newService.getAll();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/createNew")
    public void CreateNew(@RequestParam String title,
                          @RequestParam String content,
                          @RequestPart(required = false) MultipartFile file){
        newService.createNew(title,content,file);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping("/deleteNew/{newId}")
    public void DeleteNew(@PathVariable int newId){
        newService.DeleteNew(newId);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PutMapping("/updateNew/{newId}")
    public void updateNew(@PathVariable int newId,
                          @RequestParam(required = false) String title,
                          @RequestParam(required = false) String content,
                          @RequestPart(required = false) MultipartFile file){
        newService.updateNew(newId,title,content,file);
    }

}
