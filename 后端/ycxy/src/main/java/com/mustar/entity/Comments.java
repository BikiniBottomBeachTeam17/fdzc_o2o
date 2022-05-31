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
public class Comments implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 评论id
     */
    private String commentsId;

    /**
     * 评论json内容
     */
    @TableField("comments_contentJson")
    private String commentsContentjson;

    /**
     * 评论时间
     */
    @TableField("comments_createTime")
    private LocalDateTime commentsCreatetime;

    /**
     * 评论人
     */
    private String userId;

    /**
     * 回复帖子id
     */
    private String forumId;


}
