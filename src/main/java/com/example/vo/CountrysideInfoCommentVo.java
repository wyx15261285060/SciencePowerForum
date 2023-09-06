package com.example.vo;

import com.example.entity.CountrysideInfoComment;
import java.util.List;

public class CountrysideInfoCommentVo extends CountrysideInfoComment {

    private String foreignName;

    private List<CountrysideInfoCommentVo> children;

    public List<CountrysideInfoCommentVo> getChildren() {
        return children;
    }

    public void setChildren(List<CountrysideInfoCommentVo> children) {
        this.children = children;
    }

    public String getForeignName() {
        return foreignName;
    }

    public void setForeignName(String foreignName) {
        this.foreignName = foreignName;
    }
}