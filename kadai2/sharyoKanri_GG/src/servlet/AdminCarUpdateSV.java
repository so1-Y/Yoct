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
import model.CalcuLogic;
import model.SharyoBean;
import model.ShomohinBean;
import model.TireBean;

/**
 * Servlet implementation class AdminCarUpdateSV
 */
@WebServlet("/AdminCarUpdateSV")
public class AdminCarUpdateSV extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("doGet");

		String tireType = request.getParameter("tireType");
		System.out.println("★："+tireType);


		//　リクエストスコープから車番情報を取得しセッションスコープに保存
		//セッションスコープから車両情報を取得
		HttpSession sessionUpDate = request.getSession();
		sessionUpDate.setAttribute("shabanInfo", request.getParameter("shabanInfo"));
		sessionUpDate.setAttribute("shaban", request.getParameter("shaban"));

		sessionUpDate.setAttribute("mileage", request.getParameter("mileage"));
		sessionUpDate.setAttribute("carUserNumber", request.getParameter("carUserNumber"));
		sessionUpDate.setAttribute("carUser", request.getParameter("carUser"));
		sessionUpDate.setAttribute("tireType", request.getParameter("tireType"));
		sessionUpDate.setAttribute("snowTire", request.getParameter("snowTire"));


		//　フォワード
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("/WEB-INF/jsp/admin_car_update.jsp");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		HttpSession session = request.getSession();
		String shabanInfo = (String) session.getAttribute("shabanInfo");
		int shaban = Integer.parseInt((String) session.getAttribute("shaban"));

		//リクエストスコープから更新に必要な情報を取得
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");

		HttpSession sessionUpDate = request.getSession();

		//DAOインスタンスを生成
		SharyoDAO sd = new SharyoDAO();

		if(action.equals("basicInfo")) {		//車両基本情報のみの更新処理

			// 更新に必要な情報を取得しインスタンスを生成
			System.out.println("車番:"+shabanInfo+shaban);

			int mileage = Integer.parseInt(request.getParameter("mileage"));
			int carUserNumber = Integer.parseInt(request.getParameter("carUserNumber"));
			String carUser = sd.selectShainmei(carUserNumber);
			request.setAttribute("carUser", carUser);
			System.out.println("carUser"+carUser);
			String tireType = request.getParameter("tireType");
			String snowTire = request.getParameter("snowTire");

			System.out.println("mileage:"+mileage);

			SharyoBean sb = new SharyoBean(shabanInfo, shaban, carUserNumber, carUser,
											tireType, snowTire, mileage);

			String msgBinfo ="基本情報を更新しました";

			if(carUser!=null) {
				// DBにて基本情報を更新
				int cnt = sd.sharyoUpdate(sb);

				if(cnt>0 && carUser!=null) {

					// 「現時点での使用距離」を算出しテーブルへ格納
					TireBean tb = sd.selectTire(shabanInfo, shaban);
					ShomohinBean smb = sd.selectShomohin(shabanInfo, shaban);

					CalcuLogic cl = new CalcuLogic();

					//ノーマルタイヤの「現時点での使用距離」算出
					int nTkmTotal = cl.nTireKmNowTotal(sb, tb);
					//スタッドレスタイヤの「現時点での使用距離」算出
					int sTkmTotal =  cl.sTireKmNowTotal(sb, tb);

					System.out.println("n:"+nTkmTotal);
					System.out.println("s:"+sTkmTotal);

					tb.setnTireTotalNowKm(nTkmTotal);
					tb.setsTireTotalNowKm(sTkmTotal);

					int c = sd.tireUpdate(tb);		// Tireテーブル更新
					System.out.println("cnt:"+c);

					int oilNowTotal = sb.getMileage()-smb.getOilKm();
					int elementNowTotal = sb.getMileage()-smb.getElementKm();
					int batteryNowTotal = sb.getMileage()-smb.getBatteryKm();

					smb.setOilNowKm(oilNowTotal);
					smb.setElementNowKm(elementNowTotal);
					smb.setBatteryNowKm(batteryNowTotal);

					sd.shomohinUpdate(smb);		//Shomohinテーブル更新

					sessionUpDate.setAttribute("mileage", request.getParameter("mileage"));
					sessionUpDate.setAttribute("carUserNumber", request.getParameter("carUserNumber"));
					sessionUpDate.setAttribute("carUser", request.getParameter("carUser"));
					sessionUpDate.setAttribute("tireType", request.getParameter("tireType"));
					sessionUpDate.setAttribute("snowTire", request.getParameter("snowTire"));
					System.out.println("tireType:"+tireType);
				}else{
					msgBinfo = "DBでの基本情報更新に失敗しました";
				}

			}else {
				msgBinfo = "入力された社員番号の社員は登録されていません。再度確認し入力してください。";
			}

			request.setAttribute("msgBinfo", msgBinfo);

		}else{

			List<SharyoBean> sbList = sd.selectSharyo(shabanInfo, shaban);
			SharyoBean sb = sbList.get(0);

			String msg="";

			// 部品情報・点検結果の更新処理
			//　チェックボックスの値（チェック有り/なし）を取得
			int mileage = Integer.parseInt(request.getParameter("mileage"));
			String sharyoCheck = request.getParameter("sharyoCheck");
			String tireChange = request.getParameter("tireChange");
			String oilChange = request.getParameter("oilChange");
			String elementChange = request.getParameter("elementChange");
			String batteryChange = request.getParameter("batteryChange");

			if("On".equals(sharyoCheck)) {
				//点検結果の更新
				//リクエストパラメーターの取得
				String year = request.getParameter("checkYear");
				String month = request.getParameter("checkMonth");
				String day = request.getParameter("checkDay");
				String chkRslt = request.getParameter("check_rslt");
				String bikoText = request.getParameter("plus_text");

				//車両インスタンスの生成
				String lastChkDay = year + "/" + month + "/" + day;
				System.out.println(lastChkDay);
				sb = new SharyoBean(shabanInfo, shaban, lastChkDay, chkRslt, bikoText);

				//DBにて更新
				int cnt = sd.sharyoCheckUpdate(sb);

				System.out.println("142DB:"+cnt);

				if(cnt<=0) {
					msg = msg + "\r\n「車両点検」情報の更新に失敗しました。入力内容を確認してください。";
				}
			}
			if("On".equals(tireChange)) {
				String tireAction = request.getParameter("tireAction");
				if(tireAction != null) {

					System.out.println(shabanInfo+shaban);

					TireBean tb = sd.selectTire(shabanInfo, shaban);

					if(tb==null) {
						msg = msg + "\r\nタイヤテーブルの取得に失敗しました";
					}

					String tireType="";

					//リクエストパラメーターの取得
					String year = request.getParameter("tireYear");
					String month = request.getParameter("tireMonth");
					String day = request.getParameter("tireDay");
					String changeKm = request.getParameter("change_km"); //交換時のメーター距離
					String makeYear = request.getParameter("make_year"); //製造年週
//あ					String mileage = request.getParameter("mileage");    //現在のメーター距離


					System.out.println("change_km:"+changeKm);
					System.out.println("year:"+year+"/"+month);
					System.out.println("/"+day);

					switch(tireAction) {
					case "newTireNormal":	//新タイヤ（ノーマルタイヤ）装着処理
						tb.setnTireDay(year + "/" + month + "/" + day);
						tb.setnTireYear(Integer.parseInt(makeYear));
						tb.setnTireKm(Integer.parseInt(changeKm));
						tb.setnTireTotalKm(0);		// ノーマルタイヤ総使用距離を０にリセット
						if (sb.getTireType().trim().equals("スタッドレス")) { //交換前がスタッドレスだったら
							tb.setsTireTotalKm(tb.getsTireTotalKm()+(sb.getMileage()-tb.getsTireKm())); //スタッドレス累計走行距離
						}
						tireType = "ノーマル";
						break;

					case "newTireSnow":
						tb.setsTireDay(year + "/" + month + "/" + day);
						tb.setsTireYear(Integer.parseInt(makeYear));
						tb.setsTireKm(Integer.parseInt(changeKm));
						tb.setnTireTotalKm(0);		// スタッドレスタイヤ総使用距離を０にリセット
						if (sb.getTireType().trim().equals("ノーマル")) { //交換前タイヤがノーマルだったら
							tb.setnTireTotalKm(tb.getnTireTotalKm()+(sb.getMileage()-tb.getnTireKm())); //ノーマル累計走行距離
						}
						tireType = "スタッドレス";
						break;

					case "normalToSnow":	//ノーマルタイヤからスタッドレスタイヤへ
						tb.setsTireDay(year + "/" + month + "/" + day);
						tb.setsTireKm(Integer.parseInt(changeKm));
						tb.setnTireTotalKm(mileage-tb.getnTireKm()+tb.getnTireTotalKm());	// ノーマルタイヤの交換時累計距離を加算
						tireType = "スタッドレス";
						break;

					case "snowToNormal":	//スタッドレスタイヤからノーマルタイヤへ
						tb.setnTireDay(year + "/" + month + "/" + day);
						tb.setnTireKm(Integer.parseInt(changeKm));
						tb.setsTireTotalKm(mileage-tb.getsTireKm()+tb.getsTireTotalKm());	// スタッドレスタイヤの交換時累計距離を加算
						tireType = "ノーマル";
						break;

					default:
						break;
					}

					// sharyoテーブルの「使用中のタイヤ」と「総走行距離」の更新
					sb = new SharyoBean(shabanInfo, shaban, tireType, mileage);
					int cnt = sd.sharyoTireTypeUpdate(sb);
					if(cnt>0) {

						CalcuLogic cl = new CalcuLogic();

						//ノーマルタイヤの「現時点での使用距離」算出
						int nTkmTotal = cl.nTireKmNowTotal(sb, tb);
						//スタッドレスタイヤの「現時点での使用距離」算出
						int sTkmTotal =  cl.sTireKmNowTotal(sb, tb);

						tb.setnTireTotalNowKm(nTkmTotal);
						tb.setsTireTotalNowKm(sTkmTotal);

						// タイヤテーブルの更新
						cnt = sd.tireUpdate(tb);

						sessionUpDate.setAttribute("mileage", request.getParameter("mileage"));
						sessionUpDate.setAttribute("tireType", request.getParameter("tireType"));

					}else {
						msg = msg + "\r\n「使用中のタイヤ」の更新に失敗しました";
						System.out.println(msg);
					}

					System.out.println("TireDbCnt:"+cnt);
					System.out.println("mileage:"+mileage);
					System.out.println("★★TireType："+tireType);

				}
			}else {
				ShomohinBean smb = sd.selectShomohin(shabanInfo, shaban);

				if("On".equals(oilChange)) {
					//オイル交換
					//リクエストパラメーターの取得
					String partYear = request.getParameter("partYear");
					String partMonth = request.getParameter("partMonth");
					String partDay = request.getParameter("partDay");
					String partChangeKm = request.getParameter("partChange_km");

					System.out.println("oil:"+partYear + "/" + partMonth + "/" + partDay);


					int oilNowTotal = mileage-smb.getOilKm();

					//消耗品インスタンスのセッターへセット
					smb.setOilNowKm(oilNowTotal);
					smb.setOilDay(partYear + "/" + partMonth + "/" + partDay);
					smb.setOilKm(Integer.parseInt(partChangeKm));
				}
				if("On".equals(elementChange)) {
					//エレメント交換
					//リクエストパラメーターの取得
					String partYear = request.getParameter("partYear");
					String partMonth = request.getParameter("partMonth");
					String partDay = request.getParameter("partDay");
					String partChangeKm = request.getParameter("partChange_km");
					int elementNowTotal = mileage-smb.getElementKm();

					//消耗品インスタンスのセッターへセット
					smb.setElementNowKm(elementNowTotal);

					smb.setElementDay(partYear + "/" + partMonth + "/" + partDay);
					smb.setElementKm(Integer.parseInt(partChangeKm));
				}
				if("On".equals(batteryChange)) {
					//バッテリー交換
					//リクエストパラメーターの取得
					String batteryYear = request.getParameter("batteryYear");
					String batteryMonth = request.getParameter("batteryMonth");
					String batteryDay = request.getParameter("batteryDay");
					String batteryChangeKm = request.getParameter("batteryChange_km");
					int batteryNowTotal = mileage-smb.getBatteryKm();

					//消耗品インスタンスのセッターへセット
					smb.setBatteryNowKm(batteryNowTotal);
					smb.setBatteryDay(batteryYear + "/" + batteryMonth + "/" + batteryDay);
					smb.setBatteryKm(Integer.parseInt(batteryChangeKm));
				}
				// DBにてshomohinテーブルの更新
				int cnt = sd.shomohinUpdate(smb);

				if(cnt>0) {
					sessionUpDate.setAttribute("mileage", request.getParameter("mileage"));
				}else {
					msg = msg + "\r\n"+"shomohinテーブルの更新に失敗しました";
					System.out.println(msg);
				}
			}

			if(msg.equals("")) {
				msg = msg +"\r\n"+shabanInfo+shaban+"の情報更新を完了しました";
			}

			request.setAttribute("msg", msg);
		}

		//　フォワード
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("/WEB-INF/jsp/admin_car_update.jsp");
		dispatcher.forward(request, response);

	}

}
