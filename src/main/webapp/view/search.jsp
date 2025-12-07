<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
  <title>商品搜索</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style.css">
</head>
<body>
<div class="container">
  <h3>商品搜索</h3>
  <form action="${pageContext.request.contextPath}/goods/search" method="get">
    <div class="form-item">
      <label>搜索关键词：</label>
      <input type="text" name="keyword" placeholder="输入商品名称搜索..." required>
    </div>
    <div class="form-item" style="margin-left: 130px;">
      <button type="submit" class="btn">搜索</button>
    </div>
  </form>
  <div style="margin-top: 20px; margin-left: 130px;">
    <a href="${pageContext.request.contextPath}/view/index.jsp">返回首页</a>
  </div>
</div>
</body>
</html>
