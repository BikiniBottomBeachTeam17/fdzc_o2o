package com.mustar.service;

import com.mustar.common.lang.Result;
import com.mustar.entity.Likes;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mustar
 * @since 2022-05-30
 */
public interface LikesService extends IService<Likes> {

    Result like(String forumid, String userAccount);

    Result cancelLike(String forumid, String userAccount);
}
