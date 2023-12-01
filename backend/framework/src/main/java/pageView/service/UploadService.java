package pageView.service;

import org.springframework.web.multipart.MultipartFile;
import pageView.domain.ResponseResult;

public interface UploadService {
    ResponseResult uploadImage(MultipartFile img);
}
