package com.nchu16201533.post.controller;

import com.nchu16201533.post.bean.Category;
import com.nchu16201533.post.bean.RestResponse;
import com.nchu16201533.post.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;


/**
 * @Author: 16201533
 * @Date: 2019/5/29 22:12
 * @Version 1.0
 */
@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/manager/categoryManager")
    public String CategoryManager() {
        return "manager/categoryManager";
    }

    @RequestMapping("/getAllCategory")
    @ResponseBody
    public RestResponse getAllCategory() {
        return RestResponse.success().add("data", categoryService.getAllCategory());
    }

    @RequestMapping("/manager/addCategory")
    @ResponseBody
    public RestResponse addCategory(@Valid Category category, BindingResult result) {
        if (result.hasErrors()) {
            return RestResponse.fail().add("data",result.getAllErrors().get(0).getDefaultMessage());
        }
        return categoryService.addCategory(category)?RestResponse.success():RestResponse.fail().add("data","添加失败");
    }
    @RequestMapping("/manager/deleteCategory")
    @ResponseBody
    public RestResponse deleteCategory(Integer id) {
        Category category = categoryService.queryCategoryById(id);
        if (category.getProductNum()>0){
            return RestResponse.fail().add("data","此分类下还有商品");
        }else {
            return categoryService.deleteCategory(id)?RestResponse.success():RestResponse.fail().add("data","删除失败");
        }
    }
    @RequestMapping("/manager/updateCategory")
    @ResponseBody
    public RestResponse updateCategory(@Valid Category category,BindingResult result) {
        if (result.hasErrors()){
            return RestResponse.fail().add("data",result.getAllErrors().get(0).getDefaultMessage());
        }else {
            return categoryService.updateCategory(category)?RestResponse.success():RestResponse.fail().add("data","修改失败");
        }
    }
}
