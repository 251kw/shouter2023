<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="dto.UserDTO,java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Shouter - ユーザー情報 -</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
<!-- <script>
const checkbox3 = document.getElementsByName("checks")

function checkAllBox(trueOrFalse) {
  for(i = 0; i < checkbox3.length; i++) {
    checkbox3[i].checked = trueOrFalse
  }
}
</script> -->
</head>
<body>
	<%
		String check = "";
		if (request.getParameter("allCheck") != null) {
			check = "checked";
		}
	%>
	<div class="bg-success padding-y-5">
		<div class="container padding-y-5 text-center">
			<h1>ユーザー検索結果画面</h1>
		</div>
	</div>
	<%-- action 属性にサーブレットを指定 --%>
	<form action="./usr" method="get">
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
						<th colspan="2" class="color-error text-left"><c:out
								value="${requestScope.deleteAlert}" /></th>
					</tr>
				</c:if>
				<!-- 検索結果が存在しないときの処理 -->
				<c:if
					test="${requestScope.alert != null && requestScope.alert != ''}">
					<tr>
						<%-- リクエストスコープの alert の値を出力 --%>
						<th colspan="2" class="color-error text-left"><c:out
								value="${requestScope.alert}" /></th>
					</tr>
				</c:if>
				<c:if test="${requestScope.alert == null || requestScope.alert == ''}"><!-- 検索結果が存在するときの処理 -->
					<tr>
						<th></th>
						<th><input class="btn btn-success btn-sm" type="submit"
							name="allCheck" value="全選択"></th>
						<th><input class="btn btn-success btn-sm" type="submit"
							name="allClear" value="全解除"></th>
					</tr>
					<tr>
						<%-- ログインID 入力欄の名前は loginId --%>
						<th class="color-main text-left"></th>
						<th class="color-main text-left">ログインID</th>
						<th class="color-main text-left">ユーザー名</th>
						<th class="color-main text-left">アイコン</th>
						<th class="color-main text-left">プロフィール</th>
						<th class="color-main text-left"></th>
					</tr>
					<%-- リストにある要素の数だけ繰り返し --%>
					<c:forEach var="searchUser" items="${searchUser}" varStatus="loop">
						<tr>
						<!-- el式内でel式を使えないので、setを使って使用できるようにする -->
						<c:set var="deleteUser" value="${deleteUser}" />
						<c:set var="loginId" value="${searchUser.loginId}" />
						<c:choose>
						<c:when test="${fn:contains(deleteUser,loginId)}"><!--  削除確認ボタンを押す際に選択されていたユーザーだった場合-->
							<th><label class="fancy-checkbox"><input
									type="checkbox" name="checks" value="${searchUser.loginId}" <%=check%>checked><span></span></label></th>
						</c:when>
						<c:otherwise><!-- 削除ボタンを押す際にチェックがされていなかった場合 -->
							<th><label class="fancy-checkbox"><input
									type="checkbox" name="checks" value="${searchUser.loginId}" <%=check%>><span></span></label></th>
						</c:otherwise>
						</c:choose>
							<th>${searchUser.loginId}</th>
							<th>${searchUser.userName}</th>
							<th class="text-center"><span
								class="${searchUser.icon} pe-3x pe-va"></span></th>
							<th>${searchUser.profile}</th>
							<th><button class="btn btn-success btn-sm" type="submit"
									name="edit" value="${loop.index}">編集</button></th>
						</tr>
					</c:forEach>
					<tr>
						<th></th>
						<th><input class="btn btn-success btn-sm" type="submit"
							name="allCheck" value="全選択"></th>
						<th><input class="btn btn-success btn-sm" type="submit"
							name="allClear" value="全解除"></th>
						<th></th>
						<th></th>
						<th></th>
					</tr>
				</c:if>
				</table>
				<div class="text-center">
					<input class="btn" type="submit" name="delete" value="削除">
					<input class="btn" type="submit" name="return" value="戻る">
				</div>
			</div>
		</div>
	</form>
</body>
</html>