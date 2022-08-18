package com.hutu.lsz.ssm.mapper;

import com.hutu.lsz.ssm.entity.Product;
import com.hutu.lsz.ssm.vo.QueryVo;

import java.util.List;

public interface ProductMapper {

    List<Product> selectAll();

    int deleteByPrimaryKey(String id);

    int insert(Product record);

    Product selectByPrimaryKey(String id);

    int updateByPrimaryKey(Product record);

    List<Product> findByQuery(QueryVo queryVo);

    int deleteMutli(String[] ids);

}