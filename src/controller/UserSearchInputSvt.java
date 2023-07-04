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
 * Servlet implementation class UserSearchInputSvt
 */
@WebServlet("/usi")
public class UserSearchInputSvt extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserSearchInputSvt() {
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
		// TODO Auto-generated method stub
		//文字化け対策
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String loginId = request.getParameter("loginId");
		String userName = request.getParameter("userName");
		String icon1 = request.getParameter("icon1");
		String icon2 = request.getParameter("icon2");
		String profile = request.getParameter("profile");

		RequestDispatcher dispatcher = null;
		DBManager dbm = new DBManager();
		String message = null;
		if ((!dbm.isLetterDigit(loginId))&&(loginId!="")) {

			message = "ログインIDを英数字8桁以下入力してください";

			// エラーメッセージをリクエストオブジェクトに保存
			request.setAttribute("alert", message);
			// signup.jsp に処理を転送
			dispatcher = request.getRequestDispatcher("search.jsp");
			dispatcher.forward(request, response);
		} else if(userName.length()>8)
		{
			message = "ユーザー名を8文字以下入力してください";

			// エラーメッセージをリクエストオブジェクトに保存
			request.setAttribute("alert", message);
			// signup.jsp に処理を転送
			dispatcher = request.getRequestDispatcher("search.jsp");
			dispatcher.forward(request, response);
		}
		else if(profile.length()>200)
		{
			message = "プロファイルを200文字以下入力してください";

			// エラーメッセージをリクエストオブジェクトに保存
			request.setAttribute("alert", message);
			// signup.jsp に処理を転送
			dispatcher = request.getRequestDispatcher("search.jsp");
			dispatcher.forward(request, response);
		}
		else {
		HttpSession session = request.getSession();
		ArrayList<UserDTO> list = dbm.getSearching(loginId,userName,icon1,icon2,profile);

		session.setAttribute("users", list);


		dispatcher = request.getRequestDispatcher("serhresult.jsp");
		dispatcher.forward(request, response);
		}
	}

}
