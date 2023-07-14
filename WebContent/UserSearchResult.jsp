<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dto.UserDTO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
			<form action="usrdel" method="post" id="checkForm">
				<%-- リクエストスコープに alert があれば --%>
				<c:if
					test="${requestScope.alert != null && requestScope.alert != ''}">
					<%-- リクエストスコープの alert重複 の値を出力 --%>
					<p class="color-error text-center">
						<c:out value="${requestScope.alert}" />
				</c:if>
				<table class="table" border="1">
					<tr>
						<th class="color-main text-left"></th>
						<th class="color-main text-left"><input class="btn"
							type="button" onClick="checkAll()" value="全選択"></th>
						<th class="color-main text-left"><input class="btn"
							type="button" onClick="checkReset()" value="全解除"></th>
					</tr>
					<tr>
						<th class="color-main text-center"></th>
						<th class="color-main text-center">ログインID</th>
						<th class="color-main text-center">ユーザー名</th>
						<th class="color-main text-center">アイコン</th>
						<th class="color-main text-center">プロフィール</th>
						<th class="color-main text-center"></th>
					</tr>
					<%
						int count = 1;
						ArrayList<String> dellist = (ArrayList<String>)request.getAttribute("delIDlist2");//戻ってきたとき
						pageContext.setAttribute("delidlist",dellist);
						//String[] tt = (String[])session.getAttribute("check");
					%>
					<jsp:useBean id="userlist" scope="session"
						type="java.util.ArrayList<dto.UserDTO>" />
					<%-- リストにある要素の数だけ繰り返し --%>
					<c:forEach var="users" items="${userlist}">
					<% //if(check!=null){
						//String id=check.get(count);
						//}
						/*if(dellist!=null){
							String rrrr = dellist.get(count);
						}*/
						//String sample = String.valueOf(count);
						//String t=dellist.get(count);
					%>

						<tr>
							<td><label class="fancy-checkbox"><input
									type="checkbox" name="test1" value="<%=count%>"
									${delidlist.contains(users.loginId)?"checked":""}/>
									<span></span></label></td>
							<td><label class="form-control text-center">${users.loginId}</label>
								<input type="hidden" name="<%= count%>" value="${users.loginId}"></td>
							<td><label class="form-control text-center">${users.userName}</label></td>
							<td><span class="${users.icon} pe-3x pe-va" /></span></td>
							<td><label class="form-control text-center">${users.profile}</label></td>
							<td><button class="btn" type="submit" name="edit"
									value="<%=count%>" size="20" formaction="usr" method="post">編集</button></td>
						</tr>
							<%
								count++;
							%>
					</c:forEach>
					<tr>
						<th class="color-main text-left"></th>
						<th class="color-main text-left"><input class="btn"
							type="button" onClick="checkAll()" value="全選択"></th>
						<th class="color-main text-left"><input class="btn"
							type="button" onClick="checkReset()" value="全解除"></th>
					</tr>
					<tr>
						<td colspan="2" class="text-right"><input class="btn"
							type="submit" value="削除" /> <input class="btn" type="submit"
							formaction="./UserSearchInput.jsp" value="戻る" /></td>
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

</body>
</html>