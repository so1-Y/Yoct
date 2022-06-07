package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.SharyoDAO;
import model.BuhinListBean;

/**
 * 週末作成
 */
@WebServlet("/AdminPartsListSV")
public class AdminPartsListSV extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		HttpSession sessionAdmin = request.getSession();
		String selectErrMsg2 = (String) sessionAdmin.getAttribute("selectErrMsg2");


		// 全車の部品情報をテーブルから取得
		List<BuhinListBean> buhinList = new ArrayList<>();

		// データベースから部品一覧データを取得
		SharyoDAO sd = new SharyoDAO();
		buhinList = sd.selectBuhinAll();

		// 部品一覧データをリクエストスコープに保存
		request.setAttribute("buhinList", buhinList);

		//　フォワード
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("/WEB-INF/jsp/admin_partsList.jsp");
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {



		//　フォワード
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("/sharyoKanri_GG/AdminCarDispSV");
		dispatcher.forward(request, response);

	}

}
