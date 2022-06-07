package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/AdminLoginSV")
// 管理者のログイン状態を保持するためのサーブレット
public class AdminLoginSV extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 管理者のログイン状態をセッションスコープに保存
		HttpSession sessionAdmin = request.getSession();
		sessionAdmin.setAttribute("admin", "using");

		// AdminTopにリダイレクト
		response.sendRedirect("http://localhost:8080/sharyoKanri_GG/AdminTopSV");

	}


}
