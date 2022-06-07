package ver2;
import static ver2.Status.*;

public class Hero extends Character{

	public final int ATTACK = 1;
	public final int SPECIAL = 2;
	public final int ESCAPE = 3;
	public final int TALK = 4;
	public final int USE_ITEM = 5;

	private String name;
	private String equipment ="";

	boolean badEnd = false;

	ItemBox iBox = new ItemBox();
	String[] itemName = new String[ITEM_NUM_MAX];		// ここ！

	public Hero() {
		this.name="ウー";
		setHp(50);
		setMp(5);
		setPower(40);
		setDefense(10);
		setSpecialPower(100);
	}

	public void setHero(int level) {

		switch(level){
			case LEVEL_EASY			:
				this.name="アサカ";
				setHp(200);
				setMp(5);
				setPower(40);
				setDefense(10);
				setSpecialPower(100);
				break;

			case LEVEL_HARD			:
				this.name="ミナト";
				setHp(40);
				setMp(2);
				setPower(35);
				setDefense(10);
				setSpecialPower(100);
				break;

//			case SECRET_MODE			:
//				this.name="ミナト";
//				setHp(50);
//				setMp(5);
//				setPower(40);
//				setDefense(10);
//				setSpecialPower(100);
//				break;
		}


	}


	public boolean isBadEnd() {
		return badEnd;
	}


	public void setBadEnd(boolean badEnd) {
		this.badEnd = badEnd;
	}


