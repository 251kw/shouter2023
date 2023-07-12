<%@page import="dto.UserDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>Shouter - ユーザー削除確認 -</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
</head>
<body>
	<div class="bg-main padding-y-5">
		<div class="padding-y-5 text-center">
			<strong>ユーザ削除確認画面</strong><br>
			<p style="display: inline">これらのユーザを削除します。よろしいでしょうか？</p>
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-center">
			<%-- action 属性にサーブレットを指定。押すボタンによって推移先が違うのでaction属性は「?」 --%>
			<form action="?" method="post">
				<table border="1" style="width: 400px"
					class="table table-striped table-bordered table-hover">
					<tr bgcolor="#1BBC9B">
						<th style="text-align: center" nowrap>No.</th>
						<th style="text-align: center" nowrap>ログインID</th>
						<th style="text-align: center" nowrap>ユーザー名</th>
						<th style="text-align: center" nowrap>アイコン</th>
						<th style="text-align: center" nowrap>プロフィール</th>
					</tr>
						<%
							int i = 1;
						%>
					<%--udcサーブレットでリクエストスコープにセットしたnameで検索されたユーザのリストを取得 --%>
					<c:forEach var="list" items="${deletelist}">
						<tr>
							<td nowrap><%=i%></td>
							<td nowrap>${list.loginId}</td>
							<td nowrap>${list.userName}</td>
							<td nowrap><span class="${list.icon}  pe-2x pe-va"></span></td>
							<td nowrap>${list.profile}</td>
						</tr>
						<%i++;%>
					</c:forEach>
				</table>
				<div class="text-center">
					<%--削除のサーブレットへ--%>
					<button class="btn" type="submit" name="delete" value="delete"
						formaction="./udr">削除</button>
					<%--再検索する--%>
					<button class="btn" type="submit" formaction="./uer">キャンセル</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>