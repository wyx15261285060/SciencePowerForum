package com.example.service;

import cn.hutool.json.JSONUtil;
import com.example.dao.ShitishuInfoDao;
import org.springframework.stereotype.Service;
import com.example.entity.ShitishuInfo;
import com.example.entity.AuthorityInfo;
import com.example.entity.Account;
import com.example.vo.ShitishuInfoVo;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Value;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ShitishuInfoService {

    @Resource
    private ShitishuInfoDao shitishuInfoDao;

    public ShitishuInfo add(ShitishuInfo info) {
        shitishuInfoDao.insertSelective(info);
        return info;
    }

    public void delete(Long id) {
        shitishuInfoDao.deleteByPrimaryKey(id);
    }

    public void update(ShitishuInfo info) {
        shitishuInfoDao.updateByPrimaryKeySelective(info);
    }

    public ShitishuInfo findById(Long id) {
        return shitishuInfoDao.selectByPrimaryKey(id);
    }

    public List<ShitishuInfoVo> findAll() {
        return shitishuInfoDao.findByName("all");
    }

    public PageInfo<ShitishuInfoVo> findPage(String name, Integer pageNum, Integer pageSize, HttpServletRequest request) {
        PageHelper.startPage(pageNum, pageSize);
        List<ShitishuInfoVo> all = findAllPage(request, name);
        return PageInfo.of(all);
    }

    public List<ShitishuInfoVo> findAllPage(HttpServletRequest request, String name) {
		return shitishuInfoDao.findByName(name);
    }

}
