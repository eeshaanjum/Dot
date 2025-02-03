import java.util.HashMap;

import javafx.util.Pair;

public class Board {
	public HashMap<Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>, Line> segments;
	
	// change to true once encircled
	private boolean quadrantA = false;
	private boolean quadrantB = false;
	private boolean quadrantC = false;
	private boolean quadrantD = false;
	//board is made up of 12 line segments surrounding 4 quadrants
	/*	_ _
	 * |_|_|
	 * |_|_|
	 */
	
	public Board() {
		//loop from bottom to top first
		segments = new HashMap<Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>, Line>();
		for (int y = 0; y < 3; y++) {
			Pair<Integer, Integer> start = new Pair<Integer, Integer>(0,y);
			Pair<Integer, Integer> end = new Pair<Integer, Integer>(1,y);
			Line segment = new Line(start, end);
			//System.out.println(segment.checkExistance());
			segments.put(segment.makeKey(), segment);
			start = end;
			end = new Pair<Integer, Integer>(2,y);
			segment = new Line(start, end);
			segments.put(segment.makeKey(), segment);
		}
		//next loop from left to right
		for (int x = 0; x < 3; x++) {
			Pair<Integer, Integer> start = new Pair<Integer, Integer>(x,0);
			Pair<Integer, Integer> end = new Pair<Integer, Integer>(x,1);
			Line segment = new Line(start, end);
			segments.put(segment.makeKey(), segment);
			start = end;
			end = new Pair<Integer, Integer>(x,2);
			segment = new Line(start, end);
			segments.put(segment.makeKey(), segment);
		}
		//System.out.println(segments.size());
	}
	//start should always be bottom most / left most point
	//will this realize flipped line is equivalent?????
	public boolean addLine(Pair<Integer, Integer> start, Pair<Integer,Integer> end) {
		//returns true if added false if illegal
		
		Line hypothetical = new Line(start, end);
		Line real = segments.get(hypothetical.makeKey());
		if(real == null || real.checkExistance()) return false;
		real.setExistance(true);
		return true;
	}
	
	public void checkWinner() {
		//checks each of the four quadrants and see's if they are encircled
	}
}
