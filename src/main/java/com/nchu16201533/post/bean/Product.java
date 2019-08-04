package com.nchu16201533.post.bean;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class Product {
    private String id;

    @Pattern(regexp = ".{4,100}",
            message = "产品名称长度为4到100个字符")
    private String productName;
    @NotNull(message = "分类不能为空")
    private Integer categoryId;

    private Category category;

    @NotNull(message = "描述不能为空")
    @Valid
    private ProductSpecification ps;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public ProductSpecification getPs() {
        return ps;
    }

    public void setPs(ProductSpecification ps) {
        this.ps = ps;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}