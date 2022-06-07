<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="style.css" >
		<link rel="stylesheet" href="button.css" >
		<title>車両管理システム</title>
		<style>
		    #child1 {
		      background-color: black;
			  color: white;
		  	  height: 400px;
		      overflow:scroll;
		    }
		    #child2 {
		      background-color: black;
		      color: white;
		      height: 400px;
		      overflow:scroll;
		    }
		    #subTitle {
				line-height:0;
				background-color: white;
		        color: black;
		    }
		      #parent,#subTitle {
		        display: flex;
		      }
		      #child1,h2 {
		        flex-grow: 1;
		      }
		      #child2 {
		        flex-grow: 1;
		      }
		</style>


	</head>

<body>
	<div class="container">
<img src ="http://localhost:8080/sharyoKanri_GG/img/GG.jpg" width="700" height="110"  alt="GG" >
<%-- <h1>車両管理システム</h1> --%>

		<form action="/sharyoKanri_GG/MainSV?action=login"  method="post">
　　ログインID:<input type="tel" name="id" id="form-ticker-symbol" maxlength="4"><br>
　　パスワード:<input type="password" name="password"><br>
　　<input type="submit" value="login" ><br>
　　${loginErrMsg}
		</form>


　　<a href="/sharyoKanri_GG/CarListDispSV" class="btn btn-solid-silver">車両情報表示ボタン</a>
<%-- 　　<a href="/sharyoKanri_GG/CarListDispSV"><button type="button" >車両情報表示ボタン</button></a> --%>

		<br><br>
		</div>
		<hr>
		<div id="subTitle">
			<h2 id="subTitle1"> お知らせ情報</h2>
			<h2></h2>
			<h2 id="subTitle2"> 車検情報</h2>
			<h2></h2>
		</div>
		<div id="parent">
			<div id="child1">

			<c:if test="${nTireOverUsed.size()!=0}">
			<h4>ノーマルタイヤ使用距離が5万km超の車両</h4>
			</c:if>
			<c:forEach var="msg"  items="${nTireOverUsed}">
			${msg}<br>
			</c:forEach>

			<c:if test="${sTireOverUsed.size()!=0}">
			<h4>スタッドレスタイヤ使用距離が5万km超の車両</h4>
			</c:if>
			<c:forEach var="msg"  items="${sTireOverUsed}">
			${msg}<br>
			</c:forEach>

			<c:if test="${nTireOverYear.size()!=0}">
			<h4>ノーマルタイヤ使用年数が5年以上の車両</h4>
			</c:if>
			<c:forEach var="msg"  items="${nTireOverYear}">
			${msg}<br>
			</c:forEach>

			<c:if test="${sTireOverYear.size()!=0}">
			<h4>スタッドレスタイヤ使用年数が5年以上の車両</h4>
			</c:if>
			<c:forEach var="msg"  items="${sTireOverYear}">
			${msg}<br>
			</c:forEach>

			<c:if test="${oilOverUsed.size()!=0}">
			<h4>オイル交換が必要な車両</h4>
			</c:if>
			<c:forEach var="msg"  items="${oilOverUsed}">
			${msg}<br>
			</c:forEach>

			<c:if test="${elementOverUsed.size()!=0}">
			<h4>エレメント交換が必要な車両</h4>
			</c:if>
			<c:forEach var="msg"  items="${elementOverUsed}">
			${msg}<br>
			</c:forEach>

			<c:if test="${batteryOverUsed.size()!=0}">
			<h4>バッテリー交換が必要な車両</h4>
			</c:if>
			<c:forEach var="msg"  items="${batteryOverUsed}">
			${msg}<br>
			</c:forEach>
			</div>

			<div id="child2">

			<c:if test="${shaken2month.size()!=0}">
			<h4>車検が翌々月の車両（要見積作成）</h4>
			</c:if>
			<c:forEach var="msg"  items="${shaken2month}">
			${msg}<br>
			</c:forEach>

			<c:if test="${shaken1month.size()!=0}">
			<h4> 車検が翌月の車両（要日程調整）</h4>
			</c:if>
			<c:forEach var="msg"  items="${shaken1month}">
			${msg}<br>
			</c:forEach>
			</div>
		</div>
	</body>
</html>