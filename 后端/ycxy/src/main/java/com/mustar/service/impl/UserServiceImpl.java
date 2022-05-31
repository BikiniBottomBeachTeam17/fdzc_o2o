package com.mustar.service.impl;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.mustar.common.lang.Result;
import com.mustar.entity.User;
import com.mustar.mapper.UserMapper;
import com.mustar.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mustar.utils.JwtUtil;
import javafx.scene.input.DataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Formatter;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mustar
 * @since 2022-05-26
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    UserMapper userMapper;

    // 用户登录
    @Override
    public Result Login(User userInfo) {
        User user = userMapper.selectOne(Wrappers.<User>lambdaQuery()
                .eq(User::getUserAccount, userInfo.getUserAccount())
                .eq(User::getUserPassword, userInfo.getUserPassword()));
        if (user == null) {
            return Result.error(-1,"账号或密码错误");
        }
        return Result.success(MapUtil.builder()
                .put("userAccount", user.getUserAccount())
                .put("token", JwtUtil.createToken(user.getUserAccount()))
                .map());
    }

    // 用户注册
    @Override
    public Result Register(User userInfo) {
        //判断账号是否存在
        User user = userMapper.selectOne(Wrappers.<User>lambdaQuery()
                .eq(User::getUserAccount, userInfo.getUserAccount()));
        if (user != null) {
            return Result.error(-1,"账号已存在");
        }
        user=new User();
        user.setUserId(IdUtil.simpleUUID());
        user.setUserAccount(userInfo.getUserAccount());
        user.setUserPassword(userInfo.getUserPassword());
        if (userInfo.getUserPortrait() != null&&!userInfo.getUserPortrait().equals("")){
            user.setUserPortrait(userInfo.getUserPortrait());
        }
        user.setUserName(userInfo.getUserName());
        user.setUserGender(userInfo.getUserGender());
        user.setUserEmail(userInfo.getUserEmail());
        user.setUserPhone(userInfo.getUserPhone());
        user.setUserCreatetime(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        try {
            userMapper.insert(user);
        }catch (Exception e){
            return Result.error(-1,"注册失败");
        }
        return Result.success("注册成功");
    }

    // 用户修改密码
    @Override
    public Result UpUserPwd(String userId, String oldPwd, String newPwd) {
        User user = userMapper.selectOne(Wrappers.<User>lambdaQuery()
                .eq(User::getUserId, userId));
        if(oldPwd.equals(user.getUserPassword())){
            user.setUserPassword(newPwd);
            try{
                userMapper.update(user, Wrappers.<User>lambdaQuery()
                        .eq(User::getUserId, userId));
                return Result.success("修改成功");
            }catch (Exception e){
                return Result.error(-1,"修改失败");
            }
        }
        return Result.error(-1,"原密码错误");
    }

    // 用户修改信息
    @Override
    public Result UpUserInfo(User userInfo) {
        User user=userMapper.selectOne(Wrappers.<User>lambdaQuery()
                .eq(User::getUserId,userInfo.getUserId()));
        user.setUserName(userInfo.getUserName());
        user.setUserPortrait(userInfo.getUserPortrait());
        user.setUserGender(userInfo.getUserGender());
        user.setUserEmail(userInfo.getUserEmail());
        user.setUserPhone(userInfo.getUserPhone());
        try{
            userMapper.update(user, Wrappers.<User>lambdaQuery()
                    .eq(User::getUserId, userInfo.getUserId()));
            return Result.success("修改成功");
        }catch (Exception e){
            return Result.error(-1,"修改失败");
        }
    }

    //验证用户账号是否存在
    @Override
    public Result checkUserAccount(String userAccount) {
        User user = userMapper.selectOne(Wrappers.<User>lambdaQuery()
                .eq(User::getUserAccount, userAccount));
        if (user != null) {
            return Result.error(-1,"账号已存在");
        }
        return Result.success("账号可用");
    }

    // 根据用户id获取用户信息
    @Override
    public Result getUserInfo(String userAccount) {
        User user = userMapper.selectOne(Wrappers.<User>lambdaQuery()
                .eq(User::getUserAccount, userAccount));
        if (user==null){
            return Result.error(-1,"用户不存在");
        }
        return Result.success(MapUtil.builder()
                .put("userId",user.getUserId())
                .put("userAccount", user.getUserAccount())
                .put("userName", user.getUserName())
                .put("userPortrait", user.getUserPortrait())
                .put("userGender", user.getUserGender())
                .put("userEmail", user.getUserEmail())
                .put("userPhone", user.getUserPhone())
                .put("userStuid", user.getUserStuid())
                .put("userWxid",user.getUserWxid())
                .put("userIsstudent",user.getUserIsstudent())
                .put("userIsmerchant",user.getUserIsmerchant())
                .put("userIsparttime",user.getUserIsparttime())
                .map());
    }



}