<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>車両情報更新</title>

<style type="text/css">

input.rdOnly {
background-color: #d3d3d3;
}

</style>

</head>
<body>

<form action="/sharyoKanri_GG/AdminCarUpdateSV?action=basicInfo" method="post">
<h1>車両情報更新</h1>
***車両基本情報************************************************************<br><br>

<table>
<tr><td>車両番号：<input type="text" name="shabanInfos"  value="${shabanInfo} ${shaban}" class="rdOnly" readonly>　　</td>
	<td>メーター距離：<input type="text" name="mileage" value="${mileage}">km</td></tr>
<tr><td>使用者の社員番号:<input type="text" name="carUserNumber" value="${carUserNumber}">　　</td>
	<td>使用者:<input type="text" name="carUser" value="${carUser}" class="rdOnly" readonly><br></td></tr>
<tr><td>使用中のタイヤ種：<input type="text" name="tireType" value="${tireType}" class="rdOnly" readonly>　　</td>
	<td>
		冬用タイヤ必要性：<select name="snowTire" value="${snowTire}">
	<c:choose>
	<c:when test="${snowTire==null || snowTire=='必要'}">
				  <option value="必要" selected>必要</option>
				  <option value="不要">不要</option>
	</c:when>
	<c:otherwise>
				  <option value="必要">必要</option>
				  <option value="不要" selected>不要</option>
	</c:otherwise>
	</c:choose>
				  </select>
	</td></tr>
</table>
<br>
 <input type="submit" value="車両基本情報のみ更新">

※下記交換のデータは更新されませんのでご注意ください<br><br>
***************************************************************************<br>
<font color="red">${msgBinfo}</font><br>
</form>

<form action="/sharyoKanri_GG/AdminCarUpdateSV?action=buhinInfo" method="post">
<h2>部品情報・点検結果の更新</h2>
現在のメーター距離を入力し、更新したい情報をチェックボックスで選んで[更新]ボタンを押してしてください。（複数選択可）<br>
現在のメーター距離：<input type="text" name="mileage" value="${mileage}">km　（必須入力項目）<br>
<hr>

<input type="checkbox" name="sharyoCheck" value="On">車両点検
	実施日：<select name="checkYear">
	<option value="">-</option>
	<option value="2022">2022</option>
	<option value="2023">2023</option>
	<option value="2024">2024</option>
	<option value="2025">2025</option>
	<option value="2026">2026</option>
	<option value="2027">2027</option>
	<option value="2028">2028</option>
	<option value="2029">2029</option>
	<option value="2030">2030</option></select>年
	<select name="checkMonth">
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
	<select name="checkDay">
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
	<option value="31">31</option></select>日
	結果：<select name="check_rslt"><option value="OK">OK</option><option value="NG">NG</option></select><br>
	備考：<input type="text" name="plus_text">
<hr>

<input type="checkbox" name="tireChange" value="On">タイヤ交換<br>
<table>
	<c:choose>
	<c:when test="${tireType.trim() == 'スタッドレス'}">
	<tr>
	<td>
		<input type="radio" name="tireAction" value="snowToNormal">スタッドレス（冬）→ノーマル（夏）タイヤ交換
	</td>
	</tr>
	</c:when>
	<c:when test="${tireType.trim() == 'ノーマル'}">
	<tr>
	<td>
		<input type="radio" name="tireAction" value="normalToSnow">ノーマル（夏）→スタッドレス（冬）タイヤ交換
	</td>
	</tr>
	</c:when>

	</c:choose>
</table>

<table>
	<c:choose>
	<c:when test="${snowTire==null || snowTire=='必要'}">
    <tr>
      <td><input type="radio" name="tireAction" value="newTireNormal">新タイヤ（ノーマルタイヤ）装着　　　</td>
      <td><input type="radio" name="tireAction" value="newTireSnow">新タイヤ（スタッドレスタイヤ）装着</td>
    </tr>
	</c:when>
	<c:otherwise>
    <tr>
      <td><input type="radio" name="tireAction" value="newTireNormal">新タイヤ（ノーマルタイヤ）装着　　　</td>
    </tr>
	</c:otherwise>
	</c:choose>
</table>

 交換時の距離:<input type="text" name="change_km">km
<br>
	装着日：<select name="tireYear">
	<option value="">-</option>
	<option value="2022">2022</option>
	<option value="2023">2023</option>
	<option value="2024">2024</option>
	<option value="2025">2025</option>
	<option value="2026">2026</option>
	<option value="2027">2027</option>
	<option value="2028">2028</option>
	<option value="2029">2029</option>
	<option value="2030">2030</option></select>年

	<select name="tireMonth">
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

	<select name="tireDay">
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

	製造年週：<input type="text" name="make_year">※製造年収は「新タイヤ装着」の時のみ入力<br>

<hr>
<input type="checkbox" name="oilChange" value="On">オイル交換 <input type="checkbox" name="elementChange" value="On">エレメント交換<br>
交換時の距離:<input type="text" name="partChange_km">km　　　　
	最新交換日：<select name="partYear">
	<option value="partYear">-</option>
	<option value="2022">2022</option>
	<option value="2023">2023</option>
	<option value="2024">2024</option>
	<option value="2025">2025</option>
	<option value="2026">2026</option>
	<option value="2027">2027</option>
	<option value="2028">2028</option>
	<option value="2029">2029</option>
	<option value="2030">2030</option></select>年

	<select name="partMonth">
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

	<select name="partDay">
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

<hr>
<input type="checkbox" name="batteryChange" value="On">バッテリー交換<br>
  交換時の距離:<input type="text" name="batteryChange_km">km　　　　
	最新交換日：<select name="batteryYear">
	<option value="">-</option>
	<option value="2022">2022</option>
	<option value="2023">2023</option>
	<option value="2024">2024</option>
	<option value="2025">2025</option>
	<option value="2026">2026</option>
	<option value="2027">2027</option>
	<option value="2028">2028</option>
	<option value="2029">2029</option>
	<option value="2030">2030</option></select>年

	<select name="batteryMonth">
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

	<select name="batteryDay">
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
<hr>
	<input type="submit" value="更新">※上部の「車両基本情報」は更新されませんのでご注意ください。
<br><font color="red">${msg}</font>
</form>
<br>
<a href="/sharyoKanri_GG/AdminLoginSV?"><button type="button" >管理者トップ画面</button></a>
　　　　　　　　<a href="/sharyoKanri_GG/MainSV"><button type="button" >ログアウト</button></a>
</body>
</html>