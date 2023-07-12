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
 * Servlet implementation class UserSearchResultSvt
 */
@WebServlet("/usr")
public class UserSearchResultSvt extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBManager dbm;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserSearchResultSvt() {
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

		String btn = request.getParameter("delete");

		String loginId = request.getParameter("edit");

		String loginId1[] = request.getParameterValues("icon");

		String unselected = null;

		if (dbm == null) {
			dbm = new DBManager();
		}

		HttpSession session = request.getSession();
		ServletContext sc = getServletContext();

		if (btn != null) {
			if (loginId1 == null) {
				unselected = "未選択です。";

				// エラーメッセージをリクエストオブジェクトに保存
				request.setAttribute("unselected", unselected);

				RequestDispatcher rd = sc.getRequestDispatcher("/UserSearchResult.jsp");
				rd.forward(request, response);
			} else {

				session.setAttribute("deleteId", loginId1);

				ArrayList<UserDTO> deleteList = dbm.getDeleteEditUser(loginId1);

				// リストをセッションに保存
				session.setAttribute("Users", deleteList);

				RequestDispatcher rd = sc.getRequestDispatcher("/UserDeleteConfirm.jsp");
				rd.forward(request, response);
			}
		} else if (loginId != null) {

			UserDTO user = dbm.getEditUser(loginId);

			session.setAttribute("User", user);

			RequestDispatcher rd = sc.getRequestDispatcher("/UserEditInput.jsp");
			rd.forward(request, response);
		}
	}

}
