<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
  <title>我的商品</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style.css">
</head>
<body>
<c:if test="${empty sessionScope.loginUser}">
  <script>alert("请先登录！"); location.href="${pageContext.request.contextPath}/view/login.jsp";</script>
</c:if>

<div class="container">
  <h3>欢迎 <span style="color: #0066cc;">${sessionScope.loginUser.nickname}</span> 的商品管理</h3>

  <c:choose>
    <c:when test="${empty requestScope.myGoodsList}">
      <div class="empty">你还未发布任何商品</div>
      <div style="margin-left: 130px;">
        <a href="${pageContext.request.contextPath}/view/addGoods.jsp" class="btn" style="text-decoration: none;">去发布</a>
      </div>
    </c:when>
    <c:otherwise>
      <table class="table">
        <tr>
          <th>序号</th>
          <th>商品名称</th>
          <th>类型</th>
          <th>价格（元）</th>
          <th>状态</th>
          <th>发布时间</th>
          <th>操作</th>
        </tr>
        <c:forEach items="${requestScope.myGoodsList}" var="goods" varStatus="vs">
          <tr>
            <td>${vs.index + 1}</td>
            <td>${goods.goodsName}</td>
            <td>${goods.goodsType}</td>
            <td><fmt:formatNumber value="${goods.price}" pattern="0.00"/></td>
            <td>
              <c:choose>
                <c:when test="${goods.status == 1}">
                  <span class="status-sale">在售</span>
                </c:when>
                <c:when test="${goods.status == 2}">
                  <span class="status-sold">已售</span>
                </c:when>
                <c:otherwise>
                  <span class="status-off">已下架</span>
                </c:otherwise>
              </c:choose>
            </td>
            <td><fmt:formatDate value="${goods.createTime}" pattern="yyyy-MM-dd"/></td>
            <td>
              <a href="${pageContext.request.contextPath}/goods/toUpdate?goodsId=${goods.id}" style="text-decoration: none; color: #0066cc;">修改</a> |
              <a href="${pageContext.request.contextPath}/goods/delete?goodsId=${goods.id}" style="text-decoration: none; color: #0066cc;" onclick="return confirm('确定删除？')">删除</a>
            </td>
          </tr>
        </c:forEach>
      </table>
    </c:otherwise>
  </c:choose>

  <div style="margin-top: 20px; margin-left: 130px;">
    <a href="${pageContext.request.contextPath}/view/addGoods.jsp" class="btn" style="text-decoration: none;">发布新商品</a> |
    <a href="${pageContext.request.contextPath}/view/index.jsp" style="text-decoration: none; color: #333;">返回首页</a>
  </div>
</div>
</body>
</html>