<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="dto.UserDTO"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC >
<%--"-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> --%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Shouter - アカウント削除 -</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
<link rel="stylesheet" href="./css/myCss.css">

</head>
<body>
	<div class="bg-success padding-y-5">
		<div class="container padding-y-5 text-center">
			<h1>
				アカウント削除確認画面&nbsp;<span class="icon-speaker"></span>
			</h1>
			<div class="padding-y-5 text-center">
				<div style="width: 40%" class="container padding-y-5 text-center">
					<div class="color-Light">以下のアカウントを削除します。よろしいですか？</div>
				</div>
			</div>
		</div>
	</div>


	<div class="padding-y-5 text-center">
		<div style="width: 60%" class="container padding-y-5 text-center">
			<%-- action 属性にサーブレットを指定 --%>
			<form action="./udr" method="post">
				<table class="table table-striped table-borderd">

					<tr bgcolor="#bee0c2">
						<th></th>
						<th><strong>ログインID</strong></th>
						<th><strong>ユーザー名</strong></th>
						<th><strong>アイコン</strong></th>
						<th><strong>プロフィール</strong></th>
					</tr>

					<c:forEach items="${users}" var="u" varStatus="s">
						<tr>
							<td></td>
							<td>${u.loginId}</td>

							<td>${u.userName}</td>
							<td><span class="${u.icon} pe-2x pe-va"></span></td>
							<td>${u.profile}</td>
						</tr>
						<tr>
							<td><input type="hidden" name="${s.index}"
								value="${u.loginId}" /></td>
						</tr>
						<c:if test="${s.last}">
							<tr>
								<td><input type="hidden" name="max_num" value="${s.index}" /></td>
							</tr>
						</c:if>
					</c:forEach>

				</table>
				<input type="submit" value="削除" class="btn" /> <input type="submit"
					formaction="./usr" class="btn" value="戻る">
			</form>
		</div>
	</div>


</body>
</html>