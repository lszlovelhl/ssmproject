package com.hutu.lsz.ssm.mapper;

import com.hutu.lsz.ssm.entity.Order;
import java.util.List;

public interface OrderMapper {
    int deleteByPrimaryKey(String id);

    int insert(Order row);

    Order selectByPrimaryKey(String id);

    List<Order> selectAll();

    int updateByPrimaryKey(Order row);
}