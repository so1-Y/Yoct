<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%//@page import="model.SharyoBean"%>
<%//@  page import="java.util.List"%>
<%//List<SharyoBean>SharyoLists = (List<SharyoBean>)request.getAttribute("SharyoLists");
//String msg =(String)request.getAttribute("msg");%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>【車両情報一覧】</title>
</head>
<body>
<h1>【車両情報一覧】</h1>
<form action="/sharyoKanri_GG/AdminCarDispSV" method="post">
<table border="1" frame="void"class="design01 ">
<tr bgcolor="gainsboro">
<th>選択</th>
<th>車番</th>
<th>車種</th>
<th>駆動</th>
<th>社員番号</th>
<th>使用者</th>
<th>年式</th>
<th>車検満了日</th>
<th>総走行距離</th>
<th>スタッドレスタイヤ</th></tr>




<%// if(SharyoLists != null) {
	// for(SharyoBean sharyo : SharyoLists) {%>
<c:if test ="${SharyoLists != null}">
	<c:forEach var="sb"  items="${SharyoLists}">
	<tr>
	<td><input type="radio" name="shabanInfos" value="<c:out value="${sb.shabanInfo}"/>,<c:out value="${sb.shaban}"/>"></td>

	<td align="left">${ sb.shabanInfo} ${ sb.shaban}　</td>
	<td align="center">${ sb.shashu}</td>
	<td align="center">${ sb.kudo}</td>
	<td align="center">${ sb.carUserNumber} </td>
	<td align="left">${ sb.carUser} </td>
	<td align="center">${ sb.yearType}年 </td>
	<td align="center">${ sb.shakenbi}</td>
	<td align="right">　${ sb.mileage} ㎞</td>
	<td align="center">${ sb.snowTire}</td>
		</tr>
			</c:forEach>
</c:if>

</table>
<input type="submit" value="車両情報の詳細" >←←詳細情報を見たい車両を選んでクリックしてください<br>
</form>
 <font color="red">${ selectErrMsg }</font>


<hr>
<h2>検索</h2>
<form action="/sharyoKanri_GG/AdminTopSV?jyoken=sokokyori" method="post">
<table ><tr><th align="left">総走行距離</th><th>　　　　　</th><th align="left" ><input type="text" name="mileage" value=0>km以上</th>
<th>　　　　　<input type="submit" value="検索"></th></tr></table></form>

<form action="/sharyoKanri_GG/AdminTopSV?jyoken=snowNeed" method="post">
<table>
<tr>
<th>冬用タイヤ必要<input type="radio" name="SnowTire" value="必要"></th><th>　　　</th>
<th>冬用タイヤ不要<input type="radio" name="SnowTire" value="不要"></th>
<th>　　　　　　　　　　<input type="submit" value="検索"></th></tr></table></form>
<br>
<font color="red">${ choiceErrMsg }</font>
<hr>

<table>
<tr>
<td><a href= "/sharyoKanri_GG/AdminPartsListSV"><input type="button"  value="部品情報一覧" ></a></td><td>　　　　　</td>
<td><a href="/sharyoKanri_GG/AdminCarRegisterSV"><input type="button"  value="車両追加・削除" ></a></td><td>　　　　　</td>
<td><a href="/sharyoKanri_GG/RegisterUserSV"><input type="button"  value="ユーザー追加" ></a></td>
</tr></table>
<br>
<a href="/sharyoKanri_GG/MainSV"><button type="button" >ログアウト</button></a>
<p>${Msg}</p>
</body>
</html>