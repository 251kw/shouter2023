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
 * Servlet implementation class UserInfoConfirm
 */
@WebServlet("/uic")
public class UserInfoConfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserInfoConfirm() {
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
		/*hiddenで送られたデータの取得*/
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");
		String userName = request.getParameter("userName");
		String icon = request.getParameter("icon");
		String profile = request.getParameter("profile");
		String OK = request.getParameter("OK");
		String cancel = request.getParameter("cancel");
		RequestDispatcher dispatcher = null;

		if (OK != null) {//OKボタンが押された時の処理
			UserDTO newUser = new UserDTO(loginId, password, userName, icon, profile);//引数として渡すためのUserDTOのインスタンス化
			DBManager db = new DBManager();
			db.setUserInformation(newUser);//データベースに登録するsetUserInformationメソッドの呼び出し
			request.setAttribute("newUser", newUser);

			dispatcher = request.getRequestDispatcher("UserInfoResult.jsp");
		} else if (cancel != null) {//cancelボタンが押された時の処理
			dispatcher = request.getRequestDispatcher("UserInfoInput.jsp");
		}
		dispatcher.forward(request, response);
	}
}
