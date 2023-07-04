package practiceG;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

				String loginId = request.getParameter("loginId");
				//String userName = request.getParameter("userName");
				//String icon = request.getParameter("icon");
				//String profile = request.getParameter("profile");

				//if (dbm == null) {
					//dbm = new DBManager();
		//		}
				DBManager dbm = new DBManager();
				UserDTO user = dbm.userSearch(loginId);

				// リストをセッションに保存
				request.setAttribute("User", user);

				ServletContext sc = getServletContext();
				RequestDispatcher rd = sc.getRequestDispatcher("/UserSearchResult.jsp");
				rd.forward(request, response);
	}

}
