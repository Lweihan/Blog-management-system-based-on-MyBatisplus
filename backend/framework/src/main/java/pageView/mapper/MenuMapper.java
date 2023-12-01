package pageView.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import pageView.domain.entity.Menu;

import java.util.List;


/**
 * 菜单权限表(Menu)表数据库访问层
 *
 * @author 软件2001李威翰
 * @since 2022-12-23 09:53:58
 */
public interface MenuMapper extends BaseMapper<Menu> {

    List<String> selectPermsByUserId(Long userId);

    List<Menu> selectAllRouterMenu();

    List<Menu> selectRouterMenuTreeByUserId(Long userId);
}
