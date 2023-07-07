<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>検索結果</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
</head>
<body>
	<div class="bg-success padding-y-5">
		<div class="container padding-y-5 text-center">
			<h1>
				ユーザー検索結果画面&nbsp;<span class="icon-speaker"></span>
			</h1>
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-left">
			<strong class="color-main"></strong>
			<p>
				<%-- エラー文の挿入 --%>
				<%-- リクエストスコープに alert があれば --%>
				<c:if
					test="${requestScope.alert != null && requestScope.alert != ''}">
					<%-- リクエストスコープの alert の値を出力 --%>
					<p class="color-error text-left">
						<c:out value="${requestScope.alert}" />
					</p>
				</c:if>
			</p>
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 60%" class="container padding-y-5 text-center">
			<%-- action 属性にサーブレットを指定 --%>
			<form action="UserSearchInput.jsp" method="post">
				<table style="width: 700px" class="table">
					<tr>
						<td colspan="2" class="text-right"><input class="btn"
							onclick="checkall(true)" type="button" value="全選択" /></td>
						<td class="text-right"><input class="btn"
							onclick="checkall(false)" type="button" value="全解除" /></td>
					</tr>
					<%-- checkbox --%>
					<tr>
						<td class="color-main text-left"></td>

						<%-- ログインID 入力欄の名前は loginId --%>
						<%-- 半角空白禁止 --%>
						<td class="color-main text-left"><span
							class="icon-smile pe-2x pe-va"></span> ログインID</td>

						<td class="color-main text-left"><span
							class="icon-users pe-2x pe-va"></span> ユーザー名</td>

						<td class="color-main text-left"><span
							class="icon-joy pe-2x pe-va"></span> アイコン</td>

						<td class="color-main text-left"><span
							class="icon-note2 pe-2x pe-va"></span> プロフィール</td>

						<%--編集--%>
						<td class="color-main text-left"></td>
					</tr>
					<jsp:useBean id="users" scope="session"
						type="java.util.ArrayList<src.controller.UserSearchInputSvt>" />
					<c:forEach var="user" items="${users}">


						<%-- checkbox --%>
						<tr>
							<td><label class="fancy-checkbox"><input type="checkbox" name="box"><span></span>
							</label></td>
							<td class="text-left"><label>${user.loginId}</label></td>

							<td class="text-left"><label>${user.userName}</label></td>

							<td><span class="${user.icon} pe-2x pe-va"></span></td>

							<td class="text-left"><label>${user.profile}</label></td>

							<%--編集--%>
							<td class="color-main text-left"><input class="btn btn-success"
							 type="submit" value="編集" formaction="userEditInput.jsp"/></td>
						</tr>

					</c:forEach>
					<tr>
						<td colspan="2" class="text-right"><input class="btn"
							onclick="checkall(true)" type="button" value="全選択" /></td>
						<td class="text-right"><input class="btn"
							onclick="checkall(false)" type="button" value="全解除" /></td>
						<script>
							const checkbox = document.getElementsByName("box")

							function checkall(trueOrfalse) {
								for (i = 0; i < checkbox.length; i++) {
									checkbox[i].checked = trueOrfalse
								}
							}
						</script>
					</tr>
					<tr>
					<td colspan="2" class="text-right"><input class="btn btn-error"
							type="submit" value="削除" /></td>
						<td class="text-right"><input class="btn"
							type="submit" value="戻る" /></td>
					</tr>

				</table>
			</form>
		</div>
	</div>
</body>
</html>