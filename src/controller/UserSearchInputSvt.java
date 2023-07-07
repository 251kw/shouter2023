package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DBManager;
import dto.UserDTO;

/**
 * Servlet implementation class UserInfoConfirmSvt
 */
@WebServlet("/usi")
public class UserSearchInputSvt extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 半角英数チェック
	 *
	 * @return true 正常(半角英数のみ)  false エラー(半角英数以外が入ってる)
	 */
	public static boolean isHanStr(String s){
		if (!s.matches("^[0-9a-zA-Z]+$")) {
			return false;
		}
		return true;
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 文字化け対策
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		//UserSearchInput.jspから送られてきた値をパラメータで受け取る。
		String loginId = request.getParameter("loginId");
		String userName = request.getParameter("userName");
		String icon[] = request.getParameterValues("icon");	//2つあるので配列で受け取る
		String profile = request.getParameter("profile");

		String message = null;
		String message2 = null;
		String message3 = null;
		String message4 = null;

		RequestDispatcher dispatcher = null;

		HttpSession session = request.getSession();	//セッション開始

		// DataManager オブジェクトを生成。
		DBManager dbm = new DBManager();

		//入力していないとき全件検索のメソッド、elseで条件に応じて検索するメソッド
		if(loginId.equals("") && userName.equals("") && icon==null && profile.equals("")) {
			ArrayList<UserDTO> user = dbm.searchall();
			// セッションに保存
			session.setAttribute("user2", user);
			dispatcher = request.getRequestDispatcher("UserSearchResult.jsp");
			dispatcher.forward(request, response);
		}
		else if(!loginId.equals("")) {	//入力はされてるけど、
			// 半角スペースがあれば
			if (loginId.contains(" ")|| userName.contains(" ")) {
				message2 = "プロフィール以外に半角スペースは使えません";
				// エラーメッセージをリクエストオブジェクトに保存
				request.setAttribute("alert2", message2);
				// UserInfoInput.jsp に処理を転送（もう一度入力させる）
				dispatcher = request.getRequestDispatcher("UserSearchInput.jsp");
				dispatcher.forward(request, response);
			}
			// ユーザ名の姓名の間以外に全角スペースがあれば
			if (loginId.contains("　")) {
				message3 = "ユーザ名の姓名の間以外に全角スペースは使えません";
				// エラーメッセージをリクエストオブジェクトに保存
				request.setAttribute("alert3", message3);
				// UserInfoInput.jsp に処理を転送（もう一度入力させる）
				dispatcher = request.getRequestDispatcher("UserSearchInput.jsp");
				dispatcher.forward(request, response);
			}
			boolean result = isHanStr(loginId);
			if (result == false) {
				message4 = "ログインIDはすべて半角英字で入力してください";
				// エラーメッセージをリクエストオブジェクトに保存
				request.setAttribute("alert4", message4);
				// UserInfoInput.jsp に処理を転送（もう一度入力させる）
				dispatcher = request.getRequestDispatcher("UserSearchInput.jsp");
				dispatcher.forward(request, response);
			}
		}
		if(!(loginId.equals("") && userName.equals("") && icon==null && profile.equals(""))) {
			ArrayList<UserDTO> user = dbm.search(loginId, userName, icon, profile);
			if(user.size() == 0) {	//userの長さがゼロ(検索結果がなかったら)
				message = "検索結果に一致するユーザはいません";
				// エラーメッセージをリクエストオブジェクトに保存
				request.setAttribute("alert", message);
				dispatcher = request.getRequestDispatcher("UserSearchResult.jsp");
			}
			else {
				// セッションに保存
				session.setAttribute("user2", user);
				dispatcher = request.getRequestDispatcher("UserSearchResult.jsp");
			}
			dispatcher.forward(request, response);
		}
	}
}
