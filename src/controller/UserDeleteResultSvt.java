package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DBManager;

/**
 * Servlet implementation class UserDeleteResultSvt
 */
@WebServlet("/udr")
public class UserDeleteResultSvt extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserDeleteResultSvt() {
		super();
		// TODO Auto-generated constructor stub
	}

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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int num = Integer.parseInt(request.getParameter("max_num"));
		ArrayList<String> Ids = new ArrayList<String>();
		RequestDispatcher dispatcher = null;

		for(int i = 0; i <= num; i++) {
			Ids.add(request.getParameter(Integer.toString(i)));
		}

		DBManager dbm = new DBManager();
		int result = dbm.deleteUser(Ids);

		if(result==0) {
			String message_delete = "情報の削除に失敗しました。";
			request.setAttribute("alert_delete", message_delete);
			dispatcher = request.getRequestDispatcher("UserDeleteResult.jsp");
		}
		dispatcher = request.getRequestDispatcher("UserDeleteResult.jsp");
		dispatcher.forward(request, response);

	}

}
