<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">

input.rdOnly {
background-color: #d3d3d3;
}

</style>
</head>
<body>
<h1>新規車両登録</h1>
<form action="/sharyoKanri_GG/AdminCarRegisterSV?action=register" method="post">
	<table class="noborder">
		<tr class="noborder">
			<td  class="noborder" align="right">車番（上部）：</td>
			<td  class="noborder"><input type="text" name="shabanInfo"></td>
			<td  class="noborder"></td>
			<td  class="noborder" align="right">車番（４桁）：</td>
			<td  class="noborder"><input type="text" name="shaban"></td>
			<td  class="noborder" align="right">　　年式：</td>
			<td  class="noborder"><select name="yearType">
				<option value="">-</option>
				<option value="2010">2010</option>
				<option value="2011">2011</option>
				<option value="2012">2012</option>
				<option value="2013">2013</option>
				<option value="2014">2014</option>
				<option value="2015">2015</option>
				<option value="2016">2016</option>
				<option value="2017">2017</option>
				<option value="2018">2018</option>
				<option value="2019">2019</option>
				<option value="2020">2020</option>
				<option value="2021">2021</option>
				<option value="2022">2022</option>
				<option value="2023">2023</option>
				<option value="2024">2024</option>
				<option value="2025">2025</option>
				<option value="2026">2026</option>
				<option value="2027">2027</option>
				<option value="2028">2028</option>
				<option value="2029">2029</option>
				<option value="2030">2030</option></select>年</td>
		</tr>

		<tr class="noborder">
			<td  class="noborder" align="right">車種：</td>
			<td  class="noborder"><input type="text" name="shashu"></td>
			<td  class="noborder"></td>
<td  class="noborder" align="right">
	納車日：</td>
