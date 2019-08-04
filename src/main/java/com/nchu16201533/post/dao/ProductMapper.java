package com.nchu16201533.post.dao;

import com.nchu16201533.post.bean.Product;
import com.nchu16201533.post.bean.ProductExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface ProductMapper {
    int countByExample(ProductExample example);

    int deleteByExample(ProductExample example);

    @Delete({
        "delete from tb_product",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into tb_product (id, product_name, ",
        "category_id)",
        "values (#{id,jdbcType=VARCHAR}, #{productName,jdbcType=VARCHAR}, ",
        "#{categoryId,jdbcType=INTEGER})"
    })
    int insert(Product record);

    int insertSelective(Product record);

    List<Product> selectByExample(ProductExample example);

    @Select({
        "select",
        "id, product_name, category_id",
        "from tb_product",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    @ResultMap("BaseResultMap")
    Product selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Product record, @Param("example") ProductExample example);

    int updateByExample(@Param("record") Product record, @Param("example") ProductExample example);

    int updateByPrimaryKeySelective(Product record);

    @Update({
        "update tb_product",
        "set product_name = #{productName,jdbcType=VARCHAR},",
          "category_id = #{categoryId,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(Product record);
}