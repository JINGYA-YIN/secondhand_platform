<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>发布商品</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style.css">
</head>
<body>
<c:if test="${empty sessionScope.loginUser}">
    <script>alert("请先登录！"); location.href="${pageContext.request.contextPath}/view/login.jsp";</script>
</c:if>

<div class="container">
    <h3>发布二手商品</h3>
    <form action="${pageContext.request.contextPath}/goods/add" method="post">
        <div class="form-item">
            <label>商品名称：</label>
            <input type="text" name="goodsName" required>
        </div>
        <div class="form-item">
            <label>商品类型：</label>
            <input type="text" name="goodsType">
        </div>
        <div class="form-item">
            <label>价格（元）：</label>
            <input type="number" step="0.01" name="price" required>
        </div>
        <div class="form-item">
            <label>商品描述：</label>
            <textarea name="description"></textarea>
        </div>
        <div class="form-item error">${requestScope.msg}</div>
        <div class="form-item" style="margin-left: 130px;">
            <button type="submit" class="btn">发布</button>
        </div>
    </form>
    <div style="margin-top: 20px; margin-left: 130px;">
        <a href="${pageContext.request.contextPath}/view/index.jsp">返回首页</a>
    </div>
</div>
</body>
</html>
