package practiceF;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DBManager;

/**
 * Servlet implementation class UserInfoConfirmSvt
 */
@WebServlet("/uic")
public class UserInfoConfirmSvt extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBManager dbm;

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
		//文字化け対策
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String loginId = request.getParameter("loginId");
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String icon = request.getParameter("icon");
		String profile = request.getParameter("profile");

		User user = new User(loginId, password, userName, icon, profile);

		request.setAttribute("User", user);

		// １度だけ DataManager オブジェクトを生成
		if (dbm == null) {
			dbm = new DBManager();
		}

		// ログインユーザ情報と書き込み内容を引数に、リストに追加するメソッドを呼び出し
		dbm.userRegister(loginId, password, userName, icon, profile);

		ServletContext sc = getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher("/UserInfoResult.jsp");
		rd.forward(request, response);
	}
}
