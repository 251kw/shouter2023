package UserInfo;

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
 * Servlet implementation class ConfirmSvt
 */
@WebServlet("/uic")
public class ConfirmSvt extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmSvt() {
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
		//文字化け対策
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		//hiddenで送られてきたパラメータの取得
		String loginId = request.getParameter("loginId");
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String icon = request.getParameter("icon");
		String prof = request.getParameter("prof");
		RequestDispatcher dispatcher = null;

		//取得したパラメータからUserDTOオブジェクトを作り、usersテーブルにINSERT
		DBManager dbm = new DBManager();
		UserDTO user = new UserDTO(loginId, password,userName,icon,prof);
		dbm.setUser(user);
		request.setAttribute("user",user);

		//UserInfoResult.jspに遷移
		dispatcher = request.getRequestDispatcher("UserInfoResult.jsp");
		dispatcher.forward(request, response);

	}

}
