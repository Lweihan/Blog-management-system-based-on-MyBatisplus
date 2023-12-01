package pageView.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pageView.domain.ResponseResult;
import pageView.service.LinkService;

@RestController
@RequestMapping("/link")
public class linkController {

    @Autowired
    private LinkService linkService;

    @GetMapping("/getAllLink")
    public ResponseResult getAllLink() {
        ResponseResult result = linkService.getAllLink();
        return result;
    }
}
