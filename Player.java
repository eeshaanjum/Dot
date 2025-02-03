import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.locks.ReentrantLock;

import javafx.util.Pair;

public class Player extends Thread {
	private Socket conn;
	private PrintWriter outgoing;
	private BufferedReader incoming;
	private boolean turnFailed = false;
	private DotThread game;
	
	public Player(Socket connection) {
		try {
			conn = connection;
			incoming = new BufferedReader( 
	                new InputStreamReader(conn.getInputStream()));
			outgoing = new PrintWriter(
					new OutputStreamWriter(conn.getOutputStream()), true);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void startGame(DotThread game, boolean goesFirst) {
		this.game = game;
		if(goesFirst) {
			game.turnLock.lock();//startTurn();
			turnFailed = true;
			
			outgoing.println("T");
		}
		else outgoing.println("F");
		this.start();
	}
	
	public void updateBoard() {
		outgoing.println("UPDATE");
		//send information about board so scene can be updated
	}
	
	private void turnFailed(PrintWriter outgoing) {
		System.out.println("FAILED");
		outgoing.println("FAILED");
		turnFailed = true;
	}
	
	private void turnSucceeded(PrintWriter outgoing) {
		System.out.println("SUCCESS");
		outgoing.println("SUCCESS");
		turnFailed = false;
		//System.out.println(game.turnLock.isHeldByCurrentThread());
		//game.turnLock.unlock();//endTurn();
	}
	
	// reads message in format a,b,c,d where start = [a,b] and end = [c.d]
	private void takeTurn(BufferedReader incoming, PrintWriter outgoing) {
		// messages for illegal move, succesful move, succes and add point, success and victory
		try {
			String lineFromServer = incoming.readLine();
			String[] parts = lineFromServer.split(",");
			int[] points = new int[4];
			for (int i = 0; i < 4; i++) points[i] = Integer.parseInt(parts[i]);
			Pair<Integer, Integer> start = new Pair<Integer, Integer>(points[0], points[1]);
			Pair<Integer, Integer> end = new Pair<Integer, Integer>(points[2], points[3]);
			boolean res = game.getBoard().addLine(start, end);
			// ??? winner checking
			
			if(!res) turnFailed(outgoing);
			else turnSucceeded(outgoing);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		//handle messages from player
		while(true) {
			try {
				String lineFromServer = incoming.readLine();
				System.out.println(lineFromServer);
				switch(lineFromServer) {
					case "TURN":
						if(!turnFailed) game.turnLock.lock();//startTurn();
						takeTurn(incoming, outgoing);
						break;
					case "QUIT":
						//tidy up game and release other player
						conn.close();
						break;
				}
			}
			catch (Exception e) { 
				e.printStackTrace();
			}
		}
	}
}
