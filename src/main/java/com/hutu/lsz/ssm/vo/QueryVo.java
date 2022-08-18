package com.hutu.lsz.ssm.vo;

import com.hutu.lsz.ssm.entity.Product;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class QueryVo{
    private Product product;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
}
