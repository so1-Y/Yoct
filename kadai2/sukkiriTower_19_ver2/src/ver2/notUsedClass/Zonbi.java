package ver2.notUsedClass;

import ver2.Monster;

public class Zonbi extends Monster {

	Zonbi(Monster m){
		setName(m.getName());
		setHp(m.getHp());
		setMp(m.getMp());
		setPower(m.getPower());
		setDefense(m.getDefense());
		setSpecialPower(m.getSpecialPower());
		setItem(m.getItem());
	}

}
