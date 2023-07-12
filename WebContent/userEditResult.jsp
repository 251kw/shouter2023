<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="src.dto.UserDTO"%>
<%@ page import="src.controller.userEditConfirmSvt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>更新完了</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
</head>
<body>
	<%

//指定した名前をいれる、いれたvalue値のこと
	request.getParameter("loginId");
	request.getParameter("password");
	request.getParameter("userName");
	request.getParameter("icon");
	request.getParameter("profile");

%>
	<div class="bg-success padding-y-5">
		<div class="container padding-y-5 text-center">
			<h1>
				ユーザー更新結果画面&nbsp;<span class="icon-speaker"></span>
			</h1>
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-left">
			<strong class="color-main">以下の内容で更新が完了しました。</strong>
		</div>
	</div>
	<%-- セッションスコープにある UserDTO 型のオブジェクトを参照 --%>
	<div class="padding-y-5">
		<div style="width: 50%" class="container padding-y-5">
			<%-- action 属性にサーブレットを指定 --%>
			<form action="./userEditResultSvt" method="post">
				<table style="width: 700px" class="table">
					<%-- サーブレットでゲットした値を表示 --%>
					<tr>
						<td class="color-main text-left"><span
								class="icon-smile pe-2x pe-va"></span>ログインID</td>
						<td><label><%=request.getParameter("loginId")%></label><input type="hidden"
							name=loginId value="<%=request.getParameter("loginId")%>"></td>
					</tr>
					<tr>
						<td class="color-main text-left"><span
								class="icon-note pe-2x pe-va"></span>パスワード</td>
						<td><label><%=request.getParameter("password")%></label><input type="hidden"
							name=password value="<%=request.getParameter("password")%>"></td>
					</tr>
					<tr>
						<td class="color-main text-left"><span
								class="icon-users pe-2x pe-va"></span>ユーザー名</td>
						<td><label><%=request.getParameter("userName")%></label><input type="hidden"
							name=userName value="<%=request.getParameter("userName")%>"></td>
					</tr>
					<tr>
						<td class="color-main text-left"><span
								class="icon-joy pe-2x pe-va"></span>アイコン</td>
						<td><span
							class="<%= request.getParameter("icon") %> pe-2x pe-va"></span><input type="hidden"
							name=icon value="<%= request.getParameter("icon") %> pe-2x pe-va"></td>
					</tr>
					<tr>
						<td class="color-main text-left"><span
								class="icon-note2 pe-2x pe-va"></span>プロフィール</td>
						<td><label><%=request.getParameter("profile")%></label><input type="hidden"
							name=profile value="<%=request.getParameter("profile")%>"></td>
					</tr>
					<tr>
						<td colspan="2" class="text-right"><input class="btn"
							type="submit" value="戻る" /></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>