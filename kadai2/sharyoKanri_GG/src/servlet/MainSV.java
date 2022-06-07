package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import model.SharyoBean;
import model.ShomohinBean;
import model.TireBean;
import model.UsersBean;

@WebServlet("/MainSV")
public class MainSV extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// ログイン失敗時のリダイレクトに対応したエラーメッセージの取得と設定
		request.setCharacterEncoding("UTF-8");
		if(request.getAttribute("errMsg")!=null){
			System.out.println("あ");
			request.setAttribute("errMsg", request.getAttribute("errMsg"));
		}

		// 全車の部品情報をテーブルから取得
		List<BuhinListBean> buhinList = new ArrayList<>();

		// データベースから部品一覧データを取得
		SharyoDAO sd = new SharyoDAO();
		buhinList = sd.selectBuhinAll();

		List<String> nTireOverUsed = new ArrayList<>();
		List<String> sTireOverUsed = new ArrayList<>();
		List<String> nTireOverYear = new ArrayList<>();
		List<String> sTireOverYear = new ArrayList<>();
		List<String> oilOverUsed = new ArrayList<>();
		List<String> elementOverUsed = new ArrayList<>();
		List<String> batteryOverUsed = new ArrayList<>();
		List<String> shaken2month = new ArrayList<>();
		List<String> shaken1month = new ArrayList<>();

		for(BuhinListBean bb : buhinList) {

			if(bb.getnTireNowKm()>=50000) {
				nTireOverUsed.add("車番："+bb.getShabanInfo()+bb.getShaban()+
						"　使用者："+bb.getCarUser()+
						"　使用距離："+bb.getnTireNowKm()+"km");
			}
			if(bb.getsTireNowKm()>=50000) {
				System.out.println("車番："+bb.getShabanInfo()+bb.getShaban()+
						"　使用者："+bb.getCarUser()+
						"　使用距離："+bb.getsTireNowKm()+"km");
				sTireOverUsed.add("車番："+bb.getShabanInfo()+bb.getShaban()+
						"　使用者："+bb.getCarUser()+
						"　使用距離："+bb.getsTireNowKm()+"km" );
			}
			if(bb.getnTireYear()>=5) {	// ノーマルタイヤの年数チェック
				nTireOverYear.add("車番："+bb.getShabanInfo()+bb.getShaban()+
						"　使用者："+bb.getCarUser()+
						"　使用年数："+bb.getnTireYear()+"年" );
			}
			if(bb.getsTireYear()>=5) {	// スタッドレスタイヤの年数チェック
				sTireOverYear.add("車番："+bb.getShabanInfo()+bb.getShaban()+
						"　使用者："+bb.getCarUser()+
						"　使用年数："+bb.getsTireYear()+"年" );
			}
			if((bb.getMileage() - bb.getOilKm()) >=6000) {
				oilOverUsed.add("車番："+bb.getShabanInfo()+bb.getShaban()+
						"　使用者："+bb.getCarUser()+
						"　使用距離："+(bb.getMileage() - bb.getOilKm())+"km" );
			}
			if((bb.getMileage() - bb.getElementKm()) >=12000) {
				elementOverUsed.add("車番："+bb.getShabanInfo()+bb.getShaban()+
						"　使用者："+bb.getCarUser()+
						"　使用距離："+(bb.getMileage() - bb.getElementKm())+"km" );
			}
			if((bb.getMileage() - bb.getBatteryKm()) >=50000) {
				batteryOverUsed.add("車番："+bb.getShabanInfo()+bb.getShaban()+
						"　使用者："+bb.getCarUser()+
						"　使用距離："+(bb.getMileage() - bb.getBatteryKm())+"km" );
			}
		}

		List<SharyoBean> sList = sd.sharyoAll();

		Date now = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(now);

		int cYear = c.get(Calendar.YEAR);
		int cMonth = c.get(Calendar.MONTH)+1;
		int cDay = c.get(Calendar.DAY_OF_MONTH);

		for(SharyoBean sb: sList) {
			System.out.println("aaaaa");

			int yearType = sb.getYearType();
			String dateS = sb.getShakenbi();
			String[] monthAndDate = dateS.split("/");
			int shakenMonth = Integer.parseInt(monthAndDate[0].trim());
			int shakenDate =  Integer.parseInt(monthAndDate[1].trim());

			System.out.println("車番"+sb.getShabanInfo()+sb.getShaban()+
							"　使用者："+sb.getCarUser()+
							"　車検日："+cYear+"/"+sb.getShakenbi());


			if((cYear-yearType)>1) {	// 年式の翌年は車検無しなので除外
				if(1<(shakenMonth-cMonth)&& (shakenMonth-cMonth)<3) {
					shaken2month.add("車番"+sb.getShabanInfo()+sb.getShaban()+
							"　使用者："+sb.getCarUser()+
							"　車検日："+cYear+"/"+sb.getShakenbi());
				}else if(0<=(shakenMonth-cMonth)&& (shakenMonth-cMonth)<2) {
					shaken1month.add("車番"+sb.getShabanInfo()+sb.getShaban()+
							"　使用者："+sb.getCarUser()+
							"　車検日："+cYear+"/"+sb.getShakenbi());
				}
			}
		}


		System.out.println("★"+nTireOverUsed);
		System.out.println(sTireOverUsed);

		// 各メッセージのレングスを取得

		// 各「お知らせ（注意）メッセージ」とそのレングスをスコープに保存
		request.setCharacterEncoding("UTF-8");

		request.setAttribute("nTireOverUsed", nTireOverUsed);
		request.setAttribute("sTireOverUsed", sTireOverUsed);
		request.setAttribute("nTireOverYear", nTireOverYear);
		request.setAttribute("sTireOverYear", sTireOverYear);
		request.setAttribute("oilOverUsed", oilOverUsed);
		request.setAttribute("elementOverUsed", elementOverUsed);
		request.setAttribute("batteryOverUsed", batteryOverUsed);
		request.setAttribute("shaken2month", shaken2month);
		request.setAttribute("shaken1month", shaken1month);

		HttpSession sessionLogin = request.getSession();
		String loginErrMsg = (String)sessionLogin.getAttribute("loginErrMsg");
		request.setAttribute("loginErrMsg", loginErrMsg);

		// セッションスコープの破棄
		sessionLogin.invalidate();

		//ログイン画面（main.jsp）へフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
		dispatcher.forward(request, response);

