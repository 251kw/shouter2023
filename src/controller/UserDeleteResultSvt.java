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


//削除核確認画面でOKを押したとき
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//文字化け対策
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		//フォワードの準備
		RequestDispatcher dispatcher = null;
		DBManager dbm = new DBManager();

		//削除する際にLoginIdが必要なため、sessionにセットする
		HttpSession session = request.getSession();

		//セッションに保存したチェックの付いたログインIdの取得
		String[] checkBoxes = (String[]) session.getAttribute("checkBoxes");

		int resultCount =0;

		//loginIdの数だけDBManagerでdelete文を実行する
		for(int i=0; i< checkBoxes.length; i++) {
			dbm.deleteUser(checkBoxes[i]);
			resultCount++;
		}

		request.setAttribute("resultCount", resultCount); //属性値userで値userをrequestスコープに設定する。
		dispatcher = request.getRequestDispatcher("UserDeleteResult.jsp");

		//フォワードで転送
		dispatcher.forward(request, response);

	}

}
