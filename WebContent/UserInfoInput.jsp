<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>Shouter - 登録画面 -</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
<link rel="stylesheet" href="./css/mycss.css">
</head>
<body>
<%
	//最初は空文字を指定
	String ID = "";
	String NAME ="";
	String PAS ="";
	String ICON = "";
	String PROF = "";

	//文字化け対策
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html; charset=UTF-8");

	//UserInfoConfirm.jspからhiddenで来たキー(name)で、そのvalueを取得。nullじゃなかったらそれぞれ送られてきたvalueで更新/
	if(request.getParameter("loginId")!=null)
	{
		ID =request.getParameter("loginId");
	}
	if(request.getParameter("userName")!=null)
	{
		NAME =request.getParameter("userName");
	}
	if(request.getParameter("password")!=null)
	{
		PAS =request.getParameter("password");
	}
	if(request.getParameter("icon")!=null)
	{
		ICON=request.getParameter("icon");
	}
	if(request.getParameter("profile")!=null)
	{
		PROF =request.getParameter("profile");
	}
%>


<%-- <div class="bg-success padding-y-5">
		<div class="container padding-y-5 text-center">
			<h1>
				Shouter&nbsp;<span class="icon-speaker"></span>
			</h1>
		</div>
	</div>--%>

	<%-- <div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-left">
			<strong class="color-main">ユーザ登録入力画面</strong>
		</div>
	</div>--%>

	<div class="bg-main padding-y-5">
	<div class="padding-y-5 text-center">
		<strong>ユーザ登録入力画面</strong><br>
		<p style=display:inline>ユーザー登録します。内容を入力してください。</p>
	</div>
	</div>
	<%-- リクエストスコープに alert があれば リクエストスコープの alert の値を出力--%>
	<div class = "color-error text-center">
		<c:if test="${requestScope.alert1 != null && requestScope.alert1 != ''}">
			<c:out value="${requestScope.alert1}" /><br>
		</c:if>
		<c:if test="${requestScope.alert2 != null && requestScope.alert2 != ''}">
			<c:out value="${requestScope.alert2}" /><br>
		</c:if>
		<c:if test="${requestScope.alert3 != null && requestScope.alert3 != ''}">
		<c:out value="${requestScope.alert3}" />
		</c:if>
		<c:if test="${requestScope.alert4 != null && requestScope.alert4 != ''}">
		<c:out value="${requestScope.alert4}" />
		</c:if>
		<c:if test="${requestScope.alert5 != null && requestScope.alert5 != ''}">
			<c:out value="${requestScope.alert5}" />
		</c:if>
				<c:if test="${requestScope.alert6 != null && requestScope.alert6 != ''}">
			<c:out value="${requestScope.alert6}" />
		</c:if>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-center">
			<%-- action 属性にサーブレットを指定。押すボタンによって推移先が違うのでaction属性は「?」 --%>
			<form action="?" method="post">
				<table style="width: 400px" class="table">

					<tr>
						<%-- ログインID 入力欄の名前は loginId --%>
						<td class="color-main text-left"><span class="icon-users pe-2x pe-va"></span>&nbsp;ログインID</td>
						<td class="text-left"><input class="form-control" type="text" maxlength="10"
							placeholder="10文字以内で入力" name="loginId" value=<%=ID%>></td>
					<tr>
						<%-- ユーザー名 入力欄の名前は userName --%>
						<td class="color-main text-left"><span class="icon-users pe-2x pe-va"></span>&nbsp;ユーザー名</td>
						<td class="text-left"><input class="form-control" type="text" maxlength="15" pattern="\S|\S.*?\S"
							placeholder="15文字以内で入力" name="userName" value=<%=NAME%>></td>
					</tr>
					<tr>
						<%-- パスワード入力欄の名前は password --%>
						<td class="color-main text-left"><span class="icon-users pe-2x pe-va"></span>&nbsp;パスワード</td>
						<td class="text-left"><input class="form-control" type="password" maxlength="8"
							placeholder="8文字以内で入力" name="password" value=<%=PAS%>></td>
					</tr>
					<tr>
						<%-- アイコン 選択欄の名前は icon --%>
						<td class="color-main text-left"><span class="icon-users pe-2x pe-va"></span>&nbsp;アイコン</td>
						<td class = "parent">		<!--親で囲った要素の横並びをcssで指定してる-->
							<div class = "child">		<!--子で囲った要素同士の間隔をcssで指定してる-->
								<label for="icon">
								<span class = "icon-star  pe-2x pe-va"></span>
								</label>
								<input type="radio" name="icon" id="icon" value="icon-star" <% if(ICON.equals("icon-star")){%>checked<%}%>>
								<%--<input type="radio" name="icon" id="icon"
								<c:if test="${param.icon == 'icon-star'}">checked="checked"</c:if>
								value="icon-star">--%>
							</div>
							<div class = "child">
								<label for="icon">
								<span class = "icon-smile pe-2x pe-va"></span>
								</label>
								<%--<input type="radio" name="icon" id="icon" value="icon-smile" <% if(ICON.equals("icon-smile")){%>checked<%}%>> --%>
								<input type="radio" name="icon" id="icon"
								<c:if test="${param.icon == 'icon-smile'}">checked="checked"</c:if>
								value="icon-smile"
								>
							</div>
						</td>
					</tr>
					<tr>
						<%-- プロフィール 入力欄の名前は profile --%>
						<td class="color-main text-left"><span class="icon-users pe-2x pe-va"></span>&nbsp;プロフィール</td>
						<td class="text-left"><input class="form-control" type="text" maxlength="30"
							placeholder="30文字以内で入力" name="profile" value=<%=PROF%>></td>
					</tr>
					<tr>
					<%-- 登録ボタンでUserInfoInputsvt.javaにとぶ --%>
						<td colspan="2" class="text-right">
						<input class="btn" type="submit" value="登録" formaction="./uii"/>
						<input class="btn" type="submit" value="戻る" formaction="./login" />
						</td>
					</tr>


				</table>
			</form>
		</div>
	</div>
</body>
</html>