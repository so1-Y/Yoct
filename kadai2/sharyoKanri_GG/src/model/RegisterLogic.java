package model;

import dao.SharyoDAO;

public class RegisterLogic {

//		public boolean userRegister(OldUsersBean ub) {
//
//			// 入力値チェック（後から作成）
//
//			// データベース登録処理
//			SharyoDAO sd = new SharyoDAO();
//
//			boolean rslt = sd.userRegister(ub);
//
//			//★確認用
//			System.out.println("rslt:"+rslt);
//
//			return rslt;
//		}

		public String carRegisterLG(SharyoBean sb, TireBean tb, ShomohinBean smb) {

			String msg = "登録が完了しました";

			// データベース登録処理
			SharyoDAO sd = new SharyoDAO();
			int rsltNum = sd.registerNewSharyo(sb,tb,smb);

			if(rsltNum>0) {
				msg="登録に失敗しました";
				System.out.println("新規車両登録失敗理由："+rsltNum);
			}

			return msg;

		}

//		public boolean carDeleteLG(String shabanInfo, int shaban) {
//
//			// データベースから削除
//			SharyoDAO sd = new SharyoDAO();
//			boolean rslt = sd.delSharyo(shabanInfo, shaban);
//
//			System.out.println("RL32,rslt="+rslt);
//
//			return rslt;
//
//		}






}
