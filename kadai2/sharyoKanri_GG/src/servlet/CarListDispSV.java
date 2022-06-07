package servlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SharyoDAO;
import model.SharyoBean;
import model.ShomohinBean;
import model.TireBean;

/**
 * Servlet implementation class CarListDispSV
 */
@WebServlet("/CarListDispSV")
public class CarListDispSV extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 全車両情報をテーブルから検索
		SharyoDAO shoaryoDAO = new SharyoDAO();
		List<SharyoBean> SharyoLists = shoaryoDAO.sharyoAll();

		// 検索結果をリクエストスコープに保存
		request.setAttribute("SharyoLists", SharyoLists);

		RequestDispatcher dispatcher =
				request.getRequestDispatcher("/WEB-INF/jsp/carList_disp.jsp");
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//リクエストパラメータを取得
		String shabanFull = request.getParameter("select");

		if(shabanFull!=null) {
		List<String> shabanfull = Arrays.asList(shabanFull.split(","));
		String shabanInfo =shabanfull.get(0);
		String shab =shabanfull.get(1);
		int shaban= Integer.parseInt(shab);

		//詳細情報を検索
		SharyoDAO shoaryoDAO = new SharyoDAO();
		List<SharyoBean> SharyoLists = shoaryoDAO.selectSharyo(shabanInfo,shaban);
		TireBean  tb = shoaryoDAO.selectTire(shabanInfo,shaban);
		ShomohinBean smb = shoaryoDAO.selectShomohin(shabanInfo,shaban);
		SharyoBean sb = SharyoLists.get(0);


		/**********************************************************************************
		 *下記の２つの「現時点での使用距離」は「admin_car_disp.jsp」「user_disp」での
		 * 表示の為の値である。
		 * DBの各タイヤテーブルの「総使用距離」はここでは更新しないことに注意。
		 * 最後の改修にて処理を削除
		 **********************************************************************************/
//
//		CalcuLogic cl = new CalcuLogic();
//
//		//ノーマルタイヤの「現時点での使用距離」算出
//		int nTkmTotal = cl.nTireKmNowTotal(sb, tb);
//
//		//スタッドレスタイヤの「現時点での使用距離」算出
//		int sTkmTotal =  cl.sTireKmNowTotal(sb, tb);
//
//		request.setAttribute("nTkmTotal", nTkmTotal);
//		request.setAttribute("sTkmTotal", sTkmTotal);

		// 検索結果をリクエストスコープに保存
		request.setAttribute("sb", sb);
		request.setAttribute("tb", tb);
		request.setAttribute("smb", smb);

				RequestDispatcher dispatcher =
						request.getRequestDispatcher("/WEB-INF/jsp/car_Info_disp.jsp");
				dispatcher.forward(request, response);
		}else {

			SharyoDAO shoaryoDAO = new SharyoDAO();
			List<SharyoBean> SharyoLists = shoaryoDAO.sharyoAll();
			String msg="車両が選択されていません。"
					+ "車両情報一覧の左の選択ボタンで車両を選んでください。";
			// 検索結果をリクエストスコープに保存
			request.setAttribute("SharyoLists", SharyoLists);
			request.setAttribute("msg", msg);

			RequestDispatcher dispatcher =
					request.getRequestDispatcher("/WEB-INF/jsp/carList_disp.jsp");
			dispatcher.forward(request, response);

		}

	}

}
