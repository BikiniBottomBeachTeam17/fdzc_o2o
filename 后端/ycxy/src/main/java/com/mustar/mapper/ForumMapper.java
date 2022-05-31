package com.mustar.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mustar.entity.Forum;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mustar.entity.vo.ForumVo;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author mustar
 * @since 2022-05-30
 */
public interface ForumMapper extends BaseMapper<Forum> {
//    @Select("SELECT forum_id,forum_contentJson,forum_createTime,forum.user_id,user_name," +
//            "user_portrait FROM forum LEFT JOIN user ON forum.user_id=user.user_id " +
//            "order by forum_createTime desc")
//    IPage<Forum> getForumList(Page<ForumVo> page);
}
