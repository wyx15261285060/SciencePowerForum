package com.example.dao;

import com.example.entity.CountrysideInfoComment;
import com.example.vo.CountrysideInfoCommentVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface CountrysideInfoCommentDao extends Mapper<CountrysideInfoComment> {
    List<CountrysideInfoCommentVo> findAllVo(@Param("name") String name);
    List<CountrysideInfoCommentVo> findByForeignId (@Param("id") Long id, @Param("parentId") Long parentId);
}
