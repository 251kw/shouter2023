<%@page import="dto.UserDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>ユーザー登録確認画面</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
</head>
<body>
	<%  //getParameterで値を取得
		String loginId1 = request.getParameter("loginId");
		String userName1 = request.getParameter("userName");
		String password1 = request.getParameter("password");
		String icon1 = request.getParameter("icon");
		String profile1 = request.getParameter("profile");

		//リクエストオブジェクトから値を取得
		UserDTO u = (UserDTO)request.getAttribute("user");
	%>


	<div class="bg-success padding-y-5">
		<div class="container padding-y-5 text-center">
			<h1>
				ユーザー登録確認画面&nbsp;<span class="icon-speaker"></span>
			</h1>
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-left">
			<strong class="color-main">登録します。よろしいでしょうか？</strong>
		</div>

	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-center">
			<form action=? method="post">
				<table style="width: 400px" class="table">
					<tr><!-- ログインID  -->
						<th nowrap>
							<label for="loginId"> <span class="icon-smile pe-2x pe-va"> </span>&nbsp;ログインID</label>
						</th>
						<td class="text-left"><input class="form-control" type="hidden" name="loginId" value=<%= u.getLoginId() %>  size="20" autofocus />
						${user.loginId}
						</td>
					</tr>

					<tr><!-- ユーザー名-->
						<th nowrap>
							<label for="userName"> <span class="icon-note pe-2x pe-va"></span>&nbsp;ユーザー名</label>
						</th>
						<td class="text-left"><input class="form-control" type="hidden" name="userName" value=<%= u.getUserName() %> size="20" />
						${user.userName}
						</td>
					</tr>

					<tr><!-- パスワード -->
						<th nowrap>
							<label for="pass"> <span class="icon-note pe-2x pe-va"></span>&nbsp;パスワード</label>
						</th>
						<td class="text-left"><input class="form-control" type="hidden" name="password" value=<%= u.getPassword() %>  size="20" />
						${user.password}
						</td>
					</tr>

					<tr><!-- アイコン -->
						<th nowrap><span class="icon-joy pe-2x pe-va"></span>&nbsp;アイコン</th>
						<td class="text-left">
							<label for="icon"><span class=<%= u.getIcon() %> > </span></label>
							<input type="hidden" name="icon" id="icon" value=<%= u.getIcon() %> >
						</td>
					</tr>

					<tr><!-- プロフィール -->
						<th nowrap><span class="icon-joy pe-2x pe-va"></span>&nbsp;プロフィール</th>
						<td class="text-left"><input class="form-control" type="hidden" name="profile" value=<%=u.getProfile() %> size="20" />
						${user.profile}
						</td>
					</tr>

					<tr><!-- 登録＆戻るボタン -->
						<td colspan="2" class="text-right"><button class="btn" type="submit" value="OK"  formaction="./uic">OK</button></td>
						<td colspan="2" class="text-right"> <button class="btn" type="submit" value="キャンセル" formaction="UserInfoInput.jsp">キャンセル</button></td>
					</tr>


					<%-- リクエストスコープに alert があれば --%>
					<c:if
						test="${requestScope.alert != null && requestScope.alert != ''}">
						<tr>
							<%-- リクエストスコープの alert の値を出力 --%>
							<td colspan="2" class="color-error text-left">
								<c:out value="${requestScope.alert}" />
							</td>
						</tr>
					</c:if>
				</table>
			</form>
		</div>
	</div>
</body>
</html>
