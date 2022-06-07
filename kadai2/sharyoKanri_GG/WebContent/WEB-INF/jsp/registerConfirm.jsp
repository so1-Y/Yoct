<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="model.UsersBeanX" %>
    <%
    UsersBeanX registerUser = (UsersBeanX) session.getAttribute("registerUser");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー登録</title>
</head>
<body>
<p>以下のユーザーを登録します</p>
<p>
社員番号:<%= registerUser.getUserNumber() %><br>
名前:<%= registerUser.getUserName() %><br>
パスワード:<%= registerUser.getPassword() %><br>
担当地区:<%= registerUser.getArea() %><br>
</p>
<a href="/sharyoKanri_GG/RegisterUserSV">戻る</a>
<a href="/sharyoKanri_GG/RegisterUserSV?action=done">登録</a>
</body>
</html>