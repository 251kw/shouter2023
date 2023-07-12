<%@page import="dto.UserDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザー情報更新確認画面</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
<link rel="stylesheet" href="./css/stylesheet.css">
</head>

<%
	//文字化け対策
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html; charset=UTF-8");

	//変数の初期化
	String loginId = null;
	String userName = null;
	String password = null;
	String icon = null;
	String profile = null;

	//リクエストオブジェクトから値を取得
	UserDTO user = (UserDTO)request.getAttribute("user");

	loginId = user.getLoginId();
	userName =user.getUserName();
	password = user.getPassword();
	icon = user.getIcon();
	profile =user.getProfile();
%>

<body>
	<div class="bg-success padding-y-5">
		<div class="container padding-y-5 text-center">
			<h1>
				ユーザー情報更新確認画面&nbsp;<span class="icon-speaker"></span>
			</h1>
		</div>
	</div>

	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-left">
			<strong class="color-main">以下の情報で更新します。よろしいですか。</strong>
		</div>
	</div>


	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-center">
			<form action=? method="post">
				<table style="width: 400px" class="table">
					<tr><!-- ログインID  -->
						<th nowrap class="color-main text-left" >
							<label for="loginId"> <span class="icon-smile pe-2x pe-va"> </span>&nbsp;ログインID</label>
						</th>
						<td class="text-left">
							<input type="hidden" name="loginId"  size="20"  value=<%=loginId %> maxlength="10"  />
							<%=loginId %>
						</td>
					</tr>

					<tr><!-- ユーザー名-->
						<th nowrap class="color-main text-left" >
							<label for="userName"> <span class="icon-note pe-2x pe-va"></span>&nbsp;ユーザー名</label>
						</th>
						<td class="text-left">
							<input type="hidden" name="userName"  size="20"  value=<%=userName %> />
							<%=userName %>
						</td>
					</tr>

					<tr><!-- パスワード -->
						<th nowrap class="color-main text-left" >
							<label for="pass"> <span class="icon-note pe-2x pe-va"></span>&nbsp;パスワード</label>
						</th>
						<td class="text-left">
							<input type="hidden" name="password" size="20" value=<%=password %>  />
							<%=password %>
						</td>
					</tr>

					<tr><!-- アイコン -->
						<th nowrap><span class="icon-joy pe-2x pe-va"></span>&nbsp;アイコン</th>
						<td class="pe-2x pe-va">
							<label for="icon"><span class=<%=icon %> > </span></label>
							<input type="hidden" name="icon" id="icon" value=<%= icon %> >
						</td>
					</tr>

					<tr><!-- プロフィール -->
						<th nowrap class="color-main text-left" >
							<label for="profile"> <span class="icon-note pe-2x pe-va"></span>&nbsp;プロフィール</label>
						</th>
						<td class="text-left">
							<input type="hidden" name="profile"  size="20"  value=<%=profile %> readonly/>
							<%=profile %>
						</td>
					</tr>

					<tr><!-- 登録＆戻るボタン -->
						<td colspan="2" class="text-center">
							<button class="btn" type="submit" value="update" formaction="./uec" name="update">OK</button>
							<button class="btn" type="submit" value="cancel" formaction="UserEditInput.jsp">キャンセル</button>
					 	</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>