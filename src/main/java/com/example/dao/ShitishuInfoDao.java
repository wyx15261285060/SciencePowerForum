package com.example.dao;

import com.example.entity.ShitishuInfo;
import com.example.vo.ShitishuInfoVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface ShitishuInfoDao extends Mapper<ShitishuInfo> {
    List<ShitishuInfoVo> findByName(@Param("name") String name);
    
    
    
}
