<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザー検索</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
</head>
<body>
<div class="bg-success padding-y-5">
		<div class="container padding-y-5 text-center">
			<h1>
				ユーザ検索入力画面&nbsp;<span class="icon-speaker"></span>
				<p>ユーザを検索します。内容を入力してください。</p>
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
			<form action="./usi" method="post">
			<%-- リクエストスコープに alert があれば --%>

				<table style="width: 400px" class="table">
					<tr>
						<%-- ログインID 入力欄の名前は loginId --%>
						<td class="color-main text-left">ログインID</td>
						<td class="text-left"><input class="form-control" type="text"
							name="id" value="" size="20" /></td>
					</tr>
					<tr>
						<%-- ユーザ名 入力欄の名前は username --%>
						<td class="color-main text-left">ユーザ名</td>
						<td class="text-left"><input class="form-control" type="text"
							name="name"  value="" size="20" /></td>
					</tr>
					<tr>
						<%--アイコン 入力欄の名前は icon --%>
						<td class="color-main text-left">アイコン</td>
						<td class="text-left"><input type="checkbox" name="icon1"
							value="icon-rocket" /> <span class="icon-rocket pe-3x pe-va"></span>
							<input type="checkbox" name="icon2" value="icon-tools" /> <span
							class="icon-tools pe-3x pe-va"></span>
						</td>
					</tr>
					<tr>
						<%-- プロフィール 入力欄の名前は profile --%>
						<td class="color-main text-left">プロフィール</td>
						<td class="text-left"><input class="form-control" type="text"
							name="pro" value="" size="20"/></td>
					</tr>
					<tr>
						<td colspan="2" class="text-right"><input class="btn"
							type="submit" value="検索" /> <a href="index.jsp" class="btn">戻る</a>
						</td>
					</tr>
					<%-- リクエストスコープに alert があれば --%>
					<c:if
						test="${requestScope.alert != null && requestScope.alert != ''}">
						<tr>
							<%-- リクエストスコープの alert の値を出力 --%>
							<td colspan="2" class="color-error text-left"><center><h3><c:out
									value="${requestScope.alert}" /></h3></center></td>
						</tr>
					</c:if>
				</table>
			</form>
		</div>
	</div>
</body>
</html>