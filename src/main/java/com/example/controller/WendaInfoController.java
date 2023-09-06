package com.example.controller;

import com.example.common.Result;
import com.example.entity.WendaInfo;
import com.example.service.WendaInfoService;
import com.example.vo.WendaInfoVo;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/wendaInfo")
public class WendaInfoController {
    @Resource
    private WendaInfoService wendaInfoService;

    @PostMapping
    public Result<WendaInfo> add(@RequestBody WendaInfoVo wendaInfo) {
        wendaInfoService.add(wendaInfo);
        return Result.success(wendaInfo);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        wendaInfoService.delete(id);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody WendaInfoVo wendaInfo) {
        wendaInfoService.update(wendaInfo);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<WendaInfo> detail(@PathVariable Long id) {
        WendaInfo wendaInfo = wendaInfoService.findById(id);
        return Result.success(wendaInfo);
    }

    @GetMapping
    public Result<List<WendaInfoVo>> all() {
        return Result.success(wendaInfoService.findAll());
    }

    @GetMapping("/page/{name}")
    public Result<PageInfo<WendaInfoVo>> page(@PathVariable String name,
                                                @RequestParam(defaultValue = "1") Integer pageNum,
                                                @RequestParam(defaultValue = "5") Integer pageSize,
                                                HttpServletRequest request) {
        return Result.success(wendaInfoService.findPage(name, pageNum, pageSize, request));
    }

}
