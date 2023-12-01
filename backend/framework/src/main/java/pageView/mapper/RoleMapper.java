package pageView.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import pageView.domain.entity.Role;

import java.util.List;


/**
 * 角色信息表(SysRole)表数据库访问层
 *
 * @author 软件2001李威翰
 * @since 2022-12-23 10:01:10
 */
public interface RoleMapper extends BaseMapper<Role> {

    List<String> selectRoleKeyByUserId(Long userId);
}
