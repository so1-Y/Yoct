package ver2.notUsedClass;

import ver2.Monster;

public class Slime extends Monster{

	Slime(Monster m){
		setName(m.getName());
		setHp(m.getHp());
		setMp(m.getMp());
		setPower(m.getPower());
		setDefense(m.getDefense());
		setSpecialPower(m.getSpecialPower());
		setItem(m.getItem());
	}

	public void attack() {
		System.out.println("スライムの攻撃");  //★
	};

}
