<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="style.css" >
	<title>車両情報</title>
	<style>
	form{
		position:absolute;
	}
	body{
		background-color: #ebf6f7;
		background-image: url(http://localhost:8080/sharyoKanri_GG/img/istockphoto-924664228-170667a_transparent.png);
		background-position:95%;
		background-size:110px;
		background-repeat:repeat-y
	}
	input {
	font-size: 100%;
	}
	.button{
	    width: 150px;
	    padding: 10px;
	    border-radius: 100vh;
	}
	h1,h2,h3 {
		line-height:0;
	}
	</style>
</head>
<body>
<h1>車両情報</h1>
<%-- 車両情報テーブル --%>
<table border="1" width="70%">
	<tr bgcolor="gainsboro">
	 <th>車両番号</th><th>車種</th><th>年式</th><th>使用者</th>
	</tr>
	<tr align="center">
	 <td>${sharyo.shabanInfo} ${sharyo.shaban}</td><td>${sharyo.shashu}</td><td>${sharyo.yearType}</td><td>${sharyo.carUser}</td>
	</tr>
	<tr bgcolor="gainsboro">
	 <th>車検満了日</th><th>駆動</th><th colspan="2">使用中のタイヤ種</th>
	</tr>
	<tr align="center">
	 <td>${ sharyo.shakenbi }</td><td>${ sharyo.kudo }</td><td colspan="2">${ sharyo.tireType }</td>
	</tr>
</table><br>
<%-- 車両情報テーブルここまで --%>

<%-- メンテナンス情報テーブル --%>
<h2>メンテナンス情報</h2>
<table border="1" width="50%">
	<tr><th bgcolor="gainsboro">当車両走行距離</th><td align="center">${ sharyo.mileage }km</td></tr>
</table><br>
<h3>タイヤ情報（直近）</h3>
<table border="1" width="70%">
	<tr bgcolor="gainsboro">
	 <th>使用タイヤ種</th>
	 <th>使用開始日</th>
	 <th>使用開始時のメーター</th>
	 <th>使用終了日</th>
	 <th>使用終了時のメーター</th>
	 <th>現時点での使用距離</th>
	</tr>
	<tr align="center">
	 <td>ノーマル</td>
	 <td> ${tire.nTireDay}</td>
	 <td>${tire.nTireKm} km</td>
	 <c:choose>
	 	<c:when test="${sharyo.tireType.trim() == 'ノーマル'}">
		<td> - </td>
		<td> - </td>
		</c:when>
		<c:otherwise>
		<td>${tire.sTireDay}</td>
		<td>${tire.sTireKm} km</td>
		</c:otherwise>
	 </c:choose>
	 <td>${tire.nTireTotalNowKm}km</td><%--★★★ここを変更してます（安永） --%>
	</tr>
	<c:if test="${sharyo.snowTire == '必要'}"><%-- snowTireが必要だったらスタッドレス行、表示 --%>
	<tr align="center">
	 <td>スタッドレス</td>
	 <td>${tire.sTireDay}</td>
	 <td>${tire.sTireKm} km</td>
	 <c:choose>
	 	<c:when test="${sharyo.tireType.trim() == 'スタッドレス'}">
		<td align="center"> - </td>
		<td align="center"> - </td>
		</c:when>
		<c:otherwise>
		<td align="center">${tire.nTireDay}</td>
		<td align="center">${tire.nTireKm} km</td>
		</c:otherwise>
	 </c:choose>
	 <td>${tire.sTireTotalNowKm}km</td><%--★★★ここを変更してます（安永） --%>
	</tr>
	</c:if>
</table><br>
<h3>消耗品情報</h3>
<table border="1" width="70%">
	<tr bgcolor="gainsboro">
	 <th>部品名</th>
	 <th>最新交換日</th>
	 <th>最新交換時の走行距離</th>
	 <th>現時点での使用距離</th>
	</tr>
	<tr align="center">
	 <td>オイル</td>
	 <td>${ shomohin.oilDay }</td>
	 <td>${ shomohin.oilKm }km</td>
	 <td>${ sharyo.mileage - shomohin.oilKm }km</td>
	</tr>
	<tr align="center">
	 <td>エレメント</td>
	 <td>${ shomohin.elementDay }</td>
	 <td>${ shomohin.elementKm }km</td>
	 <td>${ sharyo.mileage - shomohin.elementKm }km</td>
	</tr>
	<tr align="center">
	 <td>バッテリー</td>
	 <td>${ shomohin.batteryDay }</td>
	 <td>${ shomohin.batteryKm }km</td>
	 <td>${ sharyo.mileage - shomohin.batteryKm }km</td>
	</tr>
</table><br>

<h3>直近の点検結果</h3>
<table border="1" width="50%">
	<tr>
		<th bgcolor="gainsboro">最終点検日</th>
		<th bgcolor="gainsboro">点検結果</th>
	</tr>
	<tr align="center">
		<td>${sharyo.lastChkDay}</td>
		<td>${sharyo.lastChkRslt}</td>
	</tr>
</table><br>
<%-- メンテナンス情報テーブルここまで --%>
<form>
	<input type="submit" formaction="/sharyoKanri_GG/UserCarUpdateSV" value="更新ページへ" class="button">
	<input type="submit" formaction="/sharyoKanri_GG/UserDispSV" value="ログアウト" class="button">
</form>
</body>
</html>