<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
</head>
<body>

	<div class="bg-success padding-y-5">
		<div class="container padding-y-5 text-center">
			<h1>
				ユーザー登録結果画面&nbsp;<span class="icon-speaker"></span>
			</h1>
			以下の内容で登録が完了しました。
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-center">
			<form action="index.jsp" method="post">
				<table style="width: 400px" class="table">
					<%
						String loginId1 = request.getParameter("loginId");
						String userName1 = request.getParameter("userName");
						String password1 = request.getParameter("password");
						String icon1 = request.getParameter("icon");
						String profile1 = request.getParameter("profile");
					%>
					<tr>
						<td class="color-main text-left"><span
							class="icon-id pe-2xpe-va">&nbsp;</span>ログインID</td>
						<td class="text-left">
						<td>${User.loginId}<input type="hidden" name="loginId"
							id="loginId" class="form-control" value=<%=loginId1%>></td>
					</tr>
					<tr>
						<td class="color-main text-left"><span
							class="icon-user pe-2xpe-va">&nbsp;</span>ユーザー名</td>
						<td class="text-left">
						<td>${User.userName}<input type="hidden" name="userName"
							id="userName" class="form-control" value=<%=userName1%>></td>
					</tr>
					<tr>
						<td class="color-main text-left"><span
							class="icon-door-lock pe-2xpe-va">&nbsp;</span>パスワード</td>
						<td class="text-left">
						<td>${User.password}<input type="hidden" name="password"
							id="pasword" class="form-control" value=<%=password1%>></td>
					</tr>
					<tr>
						<td class="color-main text-left"><span
							class="icon-smile pe-2xpe-va">&nbsp;</span>アイコン</td>
						<td class="text-left">
						<td><input type="hidden" name="icon" id="icon"
							class="form-control" value=<%=icon1%>><span
							class="<%=icon1%> pe-2x pe-va"></span></td>
					</tr>
					<tr>
						<td class="color-main text-left"><span
							class="icon-note pe-2xpe-va">&nbsp;</span>プロフィール</td>
						<td class="text-left">
						<td>${User.profile}<input type="hidden" name="profile"
							id="profile" class="form-control" value=<%=profile1%>></td>
					</tr>
				</table>
				<div class="center">
					<input class="btn" type="submit" value="戻る">
				</div>
			</form>
		</div>
	</div>
</body>
</html>