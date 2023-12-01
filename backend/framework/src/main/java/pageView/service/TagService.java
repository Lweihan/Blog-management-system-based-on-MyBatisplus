package pageView.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pageView.domain.ResponseResult;
import pageView.domain.dto.TagListDto;
import pageView.domain.entity.Tag;
import pageView.domain.vo.TagVo;

import java.util.List;


/**
 * 标签(Tag)表服务接口
 *
 * @author 软件2001李威翰
 * @since 2022-12-22 15:23:23
 */
public interface TagService extends IService<Tag> {
    ResponseResult pageTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto);

    ResponseResult addTag(Tag tag);

    ResponseResult deleteTag(Long id);

    ResponseResult getTag(Long id);

    ResponseResult updateTag(Tag tag);

    List<TagVo> listAllTag();
}

