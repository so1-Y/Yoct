<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>部品情報一覧</h1>

<form action="/sharyoKanri_GG/AdminCarDispSV?action=parts" method="post">

<table border="1">
<tr bgcolor="gainsboro">
<th rowspan="2">車両<br>選択</th> <th rowspan="2">車両番号</th> <th rowspan="2"> 使用者 </th>
<th colspan="3" >ノーマルタイヤ</th> <th colspan="3" >スタッドレスタイヤ</th>
<th colspan="2">オイル</th> <th colspan="2">エレメント</th> <th colspan="2">バッテリー</th>
</tr>
<tr bgcolor="gainsboro">
<th>総使用<br>距離</th> <th>使用<br>年数</th> <th>最新<br>交換日</th>
<th>総使用<br>距離</th> <th>使用<br>年数</th> <th>最新<br>交換日</th>
<th>使用<br>距離</th> <th>最新<br>交換日</th>
<th>使用<br>距離</th> <th>最新<br>交換日</th>
<th>使用<br>距離</th> <th>最新<br>交換日</th>
</tr>

		<c:forEach var="b"  items="${buhinList}">
		<tr>
			<td><input type="radio" name="shabanInfos" value="${b.shabanInfo},${b.shaban}"></td>

			<td>${b.shabanInfo } ${b.shaban }</td>	<td>${b.carUser}</td>

			<td  align="right">${b.nTireNowKm}km</td>	<td  align="right">${b.nTireYear}年</td>	<td  align="right">${b.nTireDay  }</td>

			<td  align="right">${b.sTireNowKm}km</td>	<td  align="right">${b.sTireYear}年</td>	<td  align="right">${b.sTireDay  }</td>

			<td  align="right">${b.mileage - b.oilKm}km</td>	<td  align="right">${b.oilDay  }</td>
			<td  align="right">${b.mileage - b.elementKm}km</td>	<td  align="right">${b.elementDay }</td>
			<td  align="right">${b.mileage - b.batteryKm}km</td>	<td  align="right">${b.batteryDay }</td>
		</tr>
  	  </c:forEach>

</table>

<input type="submit" name="choice"  value="詳細表示">

</form>

 <font color="red">${ selectErrMsg2 }</font>

<br><br>
<hr>
<a href="/sharyoKanri_GG/AdminLoginSV"><button type="button" >管理者トップ画面</button></a>
　　　　　　　　
<a href="/sharyoKanri_GG/MainSV"><button type="button" >ログアウト</button></a>
</body>
</html>