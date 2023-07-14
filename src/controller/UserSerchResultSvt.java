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
 * Servlet implementation class UserSerchResultSvt
 */
@WebServlet("/usr")
public class UserSerchResultSvt extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserSerchResultSvt() {
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

		String checkAll = request.getParameter("checkall");
		String loginId = (String) session.getAttribute("loginId");
		String userName = (String) session.getAttribute("userName");
		String[] icon = (String[]) session.getAttribute("icon");
		String prof = (String) session.getAttribute("prof");
		RequestDispatcher dispatcher = null;

		DBManager dbm = new DBManager();
		ArrayList<UserDTO> users  = dbm.SerchUser(loginId, userName, icon, prof);
		String check = "";

		if(checkAll != null) {
			if(checkAll.equals("全選択")) {
				check = "checked";
				request.setAttribute("check", check);
				request.setAttribute("users",users);
				dispatcher = request.getRequestDispatcher("UserSerchResult.jsp");

			}else if(checkAll.equals("全解除")) {
				check = "";
				request.setAttribute("check", check);
				request.setAttribute("users",users);
				dispatcher = request.getRequestDispatcher("UserSerchResult.jsp");
			}
		}else if(request.getParameter("edit")!=null) {
			int editIndex = Integer.parseInt(request.getParameter("edit"));
			UserDTO editUser=users.get(editIndex);
			session.setAttribute("olduser", editUser);
			dispatcher = request.getRequestDispatcher("UserEditInput.jsp");
		}else if(request.getParameterValues("indexId")!=null) {
			String[] indexId = request.getParameterValues("indexId");

			ArrayList<String> checkList=new ArrayList<String>();
			int num = 0;
			for(int i = 0; i<users.size();i++ ){
				if(indexId.length>num) {
					if(users.get(i).getLoginId().equals(indexId[num])) {
						checkList.add("checked");
						num++;
					}else {
						checkList.add("");
					}
				}else {
					checkList.add("");
				}
			}
			request.setAttribute("checkedIndex", checkList);
			request.setAttribute("users",users);
			dispatcher = request.getRequestDispatcher("UserSerchResult.jsp");

		}else {
			if(users.size() == 0) {
				request.setAttribute("noresult_error", "条件に一致する結果は存在しません。");
				dispatcher = request.getRequestDispatcher("UserSerchResult.jsp");
			}else {
				request.setAttribute("users",users);
				dispatcher = request.getRequestDispatcher("UserSerchResult.jsp");
			}
		}

		dispatcher.forward(request, response);

	}

}