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
import model.RegisterLogic;
import model.SharyoBean;
import model.ShomohinBean;
import model.TireBean;

/**
 * Servlet implementation class AdminCarRegisterSV
 */
@WebServlet("/AdminCarRegisterSV")
public class AdminCarRegisterSV extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		SharyoDAO shoaryoDAO = new SharyoDAO();
		List<SharyoBean> sbl = shoaryoDAO.sharyoAll();

		// 検索結果をリクエストスコープに保存
		request.setAttribute("sbl", sbl);

		//　フォワード
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("/WEB-INF/jsp/admin_car_register.jsp");
		dispatcher.forward(request, response);

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String errMsg ="";

		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");

		// メッセージ用のセッションスコープを生成
		HttpSession sessionReg = request.getSession();
		HttpSession sessionDel = request.getSession();

		SharyoDAO shoaryoDAO = new SharyoDAO();
		List<SharyoBean> sbl = shoaryoDAO.sharyoAll();

		// 検索結果をリクエストスコープに保存
		request.setAttribute("sbl", sbl);

		System.out.println("action:"+action);

		if(action.equals("register")) {

			// リクエストパラメーターを取得
			String shabanInfo = request.getParameter("shabanInfo");
			String shaban = request.getParameter("shaban");
			String shashu = request.getParameter("shashu");
			String yearType = request.getParameter("yearType");
			String carUserNumber = request.getParameter("carUserNumber");
			String kudo = request.getParameter("kudo");

			String shakenbi = request.getParameter("shakenMonth")+"/"+request.getParameter("shakenDay");
			String tireType = request.getParameter("tireType");
			String snowTire = request.getParameter("snowTire");

			String registeredDay = request.getParameter("year")+"/"
									+request.getParameter("month")+"/"
									+request.getParameter("day");

			System.out.println("carUserNumber:"+carUserNumber);

			System.out.println(shabanInfo);
			if(shabanInfo.equals("")) {
				System.out.println("nulljanai");
			}

			if(shabanInfo.equals("")||shaban.equals("")||shashu.equals("")||yearType.equals("")||carUserNumber.equals("")
					||kudo.equals("")||request.getParameter("shakenMonth").equals("")
					||request.getParameter("shakenDay").equals("")||tireType.equals("")||snowTire.equals("")
					||request.getParameter("year").equals("")||request.getParameter("month").equals("")
					||request.getParameter("day").equals("")) {
				errMsg = "登録できませんでした。入力内容に漏れや誤りがないか確認して再登録してください";

				sessionReg.setAttribute("errMsg", errMsg);
				sessionReg.setAttribute("msg", "");
				sessionDel.setAttribute("delMsg", "");



			}else {
				// 社員名はDBから取得
				SharyoDAO sd = new SharyoDAO();
				String carUser = sd.selectShainmei(Integer.parseInt(carUserNumber));
//				String carUser = "未定";
				String lastChkDay ="";
				String lastChkRslt ="--";
				String chkInfo ="特になし";
				int mileage =0;

				System.out.println("carUserName:"+carUser);

				SharyoBean sharyoBean = new SharyoBean(shabanInfo,
												Integer.parseInt(shaban), shashu,
												Integer.parseInt(yearType),
												Integer.parseInt(carUserNumber),
												carUser, kudo, shakenbi, tireType,
												snowTire, lastChkDay, lastChkRslt, chkInfo, mileage);

				String nDate = null;
				String sDate = null;

				System.out.println("registeredDay:"+registeredDay);

				if(tireType.equals("ノーマル")) {
					nDate = registeredDay;
				}else if(tireType.equals("スタッドレス")){
					sDate = registeredDay;
				}

				TireBean tireBean = new TireBean(shabanInfo,Integer.parseInt(shaban),9999,nDate,0,0,9999,sDate,0,0);

				ShomohinBean shomohinBean
				= new ShomohinBean(shabanInfo,Integer.parseInt(shaban),registeredDay,0,registeredDay,0,registeredDay,0);

				// 新規車両をDBに登録(sharyoテーブル、tireテーブル、shomohinテーブルのデータを１件追加）
				RegisterLogic rl = new RegisterLogic();
				String msg = rl.carRegisterLG(sharyoBean, tireBean, shomohinBean);

				if(msg.equals("登録が完了しました")) {
					msg ="車両番号["+shabanInfo+shaban+"]の"+msg;
				}

				//msgをセッションスコープに保存
				sessionReg.setAttribute("msg", msg);
				sessionDel.setAttribute("delMsg", "");
				sessionReg.setAttribute("errMsg", "");

//				sessionDel.invalidate();


//				request.setAttribute("msg", msg);

				// 新規車両の情報をセッションスコープに保存
				HttpSession session = request.getSession();
				session.setAttribute("sharyoBean", sharyoBean);

			}

		}else if(action.equals("delete")){

			// リクエストパラメーターを取得
			String delSharyo = request.getParameter("delSharyo");
			System.out.println("del:"+delSharyo);
			String [] sbox = delSharyo.split(",");

			String shabanInfo = sbox[0].trim();
			String shaban = sbox[1].trim();
/*
			// リクエストパラメーターを取得
			String shabanInfo = request.getParameter("delShabanInfo");
			String shaban = request.getParameter("delShaban");
*/

			String delMsg;

			if(shabanInfo.equals("")||shaban.equals("")) {
				delMsg = "削除できませんでした。入力内容に漏れがないか確認してください。";
			}else{
				// DBから条件指定された車番の情報を削除
				SharyoDAO sd = new SharyoDAO();

				System.out.println(shabanInfo);
				System.out.println(shaban);

				int rslt = sd.deleteSharyo(shabanInfo,Integer.parseInt(shaban));

				if(rslt<=0) {
					delMsg = shabanInfo+" "+shaban+"の削除が完了しました";
				}else {
					delMsg = shabanInfo+" "+shaban+"の削除に失敗しました。入力内容を再確認してください。";
					System.out.println("DB削除失敗理由；"+rslt);
				}
			}
			sessionDel.setAttribute("delMsg", delMsg);
			sessionReg.setAttribute("errMsg", "");
			sessionReg.setAttribute("msg", "");

//			sessionReg.invalidate();
//			request.setAttribute("delMsg", delMsg);

		}

//		//　フォワード
//		RequestDispatcher dispatcher =
//				request.getRequestDispatcher("/WEB-INF/jsp/admin_car_register.jsp");
//		dispatcher.forward(request, response);

		// AdminCarRegisterSVにリダイレクト ※エラーメッセージはセッションが必要
		response.sendRedirect("http://localhost:8080/sharyoKanri_GG/AdminCarRegisterSV");

	}

}
