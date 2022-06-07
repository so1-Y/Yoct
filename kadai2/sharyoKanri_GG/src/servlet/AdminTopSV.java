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
import model.SharyoBean;

@WebServlet("/AdminTopSV")
public class AdminTopSV extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession sessionAdmin = request.getSession();
		String admin = (String) sessionAdmin.getAttribute("admin");
		System.out.println("admin:"+admin);

		String selectErrMsg = (String) sessionAdmin.getAttribute("selectErrMsg");
		if(selectErrMsg!=null) {
			
			request.setAttribute("selectErrMsg", selectErrMsg);
		}


		if("using".equals(admin)) {
			// 全車両情報をテーブルから検索
			SharyoDAO shoaryoDAO = new SharyoDAO();
			List<SharyoBean> SharyoLists = shoaryoDAO.sharyoAll();

			// 検索結果をリクエストスコープに保存
			request.setAttribute("SharyoLists", SharyoLists);

			//管理者トップに戻ることにより不要になったセッションスコープの破棄
			HttpSession sessionAdminTop = request.getSession();

			sessionAdminTop.invalidate();

			//　フォワード
			RequestDispatcher dispatcher =
					request.getRequestDispatcher("/WEB-INF/jsp/admin_top.jsp");
			dispatcher.forward(request, response);

		}else {

			// AdminErrにフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/admin_err.jsp");
			dispatcher.forward(request, response);

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String jyoken =request.getParameter("jyoken");

		// 全車両情報をテーブルから検索
		SharyoDAO shoaryoDAO = new SharyoDAO();
		List<SharyoBean> sLists = shoaryoDAO.sharyoAll();
		List<SharyoBean> SharyoLists = new ArrayList<>();	// スコープ格納用リスト

		if(jyoken.equals("sokokyori")) {
			int mileage = Integer.parseInt(request.getParameter("mileage"));

			for(SharyoBean sb:sLists) {
				System.out.println("★"+sb.getMileage()+"km");

				if(sb.getMileage() >= mileage) {
					System.out.println(sb.getMileage()+"km");
					SharyoLists.add(sb);
				}

			}
		}else if(jyoken.equals("snowNeed")) {
			String snowTire = request.getParameter("SnowTire");

			if(snowTire!=null) {
				for(SharyoBean sb:sLists) {
					System.out.println("★"+sb.getSnowTire());

					if(snowTire.equals("必要") && sb.getSnowTire().equals("必要")) {
						SharyoLists.add(sb);
					}else if(snowTire.equals("不要") && sb.getSnowTire().equals("不要")) {
						SharyoLists.add(sb);
					}
				}
			}else {
				String choiceErrMsg = "冬用タイヤ（必要／不要）のどちらかを選択してください";
				request.setAttribute("choiceErrMsg", choiceErrMsg);
			}
		}

		System.out.println(SharyoLists);

		request.setAttribute("SharyoLists", SharyoLists);

		//　フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/admin_top.jsp");
		dispatcher.forward(request, response);

	}

}
