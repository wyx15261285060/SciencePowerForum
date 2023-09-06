package com.example.service;

import com.example.dao.WendaInfoDao;
import com.example.entity.WendaInfo;
import com.example.vo.WendaInfoVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class WendaInfoService {

    @Resource
    private WendaInfoDao wendaInfoDao;

    public WendaInfo add(WendaInfo wendaInfo) {
        wendaInfoDao.insertSelective(wendaInfo);
        return wendaInfo;
    }

    public void delete(Long id) {
        wendaInfoDao.deleteByPrimaryKey(id);
    }

    public void update(WendaInfo wendaInfo) {
        wendaInfoDao.updateByPrimaryKeySelective(wendaInfo);
    }

    public WendaInfo findById(Long id) {
        return wendaInfoDao.selectByPrimaryKey(id);
    }

    public List<WendaInfoVo> findAll() {
        List<WendaInfoVo> all = wendaInfoDao.findByParentId(0L);
        for (WendaInfoVo wendaInfoVo : all) {
            Long id = wendaInfoVo.getId();
            List<WendaInfoVo> children = new ArrayList<>(wendaInfoDao.findByParentId(id));
            wendaInfoVo.setChildren(children);
        }
        return all;
    }

    public PageInfo<WendaInfoVo> findPage(String name, Integer pageNum, Integer pageSize, HttpServletRequest request) {
        PageHelper.startPage(pageNum, pageSize);
        List<WendaInfoVo> all = findAllPage(request, name);
        return PageInfo.of(all);
    }

    public List<WendaInfoVo> findAllPage(HttpServletRequest request, String name) {
		return wendaInfoDao.findByName(name);
    }

}
