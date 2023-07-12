<%@page import="java.util.ArrayList"%>
<%@page import="dto.UserDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>削除確認画面</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
<link rel="stylesheet" href="./css/stylesheet.css">
</head>

<body>
<%
	//文字化け対策
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html; charset=UTF-8");

	//EL式を使用する際は子の記述は必要なし
	//ArrayList<UserDTO> result = (ArrayList<UserDTO>)request.getAttribute("deleteConList");
%>

	<div class="bg-success padding-y-5">
		<div class="container padding-y-5 text-center">
			<h1>
				ユーザー削除確認画面&nbsp;<span class="icon-speaker"></span>
			</h1>
		</div>
	</div>

	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-center">
		<form action=?  method="post">
			<table class="table table-striped table-bordered table-hover">
					<tr>
						<th nowrap><!-- ログインID -->
							<label for="loginId">&nbsp;ログインID</label>
						</th>
						<th nowrap><!-- ユーザー名 -->
							<label for="userName">&nbsp;ユーザー名</label>
						</th>
						<th nowrap><!-- アイコン -->
							<label for="icon">&nbsp;アイコン</label>
						</th>
						<th nowrap><!-- プロフィール -->
							<label for="profile">&nbsp;プロフィール</label>
						</th>
					</tr>

					<c:forEach var="user" items="${deleteConList}">
						<tr>
							<td>${user.loginId}</td>

							<td>${user.userName}</td>

							<td class="pe-2x pe-va"><!--アイコン-->
								<label for="icon"><span class= "${user.icon}" ></span></label>
							</td>
							<td>${user.profile}</td>
						</tr>
					</c:forEach>
			</table>

			<button class="btn" type="submit" value="OK"  formaction="./udr">OK</button>
			<button class="btn" type="submit" value="キャンセル" formaction="UserSearchResult.jsp">キャンセル</button>
		</form>
		</div>
	</div>
</body>
</html>