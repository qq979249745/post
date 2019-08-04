package com.nchu16201533.post.dao;

import com.nchu16201533.post.bean.Sale;
import com.nchu16201533.post.bean.SaleExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface SaleMapper {
    int countByExample(SaleExample example);

    int deleteByExample(SaleExample example);

    @Delete({
        "delete from tb_sale",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into tb_sale (id, customer_id, ",
        "create_time, pay_time, ",
        "total_price, status, ",
        "payment, pay_no)",
        "values (#{id,jdbcType=VARCHAR}, #{customerId,jdbcType=INTEGER}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{payTime,jdbcType=TIMESTAMP}, ",
        "#{totalPrice,jdbcType=INTEGER}, #{status,jdbcType=VARCHAR}, ",
        "#{payment,jdbcType=VARCHAR}, #{payNo,jdbcType=VARCHAR})"
    })
    int insert(Sale record);

    int insertSelective(Sale record);

    List<Sale> selectByExample(SaleExample example);

    @Select({
        "select",
        "id, customer_id, create_time, pay_time, total_price, status, payment, pay_no",
        "from tb_sale",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    @ResultMap("BaseResultMap")
    Sale selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Sale record, @Param("example") SaleExample example);

    int updateByExample(@Param("record") Sale record, @Param("example") SaleExample example);

    int updateByPrimaryKeySelective(Sale record);

    @Update({
        "update tb_sale",
        "set customer_id = #{customerId,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "pay_time = #{payTime,jdbcType=TIMESTAMP},",
          "total_price = #{totalPrice,jdbcType=INTEGER},",
          "status = #{status,jdbcType=VARCHAR},",
          "payment = #{payment,jdbcType=VARCHAR},",
          "pay_no = #{payNo,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(Sale record);
}