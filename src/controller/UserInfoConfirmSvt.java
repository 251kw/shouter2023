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
 * Servlet implementation class UserInfoConfirmSvt
 */
//UserInfoInput.jspから飛んでくる
@WebServlet("/ucs")
public class UserInfoConfirmSvt extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserInfoConfirmSvt() {
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

		//dispatcherの初期化
		RequestDispatcher dispatcher = null;

		//UserInfoInput.jspから飛んできたデータを取得し、string型の変数を宣言して代入していく
		String loginId = request.getParameter("hidden-loginID");
		String password = request.getParameter("hidden-pass");
		String UserName = request.getParameter("hidden-username");
		String Icon = request.getParameter("hidden-geticon");
		String Prof = request.getParameter("hidden-profile");
		//インスタンス化
		UserDTO user = new UserDTO(loginId, password, UserName, Icon, Prof);//(loginId,password,UserName,Icon,Prof)をUserDTOに与える

		if (user != null) {
			DBManager DBM = new DBManager();//DBmanagerを使えるようインスタンス化を行う
			DBM.setlogindata(user);//DBmanagerのsetlogindataを費用するときにuserを与える
			request.setAttribute("user", user);
			// 処理の転送先を UserInfoConfirm.jsp に指定
			dispatcher = request.getRequestDispatcher("UserResult.jsp");
		}

		// 処理を転送
		dispatcher.forward(request, response);
	}

}
