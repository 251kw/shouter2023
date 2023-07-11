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

import check.Check_func;
import dao.DBManager;
import dto.UserDTO;

/**
 * Servlet implementation class UserSerchInputSvt
 */
@WebServlet("/usi")
public class UserSerchInputSvt extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserSerchInputSvt() {
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

		String loginId = request.getParameter("loginId");
		String userName = request.getParameter("userName");
		String[] icon = request.getParameterValues("icon");
		String prof = request.getParameter("prof");

		session.setAttribute("loginId", loginId);
		session.setAttribute("userName", userName);
		session.setAttribute("icon", icon);
		session.setAttribute("lprof", prof);

		RequestDispatcher dispatcher = null;
		String regex_AlphaNum = "^[A-Za-z0-9 ]+$";
		String message_id=null;


		if(!Check_func.checkLogic(regex_AlphaNum, loginId)){
			//ログインIDに半角英数字以外が含まれる場合
			message_id="ログインIDは半角英数字でご記入ください。";
			request.setAttribute("alert_id", message_id);
		}

		DBManager dbm = new DBManager();
		ArrayList<UserDTO> users  = dbm.SerchUser(loginId, userName, icon, prof);
		if(users.size() == 0) {
			request.setAttribute("noresult_error", "条件に一致する結果は存在しません。");
			dispatcher = request.getRequestDispatcher("UserSerchResult.jsp");
		}else {
			request.setAttribute("users",users);
			dispatcher = request.getRequestDispatcher("UserSerchResult.jsp");
		}


		dispatcher.forward(request, response);

	}

}
