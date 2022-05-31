package com.mustar.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.gson.JsonObject;
import com.mustar.common.lang.Result;
import com.mustar.entity.Comments;
import com.mustar.entity.User;
import com.mustar.mapper.CommentsMapper;
import com.mustar.mapper.UserMapper;
import com.mustar.service.CommentsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mustar
 * @since 2022-05-30
 */
@Service
public class CommentsServiceImpl extends ServiceImpl<CommentsMapper, Comments> implements CommentsService {
    @Autowired
    private CommentsMapper commentsMapper;
    @Autowired
    private UserMapper userMapper;
    @Override
    public Result addComments(Map<String, Object> comments, String userAccount) {
        //获取用户id
        User user=userMapper.selectOne(Wrappers.<User>lambdaQuery()
                .eq(User::getUserAccount,userAccount));
        //添加评论
        try {
            Comments comment=new Comments();
            comment.setCommentsId(IdUtil.simpleUUID());
            JSONObject temp = JSONUtil.parseObj(comments);
            JSONObject content=JSONUtil.createObj();
            content.putOnce("content",temp.getStr("content"));
            content.putOnce("imgList",temp.getStr("imgList"));
            comment.setCommentsContentjson(content.toString());
            comment.setCommentsCreatetime(LocalDateTime.now());
            comment.setUserId(user.getUserId());
            comment.setForumId(temp.getStr("forumId"));
            commentsMapper.insert(comment);
            return Result.success("评论成功");
        }catch (Exception e){
            e.printStackTrace();
            return Result.error(-1,"添加评论失败");
        }
    }
}
