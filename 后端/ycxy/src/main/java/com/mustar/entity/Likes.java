package com.mustar.entity;

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
public class Likes implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 帖子id
     */
    private String forumId;

    /**
     * 点赞用户id
     */
    private String userId;


}
