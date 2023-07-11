package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DBManager;
import dto.UserDTO;

/**
 * Servlet implementation class UserEditInputSVT
 */
@WebServlet("/uei")
public class UserEditInputSVT extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//文字化け対策
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html:charset=UTF-8");
		//検索条件の受け取り
		String loginId = request.getParameter("loginId");
		String userName = request.getParameter("userName");
		String icon_user_female = request.getParameter("icon-user-female");
		String icon_user = request.getParameter("icon-user");
		String icon_bell = request.getParameter("icon-bell");
		String icon_smile = request.getParameter("icon-smile");
		String profile = request.getParameter("profile");
		//編集内容の受け取り
		String e_UserName = request.getParameter("e_UserName");
		String e_Password = request.getParameter("e_Password");
		String e_Icon = request.getParameter("e_Icon");
		String e_Profile = request.getParameter("e_Profile");
		String turnBack = request.getParameter("return");
		String edit = request.getParameter("edit");
		//検索条件を遷移先のファイルに送るための処理
		request.setAttribute("loginId", loginId);
		request.setAttribute("userName", userName);
		request.setAttribute("icon-user-female", icon_user_female);
		request.setAttribute("icon-user", icon_user);
		request.setAttribute("icon-bell", icon_bell);
		request.setAttribute("icon-smile", icon_smile);
		request.setAttribute("profile", profile);
		//編集内容を遷移先のファイルに送るための処理
		request.setAttribute("e_UserName", e_UserName);
		request.setAttribute("e_Password", e_Password);
		request.setAttribute("e_Icon", e_Icon);
		request.setAttribute("e_Profile", e_Profile);
		request.setAttribute("edit", edit);
		DBManager db = new DBManager();
		RequestDispatcher dispatcher = null;
		//検索条件に該当するユーザー情報の一覧を取得
		ArrayList<UserDTO> list = db.getUserList(loginId, userName, icon_user_female, icon_user, icon_bell,
				icon_smile, profile);
		//検索結果を遷移先に送るための処理
		UserDTO editUser = list.get(Integer.parseInt(edit));
		request.setAttribute("searchUser", list);

		if (turnBack != null) {//戻るボタンが押された時の処理
			dispatcher = request.getRequestDispatcher("UserSearchResult.jsp");
		} else {//編集ボタンが押された時の処理
			dispatcher = request.getRequestDispatcher("userEditConfirm.jsp");
			if (editUser.getUserName().equals(e_UserName) && editUser.getPassword().equals(e_Password)
					&& editUser.getIcon().equals(e_Icon) && editUser.getProfile().equals(e_Profile)) {//編集事項に変更があるかどうかをチェック
				String msgDuplicate = "変更される項目がありません。";
				request.setAttribute("alertDuplicate", msgDuplicate);
				dispatcher = request.getRequestDispatcher("userEditInput.jsp");
			}
			if (e_Password.contains(" ")) {//パスワード欄にスペースが入っているかどうかの確認
				String messageSpace = "パスワードにはスペースを入力しないでください。";

				request.setAttribute("alertSpace", messageSpace);
				dispatcher = request.getRequestDispatcher("userEditInput.jsp");
			}
			if (e_Password.equals("") || e_UserName.replaceAll("　", " ").trim().equals("")) {
				// 必須項目が未入力なら
				String messageBlank = "未入力項目があります。";

				// エラーメッセージをリクエストオブジェクトに保存
				request.setAttribute("alertBlank", messageBlank);

				// ユーザー情報登録画面 に処理を転送
				dispatcher = request.getRequestDispatcher("userEditInput.jsp");
			}
			if (e_UserName.length() >= 11) {//ユーザー名の文字数制限
				String messageMaxlimit_uName = "ユーザー名は10文字以内で入力してください。";
				request.setAttribute("alertMaxlimit_uName", messageMaxlimit_uName);

				dispatcher = request.getRequestDispatcher("userEditInput.jsp");
			}
			if (e_Password.length() >= 11) {//パスワードの文字数制限
				String messageMaxlimit_password = "パスワードは10文字以内で入力してください。";
				request.setAttribute("alertMaxlimit_password", messageMaxlimit_password);

				dispatcher = request.getRequestDispatcher("userEditInput.jsp");
			}
			if (!e_Password.matches("[0-9a-zA-Z]+")) {//パスワードが半角英数字で入力されているかのチェック
				String msgHalf_width = "パスワードは半角英数字で入力してください。";
				request.setAttribute("alertHalf_width", msgHalf_width);

				dispatcher = request.getRequestDispatcher("userEditInput.jsp");
			}
			if (e_Profile.length() >= 51) {//プロフィールの文字数制限
				String messageMaxlimit_profile = "プロフィールは50文字以内で入力してください。";
				request.setAttribute("alertMaxlimit_profile", messageMaxlimit_profile);

				dispatcher = request.getRequestDispatcher("userEditInput.jsp");
			}
		}
		//編集画面で入力された情報を保持するための処理
		editUser.setUserName(e_UserName);
		editUser.setPassword(e_Password);
		editUser.setIcon(e_Icon);
		editUser.setProfile(e_Profile);
		request.setAttribute("editUser", editUser);
		dispatcher.forward(request, response);

	}

}
