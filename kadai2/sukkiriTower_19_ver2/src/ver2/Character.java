package ver2;
import static ver2.Status.*;

import java.util.concurrent.TimeUnit;


public class Character implements GameInfo{

	private int hp;
	private int mp;
	private int power;
	private int defense;
	private int specialPower;
	private Status status = NORMAL;

	private String name;
	private String kind;

	TimeSleep t = new TimeSleep();

	// ゲッターセッターここから
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public int getMp() {
		return mp;
	}
	public void setMp(int mp) {
		this.mp = mp;
	}
	public int getPower() {
		return power;
	}
	public void setPower(int power) {
		this.power = power;
	}
	public int getDefense() {
		return defense;
	}
	public void setDefense(int defence) {
		this.defense = defence;
	}
	public int getSpecialPower() {
		return specialPower;
	}
	public void setSpecialPower(int specialPower) {
		this.specialPower = specialPower;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}

	// ゲッターセッターここまで

	public void attack(Character c) {

//		t.setMode(t.getGameSpeed());

		System.out.println(this.getName()+"の攻撃！");
		t.timeSleep(1);

		int damage = this.getPower() - c.getDefense();

		if(damage > 0) {
			System.out.println(c.getName()+"は"+damage+"ポイントのダメージを受けた\r\n");
			t.timeSleep(1);

			c.setHp(c.getHp()- damage );
			if(c.getHp()<0) {
				c.setHp(0);
			}

		}else {
			System.out.println(c.getName()+"はダメージを受けなかった。\r\n");
			t.timeSleep(1);
		}

	}

	public void special(Character c) {
//		t.setMode(t.getGameSpeed());

		System.out.println(this.getName()+"は必殺技をはなった‼");
		t.timeSleep(1);

		if(this.getMp()>0) {
			this.setMp(this.getMp()-1);
			System.out.println(c.getName()+"は"+this.getSpecialPower()+"ポイントのダメージを受けた\r\n");
			t.timeSleep(1);

			c.setHp(c.getHp()- this.getSpecialPower() );
			if(c.getHp()<0) {
				c.setHp(0);
			}
		}else {
			try {
				TimeUnit.SECONDS.sleep(1);
			}catch(InterruptedException e){;}
			System.out.println("しかしMPが足りなかった\r\n");
			t.timeSleep(1);
		}
	}





}
