package src.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import src.dao.DBManager;
import src.dto.UserDTO;

/**
 * Servlet implementation class UserSearchResultDeleteSvt
 */
@WebServlet("/UserSearchResultDeleteSvt")
public class UserSearchResultDeleteSvt extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserSearchResultDeleteSvt() {
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

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		//DBManager dbm = new DBManager();
		//ArrayList<UserDTO> users = dbm.getUserList(loginId, userName, icon, icon2, profile);

		//定義
		DBManager dbm = new DBManager();
		HttpSession session = request.getSession();
		//String[] idList = request.getParameterValues("id");
		//List<String> idlist = Arrays.asList(idList);
		String message = null;
		RequestDispatcher dispatcher = null;
		String x[] = request.getParameterValues("box");
		String delete=request.getParameter("delete");

		if (x==null)

			{
				message = "削除項目を選択してください。";
				// エラーメッセージをリクエストオブジェクトに保存
				request.setAttribute("alert", message);
				dispatcher = request.getRequestDispatcher("UserSearchResult.jsp");
				dispatcher.forward(request, response);

			} else {

		if (delete != null) {
			ArrayList<String> idlist = new ArrayList<String>();
			for (int i = 0; i < x.length; i++) {
				String number = "id" + x[i];
				String id = request.getParameter(number);
				idlist.add(id);
				session.setAttribute("idlist", idlist);
				ArrayList<UserDTO> list = dbm.getID(idlist);
				session.setAttribute("ID", list);
				dispatcher = request.getRequestDispatcher("userDeleteConfirm.jsp");
			}
		} else {
			/*String[] idList = request.getParameterValues("id");
			List<String> idlist = Arrays.asList(idList);
			session.setAttribute("idlist", idlist);
			ArrayList<UserDTO> list = dbm.getID(idlist);
			session.setAttribute("ID", list);*/
			dispatcher = request.getRequestDispatcher("UserSearchResult.jsp");
		}

		// 処理の転送先を .jsp に指定

		// 処理を転送
		dispatcher.forward(request, response);
	}
}
}
//}
