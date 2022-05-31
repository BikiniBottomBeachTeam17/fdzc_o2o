package com.mustar.controller;


import com.mustar.common.lang.Result;
import com.mustar.service.LikesService;
import com.mustar.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author mustar
 * @since 2022-05-30
 */
@RestController
@RequestMapping("/likes")
public class LikesController {
    @Autowired
    private LikesService likesService;
    @PostMapping("/add")
    public Result like(@RequestParam String forumid, @RequestHeader String token){
        String userAccount= JwtUtil.checkToken(token);
        if (userAccount==null){
            return Result.error(-1,"未登录或登录已过期");
        }
        return likesService.like(forumid,userAccount);
    }
    @PostMapping("/del")
    public Result cancelLike(@RequestParam String forumid, @RequestHeader String token){
        String userAccount= JwtUtil.checkToken(token);
        if (userAccount==null){
            return Result.error(-1,"未登录或登录已过期");
        }
        return likesService.cancelLike(forumid,userAccount);
    }
}
