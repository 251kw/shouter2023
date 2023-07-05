<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>検索結果</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
	<div class="bg-success padding-y-5">
		<div class="container padding-y-5 text-center">
			<h1>
				ユーザ検索結果画面&nbsp;<span class="icon-speaker"></span>
			</h1>
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-left">
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<div class="container padding-y-5 text-center">
			<%-- action 属性にサーブレットを指定 --%>
			<form action="./uii" method="post" id="checkForm">

				<table class="table" border="1">
					<tr>
						<th class="color-main text-left"></th>
						<th class="color-main text-left"><input type="button"
							onClick="checkAll()" value="全選択"></th>
						<th class="color-main text-left"><input type="button"
							onClick="checkReset()" value="全解除"></th>
					</tr>
					<tr>
						<th class="color-main text-left"></th>
						<th class="color-main text-left">ログインID</th>
						<th class="color-main text-left">ユーザー名</th>
						<th class="color-main text-left">アイコン</th>
						<th class="color-main text-left">プロフィール</th>
						<th class="color-main text-left"></th>
					</tr>
					<jsp:useBean id="user" scope="session"
						type="java.util.ArrayList<dto.UserDTO>" />
					<%-- リストにある要素の数だけ繰り返し --%>
					<c:forEach var="user" items="${user}">
						<tr>
							<td><label class="fancy-checkbox"><input type="checkbox" name="test1" value="" />
						<span></span></label></td>
							<td><input class="form-control" type="text" name="name"
								value="${user.loginId}" size="20" disabled="disabled" /></td>
							<td><input class="form-control" type="text" name="name"
								value="${user.userName}" size="20" disabled="disabled" /></td>
							<td><span class="${user.icon} pe-3x pe-va" /></span></td>
							<td><input class="form-control" type="text" name="name"
								value="${user.profile}" size="20" disabled="disabled" /></td>
							<td><input class="btn" type="submit" name="btn"
							value="編集" size="20" /></td>
						</tr>
					</c:forEach>
					<tr>
						<th class="color-main text-left"></th>
						<th class="color-main text-left"><input type="button"
							onClick="checkAll()" value="全選択"></th>
						<th class="color-main text-left"><input type="button"
							onClick="checkReset()" value="全解除"></th>
					</tr>
					<tr>
						<td colspan="2" class="text-right"><input class="btn"
							type="submit" value="削除" /> <input class="btn"
							type="submit" formaction="./UserSearchInput.jsp" value="戻る" /></td>
					</tr>
				</table>
			</form>
		</div>
	</div>

	<script>
		const checkbox1 = document.getElementsByName("test1")

		function checkAll() {
			for (i = 0; i < checkbox1.length; i++) {
				checkbox1[i].checked = true
			}
		}
	</script>

	<script>
		const checkbox2 = document.getElementsByName("test1")

		function checkReset() {
			for (i = 0; i < checkbox2.length; i++) {
				checkbox1[i].checked = false
			}
		}
	</script>
	<!--
	<div class="padding-y-5">
		<div style="width: 40%" class="container padding-y-5">
			<%-- リストにある要素の数だけ繰り返し --%>
			<c:forEach var="user" items="${user}">
				<table>
					<tr>
						<td rowspan="2" class="text-center"><span
							class="${user.icon} pe-3x pe-va"></span></td>
						<td>${user.userName}</td>
					</tr>
					<tr>
						<td>${user.loginId}</td>
					</tr>
					<tr>
						<td colspan="2"><label class="form-control">${user.profile}</label>
						</td>
					</tr>
				</table>
			</c:forEach>
		</div>
	</div>-->
</body>
</html>