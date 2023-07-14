<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="src.dto.UserDTO,java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html >
<html lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>削除確認</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
</head>
<body>
	<%
		//文字化け対策
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		//ArrayList<UserDTO> ID=session.getAttribute("ID");

		//String loginId = ID.getLoginId();
		//String password = ID.getPassword();
		//String userName = ID.getUserName();
		//String icon = ID.getIcon();
		//String profile = ID.getProfile();

		//指定した名前をいれる、いれたvalue値のこと
		if (request.getParameter("loginId") != null) {
			//loginId = request.getParameter("loginId");
		}
		if (request.getParameter("password") != null) {
			//password = request.getParameter("password");
		}
		if (request.getParameter("userName") != null) {
			//userName = request.getParameter("userName");
		}
		if (request.getParameter("icon") != null) {
			//icon = request.getParameter("icon");
		}
		if (request.getParameter("profile") != null) {
			//profile = request.getParameter("profile");
		}
	%>
	<div class="bg-success padding-y-5">
		<div class="container padding-y-5 text-center">
			<h1>
				ユーザー削除確認画面&nbsp;<span class="icon-speaker"></span>
			</h1>
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-left">
			<strong class="color-main">削除します。よろしいでしょうか？</strong>
			<p>
				<%-- エラー文の挿入 --%>
				<%-- リクエストスコープに alert があれば --%>
				<c:if
					test="${requestScope.alert2 != null && requestScope.alert2 != ''}">
					<%-- リクエストスコープの alert の値を出力 --%>
					<p class="color-error text-left">
						<c:out value="${requestScope.alert2}" />
					</p>
				</c:if>
			</p>
			<p>
				<%-- エラー文の挿入 --%>
				<%-- リクエストスコープに alert があれば --%>
				<c:if
					test="${requestScope.alert3 != null && requestScope.alert3 != ''}">
					<%-- リクエストスコープの alert の値を出力 --%>
					<p class="color-error text-left">
						<c:out value="${requestScope.alert3}" />
					</p>
				</c:if>
			</p>
			<p>
				<%-- エラー文の挿入 --%>
				<%-- リクエストスコープに alert があれば --%>
				<c:if
					test="${requestScope.alert4 != null && requestScope.alert4 != ''}">
					<%-- リクエストスコープの alert の値を出力 --%>
					<p class="color-error text-center">
						<c:out value="${requestScope.alert4}" />
					</p>
				</c:if>
			</p>
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 50%" class="container padding-y-5 text-center">
			<%-- action 属性にサーブレットを指定 --%>
			<form action="./userDeleteConfirmSvt" method="post">
				<table style="width: 700px" class="table">
					<tr>
						<td class="color-main text-left"><span
							class="icon-smile pe-2x pe-va"></span> ログインID</td>

						<td class="color-main text-left"><span
							class="icon-users pe-2x pe-va"></span> ユーザー名</td>

						<td class="color-main text-left"><span
							class="icon-joy pe-2x pe-va"></span> アイコン</td>

						<td class="color-main text-left"><span
							class="icon-note2 pe-2x pe-va"></span> プロフィール</td>
					</tr>

					<jsp:useBean id="ID" scope="session"
						type="java.util.ArrayList<src.controller.UserSearchResultDeleteSvt>" />

					<c:forEach var="ID" items="${ID}">
					<tr>
						<%-- ログインID 入力欄の名前は loginId --%>
						<%-- 半角空白禁止 --%>
						<td class="text-left"><label>${ID.loginId}</label></td>

						<td class="text-left"><label>${ID.userName}</label></td>

						<td><span class="${ID.icon} pe-2x pe-va"></span></td>

						<td class="text-left"><label>${ID.profile}</label></td>

					</tr>

					</c:forEach>

					<tr>
						<td colspan="2" class="text-right"><input class="btn"
							type="submit" value="OK" /></td>
						<td colspan="2" class="text-right"><input class="btn"
							type="submit" value="戻る" formaction="./UserSearchResultDeleteSvt" /></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>