package com.nchu16201533.post.dao;

import com.nchu16201533.post.bean.Cart;
import com.nchu16201533.post.bean.CartExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface CartMapper {
    int countByExample(CartExample example);

    int deleteByExample(CartExample example);

    @Delete({
        "delete from tb_cart",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into tb_cart (id, customer_id)",
        "values (#{id,jdbcType=INTEGER}, #{customerId,jdbcType=INTEGER})"
    })
    int insert(Cart record);

    int insertSelective(Cart record);

    List<Cart> selectByExample(CartExample example);

    @Select({
        "select",
        "id, customer_id",
        "from tb_cart",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    Cart selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Cart record, @Param("example") CartExample example);

    int updateByExample(@Param("record") Cart record, @Param("example") CartExample example);

    int updateByPrimaryKeySelective(Cart record);

    @Update({
        "update tb_cart",
        "set customer_id = #{customerId,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Cart record);
}