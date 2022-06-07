<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>車両情報更新</title>
	<style>
		body{
		   background-color: #ebf6f7;
		}
		input,select {
		font-size: 100%;
		}
		.button{
		width: 130px;
		padding: 10px;
		border-radius: 100vh;
		white-space: normal;
		}
		.button1{
		font-size: 150%;
		width: 200px;
		height: 60px;
		padding: 10px;
		border-radius: 100vh;
		}
	</style>
</head>

<body>
	<h1>車両情報更新</h1>
	<c:if test="${ not empty msg }">
		<p><b>${ msg }</b></p>
	</c:if>
	<form action="/sharyoKanri_GG/UserCarUpdateSV?action=basicInfo" method="post">
		<fieldset>
			<legend><b>車両基本情報</b></legend>
			<table cellpadding="5">
				<tr>
					<td align="right">車両番号：</td>
					<td><input type="text" name="shaban" value="${ sharyo.shabanInfo } ${sharyo.shaban}" disabled="disabled"></td>
					<td align="right">メーター距離：</td>
					<td><input type="text" name="meter" value="${ sharyo.mileage }">km</td>
				</tr>
				<tr>
					<td align="right">使用者の社員番号：</td>
					<td><input type="text" name="user_number" value="${ user.userNumber }" disabled="disabled"></td>
					<td align="right">使用者：</td>
					<td><input type="text" name="user_name" value="${sharyo.carUser}" disabled="disabled"></td>
				</tr>
				<tr>
					<td align="right">使用中のタイヤ種：</td>
					<td><input type="text" name="tire_type" value="${ sharyo.tireType }" disabled="disabled"></td>
					<td align="right">　　　　冬用タイヤ必要性：</td>
					<td>
						<select name="snow_tire" disabled="disabled">
							<c:if test="${ sharyo.snowTire == '必要'}">
					   			<option value="need" selected>必要</option>
								<option value="noNeed">不要</option>
							</c:if>
							<c:if test="${ sharyo.snowTire == '不要'}">
					   			<option value="need" >必要</option>
								<option value="noNeed" selected>不要</option>
							</c:if>
						</select>
					</td>
				</tr>
			</table><br>
			<input type="submit" value="メーター距離のみ更新" class="button">　※下記交換のデータは更新されませんのでご注意ください
		</fieldset>
	</form><br>

	<form method="post">
	<h2>部品情報・点検結果の更新</h2>
	　更新したい情報をチェックボックスで選んで更新してください。（複数選択可）<br><br>
	<td>　現在のメーター距離（入力必須）：<input type="text" name="mileage">km</td><br>
	<hr>
		<table cellpadding="10">
			<tr>
				<th rowspan="2"><input type="checkbox" name="sharyoCheck" value="On">車両点検</th>
				<td>
					実施日：
					<select name="checkYear">
						<option value="">-</option>
						<option value="2022">2022</option>
						<option value="2023">2023</option>
						<option value="2024">2024</option>
						<option value="2025">2025</option>
						<option value="2026">2026</option>
						<option value="2027">2027</option>
						<option value="2028">2028</option>
						<option value="2029">2029</option>
						<option value="2030">2030</option>
					</select>年
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
						<option value="12">12</option>
					</select>月
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
						<option value="31">31</option>
					</select>日
				</td>
				<td>結果：<select name="check_rslt"><option value="OK">OK</option><option value="NG">NG</option></select></td>
			</tr>
			<tr>
				<td>備考：<input type="text" name="plus_text" size="30"></td>
			</tr>
		</table>
		<hr>
		<table cellpadding="10">
			<tr>
				<th rowspan="3"><input type="checkbox" name="tireChange" value="On">タイヤ交換</th>
				<td>
					<input type="radio" name="tireAction" value="newTireNormal">新タイヤ（ノーマルタイヤ）装着<br>
					<input type="radio" name="tireAction" value="newTireSnow">新タイヤ（スタッドレスタイヤ）装着<br>
					<c:if test="${ sharyo.tireType.trim() == 'ノーマル'}">
						<input type="radio" name="tireAction" value="normalToSnow">ノーマル（夏）→スタッドレス（冬）タイヤ交換
					</c:if>
					<c:if test="${ sharyo.tireType.trim() == 'スタッドレス'}">
						<input type="radio" name="tireAction" value="snowToNormal">スタッドレス（冬）→ノーマル（夏）タイヤ交換
					</c:if>
				</td>
			</tr>
			<tr>
				<td>交換時の距離：<input type="text" name="change_km">km</td>
			</tr>
			<tr>
				<td>
					装着日：
					<select name="tireYear">
						<option value="">-</option>
						<option value="2022">2022</option>
						<option value="2023">2023</option>
						<option value="2024">2024</option>
						<option value="2025">2025</option>
						<option value="2026">2026</option>
						<option value="2027">2027</option>
						<option value="2028">2028</option>
						<option value="2029">2029</option>
						<option value="2030">2030</option>
					</select>年
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
						<option value="12">12</option>
					</select>月
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
						<option value="31">31</option>
					</select>日</td>
				<td>製造年週：<input type="text" name="make_year"></td><br>
		</table>
		<hr>
		<table cellpadding="10">
			<tr>
				<th align="left">
					<input type="checkbox" name="oilChange" value="On">オイル交換<br>
					<input type="checkbox" name="elementChange" value="On">エレメント交換
				</th>
				<td>交換時の距離：<input type="text" name="partChange_km">km</td>
				<td>最新交換日：
					<select name="partYear">
						<option value="">-</option>
						<option value="2022">2022</option>
						<option value="2023">2023</option>
						<option value="2024">2024</option>
						<option value="2025">2025</option>
						<option value="2026">2026</option>
						<option value="2027">2027</option>
						<option value="2028">2028</option>
						<option value="2029">2029</option>
						<option value="2030">2030</option>
					</select>年
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
						<option value="12">12</option>
					</select>月
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
						<option value="31">31</option>
					</select>日<br>
				</td>
			</tr>
		</table>
		<hr>
		<table cellpadding="10">
			<tr>
				<th><input type="checkbox" name="batteryChange" value="On">バッテリー交換</th>
				<td>交換時の距離：<input type="text" name="batteryChange_km">km</td>
				<td>
					最新交換日：
					<select name="batteryYear">
						<option value="">-</option>
						<option value="2022">2022</option>
						<option value="2023">2023</option>
						<option value="2024">2024</option>
						<option value="2025">2025</option>
						<option value="2026">2026</option>
						<option value="2027">2027</option>
						<option value="2028">2028</option>
						<option value="2029">2029</option>
						<option value="2030">2030</option>
					</select>年
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
						<option value="12">12</option>
					</select>月
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
						<option value="31">31</option>
					</select>日<br>
				</td>
			</tr>
		</table>
		<br>
		<input type="submit" formaction="/sharyoKanri_GG/UserCarUpdateSV?action=buhinInfo" value="更新" class="button1">
		<input type="submit" formmethod="post" formaction="/sharyoKanri_GG/UserDispSV" value="戻る" class="button">
		<input type="submit" formmethod="get" formaction="/sharyoKanri_GG/UserDispSV" value="ログアウト" class="button">
	</form>
</body>
</html>