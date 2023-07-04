<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Shouter - ユーザー情報登録 -</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
<link rel="stylesheet" href="./css/style.css">
</head>
<body>
	<div class="bg-success padding-y-5">
		<div class="container padding-y-5 text-center">
			<h1>ユーザー検索入力画面</h1>
		</div>
	</div>
	<%-- action 属性にサーブレットを指定 --%>
	<form action="./usi" method="get">
		<table style="width: 70%" class="table">
			<tr>
				<%-- ログインID 入力欄の名前は loginId --%>
				<td class="color-main text-left"><span
					class="icon-smile pe-2x pe-va"></span>ログインID</td>
				<td class="text-left"><input class="form-control" type="text"
					name="loginId" value="" size="20" autofocus /></td>
			</tr>
			<tr>
				<%-- ユーザー名 入力欄の名前は userName --%>
				<td class="color-main text-left"><span
					class="icon-smile pe-2x pe-va"></span>ユーザー名</td>
				<td class="text-left"><input class="form-control" type="text"
					name="userName" value="" size="20" /></td>
			</tr>
			<tr>
				<%-- ログインID 入力欄の名前は loginId --%>
				<td class="color-main text-left"><span
					class="icon-smile pe-2x pe-va"></span>アイコン</td>
				<td class="text-left">
					<div style="display: flex;">
						<span class="icon-user-female pe-2x pe-va"></span>            <label class="fancy-checkbox">
						<input type="checkbox" name="icon-user-female" value="icon-user_female">
						<span></span></label>
						<span class="icon-bell pe-2x pe-va"></span>           <label class="fancy-checkbox">
						<input type="checkbox" name="icon-bell" value="icon-bell">
						<span></span></label>
						<span class="icon-user pe-2x pe-va"></span>           <label class="fancy-checkbox">
						<input type="checkbox" name="icon-user" value="icon-user">
						<span></span></label>
						<span class="icon-smile pe-2x pe-va"></span>            <label class="fancy-checkbox">
						<input type="checkbox" name="icon-smile" value="icon-smile">
						<span></span></label>
					</div>
				</td>
			</tr>
			<tr>
				<td class="color-main text-left"><span
					class="icon-smile pe-2x pe-va"></span>プロフィール</td>
				<td class="text-left"><input class="form-control" type="text"
					name="profile" value="" size="20" />
			</tr>
			<tr>
				<c:if
					test="${requestScope.alert != null && requestScope.alert != ''}">
					<tr>
						<%-- リクエストスコープの alert の値を出力 --%>
						<td colspan="2" class="color-error text-left"><c:out
								value="${requestScope.alert}" /></td>
					</tr>
				</c:if>
			</tr>
			<tr>
				<td colspan="2" class="text-right"><input class="btn"
					type="submit" name="search" value="検索" /></td>
				<td colspan="2" class="text-right"><input class="btn"
					type="submit" name="return" value="戻る" /></td>
			</tr>
		</table>
	</form>
</body>
</html>