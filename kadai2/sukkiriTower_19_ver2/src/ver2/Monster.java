package ver2;
import static ver2.Status.*;


public class Monster extends Character {
	private int monster_no;
	private String item;
	private String note;
	private int turnCnt = 0;

//	private String name;
//	private int hp;
//	private int mp;
//	private int power;
//	private int defense;
//	private int specialPower;

	// Getter Setter ここから
	public int getMonster_no() {
		return monster_no;
	}
	public void setMonster_no(int monster_no) {
		this.monster_no = monster_no;
	}

	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}




	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}



	public void action(Hero h) {
//		t.setMode(t.seconds);

		if(this.getStatus() == TUMBLING) {
			if(this.turnCnt>1) {
				System.out.println(this.getName()+"は起き上がった");
				t.timeSleep(1);
				this.setStatus(NORMAL);
				this.turnCnt = 0;
			}
		}

		// 状態判定
		switch(this.getStatus()) {
			case NORMAL:
			case POISON:
				if(this.getStatus()==POISON) {
					System.out.println(this.getName()+"は毒におかされている");
					System.out.println(this.getName()+"は15ポイントのダメージを受けた\r\n");
					t.timeSleep(1);
					this.setHp(this.getHp()-15);
				}

				if(this.getHp()>0) {
					int hantei = new java.util.Random().nextInt(100)+1;

					if(hantei <=75 ) {	// 攻撃（確率75％）
						attack(h);
					}else if(hantei > 75 && hantei <=90) { // 様子を見る（確率15％）
						System.out.println(this.getName()+"は様子を見ている\r\n");
						t.timeSleep(1);

					}else { // 必殺技（確率10％）
						special(h);
					}

				}
				break;

			case SHOCKED:
				System.out.println(this.getName()+"は気絶しているため行動できない\r\n");
				t.timeSleep(1);
				break;

			case TUMBLING:
				System.out.println(this.getName()+"はころんでいるため行動できない\r\n");
				t.timeSleep(1);
				this.turnCnt++;
				break;
		}

	}

	public void talk(Hero h) {
//		t.setMode(t.seconds);

		switch(this.getName()) {
			case "カンダタ":
				System.out.println(super.getName()+"：『まいった！改心するからゆるしてくれよな！な！』\r\n");
				t.timeSleep(1);
				System.out.println("ゆるしますか？");
				System.out.println("SELECT>> 1.はい 2.いいえ");


				int i = new java.util.Scanner(System.in).nextInt();
				if(i == 1) {
					System.out.println(this.getName()+"：『恩に着るぜ！これは礼だ。使ってくれ』\r\n");
					t.timeSleep(1);
					System.out.println(h.getName()+"は<"+this.getItem()+">を手に入れた！\r\n");
					t.timeSleep(1);

					// 取得アイテムの格納
					h.iBox.setItemBox(this.getItem(),1);

					// モンスター回避設定
					this.setHp(-1);
				}else {
					while(i!=1) {  // 1.はい が選択されるまでループ
						System.out.println(this.getName()+"：『そんなこと言わずによ、許してくれよ！な！な！』");
						t.timeSleep(1);
						System.out.println("ゆるしますか？");
						System.out.println("SELECT>> 1.はい 2.いいえ");
						i = new java.util.Scanner(System.in).nextInt();
					}
					System.out.println(this.getName()+"：『恩に着るぜ！あばよ！またな！』\r\n");
					t.timeSleep(1);
				}
				break;

			case "魔王":
				System.out.println(this.getName()+"：『よくここまで来たな勇者"+h.getName()+"よ");
				System.out.println("ほうびに我が偉大なる力を見せてつかわそうぞ』\r\n");
				t.timeSleep(1);

		}

	}

}


