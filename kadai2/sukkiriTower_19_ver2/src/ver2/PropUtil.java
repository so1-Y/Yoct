package ver2;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * プロパティファイルに関する
 * ユーティリティクラス
 */
public class PropUtil {

	// プロパティファイルパス
	private static final String FILE_PATH = "c:\\config\\SukkiriTower.properties";

	// キー定数
	public static final String HERO_NAME = "ミナト";					// 処理モード
	public static final String CSV_OUTPUT_PATH = "csvOutputPath";	// CSV出力先パス

	Hero h;

	/*
	 * 受け取ったキーに対応するプロパティ値を返します。
	 * キーに対応するプロパティ値がない場合は空文字を返します。
	 */
	public void readHeroProp(Hero h) {
		String value = "";
		FileReader fr = null;

		try {
			fr = new FileReader(PropUtil.FILE_PATH);
			// プロパティファイル専用のAPI
			Properties p = new Properties();
			// 【重要】loadメソッドを用いて開いたファイルの内容をロード
			p.load(fr);

			// プロパティファイルのキーに対応する値を取得
			h.setName(p.getProperty("hero_name"));
			h.setHp(Integer.parseInt(p.getProperty("hero_hp")));
			h.setMp(Integer.parseInt(p.getProperty("hero_mp")));
			h.setPower(Integer.parseInt(p.getProperty("hero_power")));
			h.setDefense(Integer.parseInt(p.getProperty("hero_defense")));
			h.setSpecialPower(Integer.parseInt(p.getProperty("hero_special_power")));

		} catch (Exception e) {
			System.out.println("プロパティ情報の取得に失敗しました");
			e.printStackTrace();
		} finally {
			if(fr != null) {
				try {
					fr.close();
				} catch (IOException e) {}
			}
		}
	}
}
