<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<%@ page import="dto.UserDTO"%>
<%@ page import="java.util.ArrayList"%>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>ユーザー登録入力画面</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
<link rel="stylesheet" href="./css/UserSearch.css">
</head>

<%
	String deleteId[] = request.getParameterValues("id");
	if (deleteId != null) {
		ArrayList<String> id = new ArrayList<String>();

		for (String str : deleteId) {
			id.add(str);
		}
		pageContext.setAttribute("id", id);
	}
%>
<body>
	<div class="bg-success padding-y-5">
		<div class="container padding-y-5 text-center">
			<h1>
				ユーザー検索結果画面&nbsp;<span class="icon-speaker"></span>
			</h1>
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-center">
			<%-- action 属性にサーブレットを指定 --%>
			<table style="width: 2000px">
				<%-- リクエストスコープに unselected があれば --%>
				<c:if
					test="${requestScope.unselected  != null && requestScope.unselected  != ''}">
					<tr>
						<%-- リクエストスコープの unselected の値を出力 --%>
						<td colspan="2" class="color-error text-left"><c:out
								value="${requestScope.unselected }" /></td>
					</tr>
				</c:if>
			</table>
			<table style="width: 2000px">
				<%-- リクエストスコープに sql があれば --%>
				<c:if test="${requestScope.sql  != null && requestScope.sql  != ''}">
					<tr>
						<%-- リクエストスコープの sql の値を出力 --%>
						<td colspan="2" class="color-error text-left"><c:out
								value="${requestScope.sql}" /><a class="btn"
							href="./UserSearchInput.jsp">戻る</a></td>
					</tr>
				</c:if>
			</table>
		</div>
	</div>
	<form action="./usr" method="post">
	<c:if test="${requestScope.sql  == null}">
		<div class="padding-y-5 text-center">
			<div style="width: 70%" class="container padding-y-5 text-center">

					<table class="table table-striped table-bordered table-hover"
						style="width: 2000px">
						<tr>
							<td class="btn btn-empty btn-success" onClick="checkAllBox(true)">全選択</td>
							<td class="btn btn-empty btn-success"
								onClick="checkAllBox(false)">全解除</td>
						</tr>
						<tr>
							<th></th>
							<th>ログインID</th>
							<th>ユーザー名</th>
							<th>アイコン</th>
							<th>プロフィール</th>
							<th></th>
						</tr>
						<%

						%>
						<jsp:useBean id="users" scope="session"
							type="java.util.ArrayList<dto.UserDTO>" />

						<%-- リストにある要素の数だけ繰り返し --%>
						<c:forEach var="user" items="${users}">
							<tr>
								<td><label class="fancy-checkbox"><input
										type="checkbox" name="icon" id="icon" value="${user.loginId}"
										${id.contains(user.loginId) ?"checked":""}><span></span></label></td>
								<td>${user.loginId}</td>
								<td>${user.userName}</td>
								<td><span class="${user.icon} pe-2x pe-va"></span></td>
								<td>${user.profile}</td>
								<td colspan="2"><button class="btn btn-empty btn-light"
										name="edit" type="submit" value="${user.loginId}">編集</button></td>
							</tr>

						</c:forEach>
						<tr>
							<td class="btn btn-empty btn-success" onClick="checkAllBox(true)">全選択</td>
							<td class="btn btn-empty btn-success"
								onClick="checkAllBox(false)">全解除</td>
						</tr>
					</table>

			</div>
		</div>
		<script>
			const checkbox3 = document.getElementsByName("icon")

			function checkAllBox(trueOrFalse) {
				for (i = 0; i < checkbox3.length; i++) {
					checkbox3[i].checked = trueOrFalse
				}
			}
		</script>
		<div class="padding-y-5 text-center">
			<div style="width: 40%" class="container padding-y-5 text-center">
				<table style="width: 2000px">
					<tr>
						<td colspan="2"><input class="btn" name="delete"
							type="submit" value="削除" /><a class="btn"
							href="./UserSearchInput.jsp">戻る</a></td>
					</tr>
				</table>
			</div>
		</div>
		</c:if>
	</form>
</body>
</html>