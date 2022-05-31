package com.mustar.controller;


import cn.hutool.core.util.IdUtil;
import cn.hutool.jwt.JWTUtil;
import com.mustar.common.lang.Result;
import com.mustar.entity.Forum;
import com.mustar.service.ForumService;
import com.mustar.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author mustar
 * @since 2022-05-30
 */
@RestController
@RequestMapping("/forum")
public class ForumController {
    @Autowired
    private ForumService forumService;

    // 分页获取所有帖子
    @GetMapping("/getAllForum")
    public Result getAllForum(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestHeader(defaultValue = "") String token) {
        return forumService.getForumList(pageNum, pageSize,token);
    }
    // 根据帖子id获取帖子信息
    @GetMapping("/getForumById")
    public Result getForumById(@RequestParam String id,@RequestHeader(defaultValue = "") String token) {
        return forumService.getForumById(id,token);
    }

    //新增帖子
    @PostMapping("/addForum")
    public Result addForum(@RequestBody Map<String,Object> forum, @RequestHeader String token) {
        String userAccount=JwtUtil.checkToken(token);
        if (userAccount==null){
            return Result.error(-1,"未登录或登录已过期");
        }
        return forumService.addForum(forum,userAccount);
    }

    //根据用户账号获取帖子
    @GetMapping("/getForumByUserId")
    public Result getForumByUserId(@RequestParam String userAccount,@RequestHeader(defaultValue = "") String token) {
        return forumService.getForumByUserAccount(userAccount,token);
    }
}
