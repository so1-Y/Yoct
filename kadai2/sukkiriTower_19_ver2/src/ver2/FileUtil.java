package ver2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileUtil {

	// 保存先ファイルパス
	private static final String FILE_PATH = "c:\\result\\SukkiriResult.txt";


	private String heroName;

	private int initialHp;
	private int initialMp;
	private int finalHp;
	private int finalMp;

	private int finalPow;
	private int finalDef;
	private int finalSp;

	private String startTime;
	private String endTime;

	private boolean gameResultFlg;
	private String gameResult;

	private int finalFloor;

	SimpleDateFormat f = new SimpleDateFormat("yyyy年MM月dd日(E) HH時mm分ss秒");

	public FileUtil(Hero h) {
		Date now = new Date();
		this.startTime = f.format(now);
//		System.out.println(this.startTime);  // ★

		this.heroName = h.getName();
		this.initialHp = h.getHp();
		this.initialMp = h.getMp();
	}

	public void setFinalHp(int finalHp) {
		this.finalHp = finalHp;
	}

	public void setFinalMp(int finalMp) {
		this.finalMp = finalMp;
	}

	public void setGameResultFlg(boolean gResult) {
		this.gameResultFlg = gResult;

		if(this.gameResultFlg) {
			this.gameResult = " GAME CLEAR!! ";
		}else {
			this.gameResult = " GAME FAILED...";
		}

	}


	public void setFinalFoor(int finalFoor) {
		this.finalFloor = finalFoor;
	}

	public void setFinalPow(int finalPow) {
		this.finalPow = finalPow;
	}

	public void setFinalDef(int finalDef) {
		this.finalDef = finalDef;
	}

	public void setFinalSp(int finalSp) {
		this.finalSp = finalSp;
	}

	public void setGameResult(boolean gameResult, Hero h) {
		setGameResultFlg(gameResult);
		setFinalHp(h.getHp());
		setFinalMp(h.getMp());
		setFinalDef(h.getDefense());
		setFinalPow(h.getPower());
		setFinalSp(h.getSpecialPower());
	}

	public void recordGameResult() {



		Date now = new Date();
		this.endTime = f.format(now);

		String recordDate = "[ゲーム結果]\r\n"
				+ "ゲーム開始："+this.startTime+"\r\n"
				+ "ゲーム終了："+this.endTime+"\r\n"
				+ "\r\n"
				+ "結果："+ this.gameResult+"\r\n"
				+ "\r\n"
				+ "プレイヤー情報：	\r\n"
				+ "（名前）"+this.heroName+"\r\n"
				+ "（HP）"+this.initialHp+" → "+this.finalHp+"	\r\n"
				+ "（MP）"+this.initialMp+" → "+this.finalMp+"	\r\n"
				+ "（攻撃力）"+finalPow+"		\r\n"
				+ "（防御力）"+finalDef+"	\r\n"
				+ "（必殺技威力）"+finalSp+"	\r\n"
				+ "\r\n"
				+ "進んだ階数："+finalFloor+"	\r\n"
				+ "";

		try (
				// 書き込みファイルを開く（無い場合は新規作成）
				FileWriter fw = new FileWriter(FILE_PATH);
				// バッファリングフィルタを接続
				BufferedWriter bw = new BufferedWriter(fw);

			){
				bw.write(recordDate);		// 入力内容を書込
				bw.newLine();			// 改行文字を挿入
			}catch(IOException e ) {
				System.out.println("ファイル入力エラーです");
			}

		System.out.println("\r\n\r\n");
		System.out.println(recordDate);
	}




}
