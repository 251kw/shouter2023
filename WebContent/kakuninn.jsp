<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="src.dto.UserDTO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>確認</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
</head>
<body>
		<%--UserDTOの値userをゲット --%>
	<%
		UserDTO us = (UserDTO) request.getAttribute("user");
	%>
	<div class="bg-success padding-y-5">
		<div class="container padding-y-5 text-center">
			<h1>
				ユーザー登録確認画面&nbsp;<span class="icon-speaker"></span>
			</h1>
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 50%" class="container padding-y-5 text-left">
			<strong class="color-main">登録します。よろしいでしょうか？</strong>
		</div>
	</div>
	<%-- セッションスコープにある UserDTO 型のオブジェクトを参照 --%>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-center">
			<%-- action 属性にサーブレットを指定 --%>
			<form action="./kannryou" method="post">
				<table style="width: 700px" class="table">
					<tr>
					<%--hiddenでvalueの値を保持--%>
						<td class="color-main text-left"><span
								class="icon-smile pe-2x pe-va"></span>ログインID</td>
						<td><label><%=us.getLoginId()%></label><input type="hidden"
							name=login value="<%=us.getLoginId()%>"></td>
					</tr>
					<tr>
						<td class="color-main text-left"><span
								class="icon-note pe-2x pe-va"></span>パスワード</td>
						<td><label><%=us.getPassword()%></label><input
							type="hidden" name=pass value="<%=us.getPassword()%>">
						</td>
					</tr>
					<tr>
						<td class="color-main text-left"><span
								class="icon-users pe-2x pe-va"></span>ユーザー名</td>
						<td><label><%=us.getUserName()%></label><input
							type="hidden" name=name value="<%=us.getUserName()%>">
						</td>
					</tr>
					<tr>
						<td class="color-main text-left"><span
								class="icon-joy pe-2x pe-va"></span>アイコン</td>
						<td><span class="<%=us.getIcon()%> pe-2x pe-va"></span><input
							type="hidden" name=icon value="<%=us.getIcon()%>"></td>
					</tr>
					<tr>
						<td class="color-main text-left"><span
								class="icon-note2 pe-2x pe-va"></span>プロフィール</td>
						<td><label><%=us.getProfile()%></label><input type="hidden"
							name=prof value="<%=us.getProfile()%>"></td>
					</tr>
					<tr>
						<td colspan="2" class="text-right"><input class="btn"
							type="submit" value="OK" /></td>
							<%--hiddenを使いたいからsubmitのformactionを使う--%>
						<td colspan="2"><input class="btn" type="submit" value="戻る"
							formaction="touroku.jsp" /></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>