package com.nchu16201533.post.dao;

import com.nchu16201533.post.bean.SaleItem;
import com.nchu16201533.post.bean.SaleItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface SaleItemMapper {
    int countByExample(SaleItemExample example);

    int deleteByExample(SaleItemExample example);

    @Insert({
        "insert into tb_sale_item (sale_id, product_id, ",
        "quanity)",
        "values (#{saleId,jdbcType=VARCHAR}, #{productId,jdbcType=VARCHAR}, ",
        "#{quanity,jdbcType=INTEGER})"
    })
    int insert(SaleItem record);

    int insertSelective(SaleItem record);

    List<SaleItem> selectByExample(SaleItemExample example);

    int updateByExampleSelective(@Param("record") SaleItem record, @Param("example") SaleItemExample example);

    int updateByExample(@Param("record") SaleItem record, @Param("example") SaleItemExample example);
}