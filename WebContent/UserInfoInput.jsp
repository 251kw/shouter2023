<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>ユーザー登録入力画面</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
</head>
<body>
	<div class="bg-success padding-y-5">
		<div class="container padding-y-5 text-center">
			<h1>
				ユーザー登録入力画面&nbsp;<span class="icon-speaker"></span>
			</h1>
			ユーザー登録します。内容を入力してください。
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-center">
			<%-- action 属性にサーブレットを指定 --%>
			<form action="./uii" method="post">
				<table style="width: 400px" class="table">

					<%-- リクエストスコープに nEnterd があれば --%>
					<c:if
						test="${requestScope.noEnterd  != null && requestScope.noEnterd  != ''}">
						<tr>
							<%-- リクエストスコープの noEnterd の値を出力 --%>
							<td colspan="2" class="color-error text-left"><c:out
									value="${requestScope.noEnterd }" /></td>
						</tr>
					</c:if>
					<%-- リクエストスコープに duplication があれば --%>
					<c:if
						test="${requestScope.duplication != null && requestScope.duplication != ''}">
						<tr>
							<%-- リクエストスコープの duplication の値を出力 --%>
							<td colspan="2" class="color-error text-left"><c:out
									value="${requestScope.duplication}" /></td>
						</tr>
					</c:if>
					<%-- リクエストスコープに blank があれば --%>
					<c:if
						test="${requestScope.blank != null && requestScope.blank != ''}">
						<tr>
							<%-- リクエストスコープの blank の値を出力 --%>
							<td colspan="2" class="color-error text-left"><c:out
									value="${requestScope.blank}" /></td>
						</tr>
					</c:if>
					<%-- リクエストスコープに halfSize があれば --%>
					<c:if
						test="${requestScope.halfSize != null && requestScope.halfSize != ''}">
						<tr>
							<%-- リクエストスコープの halfSize の値を出力 --%>
							<td colspan="2" class="color-error text-left"><c:out
									value="${requestScope.halfSize}" /></td>
						</tr>
					</c:if>
					<%
						//空文字
						String loginId1 = "";
						String userName1 = "";
						String password1 = "";
						String icon1 = "";
						String profile1 = "";

						//文字化け対策
						request.setCharacterEncoding("UTF-8");
						response.setContentType("text/html;charset=UTF-8");
					%>

					<%
						if (request.getParameter("loginId") != null) {
							loginId1 = request.getParameter("loginId");
						}
						if (request.getParameter("userName") != null) {
							userName1 = request.getParameter("userName");
						}
						if (request.getParameter("password") != null) {
							password1 = request.getParameter("password");
						}
						if (request.getParameter("icon") != null) {
							icon1 = request.getParameter("icon");
						}
						if (request.getParameter("profile") != null) {
							profile1 = request.getParameter("profile");
						}
					%>
					<tr>
						<%-- ログインID 入力欄の名前は loginId --%>

						<td class="color-main text-left"><span
							class="icon-id pe-2xpe-va">&nbsp;</span>ログインID</td>
						<td class="text-left"><input class="form-control" type="text"
							maxlength="8" size="20" name="loginId" autofocus id="loginId"
							value="<%=loginId1%>"></td>
					</tr>
					<tr>
						<%-- ユーザー名入力欄の名前は userName --%>
						<td class="color-main text-left"><span
							class="icon-user pe-2xpe-va">&nbsp;</span>ユーザー名</td>
						<td class="text-left"><input class="form-control" type="text"
							maxlength="8" size="20" name="userName" id="userName"
							value="<%=userName1%>"></td>
					</tr>
					<tr>
						<%-- パスワード入力欄の名前は password --%>
						<td class="color-main text-left"><span
							class="icon-door-lock pe-2xpe-va">&nbsp;</span>パスワード</td>
						<td class="text-left"><input class="form-control"
							type="password" maxlength="8" size="20" name="password"
							id="password" value="<%=password1%>"></td>
					</tr>

					<%
						//ラジオボタンの選択保持
						/*変数を宣言し、アイコンを代入
						  confirmから取ってきた選択したアイコンと同じだったら
						  該当する変数にcheckedを代入する
						*/
						String users = "icon-users";
						String piggy = "icon-piggy";
						if (users.equals(icon1)) {
							users = "checked";
						} else if (piggy.equals(icon1)) {
							piggy = "checked";
						}
					%>
					<%-- アイコン入力欄の名前は icon --%>
					<tr>
						<td class="color-main text-left"><span
							class="icon-smile pe-2xpe-va">&nbsp;</span>アイコン</td>
						<td><label><input type="radio" required name="icon"
								id="icon" <%=users%> value="icon-users" checked><span
								class="icon-users pe-2x pe-va"></span><input type="radio"
								name="icon" id="icon" <%=piggy%> value="icon-piggy"> <span
								class="icon-piggy pe-2x pe-va"></span></label></td>
					</tr>
					<tr>
						<%-- プロフィール入力欄の名前は profile --%>
						<td class="color-main text-left"><span
							class="icon-note pe-2xpe-va">&nbsp;</span>プロフィール</td>
						<td class="text-left"><input class="form-control" type="text"
							maxlength="50" size="20" name="profile" id="profile"
							value="<%=profile1%>"></td>
					</tr>
					<tr>
						<td colspan="2" class="text-right"><input class="btn"
							type="submit" value="登録" /><a class="btn" href="./index.jsp">戻る</a></td>

					</tr>

				</table>
			</form>
		</div>
	</div>
</body>
</html>