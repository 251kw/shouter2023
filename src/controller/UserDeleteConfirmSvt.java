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
 * Servlet implementation class UserDeleteConfirmSvt
 */
@WebServlet("/udc")
public class UserDeleteConfirmSvt extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserDeleteConfirmSvt() {
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
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();

		String loginId = (String) session.getAttribute("loginId");
		String userName = (String) session.getAttribute("userName");
		String[] icon = (String[]) session.getAttribute("icon");
		String prof = (String) session.getAttribute("prof");
		String[] check = request.getParameterValues("user_check");
		RequestDispatcher dispatcher = null;

		DBManager dbm = new DBManager();
		ArrayList<UserDTO> users  = dbm.SerchUser(loginId, userName, icon, prof);
		ArrayList<UserDTO> new_user=new ArrayList<UserDTO>();

		for(String number: check) {
			int num = Integer.parseInt(number);
			new_user.add(users.get(num));
		}
		request.setAttribute("users", new_user);
		dispatcher = request.getRequestDispatcher("UserDeleteConfirm.jsp");
		dispatcher.forward(request, response);

	}

}
