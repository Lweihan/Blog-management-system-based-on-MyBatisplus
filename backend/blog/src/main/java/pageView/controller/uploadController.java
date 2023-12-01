package pageView.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pageView.domain.ResponseResult;
import pageView.service.UploadService;

@RestController
public class uploadController {

    @Autowired
    private UploadService uploadService;

    @PostMapping("/upload")
    public ResponseResult uploadImage(MultipartFile img) {
        ResponseResult result = uploadService.uploadImage(img);
        return result;
    }
}
