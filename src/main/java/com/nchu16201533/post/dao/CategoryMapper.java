package com.nchu16201533.post.dao;

import com.nchu16201533.post.bean.Category;
import com.nchu16201533.post.bean.CategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface CategoryMapper {
    int countByExample(CategoryExample example);

    int deleteByExample(CategoryExample example);

    @Delete({
        "delete from tb_category",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into tb_category (id, category_name, ",
        "product_num)",
        "values (#{id,jdbcType=INTEGER}, #{categoryName,jdbcType=VARCHAR}, ",
        "#{productNum,jdbcType=INTEGER})"
    })
    int insert(Category record);

    int insertSelective(Category record);

    List<Category> selectByExample(CategoryExample example);

    @Select({
        "select",
        "id, category_name, product_num",
        "from tb_category",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    Category selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Category record, @Param("example") CategoryExample example);

    int updateByExample(@Param("record") Category record, @Param("example") CategoryExample example);

    int updateByPrimaryKeySelective(Category record);

    @Update({
        "update tb_category",
        "set category_name = #{categoryName,jdbcType=VARCHAR},",
          "product_num = #{productNum,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Category record);
}