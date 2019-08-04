package com.nchu16201533.post.bean;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class ProductSpecification {
    private String productId;

    @Pattern(regexp = ".{4,50}",
            message = "产品描述长度为4到255个字符")
    private String specification;
    @Min(value = 0,message = "价格不能小于0") @NotNull(message = "请输入价格")
    private Integer price;
    @Min(value = 0,message = "促销价不能小于0")  @NotNull(message = "请输入促销价")
    private Integer discount;
    @Min(value = 0,message = "库存不能小于0") @NotNull(message = "请输入库存")
    private Integer inventory;

    private String productImg;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification == null ? null : specification.trim();
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg == null ? null : productImg.trim();
    }
}