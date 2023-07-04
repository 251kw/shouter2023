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
import dto.ShoutDTO;
import dto.UserDTO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
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
	//フォームから送られてきた情報をdoPostメソッドで受け取る
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//文字化け対策
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		// フォームから送信された情報の取得
		String loginId = request.getParameter("loginId"); //name属性値の値を指定しgetParameterで値の取得
		String password = request.getParameter("password");

		//フォワードの準備
		RequestDispatcher dispatcher = null;
		//alertメッセージの変数を準備
		String message = null;

		//未入力の場合
		if (loginId.equals("") || password.equals("")) {
			// ログインID かパスワードどちらか、もしくは両方とも空白の場合
			message = "ログインIDとパスワードは必須入力です";

			// エラーメッセージをリクエストオブジェクトに保存
			request.setAttribute("alert", message); //alertという属性値でmessageを格納

			// index.jsp にメッセージを転送　画面遷移
			//フォワードなので処理終了後に呼び出し元に戻らない
			dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);

		//IDもパスワードも入力されている場合　ログイン認証開始
		} else {
			// ログイン認証を行い、ユーザー情報を取得
			DBManager dbm = new DBManager();
			//DBManagerクラスのgetLoginUserメソッドで存在するユーザーが認証を行う
			UserDTO user = dbm.getLoginUser(loginId, password);

			if (user != null) { //ユーザー情報が存在した場合
				// ユーザー情報を取得できたら、書き込み内容リストを取得
				ArrayList<ShoutDTO> list = dbm.getShoutList(); //dbm = DBManagerクラスのこと
				//セッションの準備
				HttpSession session = request.getSession();

				// ログインユーザー情報、書き込み内容リストをセッションに保存
				session.setAttribute("user", user);
				session.setAttribute("shouts", list);

				// 処理の転送先をtop.jspに指定
				dispatcher = request.getRequestDispatcher("top.jsp");

			} else {//returnされたuserがnullの場合=ユーザー情報が存在になかった場合
				// ユーザー情報が取得できない場合
				// エラーメッセージをリクエストオブジェクトに保存
				message = "ログインIDまたはパスワードが違います";
				//index.jspで表示させる
				request.setAttribute("alert", message);

				// 処理の転送先をindex.jspに指定
				dispatcher = request.getRequestDispatcher("index.jsp");
			}

			// ログインできても出来なくてもgetRequestDispatcherで指定した.jspファイルにフォワード
			dispatcher.forward(request, response);
		}

	}

}
