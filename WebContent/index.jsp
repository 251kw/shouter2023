<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Shouter-ログイン-</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
</head>
<style>
input:hover {
	background: rgba(80, 200, 200, 0.2);
}
</style>

<body>
	<div class="bg-success padding-y-5">
		<div class="container padding-y-5 text-center">
			<h1>
				Shouter&nbsp;<span class="icon-speaker"></span>
			</h1>
		</div>
	</div>

	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-left">
			<strong class="color-main">ログインIDとパスワードを入力してください</strong>
		</div>
	</div>
	<div class="padding-y-5">
		<div style="width: 40%" class="container padding-y-5">
			<%-- action 属性にサーブレットを指定 --%>
			<form action="./login" method="post" name="form1">
				<table style="width: 400px" class="table">
					<tr>
						<%-- ログインID入力欄の名前はloginId --%>
						<td class="color-man text-left">ログインID</td>
						<td class="text-left"><input class="form-control" type="text"
							name="loginId" value="" size="20" autofocus="autofocus" /></td>
					</tr>
					<tr>
						<%-- パスワード入力欄の名前はpassword --%>
						<td class="color_main text_left">パスワード</td>
						<td class="text-left"><input class="form-control"
							type="password" name="password" value="" size="20" /></td>
					</tr>
					<tr>
						<%-- 登録ボタン --%>
						<td colspan="1" class="text-left">
						<input class="btn" type = "button" value="会員登録" onClick="window.location.href='./signupid.jsp'"/>
						</td>

						<%-- ログインボタン --%>
						<td colspan="1" class="text-right"><input class="btn"
							type="submit" value="ログイン" /></td>
					</tr>
					<tr>
					<%-- 検索ボタン --%>
						<td>
						</td>
						<td colspan="2" class="text-right">
						<input class="btn" type = "button" value="検索" onClick="window.location.href='./search.jsp'"/>
						</td>
						</tr>
					<%-- リクエストスコープにalertがあれば --%>
					<c:if
						test="${requestScope.alert !=null && requestScope.alert != '' }">
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

