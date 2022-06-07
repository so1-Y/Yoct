package ver2;

import java.util.concurrent.TimeUnit;

public class TimeSleep {
	public int seconds;
	private int mode;
	private int gameSpeed ;

	TimeSleep(){

	}

//	public void setMode(int mode) {
//		this.mode = mode;
//		gameSpeed = mode;
//	}

	public int getGameSpeed() {
		return gameSpeed;
	}

	public void setGameSpeed(int gameSpeed) {
		this.gameSpeed = gameSpeed;
	}


	public void timeSleep(int s) {
//		if(this.mode ==1) {
			try {
				TimeUnit.SECONDS.sleep(s);
			}catch(InterruptedException e){;}
//		}
	}
}

