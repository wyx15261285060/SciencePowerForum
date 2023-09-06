package com.example.controller;

import com.example.common.Result;
import com.example.entity.ShitishuInfoComment;
import com.example.vo.ShitishuInfoCommentVo;
import com.example.service.ShitishuInfoCommentService;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/shitishuInfoComment")
public class ShitishuInfoCommentController {
    @Resource
    private ShitishuInfoCommentService shitishuInfoCommentService;

    @PostMapping
    public Result<ShitishuInfoComment> add(@RequestBody ShitishuInfoComment commentInfo, HttpServletRequest request) {
        shitishuInfoCommentService.add(commentInfo, request);
        return Result.success(commentInfo);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        shitishuInfoCommentService.delete(id);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody ShitishuInfoComment commentInfo) {
        shitishuInfoCommentService.update(commentInfo);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<ShitishuInfoComment> detail(@PathVariable Long id) {
        ShitishuInfoComment commentInfo = shitishuInfoCommentService.findById(id);
        return Result.success(commentInfo);
    }

    @GetMapping
    public Result<List<ShitishuInfoCommentVo>> all() {
        return Result.success(shitishuInfoCommentService.findAll());
    }

    @GetMapping("/page/{name}")
    public Result<PageInfo<ShitishuInfoCommentVo>> page(@PathVariable String name,
                                                @RequestParam(defaultValue = "1") Integer pageNum,
                                                @RequestParam(defaultValue = "5") Integer pageSize,
                                                HttpServletRequest request) {
        return Result.success(shitishuInfoCommentService.findPage(name, pageNum, pageSize));
    }

    @GetMapping("/findByForeignId/{id}")
    public Result<List<ShitishuInfoCommentVo>> findByForeignId (@PathVariable Long id) {
        return Result.success(shitishuInfoCommentService.findByForeignId(id));
    }
}
