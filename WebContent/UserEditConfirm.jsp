<%@page import="dto.UserDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>Shouter - ユーザー更新確認 -</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
</head>
<%
	//文字化け対策
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html; charset=UTF-8");

	//UserEditinput.jspからhiddenで来たキー(name)で、そのvalueを取得。
	String ID = request.getParameter("loginId");
	String NAME = request.getParameter("userName");
	String PAS = request.getParameter("password");
	String ICON = request.getParameter("icon");
	String PROF = request.getParameter("profile");
%>
<body>
	<div class="bg-main padding-y-5">
		<div class="padding-y-5 text-center">
			<strong>ユーザ更新確認画面</strong><br>
			<p style="display: inline">更新します。よろしいでしょうか？</p>
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-center">
			<%-- action 属性にサーブレットを指定。押すボタンによって推移先が違うのでaction属性は「?」 --%>
			<form action="?" method="post">
				<table style="width: 400px" class="table">
					<tr>
						<%-- ログインID 入力欄の名前は loginId --%>
						<td class="color-main text-left"><span
							class="icon-users pe-2x pe-va"></span>&nbsp;ログインID</td>
						<td><input type="hidden" name="loginId"
							value=<%=ID%>><%=ID%></td>
					</tr>
					<tr>
						<%-- ユーザー名 入力欄の名前は userName --%>
						<td class="color-main text-left"><span
							class="icon-users pe-2x pe-va"></span>&nbsp;ユーザー名</td>
						<td><input type="hidden" name="userName"
							value=<%=NAME%>><%=NAME%></td>
					</tr>
					<tr>
						<%-- パスワード入力欄の名前は password --%>
						<td class="color-main text-left"><span
							class="icon-users pe-2x pe-va"></span>&nbsp;パスワード</td>
						<td><input type="hidden" name="password"
							value=<%=PAS%>><%=PAS%></td>
					</tr>
					<tr>
						<%-- アイコン 入力欄の名前は icon --%>
						<td class="color-main text-left"><span
							class="icon-users pe-2x pe-va"></span>&nbsp;アイコン</td>
						<td><input type="hidden" name="icon" id="icon"
							value=<%=ICON%>> <label for="icon">
							<span class="<%=ICON%> pe-2x pe-va"></span>
						</label></td>
					</tr>
					<tr>
						<%-- プロフィール 入力欄の名前は profile --%>
						<td class="color-main text-left"><span
							class="icon-users pe-2x pe-va"></span>&nbsp;プロフィール</td>
						<td><input type="hidden" name="profile"
							value=<%=PROF%>><%=PROF%></td>
					</tr>
					<tr>

						<td colspan="2" class="text-right">
							<input class="btn" type="submit" value="OK" formaction="./uec" />
							<input class="btn" type="submit" value="戻る" formaction="UserEditInput.jsp" />

						</td>
					</tr>

					<%-- リクエストスコープに alert があれば --%>
					<c:if
						test="${requestScope.alert != null && requestScope.alert != ''}">
						<tr>
							<%-- リクエストスコープの alert の値を出力 --%>
							<td colspan="2" class="color-error text-left"><c:out
									value="${requestScope.alert}" /></td>
						</tr>
					</c:if>
				</table>
			</form>
		</div>
	</div>
</body>
</html>