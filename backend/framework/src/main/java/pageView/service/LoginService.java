package pageView.service;

import pageView.domain.ResponseResult;
import pageView.domain.entity.User;

public interface LoginService {
    ResponseResult login(User user);

    ResponseResult logout();
}
