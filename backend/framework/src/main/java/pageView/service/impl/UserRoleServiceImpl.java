package pageView.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import pageView.domain.entity.UserRole;
import pageView.mapper.UserRoleMapper;
import pageView.service.UserRoleService;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户和角色关联表(UserRole)表服务实现类
 *
 * @author 软件2001李威翰
 * @since 2022-12-24 16:21:34
 */
@Service("userRoleService")
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {
    @Override
    public List<String> getRoleIds(Long userId) {
        LambdaQueryWrapper<UserRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserRole::getUserId, userId);
        List<UserRole> userRoles = list(queryWrapper);
        List<String> roleIds = new ArrayList<>();
        for (UserRole userRole : userRoles) {
            String roleId = userRole.getRoleId().toString();
            roleIds.add(roleId);
        }
        return roleIds;
    }
}

