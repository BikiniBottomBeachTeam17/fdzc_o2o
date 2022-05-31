package com.mustar.controller;


import cn.hutool.core.map.MapUtil;
import com.mustar.common.lang.Result;
import com.mustar.entity.User;
import com.mustar.service.UserService;
import com.mustar.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author mustar
 * @since 2022-05-26
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 验证token并更新
     * @param token token
     * @return 更新结果
     */
    @PostMapping("/upToken")
    private Result getUpToken(@RequestHeader String token){
        String userAccount = JwtUtil.checkToken(token);
        if(userAccount.equals("-1")){
            return Result.error(-1,"未登录或登录已过期");
        }
        return Result.success(MapUtil.builder()
                .put("userAccount", userAccount)
                .put("token", JwtUtil.createToken(userAccount))
                .map());
    }

    /**
     * 用户登录
     * @param userInfo 用户信息
     * @return 登录结果
     */
    @PostMapping("/login")
    private Result Logon(@RequestBody User userInfo){
        return userService.Login(userInfo);
    }

    /**
     * 验证用户名是否存在
     * @param userAccount 用户账号
     * @return 查询用户结果
     */
    @GetMapping("/checkUserAccount")
    private Result checkUserAccount(@RequestParam(defaultValue = "") String userAccount){
        return userService.checkUserAccount(userAccount);
    }

    /**
     *用户注册
     * @param userInfo 用户信息
     * @return 注册结果
     */
    @PostMapping("/register")
    private Result Register(@RequestBody User userInfo){
        return userService.Register(userInfo);
    }

    /**
     * 修改用户密码
     * @param userInfo 用户信息
     * @return 修改密码结果
     */
    @PostMapping("/UpUserPwd")
    private Result UpUserPwd(@RequestBody() Map<String, String> userInfo){
        String userId = userInfo.get("userId");
        String oldPwd = userInfo.get("oldPwd");
        String newPwd = userInfo.get("newPwd");
        return userService.UpUserPwd(userId,oldPwd,newPwd);
    }

    /**
     * 修改用户信息,只允许用户修改用户名，用户头像，用户性别，邮箱及手机号
     * @param userInfo 用户信息
     * @param token token
     * @return 修改用户信息结果
     */
    @PostMapping("/UpUserInfo")
    private Result UpUserInfo(@RequestBody User userInfo,@RequestHeader(defaultValue = "") String token){
        if (JwtUtil.checkToken(token).equals("-1")){
            return Result.error(-1,"未登录或登录状态已过期，请重新登录");
        }
        return userService.UpUserInfo(userInfo);
    }

    /**
     * 根据用户账号获取用户信息
     * @param userAccount 用户id
     * @return 用户信息
     */
    @GetMapping("/getUserInfo")
    private Result getUserInfo(@RequestParam(defaultValue = "") String userAccount){
        return userService.getUserInfo(userAccount);
    }
}
