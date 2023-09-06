package com.example.service;

import com.example.exception.CustomException;
import com.example.dao.ShitishuInfoCommentDao;
import org.springframework.stereotype.Service;
import com.example.entity.ShitishuInfoComment;
import com.example.vo.ShitishuInfoCommentVo;
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
public class ShitishuInfoCommentService {

    @Resource
    private ShitishuInfoCommentDao shitishuInfoCommentDao;

    public ShitishuInfoComment add(ShitishuInfoComment commentInfo, HttpServletRequest request) {
        Account user = (Account) request.getSession().getAttribute("user");
        if (user == null) {
            throw new CustomException("1001", "请先登录！");
        }
        commentInfo.setName(user.getName());
        commentInfo.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        shitishuInfoCommentDao.insertSelective(commentInfo);
        return commentInfo;
    }

    public void delete(Long id) {
        shitishuInfoCommentDao.deleteByPrimaryKey(id);
    }

    public void update(ShitishuInfoComment commentInfo) {
        commentInfo.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        shitishuInfoCommentDao.updateByPrimaryKeySelective(commentInfo);
    }

    public ShitishuInfoComment findById(Long id) {
        return shitishuInfoCommentDao.selectByPrimaryKey(id);
    }

    public List<ShitishuInfoCommentVo> findAll() {
        return shitishuInfoCommentDao.findAllVo(null);
    }

    public PageInfo<ShitishuInfoCommentVo> findPage(String name, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ShitishuInfoCommentVo> all = shitishuInfoCommentDao.findAllVo(name);
        return PageInfo.of(all);
    }

    public List<ShitishuInfoCommentVo> findByForeignId (Long id) {
        List<ShitishuInfoCommentVo> all = shitishuInfoCommentDao.findByForeignId(id, 0L);
        for (ShitishuInfoCommentVo reserveInfoVo : all) {
            Long parentId = reserveInfoVo.getId();
            List<ShitishuInfoCommentVo> children = new ArrayList<>(shitishuInfoCommentDao.findByForeignId(id, parentId));
            reserveInfoVo.setChildren(children);
        }
        return all;
    }
}
