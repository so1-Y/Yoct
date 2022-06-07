package ver2;

public class Main {

	public static void main(String[] args) {

		System.out.println("★☆スッキリタワー☆★\r\n"
				+ "1:ゲームスタート\r\n"
				+ "2:モンスターデータ取込（準備中）\r\n"
				+ ""
				+ ">>");

		int i = new java.util.Scanner(System.in).nextInt();


		if(i == 1) {
			Game game = new Game();
//			Game2 game = new Game2();

			game.outline();
		}else {
//			db.monsterDateInput();
			System.out.println("準備中です");
		}

	}

}
