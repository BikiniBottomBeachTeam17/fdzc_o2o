package com.mustar.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.gson.JsonObject;
import com.mustar.common.lang.Result;
import com.mustar.entity.Comments;
import com.mustar.entity.Forum;
import com.mustar.entity.Likes;
import com.mustar.entity.User;
import com.mustar.entity.vo.ForumVo;
import com.mustar.mapper.CommentsMapper;
import com.mustar.mapper.ForumMapper;
import com.mustar.mapper.LikesMapper;
import com.mustar.mapper.UserMapper;
import com.mustar.service.ForumService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mustar
 * @since 2022-05-30
 */
@Service
public class ForumServiceImpl extends ServiceImpl<ForumMapper, Forum> implements ForumService {
    @Autowired
    private ForumMapper forumMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private LikesMapper likesMapper;
    @Autowired
    private CommentsMapper commentsMapper;

    //获取论坛帖子列表
    @Override
    public Result getForumList(Integer pageNum, Integer pageSize) {
        IPage<Forum> forumPage=forumMapper.selectPage(new Page<>(pageNum,pageSize),new QueryWrapper<Forum>().orderByDesc("forum_createtime"));
        JSONObject resJson=JSONUtil.createObj();
        for(Forum forum:forumPage.getRecords()){
            //获取帖子点赞数量
            int likeNum=likesMapper.selectCount(Wrappers.<Likes>lambdaQuery()
                    .eq(Likes::getForumId,forum.getForumId()));
            //获取帖子评论数量
            int commentNum=commentsMapper.selectCount(Wrappers.<Comments>lambdaQuery()
                    .eq(Comments::getForumId,forum.getForumId()));
            User user=userMapper.selectOne(Wrappers.<User>lambdaQuery()
                    .eq(User::getUserId,forum.getUserId()));
            JSONObject jsonObject=JSONUtil.createObj();
            jsonObject.putOnce("id",forum.getForumId());
            //作者id
            jsonObject.putOnce("authorAccount",user.getUserAccount());
            jsonObject.putOnce("userPortrait",user.getUserPortrait());
            jsonObject.putOnce("userName",user.getUserName());
            long until = forum.getForumCreatetime().until(LocalDateTime.now(), ChronoUnit.SECONDS);
            if (until < 60) {
                jsonObject.putOnce("pushTime", "刚刚");
            }else if (until < 3600) {
                jsonObject.putOnce("pushTime", until / 60 + "分钟前");
            }else if (until < 3600 * 24) {
                jsonObject.putOnce("pushTime", until / 3600 + "小时前");
            }else if (until < 3600 * 24 * 3) {
                jsonObject.putOnce("pushTime", until / (3600 * 24) + "天前");
            }else {
                jsonObject.putOnce("pushTime", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(forum.getForumCreatetime()));
            }
            JSONObject tempJsonObject = JSONUtil.parseObj(forum.getForumContentjson());
            jsonObject.putOnce("content",tempJsonObject.getStr("content"));
            jsonObject.putOnce("dyImgs",tempJsonObject.get("imgList"));
            jsonObject.putOnce("like",likeNum);
            jsonObject.putOnce("comment",commentNum);
            resJson.append("forumList",jsonObject);
        }
        return Result.success(resJson);
    }

