package com.hutu.lsz.ssm.service;

import com.github.pagehelper.PageInfo;
import com.hutu.lsz.ssm.entity.Product;
import com.hutu.lsz.ssm.vo.QueryVo;

import java.util.List;

public interface ProductService {
    Product findById(String id);
    List<Product> findAll();
    void save(Product product);
    void update(Product product);
    void delete(String ids);
    PageInfo<Product> findByPage(Integer pageNo);
    List<Product> findByQuery(QueryVo queryVo);
}
