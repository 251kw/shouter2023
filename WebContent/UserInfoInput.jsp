<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="dto.UserDTO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザ登録入力</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
</head>
<body>
	<%
		//文字化け対策
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String id = "";
		String name = "";
		String pass = "";
		String pro = "";
		String icon = "";

		//あれば変数に代入
		if (request.getParameter("id") != null)
			id = request.getParameter("id");
		if (request.getParameter("name") != null)
			name = request.getParameter("name");
		if (request.getParameter("pass") != null)
			pass = request.getParameter("pass");
		if (request.getParameter("pro") != null)
			pro = request.getParameter("pro");
		if (request.getParameter("icon") != null)
			icon = request.getParameter("icon");
%>
<body>
	<div class="bg-success padding-y-5">
		<div class="container padding-y-5 text-center">
			<h1>
				ユーザ登録入力画面&nbsp;<span class="icon-speaker"></span>
				<p>ユーザを登録します。内容を入力してください。</p>
			</h1>
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-left">
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-center">
			<%-- action 属性にサーブレットを指定 --%>
			<form action="./uii" method="post">
			<%-- リクエストスコープに alert があれば --%>
					<c:if
						test="${requestScope.alert2 != null && requestScope.alert2 != ''}">
							<%-- リクエストスコープの alert重複 の値を出力 --%>
							<p class="color-error text-center">
							<c:out value="${requestScope.alert2}" />
					</c:if>
					<c:if
						test="${requestScope.alert != null && requestScope.alert != ''}">
							<%-- リクエストスコープの alert2空白 の値を出力 --%>
							<p class="color-error text-center">
							<c:out value="${requestScope.alert}" />
					</c:if>
				<table style="width: 400px" class="table">
					<tr>
						<%-- ログインID 入力欄の名前は loginId --%>
						<td class="color-main text-left">ログインID</td>
						<td class="text-left"><input class="form-control" type="text"
							pattern="[a-zA-Z0-9]+$" title="半角英数字で入力" name="id"
							 value="<%=id%>" size="20"  maxlength=10 required /></td>
					</tr>
					<tr>
						<%-- ユーザ名 入力欄の名前は username --%>
						<td class="color-main text-left">ユーザ名</td>
						<td class="text-left"><input class="form-control" type="text"
							name="name"  value="<%=name%>" size="20" required /></td>
					</tr>
					<tr>
						<%-- パスワード入力欄の名前は password --%>
						<td class="color-main text-left">パスワード</td>
						<td class="text-left"><input class="form-control"
							type="password" name="pass"  value="<%=pass%>" size="20"
							required /></td>
					</tr>
					<tr>
						<%--アイコン 入力欄の名前は icon --%>
						<td class="color-main text-left">アイコン</td>
						<td class="text-left"><input type="radio" name="icon"
							value="icon-rocket" <%if (icon.equals("icon-rocket")) {%> checked
							<%}%> required /> <span class="icon-rocket pe-3x pe-va"></span>
							<input type="radio" name="icon" value="icon-tools"
							<%if ( icon.equals("icon-tools")) {%> checked <%}%> /> <span
							class="icon-tools pe-3x pe-va"></span> <!-- <input class="form-control" type="radio" name="icon" value="icon %>" size="20" /> -->
						</td>
					</tr>
					<tr>
						<%-- プロフィール 入力欄の名前は profile --%>
						<td class="color-main text-left">プロフィール</td>
						<td class="text-left"><input class="form-control" type="text"
							name="pro" value="<%=pro%>" size="20" required /></td>
					</tr>
					<tr>
						<td colspan="2" class="text-right"><input class="btn" type="submit" value="登録" />
						<a href="index.jsp" class="btn">戻る</a>

						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>