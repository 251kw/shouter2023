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
 * Servlet implementation class UserConfirmSvt
 */
@WebServlet("/uic")
public class UserConfirmSvt extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserConfirmSvt() {
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
		// 送信情報の取得
		String loginId = request.getParameter("id");
		String password = request.getParameter("pass");
		String name = request.getParameter("name");
		String pro = request.getParameter("pro");
		String icon = request.getParameter("icon");

		RequestDispatcher dispatcher = null;

		// ログイン認証を行い、ユーザ情報を取得
		DBManager dbm = new DBManager();
		//String result=dbm.loginId(loginId);

/*		int n = -1;//絶対にならない値を適当にセット
        n=password.indexOf(" ");

		if(result.equals("true")) {
			String message="ログインIDが存在します";
			// エラーメッセージをリクエストオブジェクトに保存
			request.setAttribute("alert", message);
			error++;
		}

		//if(pass.replaceAll("　", " ").trim().isEmpty()){
        if(n!=-1) {
			// スペースがある場合エラー
			String message2="ユーザIDとPASSにはスペースは使用できません";
			// エラーメッセージをリクエストオブジェクトに保存
			request.setAttribute("alert2", message2);
			error++;
		}
        if(error>0) {
        	dispatcher = request.getRequestDispatcher("UserInfoInput.jsp");
			dispatcher.forward(request, response);
        }*/

		UserDTO user = new UserDTO(loginId,password,name,icon,pro);
		dbm.setNewUser(user);


			/*if (user != null) {
				// ユーザ情報を取得できたら、書き込み内容リストを取得
				//ArrayList<ShoutDTO> list = dbm.getShoutList();
				HttpSession session = request.getSession();

				// ログインユーザ情報、書き込み内容リストとしてセッションに保存
				session.setAttribute("user", user);
				//session.setAttribute("shouts", list);

				// 処理の転送先を top.jsp に指定
				//dispatcher = request.getRequestDispatcher("UserInfoResult.jsp");

			// 処理を転送
			//dispatcher.forward(request, response);
		}*/
			dispatcher = request.getRequestDispatcher("UserInfoResult.jsp");
			dispatcher.forward(request, response);
	}

}
