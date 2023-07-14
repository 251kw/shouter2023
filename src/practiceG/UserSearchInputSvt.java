package practiceG;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
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
	private DBManager dbm;

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
		//文字化け対策
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String sql = null;

		String loginId = request.getParameter("loginId");
		String userName = request.getParameter("userName");
		String icon1 = request.getParameter("icon1");
		String icon2 = request.getParameter("icon2");
		String profile = request.getParameter("profile");

		if (dbm == null) {
			dbm = new DBManager();
		}

		ArrayList<UserDTO> list = dbm.getUserList(loginId, userName, icon1, icon2, profile);

		HttpSession session = request.getSession();

		session.setAttribute("loginId", loginId);
		session.setAttribute("userName", userName);
		session.setAttribute("icon1", icon1);
		session.setAttribute("icon2", icon2);
		session.setAttribute("profile", profile);

		// リストをセッションに保存
		session.setAttribute("users", list);

		ServletContext sc = getServletContext();

		if (list.size() == 0) {
			sql = "検索結果がありません";

			request.setAttribute("sql", sql);
			RequestDispatcher rd = sc.getRequestDispatcher("/UserSearchResult.jsp");
			rd.forward(request, response);
		}

		RequestDispatcher rd = sc.getRequestDispatcher("/UserSearchResult.jsp");
		rd.forward(request, response);
	}

}
