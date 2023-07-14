package controller;

import java.io.IOException;

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
 * Servlet implementation class UserSearchResultSvt
 */
@WebServlet("/usr")
public class UserSearchResultSvt extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//文字化け対策
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		//フォワードの準備
		RequestDispatcher dispatcher = null;

		//DBの準備
		DBManager dbm = new DBManager();

		//編集ボタンを押下した際に登録されているloginIdをパラメーターで取得
		String editBtn = request.getParameter("edit");

		//全選択・全解除ボタンが押下された際のvalue値をパラメーターで取得
		String allBtn = request.getParameter("All");

		String check;


		//編集ボタンが押下されたときの処理
		if(editBtn != null) {
		UserDTO user = dbm.getShowResult(editBtn);

		request.setAttribute("editResult", user); //属性値userで値userをrequestスコープに設定する。
		dispatcher = request.getRequestDispatcher("UserEditInput.jsp");

		//更新の際に変更前のLoginIdが必要なため、sessionにセットする
		HttpSession session = request.getSession();
		session.setAttribute("oldLoginId",editBtn);
		}

		//全選択・全解除ボタンが押されたとき
		if(allBtn != null) {
			//全選択ボタンであった時
			if(allBtn.equals("all")) {
				check = "checked";
				request.setAttribute("check", check);
				dispatcher = request.getRequestDispatcher("UserSearchResult.jsp");

			}else if(allBtn.equals("notAll")) {
				check = "";
				request.setAttribute("check", check);
				dispatcher = request.getRequestDispatcher("UserSearchResult.jsp");
			}
		}


		//フォワードで転送
		dispatcher.forward(request, response);

	}

}
