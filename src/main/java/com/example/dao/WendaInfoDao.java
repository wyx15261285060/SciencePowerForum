package com.example.dao;

import com.example.entity.WendaInfo;
import com.example.vo.WendaInfoVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface WendaInfoDao extends Mapper<WendaInfo> {
    List<WendaInfoVo> findByName(@Param("name") String name);


    List<WendaInfoVo> findByParentId(Long parentId);
}
