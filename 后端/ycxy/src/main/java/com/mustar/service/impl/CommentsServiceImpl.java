package com.mustar.service.impl;

import com.mustar.entity.Comments;
import com.mustar.mapper.CommentsMapper;
import com.mustar.service.CommentsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mustar
 * @since 2022-05-30
 */
@Service
public class CommentsServiceImpl extends ServiceImpl<CommentsMapper, Comments> implements CommentsService {

}