<td  class="noborder" align="left">
	<select name="year">
	<option value="">-</option>
	<option value="2016">2016</option>
	<option value="2017">2017</option>
	<option value="2018">2018</option>
	<option value="2019">2019</option>
	<option value="2020">2020</option>
	<option value="2021">2021</option>
	<option value="2022">2022</option>
	<option value="2023">2023</option>
	<option value="2024">2024</option>
	<option value="2025">2025</option>
	<option value="2026">2026</option>
	<option value="2027">2027</option>
	<option value="2028">2028</option>
	<option value="2029">2029</option>
	<option value="2030">2030</option></select>年
	<select name="month">
	<option value="">-</option>
	<option value="1">1</option>
	<option value="2">2</option>
	<option value="3">3</option>
	<option value="4">4</option>
	<option value="5">5</option>
	<option value="6">6</option>
	<option value="7">7</option>
	<option value="8">8</option>
	<option value="9">9</option>
	<option value="10">10</option>
	<option value="11">11</option>
	<option value="12">12</option></select>月
	<select name="day">
	<option value="">-</option>
	<option value="1">1</option>
	<option value="2">2</option>
	<option value="3">3</option>
	<option value="4">4</option>
	<option value="5">5</option>
	<option value="6">6</option>
	<option value="7">7</option>
	<option value="8">8</option>
	<option value="9">9</option>
	<option value="10">10</option>
	<option value="11">11</option>
	<option value="12">12</option>
	<option value="13">13</option>
	<option value="14">14</option>
	<option value="15">15</option>
	<option value="16">16</option>
	<option value="17">17</option>
	<option value="18">18</option>
	<option value="19">19</option>
	<option value="20">20</option>
	<option value="21">21</option>
	<option value="22">22</option>
	<option value="23">23</option>
	<option value="24">24</option>
	<option value="25">25</option>
	<option value="26">26</option>
	<option value="27">27</option>
	<option value="28">28</option>
	<option value="29">29</option>
	<option value="30">30</option>
	<option value="31">31</option></select>日</td>

		</tr>


		<tr  class="noborder">
			<td  class="noborder" align="right">使用者の社員番号：</td>
			<td  class="noborder"><input type="text" name="carUserNumber"></td>
			<td  class="noborder"></td>
			<td  class="noborder" align="right">使用者：</td>
			<td  class="noborder"><input type="text" name="carUser"  class="rdOnly" readonly></td>
		</tr>

		<tr  class="noborder">
			<td  class="noborder" align="right">駆動：</td>
			<td  class="noborder"><select name="kudo">
				  <option value="2wd">2WD</option>
				  <option value="4wd">4WD</option>
				  </select></td>
			<td  class="noborder"></td>
			<td  class="noborder" align="right">車検満了日(mm/dd)：</td>
			<td  class="noborder"><select name="shakenMonth">
	<option value="">-</option>
	<option value="1">1</option>
	<option value="2">2</option>
	<option value="3">3</option>
	<option value="4">4</option>
	<option value="5">5</option>
	<option value="6">6</option>
	<option value="7">7</option>
	<option value="8">8</option>
	<option value="9">9</option>
	<option value="10">10</option>
	<option value="11">11</option>
	<option value="12">12</option></select>月
	<select name="shakenDay">
	<option value="">-</option>
	<option value="1">1</option>
	<option value="2">2</option>
	<option value="3">3</option>
	<option value="4">4</option>
	<option value="5">5</option>
	<option value="6">6</option>
	<option value="7">7</option>
	<option value="8">8</option>
	<option value="9">9</option>
	<option value="10">10</option>
	<option value="11">11</option>
	<option value="12">12</option>
	<option value="13">13</option>
	<option value="14">14</option>
	<option value="15">15</option>
	<option value="16">16</option>
	<option value="17">17</option>
	<option value="18">18</option>
	<option value="19">19</option>
	<option value="20">20</option>
	<option value="21">21</option>
	<option value="22">22</option>
	<option value="23">23</option>
	<option value="24">24</option>
	<option value="25">25</option>
	<option value="26">26</option>
	<option value="27">27</option>
	<option value="28">28</option>
	<option value="29">29</option>
	<option value="30">30</option>
	<option value="31">31</option></select>日<br>
			</td>
		</tr>

		<tr  class="noborder">
			<td  class="noborder" align="right">使用中のタイヤ種：</td>
			<td  class="noborder"><select name="tireType">
				  <option value="ノーマル">ノーマル</option>
				  <option value="スタッドレス">スタッドレス</option>
				  </select></td>
			<td  class="noborder">　　</td>
			<td  class="noborder" align="right">冬用タイヤ有無：</td>
			<td  class="noborder"><select name="snowTire">
				  <option value="必要">必要</option>
				  <option value="不要">不要</option>
				  </select></td>
		</tr>
	</table>
	<input type="submit" value="登録"><br>

<font color="red">${msg}${errMsg}</font><br>

</form>

	<h1>車両削除</h1>
削除する車両を選んでください　
<form action="/sharyoKanri_GG/AdminCarRegisterSV?action=delete" method="post">
<select name="delSharyo">
<c:if test ="${sbl != null}">
		<c:forEach var="sb"  items="${sbl}">
				  <option value="${ sb.shabanInfo },${ sb.shaban}">${ sb.shabanInfo} ${ sb.shaban}</option>
		</c:forEach>
</c:if>
	</select>



<%--
	<table>
	<tr>
	<td  class="noborder" align="right">車番（上部）：</td>
	<td  class="noborder"><input type="text" name="delShabanInfo"></td>
	<td  class="noborder">　　</td>
	<td  class="noborder" align="right">車番（４桁）：</td>
	<td  class="noborder"><input type="text" name="delShaban"></td>
	</tr>
	</table>
--%>
<input type="submit" value="削除"><br>

<font color="red">${delMsg}</font>

</form>
<br>
<a href="/sharyoKanri_GG/AdminLoginSV"><button type="button" >管理者トップ画面</button></a>
　　　　　　　　<a href="/sharyoKanri_GG/MainSV"><button type="button" >ログアウト</button></a>
</body>
</html>
