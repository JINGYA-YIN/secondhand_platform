package com.example.secondhand_platform.dao;

import com.example.secondhand_platform.model.Goods;
import java.util.List;

public interface GoodsDAO {
    // 发布商品
    int addGoods(Goods goods);
    // 修改商品
    int updateGoods(Goods goods);
    // 删除商品
    int deleteGoods(Integer id, Integer userId);
    // 模糊搜索商品
    List<Goods> searchGoods(String keyword);
    // 按ID查商品
    Goods findById(Integer id);
    // 查用户所有商品
    List<Goods> findByUserId(Integer userId);
    List<Goods> findAllOnSale();
}
