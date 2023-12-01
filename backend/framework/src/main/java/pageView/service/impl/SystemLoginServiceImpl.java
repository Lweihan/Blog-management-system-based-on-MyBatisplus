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
import pageView.domain.vo.UserInfoVo;
import pageView.domain.vo.loginUserVo;
import pageView.service.MLoginService;
import pageView.utils.BeanCopyUtils;
import pageView.utils.JwtUtil;
import pageView.utils.RedisCache;
import pageView.utils.SecurityUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class SystemLoginServiceImpl implements MLoginService {
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
        redisCache.setCacheObject("login:" + userId, loginUser);
        Map<String, String> map = new HashMap<>();
        map.put("token", jwt);
        return ResponseResult.okResult(map);
    }

    @Override
    public ResponseResult logout() {
        //获取当前登录的用户id
        Long userId = SecurityUtils.getUserId();
        //删除redis中对应的值
        redisCache.deleteObject("login:"+userId);
        return ResponseResult.okResult();
    }
}
