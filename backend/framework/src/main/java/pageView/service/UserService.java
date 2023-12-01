package pageView.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pageView.domain.ResponseResult;
import pageView.domain.entity.User;


/**
 * 用户表(User)表服务接口
 *
 * @author 软件2001李威翰
 * @since 2022-12-16 10:11:48
 */
public interface UserService extends IService<User> {
    ResponseResult userInfo();

    ResponseResult updateUserInfo(User user);

    ResponseResult register(User user);

    ResponseResult listUser(Integer pageNum, Integer pageSize, String userName, String phonenumber, String status);

    ResponseResult addUser(User user);

    ResponseResult deleteUser(Long id);

    ResponseResult getUser(Long id);

    ResponseResult updateUser(User user);
}

