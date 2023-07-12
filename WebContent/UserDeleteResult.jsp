<%@page import="java.util.ArrayList"%>
<%@page import="dto.UserDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>削除結果画面</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
<link rel="stylesheet" href="./css/stylesheet.css">
</head>

<body>
	<div class="bg-success padding-y-5">
		<div class="container padding-y-5 text-center">
			<h1>
				ユーザー削除結果画面&nbsp;<span class="icon-speaker"></span>
			</h1>
		</div>
	</div>

	<h4 style="color:red; text-align:center">削除が完了しました</h4>


	<form action="./uer" method="post">
		<div class="padding-y-5 text-center">
			<button class="btn" type="submit" value="戻る" >戻る</button>
		</div>
	</form>
</body>
</html>