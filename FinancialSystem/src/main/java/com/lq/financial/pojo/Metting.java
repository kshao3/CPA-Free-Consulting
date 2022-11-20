package com.lq.financial.pojo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: LQ
 * @CreateTime: 2022-10-26  23:30
 * @Description: TODO
 * @Version: 1.0
 */
@Data
public class Metting {
    private String id;
    private BigDecimal bmtotal;
    private BigDecimal retatol;
    private BigDecimal retotal;
    private BigDecimal sctotal;
    private String category;
    private String comments;
    private BigDecimal bmunitqty;
    private BigDecimal reunitqty;
    private BigDecimal scunitqty;
    private BigDecimal bmunitprice;
    private String description;
    private BigDecimal reunitprice;
    private BigDecimal scunitprice;
    private BigDecimal subcategory;
}
