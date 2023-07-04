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

		HttpSession session = request.getSession();	//セッション開始

		// DataManager オブジェクトを生成。
		DBManager dbm = new DBManager();

		//ifで入力していないとき全件検索のメソッド、elseで条件に応じて検索するメソッド
		if(loginId=="" && userName=="" && icon==null && profile=="") {
			ArrayList<UserDTO> user = dbm.searchall();
			// セッションに保存
			session.setAttribute("user", user);
		}
		else {
			ArrayList<UserDTO> user = dbm.search(loginId, userName, icon, profile);
			if(user.size() == 0)	//userの長さがゼロ(検索結果がなかったら)
			{
				message = "検索結果に一致するユーザはいません";
				// エラーメッセージをリクエストオブジェクトに保存
				request.setAttribute("alert", message);
			}
			else {
				// セッションに保存
				session.setAttribute("user", user);
			}
		}

		// UserInfoResult.jsp に処理を転送
		RequestDispatcher dispatcher = request.getRequestDispatcher("UserSearchResult.jsp");
		dispatcher.forward(request, response);
	}
}
