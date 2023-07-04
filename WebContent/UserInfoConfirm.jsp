<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dto.UserDTO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザ登録確認</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
</head>
<%
	UserDTO user  = (UserDTO)request.getAttribute("userA");
	String id = user.getLoginId();
	String name = user.getUserName();
	String pass = user.getPassword();
	String pro = user.getProfile();
	String icon = user.getIcon();
%>
<body>
	<div class="bg-success padding-y-5">
		<div class="container padding-y-5 text-center">
			<h1>
				ユーザ登録確認画面&nbsp;<span class="icon-speaker"></span>
				<p>登録します。よろしいでしょうか？</p>
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
			<form action="./uic" method="post">
				<table style="width: 400px" class="table">
					<tr>
						<%-- ログインID 入力欄の名前は loginId --%>
						<td class="color-main text-left">ログインID</td>
						<td class="text-left"><input class="form-control" type="text"
							name="id" value="<%= id %>" size="20" disabled="disabled"/>
							<input type="hidden" name="id" value="<%=id%>">
						</td>
					</tr>
					<tr>
						<%-- ユーザ名 入力欄の名前は username --%>
						<td class="color-main text-left">ユーザ名</td>
						<td class="text-left"><input class="form-control" type="text"
							name="name" value="<%= name %>" size="20" disabled="disabled"/>
							<input type="hidden" name="name" value="<%=name%>">
						</td>
					</tr>
					<tr>
						<%-- パスワード入力欄の名前は password --%>
						<td class="color-main text-left">パスワード</td>
						<td class="text-left"><input class="form-control"
							type="password" name="pass" value="<%= pass %>" size="20" disabled="disabled"/>
							<input type="hidden" name="pass"  value="<%=pass%>">
						</td>
					</tr>
					<tr>
						<%--アイコン 入力欄の名前は icon --%>
						<td class="color-main text-left">アイコン</td>
						<td class="text-left">
						<span class="<%= icon %> pe-3x pe-va"></span>
							<input type="hidden" name="icon"  value="<%=icon%>">
						</td>
					</tr>
					<tr>
						<%-- プロフィール 入力欄の名前は profile --%>
						<td class="color-main text-left">プロフィール</td>
						<td class="text-left"><input class="form-control" type="text"
							name="pro" value="<%= pro %>" size="20" disabled="disabled"/>
							<input type="hidden" name="pro"  value="<%=pro%>">
						</td>
					</tr>
					<tr>
						<td colspan="2" class="text-right">
						<input class="btn" type="submit" value="OK" />
						<input class="btn" type="submit" formaction="./UserInfoInput.jsp" value="キャンセル" />
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>