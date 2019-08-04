package com.nchu16201533.post.bean;

import javax.validation.constraints.Pattern;

public class Category {
    private Integer id;

    @Pattern(regexp = ".{2,32}",
            message = "分类名称格式错误")
    private String categoryName;

    private Integer productNum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName == null ? null : categoryName.trim();
    }

    public Integer getProductNum() {
        return productNum;
    }

    public void setProductNum(Integer productNum) {
        this.productNum = productNum;
    }
}