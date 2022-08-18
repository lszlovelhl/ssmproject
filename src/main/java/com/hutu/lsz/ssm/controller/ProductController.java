package com.hutu.lsz.ssm.controller;

import com.hutu.lsz.ssm.entity.Product;
import com.hutu.lsz.ssm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

//    @GetMapping("/page")
////    @ResponseBody
//    public PageInfo<Product> findByPage(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo){
//        // 获得返回的Product对象
//        PageInfo<Product> pageInfo = productService.findByPage(pageNo);
//        return pageInfo;
//    }

    @GetMapping("/{id}")
    public Product GetById(@PathVariable("id") String id){
        return productService.findById(id);
    }

}
