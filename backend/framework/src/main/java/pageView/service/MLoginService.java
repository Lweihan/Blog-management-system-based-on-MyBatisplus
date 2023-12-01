package pageView.service;

import pageView.domain.ResponseResult;
import pageView.domain.entity.User;

public interface MLoginService {
    ResponseResult login(User user);

    ResponseResult logout();
}
