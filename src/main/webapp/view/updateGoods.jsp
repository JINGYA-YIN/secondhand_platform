<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
  <title>修改商品</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style.css">
</head>
<body>
<c:if test="${empty sessionScope.loginUser}">
  <script>alert("请先登录！"); location.href="${pageContext.request.contextPath}/view/login.jsp";</script>
</c:if>

<div class="container">
  <h3>修改商品信息</h3>
  <form action="${pageContext.request.contextPath}/goods/update" method="post">
    <input type="hidden" name="goodsId" value="${requestScope.goods.id}">

    <div class="form-item">
      <label>商品名称：</label>
      <input type="text" name="goodsName" value="${requestScope.goods.goodsName}" required>
    </div>
    <div class="form-item">
      <label>商品类型：</label>
      <input type="text" name="goodsType" value="${requestScope.goods.goodsType}">
    </div>
    <div class="form-item">
      <label>价格（元）：</label>
      <input type="number" step="0.01" name="price" value="${requestScope.goods.price}" required>
    </div>
    <div class="form-item">
      <label>商品描述：</label>
      <textarea name="description">${requestScope.goods.description}</textarea>
    </div>
    <div class="form-item">
      <label>商品状态：</label>
      <select name="status">
        <option value="1" <c:if test="${requestScope.goods.status == 1}">selected</c:if>>在售</option>
        <option value="2" <c:if test="${requestScope.goods.status == 2}">selected</c:if>>已售</option>
        <option value="3" <c:if test="${requestScope.goods.status == 3}">selected</c:if>>已下架</option>
      </select>
    </div>
    <div class="form-item error">${requestScope.msg}</div>
    <div class="form-item" style="margin-left: 130px;">
      <button type="submit" class="btn">保存修改</button>
    </div>
  </form>
  <div style="margin-top: 20px; margin-left: 130px;">
    <a href="${pageContext.request.contextPath}/goods/myGoods">返回我的商品</a>
  </div>
</div>
</body>
</html>