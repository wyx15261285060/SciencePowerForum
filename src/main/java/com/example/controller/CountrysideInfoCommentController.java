package com.example.controller;

import com.example.common.Result;
import com.example.entity.CountrysideInfoComment;
import com.example.vo.CountrysideInfoCommentVo;
import com.example.service.CountrysideInfoCommentService;

import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/CountrysideInfoComment")
public class CountrysideInfoCommentController {
    @Resource
    private CountrysideInfoCommentService CountrysideInfoCommentService;

    @PostMapping
    public Result<CountrysideInfoComment> add(@RequestBody CountrysideInfoComment commentInfo, HttpServletRequest request) {
        CountrysideInfoCommentService.add(commentInfo, request);
        return Result.success(commentInfo);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        CountrysideInfoCommentService.delete(id);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody CountrysideInfoComment commentInfo) {
        CountrysideInfoCommentService.update(commentInfo);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<CountrysideInfoComment> detail(@PathVariable Long id) {
        CountrysideInfoComment commentInfo = CountrysideInfoCommentService.findById(id);
        return Result.success(commentInfo);
    }

    @GetMapping
    public Result<List<CountrysideInfoCommentVo>> all() {
        return Result.success(CountrysideInfoCommentService.findAll());
    }

    @GetMapping("/page/{name}")
    public Result<PageInfo<CountrysideInfoCommentVo>> page(@PathVariable String name,
                                                @RequestParam(defaultValue = "1") Integer pageNum,
                                                @RequestParam(defaultValue = "5") Integer pageSize,
                                                HttpServletRequest request) {
        return Result.success(CountrysideInfoCommentService.findPage(name, pageNum, pageSize));
    }

    @GetMapping("/findByForeignId/{id}")
    public Result<List<CountrysideInfoCommentVo>> findByForeignId (@PathVariable Long id) {
        return Result.success(CountrysideInfoCommentService.findByForeignId(id));
    }
}
