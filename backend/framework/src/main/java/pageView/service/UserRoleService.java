package pageView.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pageView.domain.entity.UserRole;

import java.util.List;


/**
 * 用户和角色关联表(UserRole)表服务接口
 *
 * @author 软件2001李威翰
 * @since 2022-12-24 16:21:34
 */
public interface UserRoleService extends IService<UserRole> {

    List<String> getRoleIds(Long userId);
}

