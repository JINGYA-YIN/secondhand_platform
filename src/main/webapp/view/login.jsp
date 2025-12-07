<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
  <title>用户登录</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style.css">
</head>
<body>
<div class="container">
  <h3>二手交易平台 - 登录</h3>
  <form action="${pageContext.request.contextPath}/user/login" method="post">
    <div class="form-item">
      <label>账号：</label>
      <input type="text" name="username" required>
    </div>
    <div class="form-item">
      <label>密码：</label>
      <input type="password" name="password" required>
    </div>
    <div class="form-item error">${requestScope.msg}</div>
    <div class="form-item" style="margin-left: 130px;">
      <button type="submit" class="btn">登录</button>
    </div>
  </form>
  <div style="margin-top: 20px; margin-left: 130px;">
    <a href="${pageContext.request.contextPath}/view/register.jsp">暂无账号？去注册</a>
  </div>
</div>
</body>
</html>