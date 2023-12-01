package pageView.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pageView.domain.ResponseResult;
import pageView.domain.entity.LoginUser;
import pageView.domain.entity.User;
import pageView.domain.vo.loginUserVo;
import pageView.domain.vo.UserInfoVo;
import pageView.service.LoginService;
import pageView.utils.BeanCopyUtils;
import pageView.utils.JwtUtil;
import pageView.utils.RedisCache;

import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        if (Objects.isNull(authentication)) {
            throw new RuntimeException("用户名或密码错误");
        }
        //获取userId,生成token,通过强制转换authentication.getPrincipal()的方式
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        //User中的Id为Long类型,createJWT()的参数为String类型,因此调用toString()进行类型转换
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        //把用户信息保存到redis
        redisCache.setCacheObject("bloglogin:" + userId, loginUser);
        //把token和userinfo封装后返回
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(loginUser.getUser(), UserInfoVo.class);
        loginUserVo loginUserVo = new loginUserVo(jwt, userInfoVo);
        return ResponseResult.okResult(loginUserVo);
    }

    @Override
    public ResponseResult logout() {
        //获取token 解析获取userId
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        redisCache.deleteObject("bloglogin:" +userId);
        return ResponseResult.okResult();
    }
}
