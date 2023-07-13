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
 * Servlet implementation class UserEditResultSvt
 */
@WebServlet("/uer")
public class UserEditResultSvt extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserEditResultSvt() {
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

		//フォワードの準備
		RequestDispatcher dispatcher = null;

		//セッションの準備
		HttpSession session = request.getSession();
		DBManager dbm = new DBManager();

		//更新結果から戻るボタンを押下した際に検索した内容が表示されるようにsessionに検索項目を保存させておく
		String loginId = (String) session.getAttribute("searchLoginId");
		String userName = (String) session.getAttribute("searchUserName");
		String[] icon = (String[]) session.getAttribute("searchIcon");
		String profile = (String) session.getAttribute("searchProfile");

		//DB検索
		ArrayList<UserDTO> list = dbm.getSearchList(loginId, userName, icon, profile);

		request.setAttribute("searchResult", list); //属性値userで値userをrequestスコープに設定する。
		dispatcher = request.getRequestDispatcher("UserSearchResult.jsp");

		// リストをセッションに保存
		session.setAttribute("user2", list);

		//フォワードで転送
		dispatcher.forward(request, response);


	}

}
