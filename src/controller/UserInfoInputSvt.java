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
@WebServlet("/uis")
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
		//文字化け対策
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		// 送信情報の取得
		//indexで宣言されているloginId、passwordを引っ張てくる
		//replaceAll("　", " ").trim();によって全角スペースを半角に変換しtrimで半角スペースを取り除く
		String loginId = request.getParameter("hidden-loginID").replaceAll("　", " ").trim();
		String UserName = request.getParameter("hidden-username");
		String password = request.getParameter("hidden-pass").replaceAll("　", " ").trim();
		String Icon = request.getParameter("hidden-geticon");
		String Prof = request.getParameter("hidden-profile");

		//初期値の宣言
		RequestDispatcher dispatcher = null;
		String message = null;

		//インスタンス化
		UserDTO user = new UserDTO(loginId, password, UserName, Icon, Prof);//(loginId,password,UserName,Icon,Prof)をUserDTOに与える

		if (user != null) {
			DBManager DBM = new DBManager();//DBmanagerを使えるようインスタンス化を行う
			DBM.IDcheck(loginId);//DBmanagerのsetlogindataを費用するときにuserを与える
			boolean result = DBM.IDcheck(loginId);

			if (result == false) {
				message = "ログインIDが使用されています";
				request.setAttribute("alert", message);
				// 処理の転送先を UserInfoInput.jsp に指定
				dispatcher = request.getRequestDispatcher("UserInfoInput.jsp");
				dispatcher.forward(request, response);
			}
			//未入力箇所がある場合のエラー
			else if (loginId.equals("") || password.equals("") || UserName.equals("") || Prof.equals("") || Icon.equals("")) {
				//未入力箇所がある場合
				message = "未入力箇所があります";

				// エラーメッセージをリクエストオブジェクトに保存
				request.setAttribute("alert", message);

				// userinfoInput.jsp に処理を転送
				dispatcher = request.getRequestDispatcher("UserInfoInput.jsp");
				dispatcher.forward(request, response);
			}
			//全角文字の場合のエラー//
			else if (loginId.matches("[^\\x01-\\x7E]+") || password.matches("[^\\x01-\\x7E]+")) {
				//全角文字
				message = "ログインID、パスワードのいずれかに全角文字含まれています";

				// エラーメッセージをリクエストオブジェクトに保存
				request.setAttribute("alert", message);

				// userinfoInput.jsp に処理を転送
				dispatcher = request.getRequestDispatcher("UserInfoInput.jsp");
				dispatcher.forward(request, response);
			}
			//スペースがどこかに含まれていた場合のエラー
			else if (!loginId.matches("[a-z0-9A-Z]+") || !password.matches("[a-z0-9A-Z]+")) {
				message = "ログインID、パスワードのいずれかにスペース含まれています";

				// エラーメッセージをリクエストオブジェクトに保存
				request.setAttribute("alert", message);

				// userinfoInput.jsp に処理を転送
				dispatcher = request.getRequestDispatcher("UserInfoInput.jsp");
				dispatcher.forward(request, response);
			}
			// ログイン認証を行い、ユーザ情報を取得
			//インスタンス化

			//Userがnull出ない場合その値をセットする
			if (user != null) {
				request.setAttribute("user", user);

				// 処理の転送先を UserInfoConfirm.jsp に指定
				dispatcher = request.getRequestDispatcher("UserInfoConfirm.jsp");
			}

			// 処理を転送
			dispatcher.forward(request, response);
		}
	}
}
