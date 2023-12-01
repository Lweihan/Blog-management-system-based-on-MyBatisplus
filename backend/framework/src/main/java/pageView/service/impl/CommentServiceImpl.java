package pageView.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pageView.domain.ResponseResult;
import pageView.domain.entity.Comment;
import pageView.domain.vo.CommentVo;
import pageView.domain.vo.PageVo;
import pageView.enums.AppHttpCodeEnum;
import pageView.exception.SystemException;
import pageView.mapper.CommentMapper;
import pageView.service.CommentService;
import pageView.service.UserService;
import pageView.utils.BeanCopyUtils;
import pageView.utils.SecurityUtils;

import java.util.List;

/**
 * 评论表(Comment)表服务实现类
 *
 * @author 软件2001李威翰
 * @since 2022-12-19 11:24:54
 */
@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    private UserService userService;

    @Override
    public ResponseResult commentList(Long articleId, Integer pageNumber, Integer pageSize) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        //对articleId进行判断
        queryWrapper.eq(Comment::getArticleId, articleId);
        //对根评论rootId进行判断, -1表示为根评论
        queryWrapper.eq(Comment::getRootId, -1);
        //根据type确定是否为友链评论
        queryWrapper.eq(Comment::getType, 0);
        //根据CreateTime排序
        queryWrapper.orderByDesc(Comment::getCreateTime);
        //分页查询,默认pageNumber=1, pageSize=10
        pageNumber = 1;
        pageSize = 10;
        Page<Comment> page = new Page(pageNumber, pageSize);
        page(page, queryWrapper);
        List<CommentVo> commentVoList = toCommentVoList(page.getRecords());
        //查询所有子评论集合，并赋值
        for (CommentVo commentVo : commentVoList) {
            //查询对应子评论
            List<CommentVo> children = getChildren(commentVo.getId());
            //赋值
            commentVo.setChildren(children);
        }
        return ResponseResult.okResult(new PageVo(commentVoList, page.getTotal()));
    }

    @Override
    public ResponseResult addComment(Comment comment) {
        if (!StringUtils.hasText(comment.getContent())) {
            throw new SystemException(AppHttpCodeEnum.CONTENT_NOT_NULL);
        }
        try {
            Long userid = SecurityUtils.getUserId();
        } catch (Exception e) {
            throw new SystemException(AppHttpCodeEnum.NO_OPERATOR_AUTH);
        }
        save(comment);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult commentList(Integer pageNumber, Integer pageSize) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        //对type进行判断
        queryWrapper.eq(Comment::getType, 1);
        //对根评论rootId进行判断, -1表示为根评论
        queryWrapper.eq(Comment::getRootId, -1);
        //根据CreateTime排序
        queryWrapper.orderByDesc(Comment::getCreateTime);
        //分页查询,默认pageNumber=1, pageSize=10
        pageNumber = 1;
        pageSize = 10;
        Page<Comment> page = new Page(pageNumber, pageSize);
        page(page, queryWrapper);
        List<CommentVo> commentVoList = toCommentVoList(page.getRecords());
        //查询所有子评论集合，并赋值
        for (CommentVo commentVo : commentVoList) {
            //查询对应子评论
            List<CommentVo> children = getChildren(commentVo.getId());
            //赋值
            commentVo.setChildren(children);
        }
        return ResponseResult.okResult(new PageVo(commentVoList, page.getTotal()));
    }

    /**
     * @mean 根据评论的id查询对应的子评论的集合
     * @param id
     * @return
     */
    private List<CommentVo> getChildren(Long id) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getRootId, id);
        queryWrapper.orderByDesc(Comment::getCreateTime);
        List<Comment> commentList = list(queryWrapper);
        List<CommentVo> commentVos = toCommentVoList(commentList);
        return  commentVos;
    }

    /**
     * @mean 赋上createBy和toCommentUserName的值，并封装成Vo
     * @param list
     * @return
     */
    private List<CommentVo> toCommentVoList(List<Comment> list) {
        List<CommentVo> commentVos = BeanCopyUtils.copyBeanList(list, CommentVo.class);
        //遍历Vo集合
        for (CommentVo commentVo : commentVos) {
            //通过createBy查询用户的昵称并赋值
            String nickName = userService.getById(commentVo.getCreateBy()).getNickName();
            commentVo.setUsername(nickName);
            //通过toCommentUserId查询用户的昵称并赋值
            //先判断是否为根评论
            if (commentVo.getToCommentUserId() != -1) {
                String toCommentUserName = userService.getById(commentVo.getToCommentUserId()).getNickName();
                commentVo.setToCommentUserName(toCommentUserName);
            }
        }
        return commentVos;
    }
}

