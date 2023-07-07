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
		String loginId = request.getParameter("loginId");
		String userName = request.getParameter("userName");
		String icon_user_female = request.getParameter("icon-user-female");
		String icon_user = request.getParameter("icon-user");
		String icon_bell = request.getParameter("icon-bell");
		String icon_smile = request.getParameter("icon-smile");
		String profile = request.getParameter("profile");
		String e_LoginId = request.getParameter("e_LoginId");
		String e_UserName = request.getParameter("e_UserName");
		String e_Password = request.getParameter("e_Password");
		String e_Icon = request.getParameter("e_Icon");
		String e_Profile = request.getParameter("e_Profie");
		String edit = request.getParameter("edit");
		request.setAttribute("loginId", loginId);
		request.setAttribute("userName", userName);
		request.setAttribute("icon-user-female", icon_user_female);
		request.setAttribute("icon-user", icon_user);
		request.setAttribute("icon-bell", icon_bell);
		request.setAttribute("icon-smile", icon_smile);
		request.setAttribute("profile", profile);
		request.setAttribute("e_LoginId", e_LoginId);
		request.setAttribute("e_UserName", e_UserName);
		request.setAttribute("e_Password", e_Password);
		request.setAttribute("e_Icon", e_Icon);
		request.setAttribute("e_Profiel", e_Profile);

		String messageSpace = "";
		String messageBlank = "";
		String messageHalf_width = "";
		String messageDuplication = "";
		String messageMaxlimit_ID = "";
		String messageMaxlimit_uName = "";
		String messageMaxlimit_password = "";
		String messageMaxlimit_profile = "";

		DBManager db = new DBManager();
		RequestDispatcher dispatcher = null;

		if (edit != null) {
			if (e_Password.contains(" ")) {//パスワード欄にスペースが入っているかどうかの確認
				messageSpace = "パスワードにはスペースを入力しないでください。";

				request.setAttribute("alertSpace", messageSpace);
				dispatcher = request.getRequestDispatcher("UserEditInput.jsp");
			}
			if (e_LoginId.equals("") || e_Password.equals("") || e_UserName.replaceAll("　", " ").trim().equals("")) {
				// 必須項目が未入力なら
				messageBlank = "未入力項目があります。";

				// エラーメッセージをリクエストオブジェクトに保存
				request.setAttribute("alertBlank", messageBlank);

				// ユーザー情報登録画面 に処理を転送
				dispatcher = request.getRequestDispatcher("UserEditInput.jsp");
			}
			if (!e_LoginId.matches("[0-9a-zA-Z]+")) {//ログインIDが半角英数字で入力されているか
				messageHalf_width = "ログインIDは半角英数字で入力してください。";
				request.setAttribute("alertHalf_width", messageHalf_width);

				dispatcher = request.getRequestDispatcher("UserEditInput.jsp");
			}
			if (db.searchDB(e_LoginId)) {//入力されたログインIDに重複がないかどうかの確認
				messageDuplication = "入力されたログインIDは既に使用されています。";
				request.setAttribute("alertDuplication", messageDuplication);

				dispatcher = request.getRequestDispatcher("UserEditInput.jsp");
			}
			if (e_LoginId.length() >= 11) {
				messageMaxlimit_ID = "ログインIDは10文字以内で入力してください。";
				request.setAttribute("alertMaxlimit_ID", messageMaxlimit_ID);

				dispatcher = request.getRequestDispatcher("UserEditInput.jsp");
			}
			if (e_UserName.length() >= 11) {
				messageMaxlimit_uName = "ユーザー名は10文字以内で入力してください。";
				request.setAttribute("alertMaxlimit_uName", messageMaxlimit_uName);

				dispatcher = request.getRequestDispatcher("UserEditInput.jsp");
			}
			if (e_Password.length() >= 11) {
				messageMaxlimit_password = "パスワードは10文字以内で入力してください。";
				request.setAttribute("alertMaxlimit_password", messageMaxlimit_password);

				dispatcher = request.getRequestDispatcher("UserEditInput.jsp");
			}
			if (e_Profile.length() >= 51) {
				messageMaxlimit_profile = "プロフィールは50文字以内で入力してください。";
				request.setAttribute("alertMaxlimit_profile", messageMaxlimit_profile);

				dispatcher = request.getRequestDispatcher("UserEditInput.jsp");
			}
			if (messageSpace.equals("") && messageBlank.equals("") && messageHalf_width.equals("")
					&& messageDuplication.equals("") && messageMaxlimit_ID.equals("") && messageMaxlimit_uName.equals("")
					&& messageMaxlimit_password.equals("") && messageMaxlimit_profile.equals("")) {
				dispatcher = request.getRequestDispatcher("UserEditConfirm.jsp");
			}

		}else {
			ArrayList<UserDTO> list = db.getUserList(loginId, userName, icon_user_female, icon_user, icon_bell, icon_smile, profile);
			request.setAttribute("searchUser", list);
			dispatcher = request.getRequestDispatcher("UserSearchResult.jsp");
		}
		dispatcher.forward(request, response);

	}

}
