package com.example.dao;

import com.example.entity.ShitishuInfoComment;
import com.example.vo.ShitishuInfoCommentVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface ShitishuInfoCommentDao extends Mapper<ShitishuInfoComment> {
    List<ShitishuInfoCommentVo> findAllVo(@Param("name") String name);
    List<ShitishuInfoCommentVo> findByForeignId (@Param("id") Long id, @Param("parentId") Long parentId);
}
