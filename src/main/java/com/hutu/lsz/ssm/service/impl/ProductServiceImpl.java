package com.hutu.lsz.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hutu.lsz.ssm.entity.Product;
import com.hutu.lsz.ssm.mapper.ProductMapper;
import com.hutu.lsz.ssm.service.ProductService;
import com.hutu.lsz.ssm.vo.QueryVo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public Product findById(String id) {
        return productMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Product> findAll() {
        return productMapper.selectAll();
    }

    @Override
    public void save(Product product) {
        productMapper.insert(product);
    }

    @Override
    public void update(Product product) {
        productMapper.updateByPrimaryKey(product);
    }

    @Override
    public void delete(String ids) {
        productMapper.deleteByPrimaryKey(ids);
    }

    @Override
    public PageInfo<Product> findByPage(Integer pageNo) {
        int pageSize = 2;
        PageHelper.startPage(pageNo, pageSize);
        List<Product> productList = productMapper.selectAll();
        PageInfo<Product> pageinfo = new PageInfo<>(productList);
        return pageinfo;
    }

    @Override
    public List<Product> findByQuery(QueryVo queryVo) {
        return productMapper.findByQuery(queryVo);
    }
}
