import java.net.ServerSocket;

public class DotServer {
	private static final int LISTENING_PORT = 32007;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// waits for 2 players to connect then starts a game for them
		try {
			ServerSocket serv = new ServerSocket(LISTENING_PORT);
			while(true) {
				System.out.println("start");
				Player p1 = new Player(serv.accept());
				System.out.println("p1");
				Player p2 = new Player(serv.accept());
				System.out.println("p2");
				new DotThread(p1, p2).start();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
