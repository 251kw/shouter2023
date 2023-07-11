package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DBManager;
import dto.UserDTO;

/**
 * Servlet implementation class UserInfoInputSvt
 */
@WebServlet("/uii")
public class UserInfoInputSvt extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserInfoInputSvt() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html:charset=UTF-8");
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");
		String userName = request.getParameter("userName");
		String icon = request.getParameter("icon");
		String profile = request.getParameter("profile");
		String turnBack = request.getParameter("return");
		String register = request.getParameter("register");
		DBManager dbm = new DBManager();

		RequestDispatcher dispatcher = null;
		String messageSpace = "";
		String messageBlank = "";
		String messageHalf_width = "";
		String messageDuplication = "";
		String messageMaxlimit_ID = "";
		String messageMaxlimit_uName = "";
		String messageMaxlimit_password = "";
		String messageMaxlimit_profile = "";
		if (register != null) {
			if (password.contains(" ")) {//パスワード欄にスペースが入っているかどうかの確認
				messageSpace = "パスワードにはスペースを入力しないでください。";

				request.setAttribute("alertSpace", messageSpace);
				dispatcher = request.getRequestDispatcher("UserInfoInput.jsp");
			}
			if (loginId.equals("") || password.equals("") || userName.replaceAll("　", " ").trim().equals("")) {
				// 必須項目が未入力なら
				messageBlank = "未入力項目があります。";

				// エラーメッセージをリクエストオブジェクトに保存
				request.setAttribute("alertBlank", messageBlank);

				// ユーザー情報登録画面 に処理を転送
				dispatcher = request.getRequestDispatcher("UserInfoInput.jsp");
			}
			if (!loginId.matches("[0-9a-zA-Z]+")) {//ログインIDが半角英数字で入力されているか
				messageHalf_width = "ログインIDは半角英数字で入力してください。";
				request.setAttribute("alertHalf_width", messageHalf_width);

				dispatcher = request.getRequestDispatcher("UserInfoInput.jsp");
			}
			if (dbm.searchDB(loginId)) {//入力されたログインIDに重複がないかどうかの確認
				messageDuplication = "入力されたログインIDは既に使用されています。";
				request.setAttribute("alertDuplication", messageDuplication);

				dispatcher = request.getRequestDispatcher("UserInfoInput.jsp");
			}
			if (loginId.length() >= 11) {
				messageMaxlimit_ID = "ログインIDは10文字以内で入力してください。";
				request.setAttribute("alertMaxlimit_ID", messageMaxlimit_ID);

				dispatcher = request.getRequestDispatcher("UserInfoInput.jsp");
			}
			if (userName.length() >= 11) {
				messageMaxlimit_uName = "ユーザー名は10文字以内で入力してください。";
				request.setAttribute("alertMaxlimit_uName", messageMaxlimit_uName);

				dispatcher = request.getRequestDispatcher("UserInfoInput.jsp");
			}
			if (password.length() >= 11) {
				messageMaxlimit_password = "パスワードは10文字以内で入力してください。";
				request.setAttribute("alertMaxlimit_password", messageMaxlimit_password);

				dispatcher = request.getRequestDispatcher("UserInfoInput.jsp");
			}
			if (profile.length() >= 51) {
				messageMaxlimit_profile = "プロフィールは50文字以内で入力してください。";
				request.setAttribute("alertMaxlimit_profile", messageMaxlimit_profile);

				dispatcher = request.getRequestDispatcher("UserInfoInput.jsp");
			}
			if (messageSpace.equals("") && messageBlank.equals("") && messageHalf_width.equals("")
					&& messageDuplication.equals("") && messageMaxlimit_ID.equals("")
					&& messageMaxlimit_uName.equals("")
					&& messageMaxlimit_password.equals("") && messageMaxlimit_profile.equals("")) {//エラーのパターンに当てはまらない場合
				UserDTO newUser = new UserDTO(loginId, password, userName, icon, profile);//インスタンス化+引数ありコンストラクタを呼び出し
				request.setAttribute("newUser", newUser);//UserDTOインスタンスをセット
				dispatcher = request.getRequestDispatcher("UserInfoConfirm.jsp");

			}
		} else if (turnBack != null) {//戻るボタンが押された時
			dispatcher = request.getRequestDispatcher("index.jsp");
		}
		dispatcher.forward(request, response);
	}
}
