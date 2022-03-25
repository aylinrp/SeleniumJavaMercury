package helpers;

public class helpers {
		
		
		public void sleepSeconds(int seconds) {
			try {
				Thread.sleep(seconds*1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
}
