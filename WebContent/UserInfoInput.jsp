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
<link rel="stylesheet" href="./css/stylesheet.css">
</head>

<body>
	<%
		//文字化け対策
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		//空文字を挿入
		String loginId1 = "";
		String userName1 = "";
		String password1 = "";
		String icon1 ="icon-smile";
		String profile1 = "";
		String checkSmile="";
	 	String checkJoy="";

	%>

	<%
		//戻るボタンを押した際、nullではない場合は値を空文字から更新する
		if (request.getParameter("loginId") != null) {
			 loginId1 = request.getParameter("loginId");
		}

		if (request.getParameter("userName") != null) {
			 userName1 = request.getParameter("userName");
		}

		if (request.getParameter("password") != null) {
			 password1 = request.getParameter("password");
		}
		if (request.getParameter("icon") != null) {
			 icon1 = request.getParameter("icon");
			 if(icon1.equals("icon-smile")){
				checkSmile = "checked";
			 }else{
				 checkJoy = "checked";
			 }
		}
		if (request.getParameter("profile") != null) {
			 profile1 = request.getParameter("profile");

		}
	%>

	<div class="bg-success padding-y-5">
		<div class="container padding-y-5 text-center">
			<h1>
				ユーザー登録入力画面&nbsp;<span class="icon-speaker"></span>
			</h1>
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-left">
			<strong class="color-main">ユーザー登録します。内容を入力してください。</strong>
		</div>

	</div>

	<!-- アラートメッセージ -->
	 <div class="alert">
		<c:if test="${requestScope.alertEmpty != null && requestScope.alertEmpty != ''}"><!-- 未入力の場合 -->
				<c:out value="${requestScope.alertEmpty}" /><br>
		</c:if>
		<c:if test="${requestScope.alertHalf != null && requestScope.alertHalf != ''}"><!-- 半角の場合 -->
				<c:out value="${requestScope.alertHalf}" /><br>
		</c:if>

		<c:if test="${requestScope.alertFull != null && requestScope.alertFull != ''}"><!-- 全角の場合 -->>
				<c:out value="${requestScope.alertFull}" /><br>
		</c:if>

		<c:if test="${requestScope.alertLoginId != null && requestScope.alertLoginId != ''}"><!-- 全角の場合 -->
					<c:out value="${requestScope.alertLoginId}" /><br>
		</c:if>

		<c:if test="${requestScope.alertNumLoginId != null && requestScope.alertNumLoginId != ''}"><!-- 半角英数字ではない場合 -->
					<c:out value="${requestScope.alertNumLoginId}" /><br>
		</c:if>

		<c:if test="${requestScope.alertPassword != null && requestScope.alertPassword != ''}"><!-- 半角英数字ではない場合 -->
					<c:out value="${requestScope.alertPassword}" /><br>
		</c:if>


	</div>



	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-center">
			<form action=? method="post">
				<table style="width: 400px" class="table">
					<tr><!-- ログインID  -->
						<th nowrap class="color-main text-left" >
							<label for="loginId"> <span class="icon-smile pe-2x pe-va"> </span>&nbsp;ログインID</label>
						</th>
						<td class="text-left"><input class="form-control" type="text" name="loginId"  size="20" autofocus value="<%=loginId1%>" maxlength="10" /></td>
					</tr>

					<tr><!-- ユーザー名-->
						<th nowrap class="color-main text-left" >
							<label for="userName"> <span class="icon-note pe-2x pe-va"></span>&nbsp;ユーザー名</label>
						</th>
						<td class="text-left"><input class="form-control" type="text" name="userName"  size="20"  value="<%=userName1%>" maxlength="30" pattern="\S|\S.*?\S"/></td>
					</tr>

					<tr><!-- パスワード -->
						<th nowrap class="color-main text-left" >
							<label for="pass"> <span class="icon-note pe-2x pe-va"></span>&nbsp;パスワード</label>
						</th>
						<td class="text-left">
							<input class="form-control" type="password" name="password" size="20" value="<%=password1%>" maxlength="10" />
						</td>
					</tr>

					<tr><!-- アイコン選択 -->
						<th nowrap class="color-main text-left" >
							<span class="icon-joy pe-2x pe-va"></span>&nbsp;アイコン
						</th>
						<td class="text-left"  id =parent>
							<div class ="child">
								<label for="icon"><span class="icon-smile pe-2x pe-va" > </span></label>
								<input type="radio" name="icon" id="icon" value="icon-smile pe-2x pe-va"  <%=checkSmile %> >
							</div>

							<div class ="child">
								<label for="icon"><span class="icon-joy pe-2x pe-va"> </span></label>
								<input type="radio" name="icon" id="icon" value="icon-joy pe-2x pe-va" <%= checkJoy %>>
							</div>
						</td>
					</tr>

					<tr><!-- プロフィール -->
						<th nowrap class="color-main text-left" >
							<label for="profile"> <span class="icon-note pe-2x pe-va"></span>&nbsp;プロフィール</label>
						</th>
						<td class="text-left"><input class="form-control" type="text" name="profile"  size="20"  value="<%=profile1%>" maxlength="100"/></td>
					</tr>

					<tr><!-- 登録＆戻るボタン -->
						<td colspan="2" class="text-right"><button class="btn" type="submit" value="登録" formaction="./uii">登録</button></td>
						<td colspan="2" class="text-right"> <button class="btn" type="submit" value="戻る" formaction="index.jsp">戻る</button></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>
