package com.mustar.service;

import com.mustar.common.lang.Result;
import com.mustar.entity.Forum;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mustar
 * @since 2022-05-30
 */
public interface ForumService extends IService<Forum> {
    //获取论坛帖子列表
    Result getForumList(Integer pageNum, Integer pageSize);

    Result getForumById(String id);

    Result addForum(Map<String, Object> forum, String userAccount);

    Result getForumByUserAccount(String userAccount);
}
