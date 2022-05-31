package com.mustar.service;

import com.mustar.common.lang.Result;
import com.mustar.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mustar
 * @since 2022-05-26
 */
public interface UserService extends IService<User> {
    //用户登录
    Result Login(User userInfo);
    //根据用户id获取用户信息
    Result getUserInfo(String userAccount);
    //用户注册
    Result Register(User userInfo);
    // 用户修改密码
    Result UpUserPwd(String userId, String oldPwd, String newPwd);
    // 用户修改信息
    Result UpUserInfo(User userInfo);
    // 验证用户账号是否存在
    Result checkUserAccount(String userAccount);
}
