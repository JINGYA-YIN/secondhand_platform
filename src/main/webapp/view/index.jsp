<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>二手交易平台 - 首页</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style.css">
</head>
<body>
<div class="container">
    <div class="header">
        <c:choose>
            <c:when test="${empty sessionScope.loginUser}">
                <a href="${pageContext.request.contextPath}/view/login.jsp">登录</a> |
                <a href="${pageContext.request.contextPath}/view/register.jsp">注册</a>
            </c:when>
            <c:otherwise>
                欢迎：<span style="color: #0066cc;">${sessionScope.loginUser.nickname}</span> |
                <a href="${pageContext.request.contextPath}/goods/myGoods">我的商品</a> |
                <a href="${pageContext.request.contextPath}/view/addGoods.jsp">发布商品</a> |
                <a href="${pageContext.request.contextPath}/user/logout">退出登录</a>
            </c:otherwise>
        </c:choose>
    </div>

    <div class="search-area">
        <h2>二手物品交易平台</h2>
        <form action="${pageContext.request.contextPath}/goods/search" method="get">
            <input type="text" name="keyword" placeholder="输入商品名称搜索...">
            <button type="submit" class="btn">搜索</button>
        </form>
    </div>

    <div class="iframe-container">
        <iframe
                src="${pageContext.request.contextPath}/index?target=goods_list"
                width="100%"
                height="500px"
                frameborder="0"
                scrolling="auto">
        </iframe>
    </div>
</div>
</body>
</html>