<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>搜索结果</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style.css">
</head>
<body>
<div class="container">
    <h3>搜索结果：<span style="color: #0066cc;">${requestScope.keyword}</span></h3>

    <c:choose>
        <c:when test="${empty requestScope.goodsList}">
            <div class="empty">暂无匹配的二手商品</div>
        </c:when>
        <c:otherwise>
            <table class="table">
                <tr>
                    <th>商品名称</th>
                    <th>类型</th>
                    <th>价格（元）</th>
                    <th>描述</th>
                    <th>发布时间</th>
                </tr>
                <c:forEach items="${requestScope.goodsList}" var="goods">
                    <tr>
                        <td>${goods.goodsName}</td>
                        <td>${goods.goodsType}</td>
                        <td><fmt:formatNumber value="${goods.price}" pattern="0.00"/></td>
                        <td>${goods.description}</td>
                        <td><fmt:formatDate value="${goods.createTime}" pattern="yyyy-MM-dd"/></td>
                    </tr>
                </c:forEach>
            </table>
        </c:otherwise>
    </c:choose>

    <div style="margin-top: 20px; margin-left: 130px;">
        <a href="${pageContext.request.contextPath}/view/search.jsp">重新搜索</a> |
        <a href="${pageContext.request.contextPath}/view/index.jsp">返回首页</a>
    </div>
</div>
</body>
</html>
