package com.nchu16201533.post.service;

import com.nchu16201533.post.bean.Category;
import com.nchu16201533.post.bean.CategoryExample;
import com.nchu16201533.post.dao.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 16201533
 * @Date: 2019/5/29 22:51
 * @Version 1.0
 */
@Service
public class CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    public List<Category> getAllCategory() {
        return categoryMapper.selectByExample(null);
    }

    public boolean addCategory(Category category) {
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.createCriteria().andCategoryNameEqualTo(category.getCategoryName());
        List<Category> categories = categoryMapper.selectByExample(categoryExample);
        if (categories.size()>0){
            return false;
        }else {
            return categoryMapper.insertSelective(category) > 0;
        }
    }

    public Category queryCategoryById(Integer id) {
        return categoryMapper.selectByPrimaryKey(id);
    }

    public boolean deleteCategory(Integer id) {
        return categoryMapper.deleteByPrimaryKey(id)>0;
    }

    public boolean updateCategory(Category category) {
        return categoryMapper.updateByPrimaryKeySelective(category) > 0;
    }
}
