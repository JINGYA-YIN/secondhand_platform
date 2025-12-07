package com.example.secondhand_platform.controller;

import com.example.secondhand_platform.model.User;
import com.example.secondhand_platform.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user/*")
public class UserServlet extends HttpServlet {
    // 实例化 UserService
    private UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        // 获取请求路径信息，如 /register, /login
        String path = req.getPathInfo();

        switch (path) {
            case "/register":
                register(req, resp);
                break;
            case "/login":
                login(req, resp);
                break;
            case "/logout":
                logout(req, resp);
                break;
            default:
                resp.getWriter().write("请求路径错误！");
        }
    }

    // 注册方法
    private void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String nickname = req.getParameter("nickname");

        // 调用优化后的 register 方法，返回 int 状态码
        // 0: 成功; 1: 账号已存在; 2: 数据库插入失败(其他错误)
        int result = userService.register(username, password, nickname);

        if (result == 0) { // 成功
            req.setAttribute("msg", "注册成功！请登录");
            req.getRequestDispatcher("/view/login.jsp").forward(req, resp);
        } else if (result == 1) { // 账号已存在
            req.setAttribute("msg", "注册失败！账号已存在");
            req.getRequestDispatcher("/view/register.jsp").forward(req, resp);
        } else { // 数据库插入失败 (result == 2)
            // 针对您遇到的连接问题，提供更准确的提示
            req.setAttribute("msg", "注册失败！服务器连接异常或数据库操作失败，请检查网络配置。");
            req.getRequestDispatcher("/view/register.jsp").forward(req, resp);
        }
    }

    // 登录方法
    private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = userService.login(username, password);
        if (user != null) {
            HttpSession session = req.getSession();
            session.setAttribute("loginUser", user);
            // 登录成功，跳转到主页
            resp.sendRedirect(req.getContextPath() + "/view/index.jsp");
        } else {
            req.setAttribute("msg", "账号或密码错误！");
            req.getRequestDispatcher("/view/login.jsp").forward(req, resp);
        }
    }

    // 退出登录方法
    private void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        session.removeAttribute("loginUser"); // 移除会话中的用户信息
        session.invalidate(); // 使会话失效
        resp.sendRedirect(req.getContextPath() + "/view/login.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // GET 请求，只处理 /logout 路径，其他请求转发给 doPost
        if ("/user/logout".equals(req.getRequestURI())) {
            logout(req, resp);
        } else {
            doPost(req, resp);
        }
    }
}