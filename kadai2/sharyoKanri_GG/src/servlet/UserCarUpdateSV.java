package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.SharyoDAO;
import model.CalcuLogic;
import model.SharyoBean;
import model.ShomohinBean;
import model.TireBean;

@WebServlet("/UserCarUpdateSV")
public class UserCarUpdateSV extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//更新画面へフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user_car_update.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//車両情報の更新をする

		//リクエストスコープから更新に必要な情報を取得
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		//セッションスコープから車両情報を取得
		HttpSession session = request.getSession();
		SharyoBean sharyo = (SharyoBean) session.getAttribute("sharyo");
		TireBean tire = (TireBean) session.getAttribute("tire");
		ShomohinBean shomohin = (ShomohinBean) session.getAttribute("shomohin");
		//DAOインスタンスを生成
		SharyoDAO sharyoDao = new SharyoDAO();

		if(action.equals("basicInfo")) {
		//車両基本情報のみの更新処理
			//リクエストスコープから入力の取得
			String meter = request.getParameter("meter");
			//入力チェック
			if (meter == null || meter.length() == 0 || sharyo.getMileage() > Integer.parseInt(meter)) {
				request.setAttribute("msg", "<font color=\"red\">※更新できませんでした。入力値を確認してください。</font>");
			} else {
				//車両インスタンスのセッターへセット
				sharyo.setMileage(Integer.parseInt(meter)); //総走行距離の更新
				int count = sharyoDao.sharyoUpdate(sharyo); //更新

				if(count>0) {
					// 「現時点での使用距離」を算出しテーブルへ格納
					CalcuLogic cl = new CalcuLogic();

					//ノーマルタイヤの「現時点での使用距離」算出
					int nTkmTotal = cl.nTireKmNowTotal(sharyo, tire);
					//スタッドレスタイヤの「現時点での使用距離」算出
					int sTkmTotal =  cl.sTireKmNowTotal(sharyo, tire);

					System.out.println("n:"+nTkmTotal);
					System.out.println("s:"+sTkmTotal);

					tire.setnTireTotalNowKm(nTkmTotal);
					tire.setsTireTotalNowKm(sTkmTotal);

					int c = sharyoDao.tireUpdate(tire);		// Tireテーブル更新
					System.out.println("cnt:"+c);

					request.setAttribute("msg", "※更新しました。");
				}else {
					request.setAttribute("msg", "※更新に失敗しました。");
				}
			}
		}else{
			// 部品情報・点検結果の更新処理
			//　チェックボックスの値（チェック有り/なし）を取得
			String sharyoCheck = request.getParameter("sharyoCheck");
			String tireChange = request.getParameter("tireChange");
			String oilChange = request.getParameter("oilChange");
			String elementChange = request.getParameter("elementChange");
			String batteryChange = request.getParameter("batteryChange");

			String mileage = request.getParameter("mileage");    //現在のメーター距離（総走行距離）
			if (mileage.length() == 0) {
				request.setAttribute("msg", "<font color=\"red\">　※現在のメーター距離が入力されていません。</font>");
			} else {
				sharyo.setMileage(Integer.parseInt(mileage));

				if("On".equals(sharyoCheck)) {
					//点検結果の更新
					//リクエストパラメーターの取得
					String year = request.getParameter("checkYear");
					String month = request.getParameter("checkMonth");
					String day = request.getParameter("checkDay");
					String chkRslt = request.getParameter("check_rslt");
					String bikoText = request.getParameter("plus_text");
					//車両インスタンスのセッターへセット
					sharyo.setLastChkDay(year + "/" + month + "/" + day);
					sharyo.setLastChkRslt(chkRslt);
					sharyo.setChkInfo(bikoText);
					//更新
					int upRslt = sharyoDao.sharyoCheckUpdate(sharyo);
					//結果
					if (upRslt == 0 ){
						request.setAttribute("msg", "<font color=\"red\">　※車両点検項目が更新できませんでした。入力値を確認してください。</font>");
					}
				}

				if("On".equals(tireChange)) {
					String tireAction = request.getParameter("tireAction");
					//★★★user_car_update.jspの変更に伴う修正案（安永）
					if(tireAction != null) {
						//リクエストパラメーターの取得
						String year = request.getParameter("tireYear");
						String month = request.getParameter("tireMonth");
						String day = request.getParameter("tireDay");
						String changeKm = request.getParameter("change_km"); //交換時のメーター距離
						String makeYear = request.getParameter("make_year"); //製造年週

						// タイヤ装着の種別ごとに該当する情報をセット
						switch(tireAction) {
						case "newTireNormal":	//新タイヤ（ノーマルタイヤ）装着時
							tire.setnTireDay(year + "/" + month + "/" + day);
							tire.setnTireYear(Integer.parseInt(makeYear));
							tire.setnTireKm(Integer.parseInt(changeKm));
							tire.setnTireTotalKm(0); //ノーマルの累計距離初期化
							if (sharyo.getTireType().trim().equals("スタッドレス")) { //交換前がスタッドレスだったら
								tire.setsTireTotalKm(tire.getsTireTotalKm()+(sharyo.getMileage()-tire.getsTireKm())); //スタッドレス累計走行距離
							}
							sharyo.setTireType("ノーマル");
							break;
						case "newTireSnow": //新タイヤ（スタッドレスタイヤ）装着時
							tire.setsTireDay(year + "/" + month + "/" + day);
							tire.setsTireYear(Integer.parseInt(makeYear));
							tire.setsTireKm(Integer.parseInt(changeKm));
							tire.setsTireTotalKm(0); //スタッドレスの累計初期化
							if (sharyo.getTireType().trim().equals("ノーマル")) { //交換前タイヤがノーマルだったら
								tire.setnTireTotalKm(tire.getnTireTotalKm()+(sharyo.getMileage()-tire.getnTireKm())); //ノーマル累計走行距離
							}
							sharyo.setTireType("スタッドレス");
							break;
						case "normalToSnow": //ノーマルタイヤからスタッドレスタイヤへ変更時
							tire.setsTireDay(year + "/" + month + "/" + day);
							tire.setsTireKm(Integer.parseInt(changeKm));
							tire.setnTireTotalKm(tire.getnTireTotalKm()+(sharyo.getMileage()-tire.getnTireKm())); //ノーマル累計走行距離（現累計＋（総走行距離-使用開始距離）？
							sharyo.setTireType("スタッドレス");
							break;
						case "snowToNormal":	//スタッドレスタイヤからノーマルタイヤへ変更時
							tire.setnTireDay(year + "/" + month + "/" + day);
							tire.setnTireKm(Integer.parseInt(changeKm));
							tire.setsTireTotalKm(tire.getsTireTotalKm()+(sharyo.getMileage()-tire.getsTireKm())); //スタッドレス累計走行距離（現累計＋（総走行距離-使用開始距離）？
							sharyo.setTireType("ノーマル");
							break;
						default:
							break;
						}

						// 「現時点での使用距離」を算出しテーブルへ格納
						CalcuLogic cl = new CalcuLogic();

						//ノーマルタイヤの「現時点での使用距離」算出
						int nTkmTotal = cl.nTireKmNowTotal(sharyo, tire);
						//スタッドレスタイヤの「現時点での使用距離」算出
						int sTkmTotal =  cl.sTireKmNowTotal(sharyo, tire);

						System.out.println("n:"+nTkmTotal);
						System.out.println("s:"+sTkmTotal);

						tire.setnTireTotalNowKm(nTkmTotal);
						tire.setsTireTotalNowKm(sTkmTotal);

						int c = sharyoDao.tireUpdate(tire);		// Tireテーブル更新
						System.out.println("cnt:"+c);

					}
				}

				if("On".equals(oilChange)) {
					//オイル交換
					//リクエストパラメーターの取得
					String partYear = request.getParameter("partYear");
					String partMonth = request.getParameter("partMonth");
					String partDay = request.getParameter("partDay");
					String partChangeKm = request.getParameter("partChange_km");
					//消耗品インスタンスのセッターへセット
					shomohin.setOilDay(partYear + "/" + partMonth + "/" + partDay);
					shomohin.setOilKm(Integer.parseInt(partChangeKm));
				}

				if("On".equals(elementChange)) {
					//エレメント交換
					//リクエストパラメーターの取得
					String partYear = request.getParameter("partYear");
					String partMonth = request.getParameter("partMonth");
					String partDay = request.getParameter("partDay");
					String partChangeKm = request.getParameter("partChange_km");
					//消耗品インスタンスのセッターへセット
					shomohin.setElementDay(partYear + "/" + partMonth + "/" + partDay);
					shomohin.setElementKm(Integer.parseInt(partChangeKm));
				}

				if("On".equals(batteryChange)) {
					//バッテリー交換
					//リクエストパラメーターの取得
					String batteryYear = request.getParameter("batteryYear");
					String batteryMonth = request.getParameter("batteryMonth");
					String batteryDay = request.getParameter("batteryDay");
					String batteryChangeKm = request.getParameter("batteryChange_km");
					//消耗品インスタンスのセッターへセット
					shomohin.setBatteryDay(batteryYear + "/" + batteryMonth + "/" + batteryDay);
					shomohin.setBatteryKm(Integer.parseInt(batteryChangeKm));
				}

				//更新
				int tireRslt = sharyoDao.tireUpdate(tire);
				int sharyoRslt = sharyoDao.sharyoUpdate(sharyo);
				int shomohinRslt  = sharyoDao.shomohinUpdate(shomohin);

				//更新結果
				if (tireRslt == 1 && sharyoRslt == 1 && shomohinRslt == 1) {
					request.setAttribute("msg", "　※更新しました。");
				}
				if (tireRslt == 0 ){
					request.setAttribute("msg", "<font color=\"red\">　※タイヤ交換項目が更新できませんでした。入力値を確認してください。</font>");
				}
				if (shomohinRslt == 0 ){
					request.setAttribute("msg", "<font color=\"red\">　※消耗品交換（オイルorエレメントorバッテリー）項目が更新できませんでした。入力値を確認してください。</font>");
				}
				//チェックボックスが全て選択されてないときの処理
				if (sharyoCheck == null && tireChange == null && oilChange == null && elementChange == null && batteryChange == null ) {
					request.setAttribute("msg", "<font color=\"red\">※チェックボックスを選択してください。</font>");
				}
			}
		}
		//フォワード
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("/WEB-INF/jsp/user_car_update.jsp");
		dispatcher.forward(request, response);
	}
}
