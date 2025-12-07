package com.example.secondhand_platform.service;

import com.example.secondhand_platform.dao.GoodsDAO;
import com.example.secondhand_platform.dao.impl.GoodsDAOimpl;
import com.example.secondhand_platform.model.Goods;

import java.util.List;

public class GoodsService {
    private GoodsDAO goodsDAO = new GoodsDAOimpl();

    // 发布商品
    public boolean addGoods(Goods goods) {
        return goodsDAO.addGoods(goods) > 0;
    }

    // 修改商品
    public boolean updateGoods(Goods goods) {
        Goods oldGoods = goodsDAO.findById(goods.getId());
        if (oldGoods == null || !oldGoods.getUserId().equals(goods.getUserId())) {
            return false;
        }
        return goodsDAO.updateGoods(goods) > 0;
    }

    // 删除商品
    public boolean deleteGoods(Integer id, Integer userId) {
        Goods goods = goodsDAO.findById(id);
        if (goods == null || !goods.getUserId().equals(userId)) {
            return false;
        }
        return goodsDAO.deleteGoods(id, userId) > 0;
    }

    // 模糊搜索
    public List<Goods> searchGoods(String keyword) {
        return goodsDAO.searchGoods(keyword);
    }

    // 查用户所有商品
    public List<Goods> findByUserId(Integer userId) {
        return goodsDAO.findByUserId(userId);
    }

    // 按ID查商品
    public Goods findById(Integer id) {
        return goodsDAO.findById(id);
    }
}
