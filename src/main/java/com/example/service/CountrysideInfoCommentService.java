package com.example.service;

import com.example.exception.CustomException;
import com.example.dao.CountrysideInfoCommentDao;
import org.springframework.stereotype.Service;
import com.example.entity.CountrysideInfoComment;
import com.example.vo.CountrysideInfoCommentVo;
import com.example.entity.Account;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CountrysideInfoCommentService {

    @Resource
    private CountrysideInfoCommentDao CountrysideInfoCommentDao;
    //添加农村振兴的信息
    public CountrysideInfoComment add(CountrysideInfoComment commentInfo, HttpServletRequest request) {
        Account user = (Account) request.getSession().getAttribute("user");
        if (user == null) {
            throw new CustomException("1001", "请先登录！");
        }
        commentInfo.setName(user.getName());
        commentInfo.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        CountrysideInfoCommentDao.insertSelective(commentInfo);
        return commentInfo;
    }

    public void delete(Long id) {
        CountrysideInfoCommentDao.deleteByPrimaryKey(id);
    }

    public void update(CountrysideInfoComment commentInfo) {
        commentInfo.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        CountrysideInfoCommentDao.updateByPrimaryKeySelective(commentInfo);
    }

    public CountrysideInfoComment findById(Long id) {
        return CountrysideInfoCommentDao.selectByPrimaryKey(id);
    }

    public List<CountrysideInfoCommentVo> findAll() {
        return CountrysideInfoCommentDao.findAllVo(null);
    }

    public PageInfo<CountrysideInfoCommentVo> findPage(String name, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<CountrysideInfoCommentVo> all = CountrysideInfoCommentDao.findAllVo(name);
        return PageInfo.of(all);
    }

    public List<CountrysideInfoCommentVo> findByForeignId (Long id) {
        List<CountrysideInfoCommentVo> all = CountrysideInfoCommentDao.findByForeignId(id, 0L);
        for (CountrysideInfoCommentVo reserveInfoVo : all) {
            Long parentId = reserveInfoVo.getId();
            List<CountrysideInfoCommentVo> children = new ArrayList<>(CountrysideInfoCommentDao.findByForeignId(id, parentId));
            reserveInfoVo.setChildren(children);
        }
        return all;
    }
}