	public String getEquipment() {
		return equipment;
	}


	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	public Map<String, Integer> getItemBox() {
//		return itemBox;
//	}
//
//	public void setItemBox(Map<String, Integer> itemBox) {
//		this.itemBox = itemBox;
//	}



	public void showStatus() {
		System.out.println("－－"+this.name+"のステータス－－－－－\r\n"
				+ "【HP:"+getHp()+" MP:"+getMp()+"】\r\n"
				+ "－－－－－－－－－－－－－－－－");
	}

	public void action(Monster m) {
//		t.setMode(t.getGameSpeed());

		System.out.println("INPUT >> 1:攻撃　2:必殺技　3:逃げる");
		System.out.println("         4:話しかける　5:道具を使う");
		System.out.println(">>");
		int i = new java.util.Scanner(System.in).nextInt();

		switch( i ){
			case ATTACK:
				attack(m);
				break;

			case SPECIAL:
				special(m);
				break;

			case ESCAPE:
				escape(m);
				break;

			case TALK:
				System.out.println(this.name+"は"+m.getName()+"に話しかけた\r\n");
				t.timeSleep(1);
				if(m.getStatus()!=SHOCKED) {
					talk(m);
				}
				break;

			case USE_ITEM:
				if(!iBox.itemBox.isEmpty()) {
					chooseItem(m);
				}else {
					System.out.println(this.name+"は使える道具を持っていなかった\r\n");
					t.timeSleep(1);
				}
		}
	}


	private void escape( Monster m) {
//		t.setMode(t.getGameSpeed());

		System.out.println(this.getName()+"は逃げ出した！");
		for(int i = 0; i <2; i++ ) {
			System.out.print("．");
			t.timeSleep(1);
		}
		System.out.println("．\r\n");
		t.timeSleep(1);

		boolean hantei = false;
		int input = new java.util.Random().nextInt(4);

		if(input == 0 || m.getStatus()== TUMBLING || m.getStatus()== SHOCKED) {
			hantei = true;		// 逃亡成功
		}

		//　☆
		if(m.getKind().equals("BOSS")) {	//★
			System.out.print("逃げちゃだめだ、");
			t.timeSleep(1);
			System.out.print("逃げちゃだめだ、");
			t.timeSleep(1);
			System.out.println("逃げちゃだめだ！\r\n");
			t.timeSleep(1);
		}else if(hantei) {  // 成功（25％）
			System.out.println("うまく逃げ切れることが出来た\r\n");
			t.timeSleep(1);

			m.setHp(-1);
		}else {			// 失敗（75％）
			System.out.println("しかし回り込まれてしまった\r\n");
			t.timeSleep(1);

		}

	}

	private void talk(Monster m ) {
//		t.setMode(t.getGameSpeed());


//		int bossTalkNum = 1;

		switch(m.getKind()) {
		case "SLIME":
			System.out.println(m.getName()+"：『ボク、わるいスライムじゃないよ』\r\n");
			t.timeSleep(1);
			System.out.println("見逃しますか？");
			System.out.println("SELECT>> 1.はい 2.いいえ");

			int i = new java.util.Scanner(System.in).nextInt();
			if(i == 1) {
				System.out.println(m.getName()+"：『ありがとう。これをあげるよ』\r\n");
				t.timeSleep(1);
				System.out.println(this.name+"は<"+m.getItem()+">と<やくそう３個>を手に入れた！\r\n");
				t.timeSleep(1);


				// 取得アイテムの格納
				iBox.setItemBox(m.getItem(),1);
				iBox.setItemBox("やくそう",3);

				// モンスター回避設定
				m.setHp(-1);
			}else {
				attack(m);
			}

			break;


		case "ZONBI":
			System.out.println("返事はない。");
			t.timeSleep(1);
			System.out.println("ただの屍のようだ。\r\n");
			t.timeSleep(1);
			break;

		case "GANG":
			System.out.println(m.getName()+"：『痛い目にあいたくなかったら、");
			System.out.println(" 金とお宝、全部置いていくんだな！』\r\n");
			t.timeSleep(1);
			break;

		case "GOBLIN":

			System.out.print(m.getName()+"：『オレサマ、");
			t.timeSleep(1);
			System.out.print("オマエ、");
			t.timeSleep(1);
			System.out.print("マルカジ");
			t.timeSleep(1);
			System.out.println("リ』\r\n");
			t.timeSleep(1);
			break;

		case "DRAGON":
			System.out.println(m.getName()+"：『ニンゲンドトキト、ハナスコトナド、ナニモナイ』\r\n");
			t.timeSleep(1);
			break;

		case "BOSS":
			System.out.println(m.getName()+"は不気味に笑っている\r\n");
			t.timeSleep(1);
		}

	}

	private void chooseItem(Monster m) {

		int cnt = 1;

		System.out.println("***道具*************************************************");
		for(String iName : iBox.itemBox.keySet()) {
			int num = iBox.itemBox.get(iName);
			System.out.print(cnt+"."+iName+"["+num+"個]    ");
			itemName[cnt-1]=iName;
			cnt++;
			if(cnt%2 == 1) {
				System.out.println("");
			}
		}

		if(cnt%2 == 0) {
			System.out.println("");
		}

		System.out.println("********************************************************\r\n");
		System.out.println("SELECT>>");

		int inputNum = new java.util.Scanner(System.in).nextInt();

		useItem(itemName[inputNum-1],m);
	}

	private void useItem(String iName, Monster m) {
//		t.setMode(t.getGameSpeed());

		int num = 0;

		switch(iName) {
			case "やくそう":
				System.out.println(this.name+"は"+iName+"を使った");
				System.out.println("勇者のHPが30回復した\r\n");
				t.timeSleep(1);
				this.setHp(this.getHp()+30);

				num = iBox.itemBox.get(iName) -1;
				if(num<=0) {
					iBox.itemBox.remove(iName);
				}
				iBox.itemBox.replace(iName, num);

				break;

			case "スライムの液":
				System.out.println(this.name+"は"+iName+"を"+m.getName()+"にぶちまけた");

				if(m.getStatus()!=SHOCKED) {
					System.out.println(m.getName()+"はすべってころんだ\r\n");
					t.timeSleep(1);
					m.setStatus(TUMBLING);
				}else {
					System.out.println("しかし、何も起こらなかった\r\n");
					t.timeSleep(1);
				}

				num = iBox.itemBox.get(iName) -1;
				if(num<=0) {
					iBox.itemBox.remove(iName);
				}
				iBox.itemBox.replace(iName, num);


				break;

			case "腐った肉":

				if(m.getStatus()!=SHOCKED) {
					System.out.println(this.name+"は"+iName+"を"+m.getName()+"にあげた");
					System.out.println(m.getName()+"は口に入れた");
					System.out.println(m.getName()+"はお腹をこわした\r\n");
					t.timeSleep(1);
					m.setStatus(POISON);
				}else {
					System.out.println("しかし、気絶している"+m.getName()+"は受け取ることができない\r\n");
					t.timeSleep(1);
				}

				num = iBox.itemBox.get(iName) -1;
				if(num<=0) {
					iBox.itemBox.remove(iName);
				}
				iBox.itemBox.replace(iName, num);
				break;


			case "カンダタのパーフェクトマスク":
				System.out.println(this.name+"は"+iName+"を装備した");
				System.out.println("辺りから臭気が消えた\r\n");
				t.timeSleep(1);

				this.equipment = iName;

				num = iBox.itemBox.get(iName) -1;
				if(num<=0) {
					iBox.itemBox.remove(iName);
				}
				iBox.itemBox.replace(iName, num);

				break;

			case "ゴブリンのフン":
				System.out.println(this.name+"は"+iName+"を"+m.getName()+"に投げた");
				t.timeSleep(1);
				System.out.println("辺りに強烈な臭気が立ち込める．．．\r\n");
				t.timeSleep(1);

				System.out.println(this.name+"はカンダタのパーフェクトマスクを装備しているので臭いに気づかない\r\n");
				t.timeSleep(1);
				System.out.println(m.getName()+"は強烈な臭いに気絶した\r\n");
				t.timeSleep(2);

				m.setStatus(SHOCKED);

				num = iBox.itemBox.get(iName) -1;
				if(num<=0) {
					iBox.itemBox.remove(iName);
				}				iBox.itemBox.replace(iName, num);
				break;

			case "竜のたま":
				System.out.println(this.name+"は"+iName+"を使った");
				//　☆
				t.timeSleep(2);

				System.out.println("しかし、何もおこらない．．．\r\n");
				t.timeSleep(1);
				System.out.println("あと、６個必要だ\r\n");
				t.timeSleep(2);

				num = iBox.itemBox.get(iName) -1;
				if(num<=0) {
					iBox.itemBox.remove(iName);
				}
				iBox.itemBox.replace(iName, num);

				break;

			case "魔王の杖":
				System.out.println(this.name+"は"+iName+"を振りかざした\r\n");
				t.timeSleep(1);
				System.out.println(this.name+"の姿がみるみるうちに変わっていく\r\n");
				for(int i = 0; i <3; i++ ) {
					System.out.print("．");
					t.timeSleep(1);
				}
				System.out.println("");
				System.out.println(this.name+"は、魔王（四代目）に姿を変えた\r\n");
				t.timeSleep(1);


				System.out.println("魔王（四代目）"+this.name+"：『セダイ、コウタイ、ジャ！！』\r\n");
				// ☆
				t.timeSleep(1);
				System.out.println("おぞましい真の魔王（三代目）はおどろきとまどっている\r\n");
				t.timeSleep(1);
				// ★
				System.out.println("魔王（四代目）"+this.name+"の攻撃！\r\n");
				System.out.println("おぞましい真の魔王（三代目）は9999のダメージを受けた\r\n");
				t.timeSleep(1);

				System.out.println("おぞましい真の魔王（三代目）は跡形もなく消え去った\r\n");
				t.timeSleep(2);

				System.out.print("魔王（四代目）"+this.name+"：『オレサマ、");
				t.timeSleep(1);
				System.out.print("オマエ、");
				t.timeSleep(1);
				System.out.print("マルカジ");
				t.timeSleep(1);
				System.out.println("リ』\r\n");
				t.timeSleep(3);



				System.out.println("世界に新たな暗黒の時代が訪れた．．．\r\n");
				t.timeSleep(1);

				this.badEnd = true;
				break;
		}

	}

	public void finalBattle(Monster m) {
//		t.setMode(t.getGameSpeed());

		System.out.println(this.name+"は魔王（三代目）を倒した！");
		t.timeSleep(1);
		System.out.println("<"+m.getItem()+">を手に入れた\r\n");
		iBox.setItemBox(m.getItem(),1);

		for(int i = 0; i <3; i++ ) {
			System.out.print("．");
			t.timeSleep(1);
		}

		System.out.print("グ、");
		t.timeSleep(1);
		for(int i = 0; i <3; i++ ) {
			System.out.print("ゴ、");
			t.timeSleep(1);
		}
		System.out.print("ゴ");
		System.out.println("\r\n");
		t.timeSleep(1);

		System.out.println("魔王から不気味な音が響く\r\n");
		t.timeSleep(1);
		System.out.println("魔王（三代目）：『まだ、終わりではないぞ・・・』\r\n");
		t.timeSleep(1);

		System.out.println("なんと魔王（三代目）は更におぞましい、真の姿を現した！！\r\n");
		t.timeSleep(3);

		m.setName("おぞましい真の魔王");
		m.setHp(110);
		m.setDefense(35);
		m.setStatus(NORMAL);
	}
}
