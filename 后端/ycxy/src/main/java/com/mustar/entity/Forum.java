package com.mustar.entity;

import java.time.LocalDateTime;
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
 * @since 2022-05-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Forum implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 帖子id
     */
    private String forumId;

    /**
     * 帖子json内容
     */
    @TableField("forum_contentJson")
    private String forumContentjson;

    /**
     * 帖子创建时间
     */
    @TableField("forum_createTime")
    private LocalDateTime forumCreatetime;

    /**
     * 帖子分类
     */
    private Integer forumClassify;

    /**
     * 发布人id
     */
    private String userId;


}
