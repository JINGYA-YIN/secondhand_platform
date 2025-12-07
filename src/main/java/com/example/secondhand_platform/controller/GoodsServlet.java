package com.example.secondhand_platform.controller;

import com.example.secondhand_platform.model.Goods;
import com.example.secondhand_platform.model.User;
import com.example.secondhand_platform.service.GoodsService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet("/goods/*")
public class GoodsServlet extends HttpServlet {
    private GoodsService goodsService = new GoodsService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        String path = req.getPathInfo();

        switch (path) {
            case "/add":
                addGoods(req, resp);
                break;
            case "/update":
                updateGoods(req, resp);
                break;
            case "/delete":
                deleteGoods(req, resp);
                break;
            case "/myGoods":
                myGoods(req, resp);
                break;
            default:
                resp.getWriter().write("请求路径错误！");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        String path = req.getPathInfo();

        if ("/search".equals(path)) {
            searchGoods(req, resp);
        } else if ("/myGoods".equals(path)) {
            myGoods(req, resp);
        } else if ("/toUpdate".equals(path)) {
            toUpdate(req, resp);
        } else {
            doPost(req, resp);
        }
    }

    // 发布商品
    private void addGoods(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser == null) {
            resp.sendRedirect(req.getContextPath() + "/view/login.jsp");
            return;
        }

        String goodsName = req.getParameter("goodsName");
        String goodsType = req.getParameter("goodsType");
        BigDecimal price = new BigDecimal(req.getParameter("price"));
        String description = req.getParameter("description");

        Goods goods = new Goods();
        goods.setUserId(loginUser.getId());
        goods.setGoodsName(goodsName);
        goods.setGoodsType(goodsType);
        goods.setPrice(price);
        goods.setDescription(description);
        goods.setStatus(1);

        boolean success = goodsService.addGoods(goods);
        if (success) {
            resp.sendRedirect(req.getContextPath() + "/goods/myGoods");
        } else {
            req.setAttribute("msg", "发布失败！");
            req.getRequestDispatcher("/view/addGoods.jsp").forward(req, resp);
        }
    }

    // 修改商品-跳转页面
    private void toUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer goodsId = Integer.parseInt(req.getParameter("goodsId"));
        Goods goods = goodsService.findById(goodsId);
        req.setAttribute("goods", goods);
        req.getRequestDispatcher("/view/updateGoods.jsp").forward(req, resp);
    }

    // 修改商品
    private void updateGoods(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser == null) {
            resp.sendRedirect(req.getContextPath() + "/view/login.jsp");
            return;
        }

        Integer goodsId = Integer.parseInt(req.getParameter("goodsId"));
        String goodsName = req.getParameter("goodsName");
        String goodsType = req.getParameter("goodsType");
        BigDecimal price = new BigDecimal(req.getParameter("price"));
        String description = req.getParameter("description");
        Integer status = Integer.parseInt(req.getParameter("status"));

        Goods goods = new Goods();
        goods.setId(goodsId);
        goods.setUserId(loginUser.getId());
        goods.setGoodsName(goodsName);
        goods.setGoodsType(goodsType);
        goods.setPrice(price);
        goods.setDescription(description);
        goods.setStatus(status);

        boolean success = goodsService.updateGoods(goods);
        if (success) {
            resp.sendRedirect(req.getContextPath() + "/goods/myGoods");
        } else {
            req.setAttribute("msg", "修改失败！无权限或商品不存在");
            req.setAttribute("goods", goods);
            req.getRequestDispatcher("/view/updateGoods.jsp").forward(req, resp);
        }
    }

    // 删除商品
    private void deleteGoods(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser == null) {
            resp.sendRedirect(req.getContextPath() + "/view/login.jsp");
            return;
        }

        Integer goodsId = Integer.parseInt(req.getParameter("goodsId"));
        boolean success = goodsService.deleteGoods(goodsId, loginUser.getId());
        if (success) {
            resp.sendRedirect(req.getContextPath() + "/goods/myGoods");
        } else {
            resp.getWriter().write("删除失败！无权限或商品不存在");
        }
    }

    // 模糊搜索
    private void searchGoods(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String keyword = req.getParameter("keyword");
        List<Goods> goodsList = goodsService.searchGoods(keyword);
        req.setAttribute("goodsList", goodsList);
        req.setAttribute("keyword", keyword);
        req.getRequestDispatcher("/view/searchResult.jsp").forward(req, resp);
    }

    // 我的商品
    private void myGoods(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser == null) {
            resp.sendRedirect(req.getContextPath() + "/view/login.jsp");
            return;
        }

        List<Goods> myGoodsList = goodsService.findByUserId(loginUser.getId());
        req.setAttribute("myGoodsList", myGoodsList);
        req.getRequestDispatcher("/view/myGoods.jsp").forward(req, resp);
    }
}
