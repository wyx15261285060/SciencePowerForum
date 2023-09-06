package com.example.vo;

import com.example.entity.ShitishuInfoComment;
import java.util.List;

public class ShitishuInfoCommentVo extends ShitishuInfoComment {

    private String foreignName;

    private List<ShitishuInfoCommentVo> children;

    public List<ShitishuInfoCommentVo> getChildren() {
        return children;
    }

    public void setChildren(List<ShitishuInfoCommentVo> children) {
        this.children = children;
    }

    public String getForeignName() {
        return foreignName;
    }

    public void setForeignName(String foreignName) {
        this.foreignName = foreignName;
    }
}