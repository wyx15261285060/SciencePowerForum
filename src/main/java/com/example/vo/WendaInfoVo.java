package com.example.vo;

import com.example.entity.WendaInfo;

import java.util.List;

public class WendaInfoVo extends WendaInfo {
    private List<WendaInfoVo> children;

    public List<WendaInfoVo> getChildren() {
        return children;
    }

    public void setChildren(List<WendaInfoVo> children) {
        this.children = children;
    }
}
