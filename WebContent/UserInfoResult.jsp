<%@page import="dto.UserDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>Shouter - 登録完了-</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
</head>
<body>
	<%--<div class="bg-success padding-y-5">
		<div class="container padding-y-5 text-center">
			<h1>
				Shouter&nbsp;<span class="icon-speaker"></span>
			</h1>
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-left">
			<strong class="color-main">ユーザ登録結果画面</strong>
		</div>
	</div>--%>

		<div class="bg-main padding-y-5">
	<div class="padding-y-5 text-center">
		<strong>ユーザ登録結果画面</strong><br>
		<p style=display:inline>以下の内容で登録が完了しました。</p>
	</div>
	</div>

	<%-- セッションスコープにある UserDTO型のオブジェクトを参照 --%>
	<jsp:useBean id="user" scope="session" type="dto.UserDTO" />
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-center">
			<%-- action 属性にindex.jspを指定。推移先はログイン画面。--%>
			<form action="index.jsp" method="post">
				<table style="width: 400px" class="table">
					<tr>
						<%-- ログインID 入力欄の名前は loginId --%>
						<td class="color-main text-left"><span class="icon-users pe-2x pe-va"></span>&nbsp;ログインID</td>
						<td>${user.loginId}</td>
					</tr>
					<tr>
						<%-- ユーザー名 入力欄の名前は userName --%>
						<td class="color-main text-left"><span class="icon-users pe-2x pe-va"></span>&nbsp;ユーザー名</td>
						<td>${user.userName}</td>
					</tr>
					<tr>
						<%-- パスワード入力欄の名前は password --%>
						<td class="color-main text-left"><span class="icon-users pe-2x pe-va"></span>&nbsp;パスワード</td>
						<td>${user.password}</td>
					</tr>
					<tr>
						<%-- アイコン 入力欄の名前は icon --%>
						<td class="color-main text-left"><span class="icon-users pe-2x pe-va"></span>&nbsp;アイコン</td>
						<td>
						<%-- hiddenで送られた値を取得 --%>
						<% String icon = request.getParameter("icon"); %>
						<label for="icon"><span class = <%=icon%>></span></label>
						</td>
					</tr>
					<tr>
						<%-- プロフィール 入力欄の名前は profile --%>
						<td class="color-main text-left"><span class="icon-users pe-2x pe-va"></span>&nbsp;プロフィール</td>
						<td>${user.profile}</td>
					</tr>
					<tr>
					<tr>
						<td colspan="2" class="text-right">
						<input class="btn" type="submit" value="戻る"/>
						</td>
					</tr>
					<%-- リクエストスコープに alert があれば --%>
					<c:if
						test="${requestScope.alert != null && requestScope.alert != ''}">
						<tr>
							<%-- リクエストスコープの alert の値を出力 --%>
							<td colspan="2" class="color-error text-left"><c:out
									value="${requestScope.alert}" /></td>
						</tr>
					</c:if>
				</table>
			</form>
		</div>
	</div>
</body>
</html>