<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー登録</title>
</head>
<body>
<h>ユーザー登録</h>
<form action="/sharyoKanri_GG/RegisterUserSV" method="post">
社員番号:<input type="text" name="id"><br>
名前:<input type="text" name="name"><br>
パスワード:<input type="text" name="pass"><br>
担当地区:<input type="text" name="tanto"><br>
<input type="submit" value="追加">
<a href="/sharyoKanri_GG/AdminLoginSV">戻る</a>

</form>

</body>
</html>