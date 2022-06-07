package test;

import dao.SharyoDAO;
import model.SharyoBean;

public class MainTest {

	public static void main(String[] args) {
		SharyoBean sb = new SharyoBean("福岡400ひ",6067,3,"安永宗一郎","スタッドレス","必要",88888);

		SharyoDAO sd = new SharyoDAO();

//		sd.sharyoUpdate(sb);

	}

}
