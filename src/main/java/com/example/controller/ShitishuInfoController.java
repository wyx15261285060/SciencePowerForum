package com.example.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.example.common.Result;
import com.example.common.ResultCode;
import com.example.entity.ShitishuInfo;
import com.example.service.*;
import com.example.vo.ShitishuInfoVo;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/shitishuInfo")
public class ShitishuInfoController {
    @Resource
    private ShitishuInfoService shitishuInfoService;

    @PostMapping
    public Result<ShitishuInfo> add(@RequestBody ShitishuInfoVo info) {
        shitishuInfoService.add(info);
        return Result.success(info);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        shitishuInfoService.delete(id);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody ShitishuInfoVo info) {
        shitishuInfoService.update(info);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<ShitishuInfo> detail(@PathVariable Long id) {
        ShitishuInfo info = shitishuInfoService.findById(id);
        return Result.success(info);
    }

    @GetMapping
    public Result<List<ShitishuInfoVo>> all() {
        return Result.success(shitishuInfoService.findAll());
    }

    @GetMapping("/page/{name}")
    public Result<PageInfo<ShitishuInfoVo>> page(@PathVariable String name,
                                                @RequestParam(defaultValue = "1") Integer pageNum,
                                                @RequestParam(defaultValue = "5") Integer pageSize,
                                                HttpServletRequest request) {
        return Result.success(shitishuInfoService.findPage(name, pageNum, pageSize, request));
    }
}
