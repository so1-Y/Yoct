package model;

import dao.SharyoDAO;

public class RegisterUserLogic {

	private static final String UsersBean = null;

	public void execute(UsersBeanX registerUser) {
		// Userテーブルにユーザー情報を１件追加
		//（SharyoDAOクラスのuserRegisterメソッドを呼び出す）
			SharyoDAO dao = new SharyoDAO();
			dao.userRegister(registerUser);
	}
}







