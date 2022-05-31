package com.mustar.controller;


import com.mustar.common.lang.Result;
import com.mustar.service.CommentsService;
import com.mustar.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
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
@RequestMapping("/comments")
public class CommentsController {
    @Autowired
    private CommentsService commentsService;
    //新增评论
    @PostMapping("/add")
    public Result addComments(@RequestBody Map<String,Object> comments, @RequestHeader String token){
        String userAccount = JwtUtil.checkToken(token);
        if (userAccount==null){
            return Result.error(-1,"未登录或登录已过期");
        }
        return commentsService.addComments(comments,userAccount);
    }

}
