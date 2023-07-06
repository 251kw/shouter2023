<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="dto.UserDTO"%>
<!DOCTYPE html PUBLIC >
<%--"-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> --%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Shouter - 検索結果 -</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
<link rel="stylesheet" href="./css/myCss.css">

</head>
<body>
	<div class="bg-success padding-y-5">
		<div class="container padding-y-5 text-center">
			<h1>
				ユーザー検索結果画面&nbsp;<span class="icon-speaker"></span>
			</h1>
		</div>
	</div>
	<%
		String check = "";
		if (request.getAttribute("check") != null) {
			check = (String) request.getAttribute("check");
		}
	%>

	<div class="padding-y-5 text-center">
		<div style="width: 60%" class="container padding-y-5 text-center">
			<%-- action 属性にサーブレットを指定 --%>


			<form action="./usi" method="post">
				<table class="table table-striped table-borderd">
					<tr>
						<td></td>
						<td><input id="checkAll" type="submit" name="checkall"
							value="全選択" formaction="./usr" class="btn btn-empty" /></td>
						<td><input id="checkLift" type="submit" name="checkall"
							value="全解除" formaction="./usr" class="btn btn-empty" /></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>

					<tr bgcolor="#bee0c2" >
						<th></th>
						<th><strong>ログインID</strong></th>
						<th><strong>ユーザー名</strong></th>
						<th><strong>アイコン</strong></th>
						<th><strong>プロフィール</strong></th>
						<th></th>
						</tr>

					<c:forEach items="${users}" var="u" varStatus="s">
						<tr>
							<td><label class="fancy-checkbox"><input type="checkbox" name="user_check"
								value="${s.index}" <%=check%> /><span></span></label></td>
							<td>${u.loginId}</td>
							<td>${u.userName}</td>
							<td><span class="${u.icon} pe-2x pe-va"></span></td>
							<td>${u.profile}</td>
							<td><input type="button" name="edit" value="編集"
								class="btn btn-sm btn-empty"></td>
							<td><input type="hidden" value="${s.index}" />
						</tr>
					</c:forEach>

					<tr>
						<td></td>
						<td><input id="checkAll" type="submit" name="checkall"
							value="全選択" formaction="./usr" class="btn btn-empty" /><span></span></td>
						<td><input id="checkLift" type="submit" name="checkall"
							value="全解除" formaction="./usr" class="btn btn-empty" /><span></span></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
				</table>
				<input type="submit" value="削除" class="btn" />
				<input type ="submit" formaction="UserSerchInput.jsp"
					class="btn" value="戻る">
			</form>
		</div>
	</div>


</body>
</html>