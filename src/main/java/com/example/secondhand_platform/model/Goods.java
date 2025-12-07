package com.example.secondhand_platform.model;

import java.math.BigDecimal;
import java.util.Date;

public class Goods {
    private Integer id;
    private Integer userId; // 发布者ID
    private String goodsName; // 商品名称
    private String goodsType; // 商品类型
    private BigDecimal price; // 价格
    private String description; // 描述
    private Integer status; // 1-在售 2-已售 3-下架
    private Date createTime; // 发布时间

    // 无参构造
    public Goods() {}

    // 全参构造
    public Goods(Integer id, Integer userId, String goodsName, String goodsType, BigDecimal price, String description, Integer status, Date createTime) {
        this.id = id;
        this.userId = userId;
        this.goodsName = goodsName;
        this.goodsType = goodsType;
        this.price = price;
        this.description = description;
        this.status = status;
        this.createTime = createTime;
    }

    // Getter & Setter
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }
    public String getGoodsName() { return goodsName; }
    public void setGoodsName(String goodsName) { this.goodsName = goodsName; }
    public String getGoodsType() { return goodsType; }
    public void setGoodsType(String goodsType) { this.goodsType = goodsType; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }
    public Date getCreateTime() { return createTime; }
    public void setCreateTime(Date createTime) { this.createTime = createTime; }
}
