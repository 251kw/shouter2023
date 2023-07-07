<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="dto.UserDTO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Shouter - ユーザー情報登録 -</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
<link rel="stylesheet" href="./css/style.css">
</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8");
	%>
	<%
		UserDTO editUser = (UserDTO) request.getAttribute("editUser");
	%>
	<%
		String icon = "";
		if (request.getParameter("icon") != null) {
			icon = request.getParameter("icon");
		}
	%>
	<%
		String smileIcon = "";
		String bellIcon = "";
		String userIcon = "";
		String userFemaleIcon = "";
		if (icon.equals("icon-user")) {
			userIcon = "checked";
		} else if (icon.equals("icon-bell")) {
			bellIcon = "checked";
		} else if (icon.equals("icon-user-female")) {
			userFemaleIcon = "checked";
		} else {
			smileIcon = "checked";
		}
	%>
	<div class="bg-success padding-y-5">
		<div class="container padding-y-5 text-center">
			<h1>
				ユーザー情報編集画面<br>編集内容を入力してください。
			</h1>
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-left">
			<strong class="color-main">編集情報を入力してください。</strong>
		</div>
	</div>
	<%-- action 属性にサーブレットを指定 --%>
	<form action="./uei" method="post">
		<input type="hidden" name="loginId" value="<%=request.getAttribute("loginId")%>">
		<input type="hidden" name="userName" value="<%=request.getAttribute("userName")%>">
		<input type="hidden" name="icon-user-female" value="<%=request.getAttribute("icon-user-female")%>">
		<input type="hidden" name="icon-user" value="<%=request.getAttribute("icon-user")%>">
		<input type="hidden" name="icon-bell" value="<%=request.getAttribute("icon-bell")%>">
		<input type="hidden" name="icon-smile" value="<%=request.getAttribute("icon-smile")%>">
		<input type="hidden" name="profile" value="<%=request.getAttribute("profile")%>">
		<table style="width: 60%" class="table">
			<c:if
				test="${requestScope.alertSpace != null && requestScope.alertSpace != ''}">
				<tr>
					<%-- リクエストスコープの alert の値を出力 --%>
					<td colspan="2" class="color-error text-left"><c:out
							value="${requestScope.alertSpace}" /></td>
				</tr>
			</c:if>
			<c:if
				test="${requestScope.alertBlank != null && requestScope.alertBlank != ''}">
				<tr>
					<%-- リクエストスコープの alert の値を出力 --%>
					<td colspan="2" class="color-error text-left"><c:out
							value="${requestScope.alertBlank}" /></td>
				</tr>
			</c:if>
			<c:if
				test="${requestScope.alertHalf_width != null && requestScope.alertHalf_width != ''}">
				<tr>
					<%-- リクエストスコープの alert の値を出力 --%>
					<td colspan="2" class="color-error text-left"><c:out
							value="${requestScope.alertHalf_width}" /></td>
				</tr>
			</c:if>
			<c:if
				test="${requestScope.alertDuplication != null && requestScope.alertDuplication != ''}">
				<tr>
					<%-- リクエストスコープの alert の値を出力 --%>
					<td colspan="2" class="color-error text-left"><c:out
							value="${requestScope.alertDuplication}" /></td>
				</tr>
			</c:if>
			<c:if
				test="${requestScope.alertMaxlimit_ID != null && requestScope.alertMaxlimit_ID != ''}">
				<tr>
					<%-- リクエストスコープの alert の値を出力 --%>
					<td colspan="2" class="color-error text-left"><c:out
							value="${requestScope.alertMaxlimit_ID}" /></td>
				</tr>
			</c:if>
			<c:if
				test="${requestScope.alertMaxlimit_uName != null && requestScope.messageMaxlimit_uName != ''}">
				<tr>
					<%-- リクエストスコープの alert の値を出力 --%>
					<td colspan="2" class="color-error text-left"><c:out
							value="${requestScope.alertMaxlimit_uName}" /></td>
				</tr>
			</c:if>
			<c:if
				test="${requestScope.alertMaxlimit_password != null && requestScope.alertMaxlimit_password != ''}">
				<tr>
					<%-- リクエストスコープの alert の値を出力 --%>
					<td colspan="2" class="color-error text-left"><c:out
							value="${requestScope.alertMaxlimit_password}" /></td>
				</tr>
			</c:if>
			<c:if
				test="${requestScope.alertMaxlimit_profile != null && requestScope.alertMaxlimit_profile != ''}">
				<tr>
					<%-- リクエストスコープの alert の値を出力 --%>
					<td colspan="2" class="color-error text-left"><c:out
							value="${requestScope.alertMaxlimit_profile}" /></td>
				</tr>
			</c:if>
			<tr>
				<%-- ログインID 入力欄の名前は loginId --%>
				<td class="color-main text-left"><span
					class="icon-smile pe-2x pe-va"></span>ログインID</td>
				<td class="text-left"><input class="form-control" type="text"
					name="e_LoginId" value="${editUser.loginId}" size="20"
					placeholder="10文字以内、半角英数字で入力してください。" autofocus /></td>
			</tr>
			<tr>
				<%-- ユーザー名 入力欄の名前は userName --%>
				<td class="color-main text-left"><span
					class="icon-smile pe-2x pe-va"></span>ユーザー名</td>
				<td class="text-left"><input class="form-control" type="text"
					name="e_UserName" value="${editUser.userName}"
					placeholder="10文字以内で入力してください。" size="20" /></td>
			</tr>
			<tr>
				<%-- パスワード入力欄の名前は password --%>
				<td class="color-main text-left"><span
					class="icon-smile pe-2x pe-va"></span>パスワード</td>
				<td class="text-left"><input class="form-control"
					type="password" name="e_Password" value="${editUser.password}" size="20"
					placeholder="10文字以内で入力してください。" /></td>
			</tr>
			<tr>
				<%-- ログインID 入力欄の名前は loginId --%>
				<td class="color-main text-left"><span
					class="icon-smile pe-2x pe-va"></span>アイコン</td>
				<td class="text-left" style="display: flex;">
					<div style="margin-left: 1em;">
						<span class="icon-user-female pe-2x pe-va"></span>
					</div>
					<div style="margin-left: 1em;">
						<label class="fancy-radio"><input type="radio" name="e_Icon"
							value="icon-user-female" <%=smileIcon%>><span></span></label>
					</div>
					<div style="margin-left: 1em">
						<span class="icon-bell pe-2x pe-va"></span>
					</div>
					<div style="margin-left: 1em;">
						<label class="fancy-radio"><input type="radio" name="e_Icon"
							value="icon-bell" <%=bellIcon%>><span></span></label>
					</div>
					<div style="margin-left: 1em;">
						<span class="icon-user pe-2x pe-va"> </span>
					</div>
					<div style="margin-left: 1em;">
						<label class="fancy-radio"><input type="radio" name="e_Icon"
							value="icon-user" <%=userIcon%>><span></span></label>
					</div>
					<div style="margin-left: 1em;">
						<span class="icon-smile pe-2x pe-va"></span>
					</div>
					<div style="margin-left: 1em;">
						<label class="fancy-radio"><input type="radio" name="e_Icon"
							value="icon-smile" <%=userFemaleIcon%>><span></span></label>
					</div>
				</td>
			</tr>
			<tr>
				<td class="color-main text-left"><span
					class="icon-smile pe-2x pe-va"></span>プロフィール</td>
				<td class="text-left"><input class="form-control" type="text"
					name="e_Profile" value="${editUser.profile}" size="20"
					placeholder="50文字以内で入力してください。" />
			</tr>
		</table>
		<div class="text-center">
			<input class="btn" type="submit" name="edit" value="編集" /> <input
				class="btn" type="submit" name="return" value="戻る" />
		</div>
	</form>
</body>
</html>