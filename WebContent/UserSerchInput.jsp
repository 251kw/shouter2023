<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Shouter - 検索条件入力 -</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
<link rel="stylesheet" href="./css/myCss.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>
<body>
	<div class="bg-success padding-y-5">
		<div class="container padding-y-5 text-center">
			<h1>
				ユーザー検索条件入力画面&nbsp;<span class="icon-speaker"></span>
			</h1>
		</div>
	</div>
	<c:if
		test="${requestScope.noresult_error != null && requestScope.noresult_error != ''}">
		<%-- リクエストスコープの alert の値を出力 --%>
		<div style="width: 40%" class="container color-error text-left">
			${requestScope.noresult_error}</div>
	</c:if>
	<div class="padding-y-5 text-center">
		<div style="width: 60%" class="container padding-y-5 text-center">
			<%-- action 属性にサーブレットを指定 --%>
			<form action="./usi" method="post">
				<table style="width: 800px" class="table">
					<tr>
						<%-- ログインID 入力欄の名前は loginId --%>
						<td class="color-main text-left"><span
							class="icon-id pe-2x pe-va"></span> <nobr>ログインID</nobr></td>
						<td class="text-left"><input class="form-control" type="text"
							name="loginId" size="20" autofocus /></td>
					</tr>
					<tr>
						<%-- ユーザー名 入力欄の名前は userName --%>
						<td class="color-main text-left"><span
							class="icon-users pe-2x pe-va"></span> <nobr>ユーザー名</nobr></td>
						<td class="text-left"><input class="form-control" type="text"
							name="userName" size="20" /></td>
					</tr>
					<tr>

						<td class="color-main text-left"><span
							class="icon-angle-up-circle pe-2x pe-va"></span> <nobr>アイコン</nobr></td>
						<td class="text-left">
							<%--　アイコン入力欄の名前は icon --%>
							<div class="parent">

								<label class="fancy-checkbox"><input type="checkbox"
									name="icon" id="icon-smile" value="icon-smile" /> <span></span></label>
								<span class="icon-smile pe-2x pe-va "></span>
							</div>
							<div class="parent">
								<label class="fancy-checkbox "><input type="checkbox"
									name="icon" id="icon-users" value="icon-users" /> <span></span>
								</label> <span class="icon-users pe-2x pe-va "></span>
							</div>
							<div class="parent">
								<label class="fancy-checkbox "><input type="checkbox"
									name="icon" id="icon-user" value="icon-user" /><span> </span>
								</label> <span class="icon-user pe-2x pe-va "></span>
							</div>
							<div class="parent">
								<label class="fancy-checkbox"> <input type="checkbox"
									name="icon" id="icon-female" value="icon-user-female" /><span></span>
								</label><span class="icon-female pe-2x pe-va"></span>
							</div>
							<div class="parent">
								<label class="fancy-checkbox "> <input type="checkbox"
									name="icon" id="icon-bell" value="icon-bell" /><span></span>
								</label><span class="icon-bell pe-2x pe-va "></span>
							</div>
						</td>
					</tr>
					<tr>
						<%-- プロフィールの名前は prof --%>
						<td class="color-main text-left"><span
							class="icon-display2 pe-2x pe-va"></span> <nobr>プロフィール</nobr></td>
						<td class="text-left"><input type="text" class="form-control"
							name="prof" id="prof" size="60" /></td>
					</tr>

					<tr>
						<%--送信ボタンと戻るボタン --%>
						<td colspan="2" class="text-right"><input class="btn"
							type="submit" value="検索" /></td>
						<td colspan="2" class="text-right"><a href="top.jsp"
							class="btn">戻る</a></td>
					</tr>:
				</table>
			</form>
		</div>
	</div>

</body>
</html>