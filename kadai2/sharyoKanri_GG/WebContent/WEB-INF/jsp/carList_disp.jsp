<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="model.SharyoBean"%>
<%@  page import="java.util.List"%>
<%
List<SharyoBean>SharyoLists = (List<SharyoBean>)request.getAttribute("SharyoLists");
String msg =(String)request.getAttribute("msg");%>
<!DOCTYPE html>
<html>
<head >
<link rel="stylesheet" type="text/css" href="style2.css">
<meta charset="UTF-8">
<title>【車両情報一覧】</title>
<style>
body{
 background:linear-gradient(90deg, #f0ffff,#A6FFCB );
}
.layer{
  /* 背景画像設定 */

  background-size: 100%;
  /* ボックス配置指定 */
  position: relative;
  margin: 0 auto;
  text-align: center;
  /* ボックスサイズ指定 */
  width: 640px;
  height: 358px;
}
* /重ねるボックスの設定 */
.layer-in{
  /* ボックス配置指定 */
  position: absolute;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  margin: auto;
  /* 上下左右中央のときのみ */
  width: 400px;
  height: 100px;
}

/* 画像基本設定 */
.layer-img{
  width: 450px;
  height: auto;
}

</style>
</head>
<body>
<h1>【車両情報一覧】</h1>
<form action="/sharyoKanri_GG/CarListDispSV" method="post">
<table  class="design01 " >
<tr>
<th >選択</th>
<th >車番</th>
<th >使用者</th>
<th >車検満了日</th>
<th >車種</th>
<th >スタッドレスタイヤ</th>
<th >最終点検日</th>
<th >最終点検結果</th>
<th >総走行距離</th>
<th >年式</th>

<% if(SharyoLists != null) {
	for(SharyoBean sharyo : SharyoLists) {%>
<tr>
<td ><input type="radio" name="select" value="<%=sharyo.getShabanInfo()%>,<%= sharyo.getShaban() %>"></td>
<td align="center">  <%= sharyo.getShabanInfo() %> <%= sharyo.getShaban() %></td>
<td align="center"><%= sharyo.getCarUser() %> </td>
<td align="center"><%= sharyo.getShakenbi()  %></td>
<td align="center"><%= sharyo.getShashu() %></td>
<td align="center"><%= sharyo.getSnowTire() %></td>
<td align="center"><%= sharyo.getLastChkDay() %> </td>
<td align="center"><%= sharyo.getLastChkRslt() %> </td>
<td align="center"><%= sharyo.getMileage() %> ㎞</td>
<td align="center"><%= sharyo.getYearType() %>年 </td>
</tr>
<%  }
}%>
<br>
</table>
<input type="submit" value="詳細情報" class="btn-square-emboss">←詳細情報を見たい車両を選んでクリックしてください
</form>
<%if(msg!=null) {%>
<font color="red"><b><%=msg%></b></font>
<%} %>
<br>
<a href="/sharyoKanri_GG/MainSV"class="btn-square-raised">トップ画面に戻る</a>
<div class="layer">
  <div class="layer-in">
    <img src="http://localhost:8080/sharyoKanri_GG/img/car_animals.png" alt="車" class="layer-img">
</div></div>
</body>
</html>