    @Override
    public Result getForumById(String id) {
        //获取帖子信息
        Forum forum=forumMapper.selectOne(Wrappers.<Forum>lambdaQuery()
                .eq(Forum::getForumId,id));
        //获取帖子作者信息
        User user=userMapper.selectOne(Wrappers.<User>lambdaQuery()
                .eq(User::getUserId,forum.getUserId()));
        //获取帖子点赞数量
        int likeNum=likesMapper.selectCount(Wrappers.<Likes>lambdaQuery()
                .eq(Likes::getForumId,forum.getForumId()));
        //获取帖子评论数量
        int commentNum=commentsMapper.selectCount(Wrappers.<Comments>lambdaQuery()
                .eq(Comments::getForumId,forum.getForumId()));
        List<Comments> comments = commentsMapper.selectList(Wrappers.<Comments>lambdaQuery()
                .eq(Comments::getForumId, forum.getForumId()).orderByAsc(Comments::getCommentsCreatetime));
        //创建返回结果
        JSONObject resJson=JSONUtil.createObj();
        //获取帖子id
        resJson.putOnce("id",forum.getForumId());
        //作者id
        resJson.putOnce("authorAccount",user.getUserAccount());
        //获取作者头像
        resJson.putOnce("userPortrait",user.getUserPortrait());
        //获取作者名称
        resJson.putOnce("userName",user.getUserName());
        //获取帖子发布时间
        long until = forum.getForumCreatetime().until(LocalDateTime.now(), ChronoUnit.SECONDS);
        if (until < 60) {
            resJson.putOnce("pushTime", "刚刚");
        }else if (until < 3600) {
            resJson.putOnce("pushTime", until / 60 + "分钟前");
        }else if (until < 3600 * 24) {
            resJson.putOnce("pushTime", until / 3600 + "小时前");
        }else if (until < 3600 * 24 * 3) {
            resJson.putOnce("pushTime", until / (3600 * 24) + "天前");
        }else {
            resJson.putOnce("pushTime", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(forum.getForumCreatetime()));
        }
        JSONObject tempJsonObject = JSONUtil.parseObj(forum.getForumContentjson());
        //获取帖子内容
        resJson.putOnce("content",tempJsonObject.getStr("content"));
        //获取帖子图片
        resJson.putOnce("dyImgs",tempJsonObject.get("imgList"));
        //获取帖子点赞数量
        resJson.putOnce("like",likeNum);
        //获取帖子评论数量
        resJson.putOnce("comment",commentNum);
        //获取评论列表
        for(Comments comment:comments){
            JSONObject jsonObject=JSONUtil.createObj();
            long time=comment.getCommentsCreatetime().until(LocalDateTime.now(), ChronoUnit.SECONDS);
            //获取评论id
            jsonObject.putOnce("id",comment.getCommentsId());
            User commentUser=userMapper.selectOne(Wrappers.<User>lambdaQuery()
                    .eq(User::getUserId,comment.getUserId()));
            //获取评论者头像
            jsonObject.putOnce("userPortrait",commentUser.getUserPortrait());
            //获取评论者名称
            jsonObject.putOnce("userName",commentUser.getUserName());
            //获取评论时间
            if (time < 60) {
                jsonObject.putOnce("pushTime", "刚刚");
            }else if (time < 3600) {
                jsonObject.putOnce("pushTime", time / 60 + "分钟前");
            }else if (time < 3600 * 24) {
                jsonObject.putOnce("pushTime", time / 3600 + "小时前");
            }else if (time < 3600 * 24 * 3) {
                jsonObject.putOnce("pushTime", time / (3600 * 24) + "天前");
            }else {
                jsonObject.putOnce("pushTime", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(comment.getCommentsCreatetime()));
            }
            //获取评论内容
            JSONObject tempJsonObject1 = JSONUtil.parseObj(comment.getCommentsContentjson());
            jsonObject.putOnce("content",tempJsonObject1.getStr("content"));
            resJson.append("comList",jsonObject);
        }
        return Result.success(resJson);
    }

    //添加帖子
    @Override
    public Result addForum(Map<String,Object> forum, String userAccount) {
        User user=userMapper.selectOne(Wrappers.<User>lambdaQuery()
                .eq(User::getUserAccount,userAccount));
        try {
            Forum addForum=new Forum();
            addForum.setForumId(IdUtil.simpleUUID());
            addForum.setForumContentjson(JSONUtil.parseObj(forum).toString());
            addForum.setForumCreatetime(LocalDateTime.now());
            addForum.setUserId(user.getUserId());
            forumMapper.insert(addForum);
            return Result.success();
        }catch (Exception e) {
            e.printStackTrace();
            return Result.error(-1,"添加失败");
        }
    }

    //根据用户账号获得帖子列表
    @Override
    public Result getForumByUserAccount(String userAccount) {
        User user=userMapper.selectOne(Wrappers.<User>lambdaQuery()
                .eq(User::getUserAccount,userAccount));
        List<Forum> forums=forumMapper.selectList(Wrappers.<Forum>lambdaQuery()
                .eq(Forum::getUserId,user.getUserId()).orderByDesc(Forum::getForumCreatetime));
        JSONObject resJson=JSONUtil.createObj();
        for(Forum forum:forums){
            int likeNum=likesMapper.selectCount(Wrappers.<Likes>lambdaQuery()
                    .eq(Likes::getForumId,forum.getForumId()));
            //获取帖子评论数量
            int commentNum=commentsMapper.selectCount(Wrappers.<Comments>lambdaQuery()
                    .eq(Comments::getForumId,forum.getForumId()));
            JSONObject jsonObject=JSONUtil.createObj();
            jsonObject.putOnce("id",forum.getForumId());
            //作者id
            jsonObject.putOnce("authorAccount",user.getUserAccount());
            jsonObject.putOnce("userPortrait",user.getUserPortrait());
            jsonObject.putOnce("userName",user.getUserName());
            long until = forum.getForumCreatetime().until(LocalDateTime.now(), ChronoUnit.SECONDS);
            if (until < 60) {
                jsonObject.putOnce("pushTime", "刚刚");
            }else if (until < 3600) {
                jsonObject.putOnce("pushTime", until / 60 + "分钟前");
            }else if (until < 3600 * 24) {
                jsonObject.putOnce("pushTime", until / 3600 + "小时前");
            }else if (until < 3600 * 24 * 3) {
                jsonObject.putOnce("pushTime", until / (3600 * 24) + "天前");
            }else {
                jsonObject.putOnce("pushTime", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(forum.getForumCreatetime()));
            }
            JSONObject tempJsonObject = JSONUtil.parseObj(forum.getForumContentjson());
            jsonObject.putOnce("content",tempJsonObject.getStr("content"));
            jsonObject.putOnce("dyImgs",tempJsonObject.get("imgList"));
            jsonObject.putOnce("like",likeNum);
            jsonObject.putOnce("comment",commentNum);
            resJson.append("forumList",jsonObject);
        }
        return Result.success(resJson);
    }
}
