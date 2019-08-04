package com.nchu16201533.post.dao;

import com.nchu16201533.post.bean.CartItem;
import com.nchu16201533.post.bean.CartItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface CartItemMapper {
    int countByExample(CartItemExample example);

    int deleteByExample(CartItemExample example);

    @Delete({
        "delete from tb_cart_item",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into tb_cart_item (id, cart_id, ",
        "product_id, quanity)",
        "values (#{id,jdbcType=INTEGER}, #{cartId,jdbcType=INTEGER}, ",
        "#{productId,jdbcType=VARCHAR}, #{quanity,jdbcType=INTEGER})"
    })
    int insert(CartItem record);

    int insertSelective(CartItem record);

    List<CartItem> selectByExample(CartItemExample example);

    @Select({
        "select",
        "id, cart_id, product_id, quanity",
        "from tb_cart_item",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    CartItem selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CartItem record, @Param("example") CartItemExample example);

    int updateByExample(@Param("record") CartItem record, @Param("example") CartItemExample example);

    int updateByPrimaryKeySelective(CartItem record);

    @Update({
        "update tb_cart_item",
        "set cart_id = #{cartId,jdbcType=INTEGER},",
          "product_id = #{productId,jdbcType=VARCHAR},",
          "quanity = #{quanity,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(CartItem record);
}