package com.mustar.entity.vo;

import lombok.Data;

import java.io.Serializable;
@Data
public class ForumVo implements Serializable {
    private String forumId;
    private String forumContentjson;
    private String forumCreatetime;
    private String userId;
    private String userName;
    private String userPortrait;
}
