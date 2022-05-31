package com.mustar.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author mustar
 * @since 2022-05-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户唯一标识
     */
    private String userId;

    /**
     * 用户账号
     */
    private String userAccount;

    /**
     * 用户密码
     */
    private String userPassword;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户头像地址
     */
    private String userPortrait;

    /**
     * 用户性别
     */
    private Integer userGender;

    /**
     * 用户邮箱
     */
    private String userEmail;

    /**
     * 用户手机号
     */
    private String userPhone;

    /**
     * 学号绑定
     */
    @TableField("user_stuID")
    private String userStuid;

    /**
     * 微信绑定
     */
    @TableField("user_wxID")
    private String userWxid;

    /**
     * 学生认证状态
     */
    @TableField("user_isStudent")
    private Integer userIsstudent;

    /**
     * 商家认证状态
     */
    @TableField("user_isMerchant")
    private Integer userIsmerchant;

    /**
     * 兼职认证状态
     */
    @TableField("user_isPartTime")
    private Integer userIsparttime;

    /**
     * 用户余额
     */
    private Double userBalance;

    /**
     * 用户状态
     */
    private Integer userStatus;
    /**
     *  用户注册时间
     */
    @TableField("user_CreateTime")
    private String userCreatetime;


}
