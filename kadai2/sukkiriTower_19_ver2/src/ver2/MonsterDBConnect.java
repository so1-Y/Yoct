package ver2;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MonsterDBConnect {

	private final String URL = "jdbc:postgresql://localhost:5432/rpg";
	private final String USER = "postgres";
	private final String PASSWORD = "test";
	ArrayList<Monster> arrayM = new ArrayList<>();


    public ArrayList<Monster> selectMonster( ) {

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
	         String sql = "";
	         sql = "SELECT * FROM monster2 "
	         		+ "order by monster_no";		// ここ！！！
//	         sql += "WHERE monster_no = ?;";  // 旧ver1

	         st = con.prepareStatement(sql);

//	         st.setInt(1, monster_no);		//旧ver1

	         /* 5) SQL文の実行 */
	         rs = st.executeQuery();

	         /* 6) モンスター情報の取得と設定 */
	         arrayM = getAndSetData(rs);

	         /* 7) PostgreSQLとの接続を切断 */
	         rs.close();
	         st.close();
	         con.close();

		} catch(Exception e) {
			System.out.println("DBアクセス時にエラーが発生しました。");
			e.printStackTrace();
		}

		return arrayM;
    }

    public ArrayList<Monster> getAndSetData( ResultSet rs) throws Exception {

    	while(rs.next()) {
        	Monster m = new Monster();

    		m.setMonster_no(rs.getInt("monster_no"));
    		m.setName(rs.getString("name"));
    		m.setHp(rs.getInt("hp"));
    		m.setMp(rs.getInt("mp"));
    		m.setPower(rs.getInt("power"));
    		m.setDefense(rs.getInt("defense"));
    		m.setSpecialPower(rs.getInt("special_power"));
    		m.setItem(rs.getString("item"));
    		m.setKind(rs.getString("kind"));
    		m.setNote(rs.getString("note"));

//    		System.out.println(rs.getInt("monster_no"));
//    		System.out.println(rs.getString("name"));

    		arrayM.add(m);
    		}
//
//    	System.out.println(arrayM.get(0).getName());
//    	System.out.println(arrayM.get(1).getName());


    	return arrayM;

    }

}
