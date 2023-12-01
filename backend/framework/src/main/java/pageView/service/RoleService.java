package pageView.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pageView.domain.ResponseResult;
import pageView.domain.dto.RoleDto;
import pageView.domain.entity.Role;

import java.util.List;


/**
 * 角色信息表(SysRole)表服务接口
 *
 * @author 软件2001李威翰
 * @since 2022-12-23 10:01:10
 */
public interface RoleService extends IService<Role> {
    List<String> selectRoleKeyByUserId(Long id);

    ResponseResult listRole(Integer pageNum, Integer pageSize, String roleName, String status);

    ResponseResult changeStatus(RoleDto roleDto);

    ResponseResult listAllRole();

    List<Role> roles(List<String> roleIds);
}

