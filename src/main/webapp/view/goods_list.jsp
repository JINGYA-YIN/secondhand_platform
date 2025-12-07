<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<html>
<head>
  <meta charset="UTF-8">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style.css">
</head>
<body>
<div class="goods-container">
  <div class="goods-title">在售商品列表</div>
  <table class="goods-table">
    <tr>
      <th>商品名称</th>
      <th>类型</th>
      <th>价格（元）</th>
      <th>发布时间</th>
      <th>状态</th>
    </tr>
    <c:forEach items="${onSaleGoods}" var="goods">
      <tr>
        <td>${goods.goodsName}</td>
        <td>${goods.goodsType}</td>
        <td>${goods.price}</td>
        <td>
          <c:if test="${not empty goods.createTime}">
            <fmt:formatDate value="${goods.createTime}" pattern="yyyy-MM-dd HH:mm"/>
          </c:if>
          <c:if test="${empty goods.createTime}">--</c:if>
        </td>
        <td><span class="status-sale">在售</span></td>
      </tr>
    </c:forEach>
    <c:if test="${empty onSaleGoods}">
      <tr><td colspan="5" class="empty-tip">暂无在售商品</td></tr>
    </c:if>
  </table>
</div>
</body>
</html>