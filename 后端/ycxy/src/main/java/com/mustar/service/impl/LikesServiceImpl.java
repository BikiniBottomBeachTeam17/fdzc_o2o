package com.mustar.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.mustar.common.lang.Result;
import com.mustar.entity.Likes;
import com.mustar.entity.User;
import com.mustar.mapper.LikesMapper;
import com.mustar.mapper.UserMapper;
import com.mustar.service.LikesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mustar
 * @since 2022-05-30
 */
@Service
public class LikesServiceImpl extends ServiceImpl<LikesMapper, Likes> implements LikesService {

    @Autowired
    private LikesMapper likesMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public Result like(String forumid, String userAccount) {
        try {
            User user = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUserAccount, userAccount));
            Likes likes = new Likes();
            likes.setForumId(forumid);
            likes.setUserId(user.getUserId());
            likesMapper.insert(likes);
            return Result.success("点赞成功");
        }catch (Exception e){
            return Result.error(-1, "点赞失败");
        }
    }

    @Override
    public Result cancelLike(String forumid, String userAccount) {
        try {
            User user = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUserAccount, userAccount));
            likesMapper.delete(Wrappers.<Likes>lambdaQuery()
                    .eq(Likes::getForumId, forumid)
                    .eq(Likes::getUserId, user.getUserId()));
            return Result.success("取消点赞成功");
        }catch (Exception e){
            return Result.error(-1, "取消点赞失败");
        }
    }
}
