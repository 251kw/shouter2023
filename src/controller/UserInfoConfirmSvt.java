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


@WebServlet("/uic")
public class UserInfoConfirmSvt extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UserInfoConfirmSvt() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//文字化け対策
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		//フォームから送られてきた情報の受け取り
		String loginId = request.getParameter("loginId"); //name属性値の値を指定しgetParameterで値の取得
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String icon = request.getParameter("icon");
		String profile = request.getParameter("profile");

		//フォワードの準備
		RequestDispatcher dispatcher = null;

		// ログイン認証を行い、ユーザー情報を取得
		DBManager dbm = new DBManager();

		//DBManagerクラスのgetLoginUserメソッドで存在するユーザーが認証を行う
		//userオブジェクトにinsertした件数の格納
		dbm.setRegisterInfo(loginId, userName, password, icon, profile);

		//セッションの準備
		HttpSession session = request.getSession();

		//insertした情報を表示させる
		UserDTO showUser = dbm.getShowResult(loginId);

		// ログインユーザー情報、書き込み内容リストとしてセッションに保存
		session.setAttribute("user", showUser); //属性値userで値showUserをrequestスコープに設定し保持させる

		dispatcher = request.getRequestDispatcher("UserInfoResult.jsp"); //UserInforesult.jspに転送
		dispatcher.forward(request, response);
	}

}
