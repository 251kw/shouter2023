<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>Shouter - ログイン -</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
<link rel="stylesheet" href="./css/stylesheet.css">
</head>
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
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-center">
			<%-- LoginServlet.javaに移動 --%>
			<form action=?  method="post">
				<table class="table">
					<tr>
						<%-- ログインID 入力欄の名前は loginId --%>
						<th nowrap class="color-main text-left" >&nbsp;ログインID </th>
						<td class="text-left"><input class="form-control" type="text" name="loginId" value="" size="20" autofocus /></td>
					</tr>

					<tr>
						<%-- パスワード入力欄の名前は password --%>
						<th nowrap class="color-main text-left" >&nbsp;パスワード </th>
						<td class="text-left"><input class="form-control" type="password" name="password" value="" size="20" /></td>
					</tr>

					<tr>
						<td  colspan="2" class="text-center">
							<button class="btn" type="submit" value="ログイン" formaction="./login">ログイン</button>
						 	<a href="UserInfoInput.jsp" class ="btn">新規登録</a>
						 </td>
					</tr>
				</table>

				<div class="color-error text-center">
					<c:if test="${requestScope.alert != null && requestScope.alert != ''}">
						<%-- リクエストスコープの alert の値を出力 --%>
						<c:out value="${requestScope.alert}" />
					</c:if>
				</div>
			</form>
		</div>
	</div>
</body>
</html>
