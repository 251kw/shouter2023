package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DBManager;

/**
 * Servlet implementation class UserDeleteResult
 */
@WebServlet("/udr")
public class UserDeleteResult extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserDeleteResult() {
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
		//文字化け対策
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		RequestDispatcher dispatcher = null;
		boolean result = false;

		//udcサーブレットでセッションに保存しておいたログインIDのリストを受け取る
		HttpSession session = request.getSession();
		String[] userlist = (String[])session.getAttribute("keepLogId");

		if (userlist != null) {
			//loginIdを引数にdelete文を実行する「DeleteUserメソッド」を呼び出し。ログインIdのリストの長さ分繰り返す。
			DBManager dbm = new DBManager();
			for(int i= 0; i<userlist.length; i++){
				String loginId = userlist[i];
				result = dbm.DeleteUser(loginId);
			}

			if(result == true) {
				//UserDeleteResult.jsp に処理を転送
				dispatcher = request.getRequestDispatcher("UserDeleteResult.jsp");
				dispatcher.forward(request, response);
			}
		}
	}

}
