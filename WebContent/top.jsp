<%--ログインユーザー情報の部分のみ表示 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>Shouter - みんなの心の叫び -</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
<link rel="stylesheet" href="./css/stylesheet.css">
</head>

<body>
	<div class="bg-success padding-y-5">
		<div class="container padding-y-5 text-center">
			<h1> <!-- ヘッダー -->
				Shouter&nbsp;<span class="icon-speaker"></span>
			</h1>
		</div>
	</div>
	<%--タイトル --%>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-left">
			<strong class="color-main">ログインユーザー情報</strong>
		</div>
	</div>

	<%-- スコープからオブジェクトの参照を取り出す --%>
	<jsp:useBean id="user" scope="session" type="dto.UserDTO" />
	<div class="padding-y-5">
		<div style="width: 40%" class="container padding-y-5">

			<%--ログインユーザー情報のテーブル--%>
			<form action="./logout" method="post">  <%--LogoutServlet.javaに転送 --%>
				<table class="table table-bordered">
					<tr>
						<td rowspan="2" class="text-center"><span class="${user.icon} pe-3x pe-va"></span></td><%--アイコン --%>
						<td width="256">${user.userName}</td>
						<td><input class="btn btn-light" type="submit" value="ログアウト" /></td>
					</tr>
					<tr>
						<td colspan="2">${user.profile}</td>
					</tr>
				</table>
			</form>
		</div>
	</div>

	<%--検索ボタン--%>
	<form action="UserSearchInput.jsp" method="post">
		<div style="width: 40%" class="container padding-y-5 text-center">
			<p>ユーザー検索</p>
			<input class="btn" type="submit" value="検索" id ="search" >
		</div>
	</form>




	<%--タイトル--%>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-left">
			<strong class="color-main">今の気持ちを叫ぼう</strong>
		</div>
	</div>


	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-center">

			<%--今の気持ちを叫ぼうの欄 --%>
			<form action="./bbs" method="post"> <%--BbsServlet.javaに転送--%>
				<table class="table">
					<tr>
						<%-- 今の気持ち入力欄 shout --%>
						<td><input class="form-control" type="text" name="shout" value="" size="60" required  /></td>
						<td><input class="btn" type="submit" value="叫ぶ" /></td>
					</tr>

					<c:if
						test="${requestScope.alertshout != null && requestScope.alertshout != ''}">
						<tr>
							<%-- リクエストスコープの alert の値を出力 --%>
							<td colspan="2" class="color-error text-left">
							<c:out value="${requestScope.alertShout}" /></td>
						</tr>
					</c:if>

				</table>

			</form>
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-left">
			<strong class="color-main">みんなの叫び</strong>
		</div>
	</div>
	<%-- セッションスコープにある ArrayList 型のオブジェクトを参照--%>
	<%--LoginServlet.javaでセットした書き込み内容が出力される --%>
	<%--JaveBeansをインスタンス化するJSPアクションタグ --%>
	<jsp:useBean id="shouts" scope="session" type="java.util.ArrayList<dto.ShoutDTO>" /> /<%--型指定にはパッケージ名も記述する--%>
	<div class="padding-y-5">
		<div style="width: 40%" class="container padding-y-5">

			<%-- リストにある要素の数だけ繰り返し表示するためにforEachタグを使う --%>
			<c:forEach var="shout" items="${shouts}">  <%--EL式の記述で値を表示することができる --%>
				<table class="table table-striped table-bordered">
					<tr>
						<td rowspan="2" class="text-center"><span class="${shout.icon} pe-3x pe-va"></span></td>
						<td>${shout.userName}</td>
					</tr>

					<tr>
						<td>${shout.date}</td>
					</tr>
					<tr>
						<td colspan="2">
							<textarea rows="5" class="form-control" readonly >${shout.writing}</textarea>
						</td>
					</tr>
				</table>
				<%--cssで投稿欄の大きさを変更できないようする --%>
				<style>
					.form-control{
						resize: none;;
					}
				</style>

			</c:forEach>
		</div>
	</div>
</body>
</html>