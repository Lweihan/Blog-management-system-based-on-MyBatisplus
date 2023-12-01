package pageView.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pageView.domain.ResponseResult;
import pageView.domain.entity.User;
import pageView.enums.AppHttpCodeEnum;
import pageView.exception.SystemException;
import pageView.service.LoginService;

@RestController
public class loginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseResult login(@RequestBody User user) {
        if (!StringUtils.hasText(user.getUserName())) {
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        ResponseResult result = loginService.login(user);
        return result;
    }

    @PostMapping("/logout")
    public ResponseResult logout() {
        ResponseResult result = loginService.logout();
        return result;
    }
}
