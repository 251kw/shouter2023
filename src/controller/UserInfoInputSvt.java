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
 * Servlet implementation class LoginServlet
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
		RequestDispatcher dispatcher = request.getRequestDispatcher("signupid.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */

	// index.jspの「ログイン」ボタンから呼び出される
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//文字化け対策
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		// 送信情報の取得
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");
		String userName = request.getParameter("userName");
		String icon = request.getParameter("icon");
		String profile = request.getParameter("profile");

		RequestDispatcher dispatcher = null;
		String message = null;

		if (loginId.equals("") || password.equals("") || userName.equals("")) {
			//ログインID かパスワードどちらか、もしくは双方未入力なら
			message = "全ての情報項目は必須入力です";

			// エラーメッセージをリクエストオブジェクトに保存
			request.setAttribute("alert", message);
			// signup.jsp に処理を転送
			dispatcher = request.getRequestDispatcher("signupid.jsp");
			dispatcher.forward(request, response);
		} else {
			//ユーザー情報を作成
			DBManager dbm = new DBManager();
			if (!dbm.isLetterDigit(loginId)) {
				message = "ログインIDを英数字8桁以下入力してください";
				// エラーメッセージをリクエストオブジェクトに保存
				request.setAttribute("alert", message);
				// signup.jsp に処理を転送
				dispatcher = request.getRequestDispatcher("signupid.jsp");
			} else if (!dbm.isLetterDigit(password)) {
				message = "パスワードを英数字8桁以下入力してください";
				// エラーメッセージをリクエストオブジェクトに保存
				request.setAttribute("alert", message);
				// signup.jsp に処理を転送
				dispatcher = request.getRequestDispatcher("signupid.jsp");
			} else if (userName.length() > 8) {
				message = "ユーザー名を8文字以下入力してください";
				// エラーメッセージをリクエストオブジェクトに保存
				request.setAttribute("alert", message);
				// signup.jsp に処理を転送
				dispatcher = request.getRequestDispatcher("signupid.jsp");
			} else if (profile.length() > 200) {
				message = "プロファイルを200文字以下入力してください";
				// エラーメッセージをリクエストオブジェクトに保存
				request.setAttribute("alert", message);
				// signup.jsp に処理を転送
				dispatcher = request.getRequestDispatcher("signupid.jsp");
			} else {
				UserDTO signupID = new UserDTO(loginId, password, userName, icon, profile);
				request.setAttribute("signupID", signupID);
				dispatcher = request.getRequestDispatcher("confirm.jsp");
			}
			dispatcher.forward(request, response);
		}
	}
}
