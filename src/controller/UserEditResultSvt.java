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
 * Servlet implementation class UserEditResultSvt
 */
@WebServlet("/uer")
public class UserEditResultSvt extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserEditResultSvt() {
		super();
		// TODO Auto-generated constructor stub
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/****削除、編集から検索結果画面にもどるときの再検索サーブレット！！****/
		// 文字化け対策
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");


		//セッション開始。UserSearchInputSvt.javaでセッションに保存した検索結果を持ってくる
		HttpSession session = request.getSession();
		String loginId = (String)session.getAttribute("saveLoginId");
		String userName = (String)session.getAttribute("saveUserName");
		String icon[] = (String[]) session.getAttribute("saveIcon");
		String profile = (String)session.getAttribute("saveProfile");


		String message = null;

		RequestDispatcher dispatcher = null;

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
		else{
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
