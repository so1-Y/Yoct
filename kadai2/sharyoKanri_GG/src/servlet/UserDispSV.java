package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/UserDispSV")
public class UserDispSV extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ログアウト処理
		//セッションスコープの破棄
		HttpSession session = request.getSession();
		session.invalidate();

		//TOP画面へフォワード
//		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
//		dispatcher.forward(request, response);

		//TOP画面へリダイレクト
		response.sendRedirect("/sharyoKanri_GG/MainSV");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//更新画面で戻るボタン押した時の処理

//		最後の改修にて削除
//		//セッションスコープから車両情報を取得
//		HttpSession session = request.getSession();
//		SharyoBean sharyo = (SharyoBean) session.getAttribute("sharyo");
//		TireBean tire = (TireBean) session.getAttribute("tire");
//
//		//タイヤの「現時点での使用距離」算出
//		if (sharyo.getTireType().trim().equals("ノーマル")) {
//			int nTkmTotal = tire.getnTireTotalKm() + (sharyo.getMileage() - tire.getnTireKm());
//			int sTkmTotal = tire.getsTireTotalKm();
//			request.setAttribute("nTkmTotal", nTkmTotal);
//			request.setAttribute("sTkmTotal", sTkmTotal);
//		} else {
//			int nTkmTotal = tire.getnTireTotalKm();
//			int sTkmTotal = tire.getsTireTotalKm() + (sharyo.getMileage() - tire.getsTireKm());
//			request.setAttribute("nTkmTotal", nTkmTotal);
//			request.setAttribute("sTkmTotal", sTkmTotal);
//		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user_disp.jsp");
		dispatcher.forward(request, response);
	}
}
