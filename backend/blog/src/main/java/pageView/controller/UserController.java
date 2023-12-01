package pageView.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pageView.domain.ResponseResult;
import pageView.domain.entity.User;
import pageView.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/userInfo")
    public ResponseResult userInfo() {
        ResponseResult result = userService.userInfo();
        return result;
    }

    @PutMapping("/userInfo")
    public ResponseResult updateUserInfo(@RequestBody User user) {
        ResponseResult result = userService.updateUserInfo(user);
        return result;
    }

    @PostMapping("/register")
    public ResponseResult register(@RequestBody User user) {
        ResponseResult result = userService.register(user);
        return result;
    }

}
