package pageView.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pageView.domain.ResponseResult;
import pageView.domain.entity.LoginUser;
import pageView.domain.entity.Menu;
import pageView.domain.entity.User;
import pageView.domain.vo.AdminUserInfo;
import pageView.domain.vo.RoutersVo;
import pageView.domain.vo.UserInfoVo;
import pageView.enums.AppHttpCodeEnum;
import pageView.exception.SystemException;
import pageView.service.LoginService;
import pageView.service.MLoginService;
import pageView.service.MenuService;
import pageView.service.RoleService;
import pageView.utils.BeanCopyUtils;
import pageView.utils.SecurityUtils;

import java.util.List;

@RestController
public class LoginController {
    @Autowired
    private MLoginService mloginService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleService roleService;

    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user) {
        if (!StringUtils.hasText(user.getUserName())) {
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        ResponseResult result = mloginService.login(user);
        return result;
    }

    @GetMapping("getInfo")
    public ResponseResult<AdminUserInfo> getInfo() {
        //获取当前登录的用户
        LoginUser loginUser = SecurityUtils.getLoginUser();
        //根据用户id查询权限信息
        List<String> perms = menuService.selectPermsByUserId(loginUser.getUser().getId());
        //根据用户id查询角色信息
        List<String> roleKeyList = roleService.selectRoleKeyByUserId(loginUser.getUser().getId());
        //封装数据返回
        User user = loginUser.getUser();
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(user, UserInfoVo.class);
        AdminUserInfo adminUserInfo = new AdminUserInfo(perms, roleKeyList, userInfoVo);
        return ResponseResult.okResult(adminUserInfo);
    }

    @GetMapping("getRouters")
    public ResponseResult<RoutersVo> getRouters(){
        Long userId = SecurityUtils.getUserId();
        //查询menu 结果是tree的形式
        List<Menu> menus = menuService.selectRouterMenuTreeByUserId(userId);
        //封装数据返回
        return ResponseResult.okResult(new RoutersVo(menus));
    }

    @PostMapping("/user/logout")
    public ResponseResult logout(){
        return mloginService.logout();
    }
}
