package com.example.controller;

import com.example.common.Result;
import com.example.entity.Account;
import com.example.entity.CountrysideInfo;
import com.example.service.CountrysideInfoService;
import com.example.vo.CountrysideInfoVo;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/CountrysideInfo")
public class CountrysideInfoController {

    @Resource
    private NxSystemFileController nxSystemFileController;
    @Resource
    private CountrysideInfoService CountrysideInfoService;
    @PostMapping
    public Result<CountrysideInfo> add(@RequestBody CountrysideInfo info, HttpServletRequest request) {
        Account account = (Account) request.getSession().getAttribute("user");
        info.setUserName(account.getName());
        info.setLevel(account.getLevel());
        info.setUploadUserId(account.getId());
        CountrysideInfoService.add(info);
        return Result.success(info);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id, HttpServletRequest request) {
        Account account = (Account) request.getSession().getAttribute("user");
        CountrysideInfo info = CountrysideInfoService.findById(id);
        if (!account.getLevel().equals(info.getLevel()) || !account.getId().equals(info.getUploadUserId())) {
            return Result.error("1001", "不能删除他人的记录");
        }
        CountrysideInfoService.delete(id);
        // 删除对应文件记录
        if (info.getFileId() != null) {
            nxSystemFileController.deleteFile(info.getFileId().toString());
        }
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody CountrysideInfo info, HttpServletRequest request) {
        Account account = (Account) request.getSession().getAttribute("user");
        if (!account.getLevel().equals(info.getLevel()) || !account.getId().equals(info.getUploadUserId())) {
            return Result.error("1001", "不能修改他人的记录");
        }
        CountrysideInfoService.update(info);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<CountrysideInfoVo> detail(@PathVariable Long id) {
        CountrysideInfoVo info = CountrysideInfoService.findById(id);
        return Result.success(info);
    }

    @GetMapping
    public Result<List<CountrysideInfoVo>> all() {
        return Result.success(CountrysideInfoService.findAll());
    }

    @GetMapping("/page/{name}")
    public Result<PageInfo<CountrysideInfoVo>> page(@PathVariable String name,
                                             @RequestParam(defaultValue = "1") Integer pageNum,
                                             @RequestParam(defaultValue = "5") Integer pageSize,
                                             HttpServletRequest request) {
        return Result.success(CountrysideInfoService.findPage(name, pageNum, pageSize));
    }

}
