<%@page import="controller.UserConfirmSvt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザ登録結果</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
</head>
<body>
	<div class="bg-success padding-y-5">
		<div class="container padding-y-5 text-center">
			<h1>
				ユーザ登録結果画面&nbsp;<span class="icon-speaker"></span>
				<p>以下の内容で登録が完了しました。</p>
			</h1>
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-left">
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-center">
			<%-- action 属性にサーブレットを指定 --%>
			<form action="">
				<table style="width: 600px" class="table">
					<tr>
						<%-- ログインID 入力欄の名前は loginId --%>
						<td class="color-main text-left">ログインID</td>
						<td class="text-left">
						<label><%= request.getParameter("id")%></label></td>
					</tr>
					<tr>
						<%-- ユーザ名 入力欄の名前は username --%>
						<td class="color-main text-left">ユーザ名</td>
						<td class="text-left">
						<label><%= request.getParameter("name")%></label></td>
					</tr>
					<tr>
						<%-- パスワード入力欄の名前は password --%>
						<td class="color-main text-left">パスワード</td>
						<td class="text-left">
						<label><%= request.getParameter("pass")%></label></td>
					</tr>
					<tr>
						<%--アイコン 入力欄の名前は icon --%>
						<td class="color-main text-left">アイコン</td>
						<td class="text-left">
						<span class="<%= request.getParameter("icon") %> pe-3x pe-va"></span>
						</td>
					</tr>
					<tr>
						<%-- プロフィール 入力欄の名前は profile --%>
						<td class="color-main text-left">プロフィール</td>
						<td class="text-left">
						<label><%=request.getParameter("pro")%></label></td>
					</tr>
					<tr>
						<td colspan="2" class="text-right">
						<a href="index.jsp" class="btn">戻る</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>