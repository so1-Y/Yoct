package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.BuhinListBean;
import model.CalcuLogic;
import model.SharyoBean;
import model.ShomohinBean;
import model.TireBean;
import model.UsersBean;
import model.UsersBeanX;




/**
 * 車両管理関連のテーブルへのアクセスを担当する
 * DAOクラスです。
 */
public class SharyoDAO{
    private final String URL = "jdbc:postgresql://localhost:5432/sharyokanri";
    private final String USER = "postgres";
    private final String PASSWORD = "test";

	/**
	 * 「車両」テーブルから全てのデータを取得する
	 *　 作成者：上津原
	 */
    public List<SharyoBean> sharyoAll() {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        List<SharyoBean> SharyoLists = null;

		try {
	         /* JDBCドライバの定義 */
	         Class.forName("org.postgresql.Driver");

	         /* PostgreSQLへの接続 */
	         con = DriverManager.getConnection(URL, USER, PASSWORD);

	         /* SELECT文の準備 */
	         String sql = "SELECT * FROM Sharyo";
	         sql +=" ORDER BY car_user_number;";
	         st = con.prepareStatement(sql);


	         /* SELECT文の実行 */
	         rs = st.executeQuery();

	         /* 結果をリストに移し替える */
	         SharyoLists = makeResultData(rs);

		} catch(Exception e) {
			System.out.println("DBアクセス時にエラーが発生しました。");
			e.printStackTrace();
		} finally {
	         /* PostgreSQLとの接続を切断 */
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {}
			}

			if(st != null) {
				try {
					st.close();
				} catch (SQLException e) {}
			}

			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {}
			}
		}

        return SharyoLists;
    }


    //車番からデータを検索する

    /**
	 * 「車両」テーブルから車番でデータを検索し、検索結果を返します。
	 * 　作成者：上津原
	 */
    public List<SharyoBean> selectSharyo(String  shabanInfo, int shaban) {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        List<SharyoBean> SharyoLists = null;

		try {
	         /* JDBCドライバの定義 */
	         Class.forName("org.postgresql.Driver");

	         /* PostgreSQLへの接続 */
	         con = DriverManager.getConnection(URL, USER, PASSWORD);

	         /* SELECT文の準備 */
	         String sql = "SELECT * ";
	         sql += "FROM Sharyo ";
	         sql += "WHERE shaban_info= ? ";
	         sql += "AND shaban= ? ";
	         sql += "ORDER BY car_user_number; ";
	         st = con.prepareStatement(sql);
	         st.setString(1,shabanInfo );
	         st.setInt(2,shaban);

	         /* SELECT文の実行 */
	         rs = st.executeQuery();

	         /* 結果をリストに移し替える */
	         SharyoLists = makeResultData(rs);

		} catch(Exception e) {
			System.out.println("DBアクセス時にエラーが発生しました。");
			e.printStackTrace();
		} finally {
	         /* PostgreSQLとの接続を切断 */
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {}
			}

			if(st != null) {
				try {
					st.close();
				} catch (SQLException e) {}
			}

			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {}
			}
		}

        return SharyoLists;
    }

    /**
	 * 「車両」テーブルからユーザーIDでデータを検索し、一件の検索結果を返します。
	 *  作成者：加藤
	 */
    public SharyoBean selectSharyoUser(int userNumber) {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        List<SharyoBean> SharyoLists = null;

		try {
	         /* JDBCドライバの定義 */
	         Class.forName("org.postgresql.Driver");

	         /* PostgreSQLへの接続 */
	         con = DriverManager.getConnection(URL, USER, PASSWORD);

	         /* SELECT文の準備 */
	         String sql = "SELECT * ";
	         sql += "FROM Sharyo ";
	         sql += "WHERE car_user_number = ? ;";
	         st = con.prepareStatement(sql);
	         st.setInt(1, userNumber);

	         /* SELECT文の実行 */
	         rs = st.executeQuery();

	         /* 結果をリストに移し替える */
	         SharyoLists = makeResultData(rs);

		} catch(Exception e) {
			System.out.println("DBアクセス時にエラーが発生しました。");
			e.printStackTrace();
		} finally {
	         /* PostgreSQLとの接続を切断 */
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {}
			}

			if(st != null) {
				try {
					st.close();
				} catch (SQLException e) {}
			}

			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {}
			}
		}
		// 検索結果が1件の場合（主キーでの検索のため、結果は最大でも1件）
        // は検索結果をShohinRecordインスタンスとして返す。
        // 検索結果がない場合はnullを返す。
        if(SharyoLists != null && SharyoLists.size() == 1) {
       	 return SharyoLists.get(0);
        } else {
       	 return null;
        }
    }


    /**
     * 車両テーブルの検索結果をリストで返します。
     * 作成者：？
     */
    public ArrayList<SharyoBean> makeResultData(ResultSet rs) throws Exception {

    	// 全検索結果を格納するリストを準備
    	ArrayList<SharyoBean> SharyoLists = new ArrayList<SharyoBean>();

    	while(rs.next()) {
    		// 1行分のデータを読込む
    		String  shabanInfo = rs.getString("shaban_info");
    		int     shaban = rs.getInt("shaban");
    		String  shashu = rs.getString("shashu");
    		int  yearType = rs.getInt("year_type");
    		int  carUserNumber = rs.getInt("car_user_number");
    		String  carUser = rs.getString("car_user");
    		String  kudo =  rs.getString("kudo");
    		String shakenbi = rs.getString("shakenbi");
    		String tireType = rs.getString("tire_type");
    		String snowTire  = rs.getString("snow_tire");
    		String  lastChkDay = rs.getString("last_chk_day");
    		String lastChkRslt = rs.getString("last_chk_rslt");
    		String chkInfo = rs.getString("chk_info");
    		int  mileage = rs.getInt("mileage");



    		// 車両情報を格納するインスタンス()車両情報
    		SharyoBean SharyoList= new SharyoBean(  shabanInfo, shaban,  shashu, yearType,
    				 carUserNumber, carUser, kudo, shakenbi, tireType, snowTire ,
    				  lastChkDay, lastChkRslt, chkInfo,  mileage);

            // リストに1行分のデータを追加する
    		SharyoLists.add(SharyoList);
    	}
    	return SharyoLists;
    }

    /**
	 * 「ユーザー」テーブルからユーザーIDでデータを検索し、一件の検索結果を返します。
	 * 	作成者：加藤
	 */
    public UsersBean selectUser(int id) {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        List<UsersBean> usersList = null;

		try {
	         /* JDBCドライバの定義 */
	         Class.forName("org.postgresql.Driver");

	         /* PostgreSQLへの接続 */
	         con = DriverManager.getConnection(URL, USER, PASSWORD);

	         /* SELECT文の準備 */
	         String sql = "SELECT * ";
	         sql += "FROM users ";
	         sql += "WHERE user_number = ? ;";
	         st = con.prepareStatement(sql);
	         st.setInt(1, id);

	         /* SELECT文の実行 */
	         rs = st.executeQuery();

	         /* 結果をリストに移し替える */
	         usersList = makeUsersData(rs);

		} catch(Exception e) {
			System.out.println("DBアクセス時にエラーが発生しました。");
			e.printStackTrace();
		} finally {
	         /* PostgreSQLとの接続を切断 */
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {}
			}

			if(st != null) {
				try {
					st.close();
				} catch (SQLException e) {}
			}

			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {}
			}
		}
        // 検索結果が1件の場合（主キーでの検索のため、結果は最大でも1件）
        // は検索結果をShohinRecordインスタンスとして返す。
        // 検索結果がない場合はnullを返す。
        if(usersList != null && usersList.size() == 1) {
       	 return usersList.get(0);
        } else {
       	 return null;
        }
    }

    /**
	 * 「タイヤ」テーブルから車番でデータを検索し、一件の検索結果を返します。
	 * 　作成者：加藤
	 */
    public TireBean selectTire(String shabanInfo, int shaban) {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        List<TireBean> tireList = null;

		try {
	         /* JDBCドライバの定義 */
	         Class.forName("org.postgresql.Driver");

	         /* PostgreSQLへの接続 */
	         con = DriverManager.getConnection(URL, USER, PASSWORD);

	         /* SELECT文の準備 */
	         String sql = "SELECT * ";
	         sql += "FROM tire ";
	         sql += "WHERE shaban_info = ? ";
	         sql += "AND shaban = ? ; ";
	         st = con.prepareStatement(sql);
	         st.setString(1, shabanInfo);
	         st.setInt(2, shaban);

	         /* SELECT文の実行 */
	         rs = st.executeQuery();

	         /* 結果をリストに移し替える */
	         tireList = makeTireData(rs);

		} catch(Exception e) {
			System.out.println("DBアクセス時にエラーが発生しました。");
			e.printStackTrace();
		} finally {
	         /* PostgreSQLとの接続を切断 */
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {}
			}

			if(st != null) {
				try {
					st.close();
				} catch (SQLException e) {}
			}

			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {}
			}
		}
        // 検索結果が1件の場合（主キーでの検索のため、結果は最大でも1件）
        // は検索結果をShohinRecordインスタンスとして返す。
        // 検索結果がない場合はnullを返す。
        if(tireList != null && tireList.size() == 1) {
       	 return tireList.get(0);
        } else {
       	 return null;
        }
    }

    /**
	 * 「消耗品」テーブルから車番でデータを検索し、一件の検索結果を返します。
	 * 　作成者：加藤
	 */
    public ShomohinBean selectShomohin(String shabanInfo, int shaban) {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        List<ShomohinBean> shomohinList = null;

		try {
	         /* JDBCドライバの定義 */
	         Class.forName("org.postgresql.Driver");

	         /* PostgreSQLへの接続 */
	         con = DriverManager.getConnection(URL, USER, PASSWORD);

	         /* SELECT文の準備 */
	         String sql = "SELECT * ";
	         sql += "FROM shomohin ";
	         sql += "WHERE shaban_info = ? ";
	         sql += "AND shaban = ? ; ";
	         st = con.prepareStatement(sql);
	         st.setString(1, shabanInfo);
	         st.setInt(2, shaban);

	         /* SELECT文の実行 */
	         rs = st.executeQuery();

	         /* 結果をリストに移し替える */
	         shomohinList = makeShomohinData(rs);

		} catch(Exception e) {
			System.out.println("DBアクセス時にエラーが発生しました。");
			e.printStackTrace();
		} finally {
	         /* PostgreSQLとの接続を切断 */
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {}
			}

			if(st != null) {
				try {
					st.close();
				} catch (SQLException e) {}
			}

			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {}
			}
		}
        // 検索結果が1件の場合（主キーでの検索のため、結果は最大でも1件）
        // は検索結果をShohinRecordインスタンスとして返す。
        // 検索結果がない場合はnullを返す。
        if(shomohinList != null && shomohinList.size() == 1) {
       	 return shomohinList.get(0);
        } else {
       	 return null;
        }
    }


    /**
     * 「タイヤ」テーブルの検索結果をリストで返します。
     */
    public ArrayList<TireBean> makeTireData(ResultSet rs) throws Exception {

    	// 全検索結果を格納するリストを準備
    	ArrayList<TireBean> tireList = new ArrayList<TireBean>();

    	while(rs.next()) {
    		// 1行分のデータを読込む
    		String shabanInfo = rs.getString("shaban_info");
    		int shaban = rs.getInt("shaban");
    		int nTireYear = rs.getInt("n_tire_year");
    		String nTireDay = rs.getString("n_tire_day");
    		int nTireKm = rs.getInt("n_tire_km");
    		int nTireTotalKm = rs.getInt("n_tire_total_km");
    		int nTireNowKm = rs.getInt("n_tire_now_km");
    		int sTireYear = rs.getInt("s_tire_year");
    		String sTireDay = rs.getString("s_tire_day");
    		int sTireKm = rs.getInt("s_tire_km");
    		int sTireTotalKm = rs.getInt("s_tire_total_km");
    		int sTireNowKm = rs.getInt("s_tire_now_km");


    		// 1行分のデータを格納するインスタンス
    		TireBean tire = new TireBean(shabanInfo,
    									shaban,
    									nTireYear,
    									nTireDay,
										nTireKm,
   										nTireTotalKm,
   										nTireNowKm,
   										sTireYear,
   										sTireDay,
   										sTireKm,
   										sTireTotalKm,
   										sTireNowKm);

            // リストに1行分のデータを追加する
            tireList.add(tire);
    	}
    	return tireList;
    }

    /**
     * 「消耗品」テーブルの検索結果をリストで返します。
     */
    public ArrayList<ShomohinBean> makeShomohinData(ResultSet rs) throws Exception {

    	// 全検索結果を格納するリストを準備
    	ArrayList<ShomohinBean> shomohinList = new ArrayList<ShomohinBean>();

    	while(rs.next()) {
    		// 1行分のデータを読込む
    		String shabanInfo = rs.getString("shaban_info");
    		int shaban = rs.getInt("shaban");
    		String oilDay = rs.getString("oil_day");
    		int oilKm = rs.getInt("oil_km");
    		int oilNowKm = rs.getInt("oil_now_km");
    		String elementDay = rs.getString("element_day");
    		int elementKm = rs.getInt("element_km");
    		int elementNowKm = rs.getInt("element_now_km");
    		String batteryDay = rs.getString("battery_day");
    		int batteryKm = rs.getInt("battery_km");
    		int batteryNowKm = rs.getInt("battery_now_km");


    		// 1行分のデータを格納するインスタンス
    		ShomohinBean shomohin = new ShomohinBean(shabanInfo,
    											 	 shaban,
    											 	 oilDay,
    											 	 oilKm,
    											 	 oilNowKm,
    											 	 elementDay,
    											 	 elementKm,
    											 	 elementNowKm,
    											 	 batteryDay,
    											 	 batteryKm,
    											 	 batteryNowKm);

            // リストに1行分のデータを追加する
            shomohinList.add(shomohin);
    	}
    	return shomohinList;
    }

	/**
     * 「ユーザー」テーブルの検索結果をリストで返します。
     * 　作成者：加藤
     */
    public ArrayList<UsersBean> makeUsersData(ResultSet rs) throws Exception {

    	// 全検索結果を格納するリストを準備
    	ArrayList<UsersBean> userList = new ArrayList<UsersBean>();

    	while(rs.next()) {
    		// 1行分のデータを読込む
    		int userNumber = rs.getInt("user_number");
    		String userName = rs.getString("user_name");
    		String password = rs.getString("password");
    		String area = rs.getString("area");

    		// 1行分のデータを格納するインスタンス
    		UsersBean User = new UsersBean(userNumber,
    				                       userName,
    				                       password,
    				                       area);

            // リストに1行分のデータを追加する
            userList.add(User);
    	}
    	return userList;

    }
    /**
     * 「車両」テーブルの基本情報関連のデータを1件更新します。
     *  作成者：上津原
     */
    public int sharyoUpdate(SharyoBean sharyobean){
        Connection con = null;
        PreparedStatement st = null;
        int insCnt = 0;		// 更新件数

		try {
	         /* JDBCドライバの定義 */
	         Class.forName("org.postgresql.Driver");

	         /* PostgreSQLへの接続 */
	         con = DriverManager.getConnection(URL, USER, PASSWORD);

	         /* UPDATE文の準備 */
	         String sql = "";
	         sql = "UPDATE sharyo ";
	         sql += "SET car_user_number = ?, ";
	         sql += "    car_user = ?, ";
	         sql += "    tire_type = ?, ";
	         sql += "    snow_tire = ?, ";
	         sql += "    mileage = ? ";
	         sql += "WHERE shaban_info = ? ";
	         sql +=  "AND shaban = ?;";

	         st = con.prepareStatement(sql);
	         st.setInt(1, sharyobean.getCarUserNumber());
	         st.setString(2, sharyobean.getCarUser());
	         st.setString(3, sharyobean.getTireType());
	         st.setString(4, sharyobean.getSnowTire());
	         st.setInt(5,sharyobean.getMileage() );
	         st.setString(6, sharyobean.getShabanInfo());
	         st.setInt(7, sharyobean.getShaban());
	         /* UPDATE文の実行 */
	         insCnt = st.executeUpdate();

		} catch(Exception e) {
			System.out.println("DBアクセス時にエラーが発生しました。");
			e.printStackTrace();
		} finally {
	        /* PostgreSQLとの接続を切断 */
			if(st != null) {
				try {
					st.close();
				} catch (SQLException e) {}
			}

			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {}
			}
		}

        return insCnt;
    }


    /**
     * 「車両」テーブルの「点検項目」を1件更新します。
     *  作成者：加藤
     */
    public int sharyoCheckUpdate(SharyoBean sharyobean){
        Connection con = null;
        PreparedStatement st = null;
        int insCnt = 0;		// 更新件数

		try {
	         /* JDBCドライバの定義 */
	         Class.forName("org.postgresql.Driver");

	         /* PostgreSQLへの接続 */
	         con = DriverManager.getConnection(URL, USER, PASSWORD);

	         /* UPDATE文の準備 */
	         String sql = "";
	         sql = "UPDATE sharyo ";
	         sql += "SET last_chk_day = to_date(?, 'yyyy/mm/dd'), ";
	         sql += "last_chk_rslt = ? , ";
	         sql += "chk_info = ? ";
	         sql += "WHERE shaban_info = ? ";
	         sql +=  "AND shaban = ?;";

	         st = con.prepareStatement(sql);
	         st.setString(1, sharyobean.getLastChkDay());
	         st.setString(2, sharyobean.getLastChkRslt());
	         st.setString(3,sharyobean.getChk_info() );
	         st.setString(4, sharyobean.getShabanInfo());
	         st.setInt(5, sharyobean.getShaban());
	         /* UPDATE文の実行 */
	         insCnt = st.executeUpdate();

		} catch(Exception e) {
			System.out.println("DBアクセス時にエラーが発生しました。");
			e.printStackTrace();
		} finally {
	        /* PostgreSQLとの接続を切断 */
			if(st != null) {
				try {
					st.close();
				} catch (SQLException e) {}
			}

			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {}
			}
		}
        return insCnt;
    }

    /**
     * 「車両」テーブルの「使用タイヤ」と「総走行距離」を1件更新します。
     *  作成者：安永
     */
    public int sharyoTireTypeUpdate(SharyoBean sb){
        Connection con = null;
        PreparedStatement st = null;
        int insCnt = 0;		// 更新件数

		try {
	         /* JDBCドライバの定義 */
	         Class.forName("org.postgresql.Driver");

	         /* PostgreSQLへの接続 */
	         con = DriverManager.getConnection(URL, USER, PASSWORD);

	         /* UPDATE文の準備 */
	         String sql = "";
	         sql = "UPDATE sharyo ";
	         sql += "SET tire_type = ?, mileage = ? WHERE shaban_info = ? AND shaban = ?;";

	         System.out.println("db:mileage="+sb.getMileage());

	         st = con.prepareStatement(sql);
	         st.setString(1, sb.getTireType());
	         st.setInt(2, sb.getMileage());
	         st.setString(3, sb.getShabanInfo());
	         st.setInt(4, sb.getShaban());
	         /* UPDATE文の実行 */
	         insCnt = st.executeUpdate();
		} catch(Exception e) {
			System.out.println("DBアクセス時にエラーが発生しました。");
			e.printStackTrace();
		} finally {
	        /* PostgreSQLとの接続を切断 */
			if(st != null) {
				try {
					st.close();
				} catch (SQLException e) {}
			}

			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {}
			}
		}
        return insCnt;
    }

    /**
     * 「タイヤ」テーブルのデータを1件更新します。
     *  作成者：加藤
     */
    public int tireUpdate(TireBean tire) {
        Connection con = null;
        PreparedStatement st = null;
        int insCnt = 0;		// 更新件数

		try {
	         /* JDBCドライバの定義 */
	         Class.forName("org.postgresql.Driver");

	         /* PostgreSQLへの接続 */
	         con = DriverManager.getConnection(URL, USER, PASSWORD);

	         /* UPDATE文の準備 */
	         String sql = "";
	         sql = "UPDATE tire ";
	         sql += "SET n_tire_year = ?, ";
	         sql += "n_tire_day = to_date(?, 'yyyy/mm/dd'), ";
	         sql += "n_tire_km = ?, ";
	         sql += "n_tire_total_km = ?, ";
	         sql += "n_tire_now_km = ?, ";
	         sql += "s_tire_year = ?, ";
	         sql += "s_tire_day = to_date(?, 'yyyy/mm/dd'), ";
	         sql += "s_tire_km = ?, ";
	         sql += "s_tire_total_km = ?, ";
	         sql += "s_tire_now_km = ? ";
	         sql += "WHERE shaban_info = ? ";
	         sql += "AND shaban = ? ;";

	         st = con.prepareStatement(sql);
	         st.setInt(1, tire.getnTireYear());
	         st.setString(2, tire.getnTireDay());
	         st.setInt(3, tire.getnTireKm());
	         st.setInt(4, tire.getnTireTotalKm());
	         st.setInt(5, tire.getnTireTotalNowKm());
	         st.setInt(6, tire.getsTireYear());
	         st.setString(7, tire.getsTireDay());
	         st.setInt(8, tire.getsTireKm());
	         st.setInt(9, tire.getsTireTotalKm());
	         st.setInt(10, tire.getsTireTotalNowKm());
	         st.setString(11, tire.getShabanInfo());
	         st.setInt(12, tire.getShaban());

	         /* UPDATE文の実行 */
	         insCnt = st.executeUpdate();

		} catch(Exception e) {
			System.out.println("DBアクセス時にエラーが発生しました。");
			e.printStackTrace();
		} finally {
	        /* PostgreSQLとの接続を切断 */
			if(st != null) {
				try {
					st.close();
				} catch (SQLException e) {}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {}
			}
		}
        return insCnt;
    }

    /**
     * 「消耗品」テーブルのデータを1件更新します。
     *   作成者：加藤
     */
    public int shomohinUpdate(ShomohinBean shomohin) {
        Connection con = null;
        PreparedStatement st = null;
        int insCnt = 0;		// 更新件数

		try {
	         /* JDBCドライバの定義 */
	         Class.forName("org.postgresql.Driver");

	         /* PostgreSQLへの接続 */
	         con = DriverManager.getConnection(URL, USER, PASSWORD);

	         /* UPDATE文の準備 */
	         String sql = "";
	         sql = "UPDATE shomohin ";
	         sql += "SET oil_day = to_date(?, 'yyyy/mm/dd'), ";
	         sql += "oil_km = ?, ";
	         sql += "oil_now_km = ?, ";
	         sql += "element_day = to_date(?, 'yyyy/mm/dd'), ";
	         sql += "element_km = ?, ";
	         sql += "element_now_km = ?, ";
	         sql += "battery_day = to_date(?, 'yyyy/mm/dd'), ";
	         sql += "battery_km = ?, ";
	         sql += "battery_now_km = ? ";
	         sql += "WHERE shaban_info = ? ";
	         sql += "AND shaban = ? ;";

	         st = con.prepareStatement(sql);
	         st.setString(1, shomohin.getOilDay());
	         st.setInt(2, shomohin.getOilKm());
	         st.setInt(3, shomohin.getOilNowKm());
	         st.setString(4, shomohin.getElementDay());
	         st.setInt(5, shomohin.getElementKm());
	         st.setInt(6, shomohin.getElementNowKm());
	         st.setString(7, shomohin.getBatteryDay());
	         st.setInt(8, shomohin.getBatteryKm());
	         st.setInt(9, shomohin.getBatteryNowKm());
	         st.setString(10, shomohin.getShabanInfo());
	         st.setInt(11, shomohin.getShaban());

	         /* UPDATE文の実行 */
	         insCnt = st.executeUpdate();

		} catch(Exception e) {
			System.out.println("DBアクセス時にエラーが発生しました。");
			e.printStackTrace();
		} finally {
	        /* PostgreSQLとの接続を切断 */
			if(st != null) {
				try {
					st.close();
				} catch (SQLException e) {}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {}
			}
		}
        return insCnt;
    }




    /**
	 * Usersテーブルにデータを１件追加します。
	 * 作成者：安永
	 */
	public boolean userRegister( UsersBeanX ub) {
        try {
    		Class.forName("org.postgresql.Driver");
        }catch(Exception e) {
        	return false;
        }

		try(Connection con = DriverManager.getConnection(URL, USER, PASSWORD) ){

	         PreparedStatement st;
	         ResultSet rs;

	         // ※ドライバの定義なし

	         // SQL文の準備
	         String sql = "INSERT INTO users VALUES (?, ?, ?, ?)";

	         st = con.prepareStatement(sql);

	         st.setInt(1, Integer.parseInt(ub.getUserNumber()));
	         st.setString(2, ub.getUserName());
	         st.setString(3, ub.getPassword());
	         st.setString(4, ub.getArea());

             // SQL文（INSERT）の実行
             int result = st.executeUpdate();

             if(result!=1) {
            	 return false;
             }
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}

		return true;
	}

    /*********************************
     * 「Users」テーブルから         *
     * 社員番号で社員名を検索します。*
     * 作成者：安永　　　　　　      *
     *********************************/
    public String selectShainmei(int userNumber) {

    	String userName = null;

		try {
	         /* 1) PostgreSQLへの接続情報 */
	         Connection con;
	         PreparedStatement st;
	         ResultSet rs;

	         /* 2) JDBCドライバの定義 */
	         Class.forName("org.postgresql.Driver");

	         /* 3) PostgreSQLへの接続 */
	         con = DriverManager.getConnection(URL, USER, PASSWORD);

	         /* 4) SQL文の準備 */
	         /* 注意：1つのSQL文の後は必ず半角スペースを入れること*/
	         String sql = "";
	         sql = "SELECT user_name FROM Users ";
	         sql += "Where user_number = ?;";	// 1つ目の"？"

	         st = con.prepareStatement(sql);
	         st.setInt(1, userNumber);				// 1つ目の"？"に値（検索条件）をセット

	         /* 5) SQL文の実行 */
	         rs = st.executeQuery();

	         if(rs.next()) {
		         userName =rs.getString("user_name");
	         }else {
	        	 System.out.println("user_nameはありませんでした");
	         }

	         /* 6) PostgreSQLとの接続を切断 */
	         rs.close();
	         st.close();
	         con.close();

		} catch(Exception e) {
			System.out.println("DBアクセス時にエラーが発生しました。");
			e.printStackTrace();

			return userName;
		}

		return userName;

    }

    /********************************************
     * 新規車両の登録に伴い、関連するデータを   *
     * トランザクション処理にて                 *
     * ３つのテーブルに追加します。             *
     * 作成者：安永                             *
     ********************************************/
    public int registerNewSharyo(SharyoBean sb, TireBean tb, ShomohinBean smb) {

    	final int REGISTER_OK = 0;
    	final int REGISTER_NG_SHARYO =1;
    	final int REGISTER_NG_TIRE =10;
    	final int REGISTER_NG_SHOMOHIN =100;
    	final int DB_ERR=4;

    	int rsltNum = REGISTER_OK;

        try {
    		Class.forName("org.postgresql.Driver");
        }catch(Exception e) {
        	return DB_ERR;
        }

		try(Connection con = DriverManager.getConnection(URL, USER, PASSWORD) ){

			con.setAutoCommit(false);

			 PreparedStatement st;
	         ResultSet rs;

	         // 車両テーブルに新車両のデータを追加するSQL文の実行
	         String sql = "INSERT INTO sharyo VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	         st = con.prepareStatement(sql);

	        st.setString(1, sb.getShabanInfo());
            st.setInt(2, sb.getShaban());
            st.setString(3, sb.getShashu());
            st.setInt(4, sb.getYearType());
            st.setInt(5, sb.getCarUserNumber());
            st.setString(6, sb.getCarUser());
            st.setString(7, sb.getKudo());
            st.setString(8, sb.getShakenbi());
            st.setString(9, sb.getTireType());
            st.setString(10, sb.getSnowTire());
            st.setObject(11, null);
            st.setString(12, sb.getLastChkRslt());
            st.setString(13, sb.getChk_info());
            st.setInt(14, sb.getMileage());

            int result = st.executeUpdate();

            System.out.println("ShohinDAO183："+result);

            if(result!=1) {
            	rsltNum = REGISTER_NG_SHARYO;
//           	 return rsltNum;
            }

	        // Tireテーブルに新車両の情報を追加するSQL文の実行
            // SQL文の準備
//       	 sql = "INSERT INTO tire VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

             if(tb.getnTireDay()!=null) {
            	 sql = "INSERT INTO tire VALUES (?, ?, ?, to_date(?, 'yyyy/mm/dd'), ?, ?, ?, ?, ?, ?, ?, ?)";
             }else if(tb.getsTireDay()!=null){
            	 sql = "INSERT INTO tire VALUES (?, ?, ?, ?, ?, ?, ?, ?, to_date(?, 'yyyy/mm/dd'), ?, ?, ?)";
	         }else {
            	 sql = "INSERT INTO tire VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	         }

	         st = con.prepareStatement(sql);

	         st.setString(1, tb.getShabanInfo());
	         st.setInt(2, tb.getShaban());
	         st.setInt(3, tb.getnTireYear());
//        	 st.setObject(4,null);

	         if(tb.getnTireDay()!=null) {
	        	 st.setString(4, tb.getnTireDay());
	         }else{
	        	 st.setObject(4,null);
	         }
	         st.setInt(5, tb.getnTireKm());
	         st.setInt(6, tb.getnTireTotalKm());
	         st.setInt(7, tb.getnTireTotalNowKm());		// ★err
	         st.setInt(8,tb.getsTireYear());
//        	 st.setObject(9,null);

	         if(tb.getsTireDay()!=null) {
	        	 st.setString(9, tb.getsTireDay());
	         }else{
	        	 st.setObject(9,null);
	         }

	         st.setInt(10, tb.getsTireKm());
	         st.setInt(11,tb.getsTireTotalKm());
	         st.setInt(12,0);

	         result = st.executeUpdate();

            if(result!=1) {
            	rsltNum = rsltNum + REGISTER_NG_TIRE;
            	return rsltNum;
            }

	        // shomohinテーブルに新車両の情報を追加するSQL文の実行
            // SQL文の準備

//            sql = "INSERT INTO shomohin VALUES (?, ?, ?, ?, ?, ?, ?, ?)";


	         sql = "INSERT INTO shomohin VALUES (?, ?, "
	         		+ "to_date(?, 'yyyy/mm/dd'), ?, ?,"
	         		+ "to_date(?, 'yyyy/mm/dd'), ?, ?,"
	         		+ "to_date(?, 'yyyy/mm/dd'), ?, ?)";


	         st = con.prepareStatement(sql);

	         st.setString(1, smb.getShabanInfo());
             st.setInt(2, smb.getShaban());
             st.setString(3,smb.getOilDay());
             st.setInt(4, smb.getOilKm());
             st.setInt(5, 0);
             st.setString(6,smb.getElementDay());
             st.setInt(7, smb.getElementKm());
             st.setInt(8, 0);
             st.setString(9,smb.getBatteryDay());
             st.setInt(10, smb.getBatteryKm());
             st.setInt(11, 0);

             // SQL文（INSERT）の実行
             result = st.executeUpdate();

             System.out.println("ShohinDAO183："+result);

             if(result!=1) {
             	rsltNum = rsltNum + REGISTER_NG_SHOMOHIN;
             }

             if(rsltNum>0) {	// いずれかのデータ追加処理に失敗した場合
            	 con.rollback();
             }else {			// 全てのデータ追加処理に成功した場合
            	 con.commit();
             }

		}catch(SQLException e){
			e.printStackTrace();
			return DB_ERR;
		}
    	return rsltNum;
    }

    /**
     * shraryoテーブルに１件のデータを追加します。
     * 作成者：安永
     */
	public boolean addSharyo(SharyoBean sb) {

        try {
    		Class.forName("org.postgresql.Driver");
        }catch(Exception e) {
        	return false;
        }

		try(Connection con = DriverManager.getConnection(URL, USER, PASSWORD) ){

	         PreparedStatement st;
	         ResultSet rs;

	         // ※ドライバの定義なし

	         // SQL文の準備
	         String sql = "INSERT INTO sharyo VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	         st = con.prepareStatement(sql);

	         st.setString(1, sb.getShabanInfo());
             st.setInt(2, sb.getShaban());
             st.setString(3, sb.getShashu());
             st.setInt(4, sb.getYearType());
             st.setInt(5, sb.getCarUserNumber());
             st.setString(6, sb.getCarUser());
             st.setString(7, sb.getKudo());
             st.setString(8, sb.getShakenbi());
             st.setString(9, sb.getTireType());
             st.setString(10, sb.getSnowTire());
             st.setObject(11, null);
             st.setString(12, sb.getLastChkRslt());
             st.setString(13, sb.getChk_info());
             st.setInt(14, sb.getMileage());

//             Date today = new Date();
//             st.setDate(6, new java.sql.Date(today.getTime()));
//             st.setDate(6, new java.sql.Date(new Date().getTime()));

             // SQL文（INSERT）の実行
             int result = st.executeUpdate();

             System.out.println("ShohinDAO183："+result);

             if(result!=1) {
            	 return false;
             }
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}

		return true;
	}

    /**
     * Tireテーブルに１件のデータを追加します。
     */
	public boolean addTire(TireBean tb) {

        try {
    		Class.forName("org.postgresql.Driver");
        }catch(Exception e) {
        	return false;
        }

		try(Connection con = DriverManager.getConnection(URL, USER, PASSWORD) ){

	         PreparedStatement st;
	         ResultSet rs;

	         // ※ドライバの定義なし

	         // SQL文の準備
	         String sql = "INSERT INTO tire VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	         st = con.prepareStatement(sql);

	         st.setString(1, tb.getShabanInfo());
             st.setInt(2, tb.getShaban());
             st.setInt(3, tb.getnTireYear());
             st.setObject(4,null);
             st.setInt(5, tb.getnTireKm());
             st.setInt(6, tb.getnTireTotalKm());
             st.setInt(7,tb.getnTireYear());
             st.setObject(8, null);
             st.setInt(9, tb.getsTireTotalKm());
             st.setInt(10,tb.getsTireYear());

             // SQL文（INSERT）の実行
             int result = st.executeUpdate();

             System.out.println("ShohinDAO183："+result);

             if(result!=1) {
            	 return false;
             }
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}

		return true;
	}

    /**
     * Shomohinテーブルに１件のデータを追加します。
     */
	public boolean addShomohin(ShomohinBean smb) {

        try {
    		Class.forName("org.postgresql.Driver");
        }catch(Exception e) {
        	return false;
        }

		try(Connection con = DriverManager.getConnection(URL, USER, PASSWORD) ){

	         PreparedStatement st;
	         ResultSet rs;

	         // ※ドライバの定義なし

	         // SQL文の準備
	         String sql = "INSERT INTO shomohin VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

	         st = con.prepareStatement(sql);

	         st.setString(1, smb.getShabanInfo());
             st.setInt(2, smb.getShaban());
             st.setObject(3,null);
             st.setInt(4, smb.getOilKm());
             st.setObject(5,null);
             st.setInt(6, smb.getElementKm());
             st.setObject(7,null);
             st.setInt(8, smb.getBatteryKm());

             // SQL文（INSERT）の実行
             int result = st.executeUpdate();

             System.out.println("ShohinDAO183："+result);

             if(result!=1) {
            	 return false;
             }
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}

		return true;
	}

    /********************************************
     * 車両の削除に伴い、関連する全てのテーブル *
     * をトランザクション処理にて削除します。   *
     * 作成者：安永                             *
     ********************************************/
    public int deleteSharyo(String shabanInfo, int shaban) {

    	final int REGISTER_OK = 0;
    	final int REGISTER_NG_SHARYO =1;
    	final int REGISTER_NG_TIRE =10;
    	final int REGISTER_NG_SHOMOHIN =100;
    	final int DB_ERR=4;

    	int rsltNum = REGISTER_OK;

        try {
    		Class.forName("org.postgresql.Driver");
        }catch(Exception e) {
        	return DB_ERR;
        }

		try(Connection con = DriverManager.getConnection(URL, USER, PASSWORD) ){

			con.setAutoCommit(false);

			 PreparedStatement st;
	         ResultSet rs;

	         // 車両テーブルから条件に指定された1台の情報を削除
	         /* SELECT文の準備 */
	         String sql = "";
	         sql = "DELETE FROM sharyo ";
	         sql += "WHERE shaban_info = ? AND shaban = ?;";

	         st = con.prepareStatement(sql);
	         st.setString(1, shabanInfo);
	         st.setInt(2, shaban);

	         /* DELETE文の実行 */
            int result = st.executeUpdate();

            System.out.println("ShohinDAO183："+result);

            if(result!=1) {
            	rsltNum = REGISTER_NG_SHARYO;
//           	 return rsltNum;
            }

	        // Tireテーブルから条件に指定された1台の情報を削除
	         /* SELECT文の準備 */
	         sql = "";
	         sql = "DELETE FROM tire ";
	         sql += "WHERE shaban_info = ? AND shaban = ?;";

	         st = con.prepareStatement(sql);
	         st.setString(1, shabanInfo);
	         st.setInt(2, shaban);

	         /* DELETE文の実行 */
             result = st.executeUpdate();

            if(result!=1) {
            	rsltNum = rsltNum + REGISTER_NG_TIRE;
            	return rsltNum;
            }

	        // shomohinテーブルから条件に指定された1台の情報を削除
	         /* SELECT文の準備 */
	         sql = "";
	         sql = "DELETE FROM shomohin ";
	         sql += "WHERE shaban_info = ? AND shaban = ?;";

	         st = con.prepareStatement(sql);
	         st.setString(1, shabanInfo);
	         st.setInt(2, shaban);

	         /* DELETE文の実行 */
            result = st.executeUpdate();

           if(result!=1) {
           	rsltNum = rsltNum + REGISTER_NG_TIRE;
           	return rsltNum;
           }

             System.out.println("ShohinDAO183："+result);

             if(result!=1) {
             	rsltNum = rsltNum + REGISTER_NG_SHOMOHIN;
             }

             if(rsltNum>0) {	// いずれかのデータ追加処理に失敗した場合
            	 con.rollback();
             }else {			// 全てのデータ追加処理に成功した場合
            	 con.commit();
             }

		}catch(SQLException e){
			e.printStackTrace();
			return DB_ERR;
		}
    	return rsltNum;
    }


    /**
     * 「Sharyo」テーブルから車番情報でSharyoテーブルのデータを１件削除します。
     *  作成者：安永
     */
    public boolean delSharyo(String shabanInfo, int shaban) {
        /* PostgreSQLへの接続情報 */
        Connection con = null;
        PreparedStatement st = null;
        int delCnt=0;

		try {
	         /* JDBCドライバの定義 */
	         Class.forName("org.postgresql.Driver");

	         /* PostgreSQLへの接続 */
	         con = DriverManager.getConnection(URL, USER, PASSWORD);

	         /* SELECT文の準備 */
	         String sql = "";
	         sql = "DELETE FROM sharyo ";
	         sql += "WHERE shaban_info = ? AND shaban = ?;";

	         st = con.prepareStatement(sql);
	         st.setString(1, shabanInfo);
	         st.setInt(2, shaban);

	         /* DELETE文の実行 */
	         delCnt = st.executeUpdate();
	         System.out.println("DAO210:"+delCnt + "行削除されました。");

		} catch(Exception e) {
			System.out.println("DBアクセス時にエラーが発生しました。");
			e.printStackTrace();
			return false;
		} finally {
	         /* PostgreSQLとの接続を切断 */
			if(st != null) {
				try {
					st.close();
				} catch (SQLException e) {}
			}

			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {}
			}
		}
		return true;
    }

    /**
     * 「Shomohin」テーブルから車番情報でデータを１件削除します。
     *  作成者：安永
     */
    public boolean delShomohin(String shabanInfo, int shaban) {
        /* PostgreSQLへの接続情報 */
        Connection con = null;
        PreparedStatement st = null;
        int delCnt=0;

		try {
	         /* JDBCドライバの定義 */
	         Class.forName("org.postgresql.Driver");

	         /* PostgreSQLへの接続 */
	         con = DriverManager.getConnection(URL, USER, PASSWORD);

	         /* SELECT文の準備 */
	         String sql = "";
	         sql = "DELETE FROM shomohin ";
	         sql += "WHERE shaban_info = ? AND shaban = ?;";

//	         System.out.println("shohinDAO 99行目:id="+shaban);

	         st = con.prepareStatement(sql);
	         st.setString(1, shabanInfo);
	         st.setInt(2, shaban);

	         /* DELETE文の実行 */
	         delCnt = st.executeUpdate();
	         System.out.println("DAO210:"+delCnt + "行削除されました。");

		} catch(Exception e) {
			System.out.println("DBアクセス時にエラーが発生しました。");
			e.printStackTrace();
			return false;
		} finally {
	         /* PostgreSQLとの接続を切断 */
			if(st != null) {
				try {
					st.close();
				} catch (SQLException e) {}
			}

			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {}
			}
		}
		return true;
    }


    /**
     * 「Sharyo」「tire」「shomohin」を結合したテーブルから部品一覧用のデータを全取得します。
     *  作成者：安永　0409
     */
    public List<BuhinListBean> selectBuhinAll() {

		List<BuhinListBean> blbList = new ArrayList<>();

        // ドライバの定義
        try {

    		Class.forName("org.postgresql.Driver");
        }catch(Exception e) {
        	return blbList;
        }

		try(Connection con = DriverManager.getConnection(URL, USER, PASSWORD) ){

			PreparedStatement st;
	         ResultSet rs;

	         // SQL文の準備
	         /* SELECT文の準備 */
	         String sql = "select sh.shaban_info, sh.shaban, sh.car_user, sh.mileage, "
	         		+ "t.n_Tire_year, t.n_Tire_day, t.n_Tire_total_km, t.n_Tire_now_km, "
	         		+ "t.s_Tire_year, t.s_Tire_day, t.s_Tire_total_km,  t.s_Tire_now_km,"
	         		+ "s.oil_day, s.oil_km, s.oil_now_km, "
	         		+ "s.element_day, s.element_km, s.element_now_km, "
	         		+ "s.battery_day, s.battery_km, s.battery_now_km "
	         		+ "from sharyo as sh "
	         		+ "inner join tire as t on (sh.shaban_info=t.shaban_info and sh.shaban = t.shaban) "
	         		+ "inner join shomohin as s on (sh.shaban_info=s.shaban_info and sh.shaban=s.shaban);";
	         st = con.prepareStatement(sql);

	         /* SELECT文の実行 */
	         rs = st.executeQuery();

	         /* 結果をリストに移し替える */
	         blbList = makeBuhinList(rs);

		}catch(Exception e){
			e.printStackTrace();
			return blbList;
		}

    	return blbList;
    }

    /**
     * 部品一覧をリストで返します。
     * 作成者：安永
     */
    public ArrayList<BuhinListBean> makeBuhinList(ResultSet rs) throws Exception {

    	// 全検索結果を格納するリストを準備
		ArrayList<BuhinListBean> blbList = new ArrayList<>();

    	while(rs.next()) {
    		// 1行分のデータを読込む
    		String  shabanInfo = rs.getString("shaban_info");
    		int     shaban = rs.getInt("shaban");
    		String  carUser = rs.getString("car_user");
    		int  mileage = rs.getInt("mileage");

    		// DBから取得した値は製造年週(wwyy)なので、現在日時を元に
    		// 使用年数を計算し設定する
    		CalcuLogic cl = new CalcuLogic();

    		String nTireDay = rs.getString("n_tire_day");
    		int nTireTotalKm = rs.getInt("n_tire_total_km");
    		int nTireNowKm = rs.getInt("n_tire_now_km");
    		int nTireYear = rs.getInt("n_tire_year");
		    nTireYear = cl.usedTireYearCalcu(nTireYear);



    		String sTireDay = rs.getString("s_tire_day");
      		int sTireTotalKm = rs.getInt("s_tire_total_km");
      		int sTireNowKm = rs.getInt("s_tire_now_km");
    		int sTireYear = rs.getInt("s_tire_year");
			sTireYear = cl.usedTireYearCalcu(sTireYear);

			System.out.println();
			System.out.println("@DAOsnow："+shabanInfo+shaban+sTireNowKm);

    		if(sTireDay==null) {
    			sTireDay="未使用";
    			sTireTotalKm=0;
    			sTireYear=0;
    		}

    		String oilDay = rs.getString("oil_day");
    		int oilKm = rs.getInt("oil_km");
    		int oilNowKm = rs.getInt("oil_now_km");
    		String elementDay = rs.getString("element_day");
    		int elementKm = rs.getInt("element_km");
    		int elementNowKm = rs.getInt("element_now_km");
    		String batteryDay = rs.getString("battery_day");
    		int batteryKm = rs.getInt("battery_km");
    		int batteryNowKm = rs.getInt("battery_now_km");

    		// 部品情報を格納するインスタンス()を生成
    		BuhinListBean blb = new BuhinListBean(shabanInfo, shaban, carUser, mileage,
    				 nTireYear, nTireDay, nTireTotalKm, nTireNowKm,
    				 sTireYear, sTireDay, sTireTotalKm, sTireNowKm,
    				 oilDay, oilKm, oilNowKm,
    				 elementDay, elementKm, elementNowKm,
    				 batteryDay, batteryKm, batteryNowKm);

    		// リストに1行分のデータを追加する
    		blbList.add(blb);
    	}
    	return blbList;
    }


}