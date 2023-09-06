package com.example.dao;

import com.example.entity.CountrysideInfo;
import com.example.vo.CountrysideInfoVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface CountrysideInfoDao extends Mapper<CountrysideInfo> {
    List<CountrysideInfoVo> findByNameAndId(@Param("name") String name, @Param("id") Long id);
}
