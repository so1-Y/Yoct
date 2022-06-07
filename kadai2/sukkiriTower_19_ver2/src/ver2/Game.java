package ver2;

import java.util.ArrayList;

public class Game implements GameInfo{

	Hero h;
	Monster m;
	MonsterDBConnect db;
	FileUtil fu;
	TimeSleep t = new TimeSleep();

	ArrayList<Monster> arrayM = new ArrayList<>();

	private int beatenMonsterNum =0;	// 倒したモンスターの数
	private int floor;					// 階数

	// ☆
	private int gameLevel = LEVEL_NORMAL;


	// ★

	//　☆１ここから
	Game(){
		this.floor = 1;
		this.h = new Hero();
	}

	public int getFloor() {
		return this.floor;
	}

	public int getGameLevel() {
		return gameLevel;
	}

	public void outline(){

		 // モンスターDB接続
		db = new MonsterDBConnect();     //★★★
		 // モンスター情報の取得
		this.arrayM = db.selectMonster();     //★★★


		// ゲーム準備
		gameSetting();

		System.out.println("∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞\r\n");
		System.out.println("今、すっきりタワーで、勇者"+h.getName()+"の最後の戦いが始まる！！\r\n");
		System.out.println("∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞\r\n\r\n");
		t.timeSleep(2);

		do{

			m = this.arrayM.get(this.floor-1);


//			// ◆モンスター種別ごとのインスタンス生成
//			monsterKindDefine(m);

			// 戦闘
			battle_scenario(m);
/*	★★★Debugモード
			battle_scenario(m);
*/

			this.floor++;

		}while(h.getHp() > 0 && this.floor <= FLOOR_NUM_MAX);

		if(h.getHp()<=0) {
			t.timeSleep(2);
			// ゲーム結果の記録
			fu.setGameResult(false, h);
			System.out.println("〓〓〓〓〓 G A M E  O V E R 〓〓〓〓〓\r\n\r\n");
			t.timeSleep(3);
		}else {
			// ゲーム結果の記録
			fu.setGameResult(true, h);
			System.out.println("〓〓〓〓〓 H A P P Y  E N D 〓〓〓〓〓\r\n\r\n");
			t.timeSleep(3);
		}

		// ゲーム結果の保存
		fu.recordGameResult();

	}

	public void battle_scenario(Monster m) {
/*	★★★Debugモード
	public void battle_scenario(Monster m) {
*/
		String hName = h.getName();
		String mName = m.getName()+m.getNote();
		int floor = this.getFloor();

		System.out.println("勇者"+h.getName()+"は"+floor+"階の扉を開けた\r\n");
		t.timeSleep(1);
		System.out.println("〓〓〓〓〓 B A T T L E  S T A R T 〓〓〓〓〓\r\n");
		t.timeSleep(1);

		fu.setFinalFoor(floor);

		System.out.println(mName+"が現れた！\r\n"+ "");
		t.timeSleep(1);

		if(m.getName().equals("魔王")) {
			System.out.println("魔王は"+h.getName()+"に話しかけてきた\r\n");
			m.talk(h);
			t.timeSleep(2);
		}

		do {
			h.showStatus();
			h.action(m);

			if(h.isBadEnd()) {
				h.setHp(0);
			}

			if(m.getName().equals("魔王") && m.getHp() == 0) {
				h.finalBattle(m);
			}

			if(m.getHp() <= 0) 	break;

			if(m.getHp()>0 && h.getHp()>0) {
				m.action(h);
			}



		}while(h.getHp()>0 && m.getHp()>0);


		battleResult();

	}

