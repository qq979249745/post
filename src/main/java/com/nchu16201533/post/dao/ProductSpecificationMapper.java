package com.nchu16201533.post.dao;

import com.nchu16201533.post.bean.ProductSpecification;
import com.nchu16201533.post.bean.ProductSpecificationExample;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface ProductSpecificationMapper {
    int countByExample(ProductSpecificationExample example);

    int deleteByExample(ProductSpecificationExample example);

    @Insert({
        "insert into tb_product_specification (product_id, specification, ",
        "price, discount, ",
        "inventory, product_img)",
        "values (#{productId,jdbcType=VARCHAR}, #{specification,jdbcType=VARCHAR}, ",
        "#{price,jdbcType=INTEGER}, #{discount,jdbcType=INTEGER}, ",
        "#{inventory,jdbcType=INTEGER}, #{productImg,jdbcType=VARCHAR})"
    })
    int insert(ProductSpecification record);

    int insertSelective(ProductSpecification record);

    List<ProductSpecification> selectByExample(ProductSpecificationExample example);

    int updateByExampleSelective(@Param("record") ProductSpecification record, @Param("example") ProductSpecificationExample example);

    int updateByExample(@Param("record") ProductSpecification record, @Param("example") ProductSpecificationExample example);
}