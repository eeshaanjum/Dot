import javafx.util.Pair;

public class Line {
	public Pair<Integer, Integer> start;
	public Pair<Integer, Integer> end;
	private boolean exists = false;
	
	public Line(Pair<Integer, Integer> start, Pair<Integer,Integer> end) {
		//System.out.println(start + " -> " + end);
		this.start = start;
		this.end = end;
	}
	
	public Pair<Pair<Integer, Integer>, Pair<Integer, Integer>> makeKey() {
		return new Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>(start, end);
	}
	
	public boolean checkExistance() {
		return exists;
	}
	
	public void setExistance(boolean state) {
		exists = state;
	}
}
