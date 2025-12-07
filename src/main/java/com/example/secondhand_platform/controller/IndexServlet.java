package com.example.secondhand_platform.controller;

import com.example.secondhand_platform.model.Goods;
import com.example.secondhand_platform.service.GoodsService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {
    private GoodsService goodsService = new GoodsService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 获取所有在售商品
        List<Goods> onSaleGoods = goodsService.getAllOnSaleGoods();
        req.setAttribute("onSaleGoods", onSaleGoods);

        String target = req.getParameter("target");
        if ("goods_list".equals(target)) {
            // iframe请求：转发到商品列表页面
            req.getRequestDispatcher("/view/goods_list.jsp").forward(req, resp);
        } else {
            // 非iframe请求：仍转发到原首页
            req.getRequestDispatcher("/view/index.jsp").forward(req, resp);
        }
    }
}