<%@page import="dto.UserDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザー情報更新画面</title>
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

		//変数の初期化
		String loginId = null;
		String userName = null;
		String password = null;
		String icon = null;
		String icon1= "";
		String profile = null;
		String checkSmile= "";
	 	String checkJoy="";

		UserDTO user = (UserDTO)request.getAttribute("editResult");


		//検索結果画面で編集ボタンを押された際の値尾の受け取り
		if(user !=null){
			loginId = user.getLoginId();
			userName =user.getUserName();
			password = user.getPassword();
			icon = user.getIcon();
			icon1="";
			profile =user.getProfile();
			checkSmile= "";
			checkJoy="";

			if(icon !=null){
				if(icon.equals("icon-smile")){
					checkSmile = "checked";
				}else{
					checkJoy = "checked";
				}
			}
		}

		//更新確認画面から戻るボタンを押され編集画面に遷移した際の値の受け取り
		else{
			if (request.getParameter("loginId") != null) {
				loginId = request.getParameter("loginId");
			}

			if (request.getParameter("userName") != null) {
				 userName = request.getParameter("userName");
			}

			if (request.getParameter("password") != null) {
				 password = request.getParameter("password");
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
				 profile = request.getParameter("profile");
			}
		}

	%>

	<div class="bg-success padding-y-5">
		<div class="container padding-y-5 text-center">
			<h1>
				ユーザー情報更新画面&nbsp;<span class="icon-speaker"></span>
			</h1>
		</div>
	</div>

	 <div class="alert">
		<c:if test="${requestScope.alertLoginId != null && requestScope.alertLoginId != ''}"><!-- ログインID入力値エラー -->
			<c:out value="${requestScope.alertLoginId}" /><br>
		</c:if>

		<c:if test="${requestScope.alertNumLoginId != null && requestScope.alertNumLoginId != ''}"><!-- ログインID半角英数字以外エラー -->
			<c:out value="${requestScope.alertNumLoginId}" /><br>
		</c:if>
		<c:if test="${requestScope.alertPassword != null && requestScope.alertPassword != ''}"><!-- パスワード半角英数字以外エラー -->
			<c:out value="${requestScope.alertPassword}" /><br>
		</c:if>
		<c:if test="${requestScope.alertdoubleLoginId != null && requestScope.alertdoubleLoginId != ''}"><!-- ログインID重複エラー -->
			<c:out value="${requestScope.alertdoubleLoginId}" /><br>
		</c:if>
		<c:if test="${requestScope.alertHalf != null && requestScope.alertHalf != ''}"><!--半角空文字エラー -->
			<c:out value="${requestScope.alertHalf}" /><br>
		</c:if>
		<c:if test="${requestScope.alertFull != null && requestScope.alertFull != ''}"><!-- 全角空文字エラー -->
			<c:out value="${requestScope.alertFull}" /><br>
		</c:if>
	</div>


	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-left">
			<strong class="color-main">ユーザー情報を編集します。内容を入力してください。</strong>
		</div>
	</div>


	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-center">
			<form action=? method="post">
				<table style="width: 400px" class="table">
					<tr><!-- ログインID  -->
						<th nowrap class="color-main text-left" >
							<label for="loginId"> <span class="icon-smile pe-2x pe-va"> </span>&nbsp;ログインID</label>
						</th>
						<td class="text-left">
							<input class="form-control" type="text" name="loginId"  size="20"  value="<%= loginId %>" required autofocus maxlength="10"  />
						</td>
					</tr>

					<tr><!-- ユーザー名-->
						<th nowrap class="color-main text-left" >
							<label for="userName"> <span class="icon-note pe-2x pe-va"></span>&nbsp;ユーザー名</label>
						</th>
						<td class="text-left">
							<input class="form-control" type="text" name="userName"  size="20"  value="<%= userName %>"  maxlength="30" pattern="\S|\S.*?\S" required/>
						</td>
					</tr>

					<tr><!-- パスワード -->
						<th nowrap class="color-main text-left" >
							<label for="pass"> <span class="icon-note pe-2x pe-va"></span>&nbsp;パスワード</label>
						</th>
						<td class="text-left">
							<input class="form-control" type="text" name="password" size="20" value="<%=password %>" maxlength="10" required />
						</td>
					</tr>

					<tr><!-- アイコン選択 -->
						<th nowrap class="color-main text-left" ><span class="icon-joy pe-2x pe-va"></span>&nbsp;アイコン</th>
						<td class="text-left"  id =parent>
							<div class ="child">
								<span class="icon-smile pe-2x pe-va" > </span>
								<label class="fancy-radio">
									<input type="radio" name="icon" id="icon" value="icon-smile pe-2x pe-va"   <%= checkSmile %>  ><span></span>
								</label>
							</div>
							<div class ="child">
								<span class="icon-joy pe-2x pe-va"> </span>
								<label class="fancy-radio">
									<input type="radio" name="icon" id="icon" value="icon-joy pe-2x pe-va"  <%=checkJoy %> ><span></span>
								</label>
							</div>
						</td>
					</tr>

					<tr><!-- プロフィール -->
						<th nowrap class="color-main text-left" >
							<label for="profile"> <span class="icon-note pe-2x pe-va"></span>&nbsp;プロフィール</label>
						</th>
						<td class="text-left">
							<input class="form-control" type="text" name="profile"  size="20"  value="<%= profile %>" maxlength="100"  required/>
						</td>
					</tr>

					<tr><!-- 登録＆戻るボタン -->
						<td colspan="2" class="text-center">
							<button class="btn" type="submit" value="update" formaction="./uei" name="update">更新</button>
					 		<a href="UserSearchResult.jsp" class ="btn">戻る</a>
					 	</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>