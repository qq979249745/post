package com.nchu16201533.post.service;

import com.nchu16201533.post.bean.Category;
import com.nchu16201533.post.bean.Product;
import com.nchu16201533.post.bean.ProductExample;
import com.nchu16201533.post.bean.ProductSpecificationExample;
import com.nchu16201533.post.dao.CategoryMapper;
import com.nchu16201533.post.dao.ProductMapper;
import com.nchu16201533.post.dao.ProductSpecificationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

/**
 * @Author: 16201533
 * @Date: 2019/5/31 19:52
 * @Version 1.0
 */
@Service
public class ProductService {
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductSpecificationMapper productSpecificationMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Value("${web.upload-path}")
    private String path;

    public boolean addProduct(Product product, MultipartFile file) {
        try{
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            product.setId(uuid);
            String originalFilename = file.getOriginalFilename();
            String fileName=uuid+originalFilename.substring(originalFilename.lastIndexOf("."));
            product.getPs().setProductImg(fileName);
            product.getPs().setProductId(uuid);

            File f=new File(path+fileName);
            if (!f.getParentFile().exists()){
                f.getParentFile().mkdirs();
            }
            file.transferTo(f);
            return productMapper.insert(product)>0&&productSpecificationMapper.insert(product.getPs())>0;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public List<Product> getProductByCategoryId(Integer id) {
        ProductExample productExample = new ProductExample();
        productExample.createCriteria().andCategoryIdEqualTo(id);
        List<Product> products = productMapper.selectByExample(id<0?null:productExample);

        for (Product product : products) {
            Category category = categoryMapper.selectByPrimaryKey(product.getCategoryId());
            product.setCategory(category);
            ProductSpecificationExample productSpecificationExample = new ProductSpecificationExample();
            productSpecificationExample.createCriteria().andProductIdEqualTo(product.getId());
            product.setPs(productSpecificationMapper.selectByExample(productSpecificationExample).get(0));
        }
        return products;
    }

    public Product getProductById(String id) {
        Product product = productMapper.selectByPrimaryKey(id);
        Category category = categoryMapper.selectByPrimaryKey(product.getCategoryId());
        product.setCategory(category);
        ProductSpecificationExample productSpecificationExample = new ProductSpecificationExample();
        productSpecificationExample.createCriteria().andProductIdEqualTo(product.getId());
        product.setPs(productSpecificationMapper.selectByExample(productSpecificationExample).get(0));
        return product;
    }

    public boolean editProduct(Product product) {
        ProductSpecificationExample productSpecificationExample = new ProductSpecificationExample();
        productSpecificationExample.createCriteria().andProductIdEqualTo(product.getId());
        ProductExample productExample=new ProductExample();
        productExample.createCriteria().andIdEqualTo(product.getId());
        return productSpecificationMapper.updateByExampleSelective(product.getPs(),productSpecificationExample)>0&&
        productMapper.updateByExampleSelective(product,productExample)>0;
    }
}
