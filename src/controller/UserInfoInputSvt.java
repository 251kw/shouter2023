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
 * Servlet implementation class UserInfoInputSvt
 */
@WebServlet("/uii")
public class UserInfoInputSvt extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserInfoInputSvt() {
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
		// 文字化け対策
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		RequestDispatcher dispatcher = null;

		// 送信情報の取得
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");
		String icon = request.getParameter("icon");
		String pro = request.getParameter("pro");

		UserDTO user = new UserDTO(id, pass, name, icon, pro);
		request.setAttribute("userA", user);

		DBManager dbm = new DBManager();
		String result=dbm.loginId(id);

		int n = -1;//絶対にならない値を適当にセット
        n=pass.indexOf(" ");
        int error=0;
		if(result.equals("true")) {
			String message="ログインIDが存在します";
			// エラーメッセージをリクエストオブジェクトに保存
			request.setAttribute("alert", message);
			error++;
		}

		//if(pass.replaceAll("　", " ").trim().isEmpty()){
        if(n!=-1) {
			// スペースがある場合エラー
			String message2="ユーザIDとパスワードにはスペースは使用できません";
			// エラーメッセージをリクエストオブジェクトに保存
			request.setAttribute("alert2", message2);
			error++;
		}
        if(error>0) {
        	dispatcher = request.getRequestDispatcher("UserInfoInput.jsp");
			dispatcher.forward(request, response);
        }

		//UserInfoConfirm.jsp に処理を転送
		dispatcher = request.getRequestDispatcher("/UserInfoConfirm.jsp");
		dispatcher.forward(request, response);
	}

}
