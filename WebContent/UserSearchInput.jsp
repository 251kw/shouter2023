<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>検索</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
</head>
<body>
	<div class="bg-success padding-y-5">
		<div class="container padding-y-5 text-center">
			<h1>
				ユーザー検索入力画面&nbsp;<span class="icon-speaker"></span>
			</h1>
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-left">
			<strong class="color-main"></strong>
			<p>
				<%-- エラー文の挿入 --%>
				<%-- リクエストスコープに alert があれば --%>
				<c:if
					test="${requestScope.alert != null && requestScope.alert != ''}">
					<%-- リクエストスコープの alert の値を出力 --%>
					<p class="color-error text-left">
						<c:out value="${requestScope.alert}" />
					</p>
				</c:if>
			</p>
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 50%" class="container padding-y-5 text-center">
			<%-- action 属性にサーブレットを指定./UserSearchInputSvt --%>
			<form action="./UserSearchInputSvt" method="post">
				<table style="width: 700px" class="table">
					<tr>
						<%-- ログインID 入力欄の名前は loginId --%>
						<%-- 半角空白禁止 --%>
						<td class="color-main text-left"><span
							class="icon-smile pe-2x pe-va"></span> ログインID</td>
						<td class="text-left"><input class="form-control" type="text"
							name="loginId" size="20" id="login" pattern="[0-9a-zA-Z]+$"
							placeholder="10文字以下半角英数字空白禁止" autofocus maxlength="10"
							value=""></td>
					</tr>
					<tr>
						<td class="color-main text-left"><span
							class="icon-users pe-2x pe-va"></span> ユーザー名</td>
						<td class="text-left"><input class="form-control" type="text"
							name="userName" size="20" id="username" maxlength="10"
							placeholder="10文字以下" value=""></td>
					</tr>
					<tr>
						<%--checkedで必ずチェックいれる、選択したiconがicon-smileならそっちにチェック--%>
						<td class="color-main text-left"><span
							class="icon-joy pe-2x pe-va"></span> アイコン</td>
						<td><label><input type="checkbox" name="icon"
								value="icon-users"><span class="icon-users pe-2x pe-va"></span>
						</label> <label><input type="checkbox" name="icon2"
								value="icon-smile"><span
								class="icon-smile pe-2x pe-va"></span></label></td>
					</tr>
					<tr>
						<td class="color-main text-left"><span
							class="icon-note2 pe-2x pe-va"></span> プロフィール</td>
						<td class="text-left"><input class="form-control" type="text"
							name="profile" size="20" id="profile" maxlength="20"
							placeholder="20文字以下" value=""></td>
					</tr>
					<tr>
						<td colspan="2" class="text-right"><input class="btn"
							type="submit" value="検索" /></td>
						<td colspan="2"><a href="top.jsp" class="btn">戻る</a></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>