	private void battleResult() {
		if(m.getHp()==-1) {
			System.out.println("勇者"+h.getName()+"は次の階に上った\r\n");
			t.timeSleep(1);
			this.beatenMonsterNum++;
		}
		else if(h.getHp()>0 && this.beatenMonsterNum < NORMAL_MONSTER_NUM_MAX) {
			if(!m.getName().equals("カンダタ")) {
				System.out.println("勇者"+h.getName()+"は"+m.getName()+"を倒した");
				System.out.println("<"+m.getItem()+">を手に入れた\r\n");
				t.timeSleep(2);


				if(m.getItem().equals("ゴブリンのフン") && (!h.getEquipment().equals("カンダタのパーフェクトマスク"))) {
					System.out.println("辺りに強烈な臭気が漂う");
					for(int i = 0; i <3; i++ ) {
						System.out.print("．");
						t.timeSleep(1);
					}

					System.out.println(h.getName()+"は気絶した");
					for(int i = 0; i <3; i++ ) {
						System.out.print("．");
						t.timeSleep(1);
					}

					System.out.println(h.getName()+"は気絶している間に、他のモンスターに襲われた！");
					System.out.println("その後、"+h.getName()+"の姿を見たものはいない\r\n\r\n");
					t.timeSleep(1);
					h.setHp(0);
				}else {
					// 取得アイテムの格納
					h.iBox.setItemBox(m.getItem(),1);
				}
			}else {
				m.talk(h);
				if(this.gameLevel == LEVEL_EASY) {
					System.out.println("どこからともなく天の声が聞こえる\r\n");
					t.timeSleep(1);
					System.out.println("『EASYモードでプレイ中の勇者"+h.getName()+"よ、");
					System.out.println("　次の戦闘でこのマスクを必ず使いなさい。。。』\r\n");
					t.timeSleep(2);
				}
			}

			if(h.getHp()>0) {
				System.out.println("勇者"+h.getName()+"は次の階に上った\r\n");
				this.beatenMonsterNum++;
				t.timeSleep(2);
			}

		}else if(h.getHp()>0 && this.beatenMonsterNum == NORMAL_MONSTER_NUM_MAX){
			System.out.println("勇者"+h.getName()+"は"+m.getName()+"を倒した\r\n");
			t.timeSleep(2);

			System.out.println("世界に平和が戻った！！\r\n");
			t.timeSleep(3);

		}else if(h.isBadEnd()){
			// 処理なし
		}else {
			System.out.println("勇者"+h.getName()+"は倒れた");
			System.out.println("その後、"+h.getName()+"の姿を見たものはいない\r\n\r\n");
			t.timeSleep(1);
		}

	}

	private void gameSetting(){

		System.out.println("ゲームの難易度を選んでください");
		System.out.println("1:EASY（簡単）　2:NORMAL（普通）　3:VERY HARD（バリむず）　");
		System.out.println("SELECT >>");
		this.gameLevel = new java.util.Scanner(System.in).nextInt();

		// ☆
		if(this.gameLevel == LEVEL_NORMAL) {
			// プロパティファイル（Hero初期値）の設定
			PropUtil pu = new PropUtil();		//★★★
			pu.readHeroProp(this.h);			//★★★
			System.out.println("NORMALモードでゲームを開始します\r\n\r\n\r\n");
			t.timeSleep(2);

		}else if(this.gameLevel==LEVEL_EASY || this.gameLevel ==LEVEL_HARD){
			h.setHero(this.gameLevel);
			if(this.gameLevel==LEVEL_EASY) {
				System.out.println("EASYモードでゲームを開始します\r\n\r\n\r\n");
				t.timeSleep(2);
			}else {
				System.out.println("VERY HARDモードでゲームを開始します\r\n\r\n\r\n");
				t.timeSleep(2);
			}

		}else {
			h.setHero(SECRET_MODE);
			System.out.println("SECRETモードでゲームを開始します\r\n\r\n\r\n");
			t.timeSleep(2);

		}

		// ゲーム結果出力ファイルの生成
		fu = new FileUtil(this.h);

		//★

		//		System.out.println("ゲームのスピードを選んでください");
//		System.out.println("1:普通　2:超早い");
//		System.out.println("SELECT >>");
//		int i = new java.util.Scanner(System.in).nextInt();
//
//		if(i!=2) {
//			System.out.println("ロードが完了しました．．．\r\n\r\n");
//			System.out.println("ゲームスピード：[普通]で開始します\r\n\r\n\r\n\r\n");
////			t.setMode(NORMAL_SPEED);
//			t.timeSleep(1);
//			}else {
//			System.out.println("ゲームスピード：[超早い]で開始します\r\n\r\n\r\n\r\n");
////			t.setMode(HIGH_SPEED);
//			}


	}
	//　★１ここまで



//　以下、未実装　拡張可能
//	public void monsterKindDefine(Monster m) {
//
//		switch(m.getKind()) {
//			case "SLIME":
//				Slime s = new Slime(m);
//				break;
//
//			case "ZONBI":
//				Zonbi z = new Zonbi(m);
//				break;
//
//			case "GANG":
//				Gang g = new Gang(m);
//				break;
//
//			case "GOBLIN":
//				Goblin gb = new Goblin(m);
//				break;
//
//			case "DRAGON":
//				Dragon d = new Dragon(m);
//
//			case "BOSS":
//				Boss b = new Boss(m);
//		}

}
