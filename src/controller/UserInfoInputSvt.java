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
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	// index.jsp の「新規登録」ボタンから呼び出される
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String loginId = request.getParameter("loginId");
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String icon = request.getParameter("icon");
		String profile = request.getParameter("profile");
		RequestDispatcher dispatcher = null;

		String message1 = null;
		String message2 = null;
		String message3 = null;
		String message4 = null;
		String message5 = null;
		String message6 = null;

		// 未入力の項目があれば
		if (loginId.equals("") || userName.equals("") || password.equals("") || profile.equals("")) {
			message1 = "すべて必須項目です";
			// エラーメッセージをリクエストオブジェクトに保存
			request.setAttribute("alert1", message1);
			// UserInfoInput.jsp に処理を転送（もう一度入力させる）
			dispatcher = request.getRequestDispatcher("UserInfoInput.jsp");
		}

		// 半角スペースがあれば
		if (loginId.contains(" ")|| userName.contains(" ") || password.contains(" ")) {
			message2 = "半角スペースは使えません";
			// エラーメッセージをリクエストオブジェクトに保存
			request.setAttribute("alert2", message2);
			// UserInfoInput.jsp に処理を転送（もう一度入力させる）
			dispatcher = request.getRequestDispatcher("UserInfoInput.jsp");
		}

		// ユーザ名の姓名の間以外に全角スペースがあれば
		if (loginId.contains("　") || password.contains("　")
			/*|| !userName.equals("") && userName.substring(0,1).equals("　")*/) {
			message3 = "ユーザ名の姓名の間以外に全角スペースは使えません";
			// エラーメッセージをリクエストオブジェクトに保存
			request.setAttribute("alert3", message3);
			// UserInfoInput.jsp に処理を転送（もう一度入力させる）
			dispatcher = request.getRequestDispatcher("UserInfoInput.jsp");
		}

		//以上の条件式を全部否定。それ以外のとき。
		if (! (loginId.equals("") || userName.equals("") || password.equals("") /*|| icon==null*/ || profile.equals("")
			   || loginId.contains(" ")|| userName.contains(" ")|| password.contains(" ")
			   || loginId.contains("　")|| password.contains("　") /*|| userName.substring(0,1).equals("　")*/)
		   ) {

			DBManager dbm = new DBManager();
			boolean ID = dbm.searchLoginId(loginId);

			boolean result = isHanStr(loginId);
			boolean result2 = isHanStr(password);

			//検索結果があった場合trueが返ってくる（重複している）ので、
			if(ID == true) {
				message5 = "このログインIDはすでに使われています";
				// エラーメッセージをリクエストオブジェクトに保存
				request.setAttribute("alert5", message5);
				// UserInfoInput.jsp に処理を転送（もう一度入力させる）
				dispatcher = request.getRequestDispatcher("UserInfoInput.jsp");
			}
			else if (result == false) {
				message4 = "ログインIDはすべて半角で入力してください";
				// エラーメッセージをリクエストオブジェクトに保存
				request.setAttribute("alert4", message4);
				// UserInfoInput.jsp に処理を転送（もう一度入力させる）
				dispatcher = request.getRequestDispatcher("UserInfoInput.jsp");
			}
			else if (result2 == false) {
				message6 = "パスワードはすべて半角で入力してください";
				// エラーメッセージをリクエストオブジェクトに保存
				request.setAttribute("alert6", message6);
				// UserInfoInput.jsp に処理を転送（もう一度入力させる）
				dispatcher = request.getRequestDispatcher("UserInfoInput.jsp");
			}
			else {
				//UserDTO　インスタンス化
				UserDTO user = new UserDTO(loginId, password, userName, icon, profile);
				// ユーザ情報をリクエストに保存
				request.setAttribute("user", user);
				// 問題なければ、処理の転送先を UserInfoConfirm.jsp に指定。確認画面表示される。
				dispatcher = request.getRequestDispatcher("UserInfoConfirm.jsp");
			}
		}
		//処理転送
		dispatcher.forward(request, response);
	}
}
