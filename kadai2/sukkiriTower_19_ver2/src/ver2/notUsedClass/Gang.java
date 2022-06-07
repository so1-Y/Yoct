package ver2.notUsedClass;

import ver2.Monster;

public class Gang  extends Monster {

	Gang(Monster m){
		setName(m.getName());
		setHp(m.getHp());
		setMp(m.getMp());
		setPower(m.getPower());
		setDefense(m.getDefense());
		setSpecialPower(m.getSpecialPower());
		setItem(m.getItem());
	}


}
