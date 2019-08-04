package com.nchu16201533.post.controller;

import com.nchu16201533.post.bean.Product;
import com.nchu16201533.post.bean.RestResponse;
import com.nchu16201533.post.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * @Author: 16201533
 * @Date: 2019/5/30 20:44
 * @Version 1.0
 */
@Controller
public class ProductController {
    @Autowired
    private ProductService productService;
    @Value("${web.upload-path}")
    private String path;

    @RequestMapping("/manager/productManager/{id}")
    public String productManager(@PathVariable(name = "id")Integer id){
        return "/manager/productManager";
    }

    @RequestMapping("/manager/addProduct")
    public String addProduct(){
        return "/manager/addProduct";
    }

    @RequestMapping("/manager/editProduct/{id}")
    public String editProduct(@PathVariable(name = "id")String id, Model model){
        Product productById = productService.getProductById(id);
        model.addAttribute("product",productById);
        return "/manager/editProduct";
    }

    @RequestMapping("/manager/editProduct")
    @ResponseBody
    public RestResponse editProduct(@Valid Product product,BindingResult result,@RequestParam("file") MultipartFile file){
        if (result.hasErrors()){
            return RestResponse.fail().add("data",result.getAllErrors().get(0).getDefaultMessage());
        }else {
            if (file.getSize()>0){
                String uuid = UUID.randomUUID().toString().replaceAll("-", "");
                String originalFilename = file.getOriginalFilename();
                String fileName=uuid+originalFilename.substring(originalFilename.lastIndexOf("."));
                product.getPs().setProductImg(fileName);

                File f=new File(path+fileName);
                if (!f.getParentFile().exists()){
                    f.getParentFile().mkdirs();
                }
                try {
                    file.transferTo(f);
                } catch (IOException e) {
                    e.printStackTrace();
                    return RestResponse.fail().add("data","上传失败");
                }
            }
            return productService.editProduct(product)? RestResponse.success().add("data","/manager/editProduct/"+product.getId())
                    :RestResponse.fail().add("data","上传失败");
        }

    }

    @PostMapping("/manager/submitProduct")
    @ResponseBody
    public RestResponse submitProduct(@Valid Product product, BindingResult result,@RequestParam("file") MultipartFile file ){
        if (file.getSize()<=0){
            return RestResponse.fail().add("data","请上传图片");
        }else {
            if (result.hasErrors()){
                return RestResponse.fail().add("data",result.getAllErrors().get(0).getDefaultMessage());
            }else {
                return productService.addProduct(product,file)? RestResponse.success().add("data","/manager/editProduct/"+product.getId())
                        :RestResponse.fail().add("data","上传失败");
            }
        }
    }
    @GetMapping("/getProductByCategoryId")
    @ResponseBody
    public RestResponse getProductByCategoryId(Integer id){
        List<Product> productByCategoryId = productService.getProductByCategoryId(id);
        return RestResponse.success().add("data",productByCategoryId);
    }
    @RequestMapping("/productDetail/{id}")
    public String productDetail(@PathVariable(name = "id") String id,Model model){
        Product productById = productService.getProductById(id);
        model.addAttribute("product",productById);
        return "productDetail";
    }
}
