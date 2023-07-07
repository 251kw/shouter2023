<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>ユーザー登録入力画面</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
</head>
<body>
	<div class="bg-success padding-y-5">
		<div class="container padding-y-5 text-center">
			<h1>
				ユーザー検索条件入力画面&nbsp;<span class="icon-speaker"></span>
			</h1>
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-center">
			<%-- action 属性にサーブレットを指定 --%>
			<form action="./usi" method="post">
				<table style="width: 400px" class="table">

					<%-- リクエストスコープに sql があれば --%>
					<c:if
						test="${requestScope.sql  != null && requestScope.sql  != ''}">
						<tr>
							<%-- リクエストスコープの noEnterd の値を出力 --%>
							<td colspan="2" class="color-error text-left"><c:out
									value="${requestScope.sql }" /></td>
						</tr>
					</c:if>
					<%
						//文字化け対策
						request.setCharacterEncoding("UTF-8");
						response.setContentType("text/html;charset=UTF-8");
					%>
					<tr>
						<%-- ログインID 入力欄の名前は loginId --%>

						<td class="color-main text-left"><span
							class="icon-id pe-2xpe-va">&nbsp;</span>ログインID</td>
						<td class="text-left"><input class="form-control" type="text"
							maxlength="8" size="20" name="loginId" autofocus id="loginId"
							value=""></td>
					</tr>
					<tr>
						<%-- ユーザー名入力欄の名前は userName --%>
						<td class="color-main text-left"><span
							class="icon-user pe-2xpe-va">&nbsp;</span>ユーザー名</td>
						<td class="text-left"><input class="form-control" type="text"
							maxlength="8" size="20" name="userName" id="userName" value=""></td>
					</tr>
					<%-- アイコン入力欄の名前は icon --%>
					<tr>
						<td class="color-main text-left"><span
							class="icon-smile pe-2xpe-va">&nbsp;</span>アイコン</td>
						<td><label class="fancy-checkbox icon-users pe-2x pe-va"><input
								type="checkbox" name="icon1" id="icon1" value="icon-users"><span></span></label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label
							class="fancy-checkbox icon-piggy pe-2x pe-va"><input
								type="checkbox" name="icon2" id="icon2" value="icon-piggy">
								<span></span></label></td>
					</tr>
					<tr>
						<%-- プロフィール入力欄の名前は profile --%>
						<td class="color-main text-left"><span
							class="icon-note pe-2xpe-va">&nbsp;</span>プロフィール</td>
						<td class="text-left"><input class="form-control" type="text"
							maxlength="50" size="20" name="profile" id="profile" value=""></td>
					</tr>
					<tr>
						<td colspan="2" class="text-right"><input class="btn"
							type="submit" value="検索" /><a class="btn" href="./top.jsp">戻る</a></td>
					</tr>

				</table>
			</form>
		</div>
	</div>
</body>
</html>