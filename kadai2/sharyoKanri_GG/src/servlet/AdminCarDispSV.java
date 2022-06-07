package servlet;

import java.io.IOException;
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
import model.ShomohinBean;
import model.TireBean;

/**
 * Servlet implementation class AdminCarDispSV
 */
@WebServlet("/AdminCarDispSV")
public class AdminCarDispSV extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("doGet");




		//　フォワード
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("/WEB-INF/jsp/admin_car_disp.jsp");
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("doPost");

		// リクエストスコープ（shabanInfo,shaban)の取得
		request.setCharacterEncoding("UTF-8");
		String shabanInfos = request.getParameter("shabanInfos");

		if(shabanInfos!=null){
			String[] infos = shabanInfos.split(",");

			String shabanInfo = infos[0].trim();
			int shaban = Integer.parseInt(infos[1].trim());

			System.out.println("★AdminCarDispSV:"+shabanInfo+"  "+shaban);


			// 車番情報と車番を元にSharyo,Tire,Shomohinテーブルの情報を取得しリクエストスコープに保存

			SharyoDAO sd = new SharyoDAO();

			List<SharyoBean> sbList = sd.selectSharyo(shabanInfo, shaban);
			SharyoBean sb = sbList.get(0);
			TireBean tb = sd.selectTire(shabanInfo, shaban);
			ShomohinBean smb = sd.selectShomohin(shabanInfo, shaban);

			System.out.println("@"+shabanInfo+shaban);

			request.setAttribute("sb", sb);
			request.setAttribute("tb", tb);
			request.setAttribute("smb", smb);


			/**********************************************************************************
			 *下記の２つの「現時点での使用距離」は「admin_car_disp.jsp」「user_disp」での
			 * 表示の為の値である。
			 * DBの各タイヤテーブルの「総使用距離」はここでは更新しないことに注意。
			 **********************************************************************************/
//			CalcuLogic cl = new CalcuLogic();
//
//			//ノーマルタイヤの「現時点での使用距離」算出
//			int nTkmTotal = cl.nTireKmNowTotal(sb, tb);
//
//			//スタッドレスタイヤの「現時点での使用距離」算出
//			int sTkmTotal =  cl.sTireKmNowTotal(sb, tb);
//
//
//			request.setAttribute("nTkmTotal", nTkmTotal);
//			request.setAttribute("sTkmTotal", sTkmTotal);

			System.out.println("@@@@@");


			//　フォワード
			RequestDispatcher dispatcher =
					request.getRequestDispatcher("/WEB-INF/jsp/admin_car_disp.jsp");
			dispatcher.forward(request, response);

		}else {
			String selectErrMsg = "車両が選択されていません。"
					+ "車両情報一覧の左の選択ボタンで車両を選んでください。";

			HttpSession sessionAdminTop = request.getSession();
			sessionAdminTop.setAttribute("admin", "using");

			request.setCharacterEncoding("UTF-8");
			String action = request.getParameter("action");

			if(action!=null) {
				if(action.equals("parts")) {
					sessionAdminTop.setAttribute("selectErrMsg2", selectErrMsg);

					//AdminPartsListにリダイレクト
					response.sendRedirect("http://localhost:8080/sharyoKanri_GG/AdminPartsListSV");
				}else {
					sessionAdminTop.setAttribute("selectErrMsg", selectErrMsg);
					// AdminTopにリダイレクト
					response.sendRedirect("http://localhost:8080/sharyoKanri_GG/AdminTopSV");
				}
			}else {
				sessionAdminTop.setAttribute("selectErrMsg", selectErrMsg);
				// AdminTopにリダイレクト
				response.sendRedirect("http://localhost:8080/sharyoKanri_GG/AdminTopSV");
			}

		}


	}

}
