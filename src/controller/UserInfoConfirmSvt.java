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
import dto.UserDTO;

/**
 * Servlet implementation class UserInfoConfirmSvt
 */
@WebServlet("/uic")
public class UserInfoConfirmSvt extends HttpServlet {
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

		//UserInfoConfirm.jspのhiddenで送られてきた値をパラメータで受け取る。
		String loginId = request.getParameter("loginId");
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String icon = request.getParameter("icon");
		String profile = request.getParameter("profile");

		HttpSession session = request.getSession();	//セッション開始

		// DataManager オブジェクトを生成。
		DBManager dbm = new DBManager();

		// 新規ユーザ情報と書き込み内容を引数に、ユーザー情報をリストに追加するメソッドを呼び出し
		int result = dbm.setNewuser(loginId,  password, userName, icon, profile);

		//setNewuserメソッドの戻り値は、更新された行数が返ってくる。なので、1だったらinsert成功
		if(result == 1) {

		// 新規登録したユーザ情報を取得
		UserDTO user = dbm.getNewuser(loginId);

		// セッションに保存
		session.setAttribute("user", user);

		// UserInfoResult.jsp に処理を転送
		RequestDispatcher dispatcher = request.getRequestDispatcher("UserInfoResult.jsp");
		dispatcher.forward(request, response);
		}
	}
}
