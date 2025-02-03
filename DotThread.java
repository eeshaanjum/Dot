import java.util.concurrent.locks.ReentrantLock;

//handles gameplay
public class DotThread extends Thread {
	private Player p1;
	private Player p2;
	private Board board = new Board();
	public ReentrantLock turnLock = new ReentrantLock();
	
	public DotThread(Player p1, Player p2) {
		this.p1 = p1;
		this.p2 = p2;
	}
	
	//alternates between player turns
	public void run() {
		try {
			System.out.println("game thread start");
			p1.startGame(this, true);
			p2.startGame(this, false);
			System.out.println("player threads started");
			p1.join();
			p2.join();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Board getBoard() {
		return board;
	}
	
	public void startTurn() {
		//System.out.println("tries to lock");
		//System.out.println(turnLock.getHoldCount());
		turnLock.lock();
		System.out.println("locked");
		System.out.println(turnLock.getHoldCount());
	}
	
	public void endTurn() {
		//System.out.println(turnLock.getHoldCount());
		//System.out.println("tries to unlock");
		//turnLock.unlock();
		p1.updateBoard();
		p2.updateBoard();
	}
}
