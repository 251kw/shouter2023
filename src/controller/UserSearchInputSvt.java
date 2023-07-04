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
@WebServlet("/uss")
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

		//初期値の宣言
		RequestDispatcher dispatcher = null;

		// 送信情報の取得
		//indexで宣言されているloginId、passwordを引っ張てくる
		String loginId = request.getParameter("LoginId");
		String username = request.getParameter("Username");
		String icon = request.getParameter("Icon");
		String icon1 = request.getParameter("Iconn");
		String profile = request.getParameter("Prof");

			DBManager dbm1 = new DBManager();
			ArrayList<UserDTO> Serach1 = dbm1.Search(loginId, username, icon,icon1, profile);
			//Userがnull出ない場合その値をセットする
			if (Serach1 != null) {
				HttpSession session = request.getSession();
				session.setAttribute("user",Serach1);

				// 処理の転送先を UserInfoConfirm.jsp に指定
				dispatcher = request.getRequestDispatcher("UserSearchResult.jsp");
			}

			// 処理を転送
			dispatcher.forward(request, response);
		}
	}
