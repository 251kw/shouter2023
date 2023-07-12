<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="dto.UserDTO,java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Shouter - ユーザー情報削除 -</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
</head>
<body>
	<div class="bg-success padding-y-5">
		<div class="container padding-y-5 text-center">
			<h1>ユーザー削除結果画面</h1>
		</div>
	</div>
	<%-- action 属性にサーブレットを指定 --%>
	<form action="./udr" method="get">
		<!-- 全選択ボタンまたは全解除ボタンが押された場合の値の受け渡しのためにhiddenを使用 -->
		<input type="hidden" name="loginId"
			value="<%=request.getAttribute("loginId")%>"> <input
			type="hidden" name="userName"
			value="<%=request.getAttribute("userName")%>"> <input
			type="hidden" name="icon-user-female"
			value=<%=request.getAttribute("icon-user-female")%>> <input
			type="hidden" name="icon-user"
			value=<%=request.getAttribute("icon-user")%>> <input
			type="hidden" name="icon-bell"
			value=<%=request.getAttribute("icon-bell")%>> <input
			type="hidden" name="icon-smile"
			value=<%=request.getAttribute("icon-smile")%>> <input
			type="hidden" name="profile"
			value="<%=request.getAttribute("profile")%>">
		<div class="padding-y-5">
			<div style="width: 60%" class="container padding-y-5">
				<table class="table table-striped table-bordered">
					<c:if
						test="${requestScope.deleteAlert != null && requestScope.deleteAlert != ''}">
						<tr>
							<%-- リクエストスコープの alert の値を出力 --%>
							<td colspan="2" class="color-error text-left"><c:out
									value="${requestScope.deleteAlert}" /></td>
						</tr>
					</c:if>
					<c:if
						test="${requestScope.alert == null || requestScope.alert == ''}">
						<tr>
							<%-- リクエストスコープの alert の値を出力 --%>
							<td colspan="2" class="color-error text-left">
								削除が完了しました。</td>
						</tr>
					</c:if>
				</table>
				<div class="text-center">
					<input class="btn" type="submit" name="return" value="戻る">
				</div>
			</div>
		</div>
	</form>
</body>
</html>