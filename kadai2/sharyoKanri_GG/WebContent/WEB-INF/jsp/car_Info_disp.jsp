<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@page import="model.SharyoBean"%>
   <%@page import="model.TireBean"%>
   <%@page import="model.ShomohinBean"%>
   <%@  page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
List<SharyoBean>SharyoLists = (List<SharyoBean>)request.getAttribute("SharyoLists");
TireBean tireList = (TireBean)request.getAttribute("tireList");
ShomohinBean shomohinLis = (ShomohinBean)request.getAttribute("shomohinLis");
--%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="style2.css">
<meta charset="UTF-8">
<title>【車両情報】</title>
</head>
<body class="background_test">
<h1>車両情報</h1>
<table  class="design01 ">
	<tr>
	 <th>車両番号</th><th>車種</th><th>年式</th><th>使用者</th>
	</tr>
	<tr>
	 <td  align="center">${sb.shabanInfo} ${sb.shaban}</td><td  align="center">${sb.shashu}</td><td  align="center">${sb.yearType}</td><td  align="center">${sb.carUser}</td>
	</tr>
	<tr >
	 <th>車検満了日</th><th>駆動</th><th colspan="2">使用中のタイヤ種</th>
	</tr>
	<tr>
	 <td  align="center">${sb.shakenbi}</td><td  align="center">${sb.kudo}</td><td colspan="2">${sb.tireType}</td>
	</tr>
</table>
<%-- 車両情報テーブル --%>

<%-- メンテナンス情報テーブル --%>
<h1>メンテナンス情報</h1>
<table class="design01 ">
	<tr><th>当車両走行距離</th><td align="center">${ sb.mileage }km</td></tr>
</table>
<h3>タイヤ情報（直近）</h3>
<table class="design01 ">
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
	 <td> ${tb.nTireDay}</td>
	 <td>${tb.nTireKm} km</td>
	 <c:choose>
	 	<c:when test="${sb.tireType.trim() == 'ノーマル'}">
		<td> - </td>
		<td> - </td>
		</c:when>
		<c:otherwise>
		<td>${tb.sTireDay}</td>
		<td>${tb.sTireKm} km</td>
		</c:otherwise>
	 </c:choose>
	 <td>${tb.nTireTotalNowKm}km</td><%--★★★ここを変更してます（安永） --%>
	</tr>
	<c:if test="${sb.snowTire == '必要'}"><%-- snowTireが必要だったらスタッドレス行、表示 --%>
	<tr align="center">
	 <td>スタッドレス</td>
	 <td>${tb.sTireDay}</td>
	 <td>${tb.sTireKm} km</td>
	 <c:choose>
	 	<c:when test="${sb.tireType.trim() == 'スタッドレス'}">
		<td align="center"> - </td>
		<td align="center"> - </td>
		</c:when>
		<c:otherwise>
		<td align="center">${tb.nTireDay}</td>
		<td align="center">${tb.nTireKm} km</td>
		</c:otherwise>
	 </c:choose>
	 <td>${tb.sTireTotalNowKm}km</td><%--★★★ここを変更してます（安永） --%>
	</tr>
	</c:if>

</table>
<h3>消耗品情報</h3>
<table class="design01 ">
	<tr>
	 <th>部品名</th>
	 <th>最新交換日</th>
	 <th>最新交換時の走行距離</th>
	 <th>現時点での使用距離</th>
	</tr>
	<tr align="center">
	 <td>オイル</td>
	 <td>${ smb.oilDay }</td>
	 <td>${ smb.oilKm }km</td>
	 <td>${ sb.mileage - smb.oilKm }km</td>
	</tr>
	<tr align="center">
	 <td>エレメント</td>
	 <td>${ smb.elementDay }</td>
	 <td>${ smb.elementKm }km</td>
	 <td>${ sb.mileage - smb.elementKm }km</td>
	</tr>
	<tr align="center">
	 <td>バッテリー</td>
	 <td>${ smb.batteryDay }</td>
	 <td>${ smb.batteryKm }km</td>
	 <td>${ sb.mileage - smb.batteryKm }km</td>
	</tr>
</table>

<h3>直近の点検結果</h3>
<table class="design01 ">
	<tr><th >最終点検日</th><td>${sb.lastChkDay}</td><th >点検結果</th><td>${sb.lastChkRslt}</td></tr>
</table>
<a href ="/sharyoKanri_GG/CarListDispSV"class="btn-square-emboss" >一覧へ戻る</a>　　　<a href="/sharyoKanri_GG/MainSV"class="btn-square-raised">トップ画面に戻る</a>


</body>
</html>