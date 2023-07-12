<%@page import="dto.UserDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>Shouter - ユーザー削除結果 -</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
</head>
<body>
	<div class="bg-main padding-y-5">
		<div class="padding-y-5 text-center">
			<strong>ユーザ削除結果画面</strong><br>
			<p style="display: inline">選択されたユーザを削除しました</p>
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-center">
			<%--再検索のサーブレットに処理を転送 --%>
			<form action="./uer" method="post">
				<button class="btn" type="submit">戻る</button>
			</form>
		</div>
	</div>
</body>
</html>