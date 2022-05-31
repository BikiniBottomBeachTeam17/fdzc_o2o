package com.mustar.service;

import com.mustar.common.lang.Result;
import com.mustar.entity.Comments;
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
public interface CommentsService extends IService<Comments> {
    Result addComments(Map<String, Object> comments, String userAccount);
}