//		System.out.println(request.getSession());
//
//		//ログインエラーメッセージのセッションスコープ破棄
//		HttpSession sessionLogin = request.getSession();
//		sessionLogin.invalidate();
//
//		//管理者用の車両情報更新関連のセッションスコープ破棄
//		HttpSession sessionUpDate = request.getSession();
//		sessionUpDate.invalidate();
//
//		System.out.println(request.getSession());


	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//ログインボタン押下後の処理
		//入力されたログインIDとパスワードの取得
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String password = request.getParameter("password");
//		String forwardPath = "";	// ログイン失敗処理追加の修正に伴い削除

		// ログイン処理
		SharyoDAO sharyoDao = new SharyoDAO();
		UsersBean user = null;

		boolean isNumeric =  id.matches("[+-]?\\d*(\\.\\d+)?");
		System.out.println("数字？"+isNumeric);

		if(!id.equals("") && isNumeric) {

			user = sharyoDao.selectUser(Integer.parseInt(id));

			if(user!=null) {    // 修正（安永）

				if(user.getPassword().equals(password)) { //パスワードの照合

					//一般と管理者のフォワード先切り分け
					if(user.getUserNumber() == 9999) {	// 管理者がログインした場合

						HttpSession sessionAdmin = request.getSession();
						String admin = "using";
						sessionAdmin.setAttribute("admin", admin);

						System.out.println("ここここ");

						// 管理者用トップ画面にリダイレクト
						response.sendRedirect("http://localhost:8080/sharyoKanri_GG/AdminTopSV");

					} else {	//一般ユーザーがログインした場合
						// ユーザー情報をセッションスコープに保存
						HttpSession session = request.getSession();
						session.setAttribute("user", user);
						// ユーザー情報から車両情報を取得、スコープに保存
						SharyoBean sharyo = sharyoDao.selectSharyoUser(user.getUserNumber());
						session.setAttribute("sharyo", sharyo);
						// 車両情報からタイヤ・消耗品情報を取得、スコープに保存
						System.out.println(sharyo.getShabanInfo());
						System.out.println(sharyo.getShaban());
						TireBean tire = sharyoDao.selectTire(sharyo.getShabanInfo(), sharyo.getShaban());
						ShomohinBean shomohin = sharyoDao.selectShomohin(sharyo.getShabanInfo(), sharyo.getShaban());
						session.setAttribute("tire", tire);
						session.setAttribute("shomohin", shomohin);

						// 最後の改修にて削除
						/**********************************************************************************
						 *下記の２つの「現時点での使用距離」は「user_disp」での表示の為の値である。
						 * DBの各タイヤテーブルの「総使用距離」はここでは更新しない。
						 **********************************************************************************/
//						CalcuLogic cl = new CalcuLogic();
//
//						//ノーマルタイヤの「現時点での使用距離」算出
//						int nTkmTotal = cl.nTireKmNowTotal(sharyo, tire);
//
//						//スタッドレスタイヤの「現時点での使用距離」算出
//						int sTkmTotal =  cl.sTireKmNowTotal(sharyo, tire);
//
//						request.setAttribute("nTkmTotal", nTkmTotal);
//						request.setAttribute("sTkmTotal", sTkmTotal);
//

						// フォワード
						RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user_disp.jsp");
						dispatcher.forward(request, response);

					}
				} else {
					//ログイン失敗時の処理（IDパスワード不一致）
					String loginErrMsg = "ログインに失敗しました。"
							+ "\r\n入力内容を再確認してください。";

					// セッションスコープにログイン失敗メッセージを保存
					HttpSession sessionLogin = request.getSession();
					sessionLogin.setAttribute("loginErrMsg", loginErrMsg);

					// MainSVにリダイレクト
					response.sendRedirect("http://localhost:8080/sharyoKanri_GG/MainSV");
				}


			}else {
				//ログイン失敗時の処理（IDなし、user取得失敗）
				String loginErrMsg = "ログインに失敗しました。"
						+ "\r\n入力内容を再確認してください。";

				// セッションスコープにログイン失敗メッセージを保存
				// ※注意：ログイン成功時には要セッションスコープの破棄
				HttpSession sessionLogin = request.getSession();
				sessionLogin.setAttribute("loginErrMsg", loginErrMsg);

				// MainSVにリダイレクト
				response.sendRedirect("http://localhost:8080/sharyoKanri_GG/MainSV");

			}
		}else {
			//ログイン失敗時の処理（IDなし、user取得失敗）
			String loginErrMsg = "ログインに失敗しました。"
					+ "\r\n入力内容を再確認してください。";

			// セッションスコープにログイン失敗メッセージを保存
			// ※注意：ログイン成功時には要セッションスコープの破棄
			HttpSession sessionLogin = request.getSession();
			sessionLogin.setAttribute("loginErrMsg", loginErrMsg);

			// MainSVにリダイレクト
			response.sendRedirect("http://localhost:8080/sharyoKanri_GG/MainSV");

		}



	}
}
