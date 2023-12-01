package pageView.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pageView.domain.ResponseResult;
import pageView.domain.entity.Link;


/**
 * 友链(Link)表服务接口
 *
 * @author makejava
 * @since 2022-12-16 09:10:44
 */
public interface LinkService extends IService<Link> {
    ResponseResult getAllLink();

    ResponseResult listLink(Integer pageNum, Integer pageSize, String name, String status);

    ResponseResult addLink(Link link);

    ResponseResult getLink(Long id);

    ResponseResult updateLink(Link link);

    ResponseResult deleteLink(Long id);
}

