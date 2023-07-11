<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Shouter - ユーザー情報登録 -</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
<link rel="stylesheet" href="./css/style.css">
</head>
<body>
	<%//戻るボタンが押された時にログインID、ユーザー名、プロフィールの情報が保持されるための処理
		String loginId = "";
		String userName = "";
		String profile = "";
		if(request.getAttribute("loginId") != null){
			loginId = (String)request.getAttribute("loginId");
		}
		if(request.getAttribute("userName")!=null){
			userName = (String)request.getAttribute("userName");
		}
		if(request.getAttribute("profile")!=null){
			profile = (String)request.getAttribute("profile");
		}
	%>
	<%//検索結果画面から戻るボタンが押された時に検索条件のアイコンが保持されるための処理
		String iconSmile ="";
		String iconBell="";
		String iconUser="";
		String iconUserFemale="";
		String icon_user = (String)request.getAttribute("icon-user");
		String icon_user_female= (String)request.getAttribute("icon-user-female");
		String icon_bell = (String)request.getAttribute("icon-bell");
		String icon_smile = (String)request.getAttribute("icon-smile");
		if(icon_user!=null  && !(icon_user.equals("null"))){
			iconUser = "checked";
		}
		if(icon_user_female!=null && !(icon_user_female.equals("null"))){
			iconUserFemale = "checked";
		}
		if(icon_smile!=null && !(icon_smile.equals("null"))){
			iconSmile = "checked";
		}
		if(icon_bell!=null && !(icon_bell.equals("null"))){
			iconBell = "checked";
		}
	%>
	<div class="bg-success padding-y-5">
		<div class="container padding-y-5 text-center">
			<h1>ユーザー検索入力画面</h1>
		</div>
	</div>
	<%-- action 属性にサーブレットを指定 --%>
	<form action="./usi" method="get">
		<table style="width: 70%" class="table">
			<tr>
				<%-- ログインID 入力欄の名前は loginId --%>
				<td class="color-main text-left"><span
					class="icon-smile pe-2x pe-va"></span>ログインID</td>
				<td class="text-left"><input class="form-control" type="text"
					name="loginId" value="<%=loginId %>" size="20" autofocus /></td>
			</tr>
			<tr>
				<%-- ユーザー名 入力欄の名前は userName --%>
				<td class="color-main text-left"><span
					class="icon-smile pe-2x pe-va"></span>ユーザー名</td>
				<td class="text-left"><input class="form-control" type="text"
					name="userName" value="<%=userName %>" size="20" /></td>
			</tr>
			<tr>
				<%-- ログインID 入力欄の名前は loginId --%>
				<td class="color-main text-left"><span
					class="icon-smile pe-2x pe-va"></span>アイコン</td>
				<td class="text-left">
					<div style="display: flex;">
						<span class="icon-user-female pe-2x pe-va"></span><label
							class="fancy-checkbox"> <input type="checkbox"
							name="icon-user-female" value="icon-user-female" <%=iconUserFemale %>> <span></span></label>
						<span class="icon-bell pe-2x pe-va"></span><label
							class="fancy-checkbox"> <input type="checkbox"
							name="icon-bell" value="icon-bell" <%=iconBell %>> <span></span></label> <span
							class="icon-user pe-2x pe-va"></span><label
							class="fancy-checkbox"> <input type="checkbox"
							name="icon-user" value="icon-user" <%=iconUser %>> <span></span></label> <span
							class="icon-smile pe-2x pe-va"></span><label
							class="fancy-checkbox"> <input type="checkbox"
							name="icon-smile" value="icon-smile" <%=iconSmile %>> <span></span></label>
					</div>
				</td>
			</tr>
			<tr>
				<td class="color-main text-left"><span
					class="icon-smile pe-2x pe-va"></span>プロフィール</td>
				<td class="text-left"><input class="form-control" type="text"
					name="profile" value="<%=profile %>" size="20" />
			</tr>
			<tr>
				<!--<c:if
					test="${requestScope.alert != null && requestScope.alert != ''}">
					<tr>
						<%-- リクエストスコープの alert の値を出力 --%>
						<td colspan="2" class="color-error text-left"><c:out
								value="${requestScope.alert}" /></td>
					</tr>
				</c:if>-->
			</tr>
		</table>
		<div class="text-center">
			<input class="btn" type="submit" name="search" value="検索" /> <input
				class="btn" type="submit" name="return" value="戻る" />
		</div>
	</form>
</body>
</html>