package com.example.service;

import cn.hutool.core.collection.CollectionUtil;
import com.example.dao.CountrysideInfoDao;
import com.example.entity.CountrysideInfo;
import com.example.vo.CountrysideInfoVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CountrysideInfoService {

    @Resource
    private CountrysideInfoDao CountrysideInfoDao;

    public CountrysideInfo add(CountrysideInfo info) {
        CountrysideInfoDao.insertSelective(info);
        return info;
    }

    public void delete(Long id) {
        CountrysideInfoDao.deleteByPrimaryKey(id);
    }

    public void update(CountrysideInfo info) {
        CountrysideInfoDao.updateByPrimaryKeySelective(info);
    }

    public CountrysideInfoVo findById(Long id) {
        List<CountrysideInfoVo> list = CountrysideInfoDao.findByNameAndId(null, id);
        if (!CollectionUtil.isEmpty(list)) {
            return list.get(0);
        }
        return new CountrysideInfoVo();
    }

    public List<CountrysideInfoVo> findAll() {
        return CountrysideInfoDao.findByNameAndId("all", null);
    }

    public PageInfo<CountrysideInfoVo> findPage(String name, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<CountrysideInfoVo> info = CountrysideInfoDao.findByNameAndId(name, null);
        return PageInfo.of(info);
    }
}
