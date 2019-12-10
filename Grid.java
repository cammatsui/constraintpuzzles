public class Grid {

	private int size;
	private Spot[][] theGrid;
	private int spotCounter;

	//The grid is the set of values for the puzzle
	public Grid(int s) {
		theGrid = new Spot[s][s];
		size = s;
		spotCounter = 0;
	}

	//Using one number to describe position in an array allows
	//simpler ways of filling it.
	public void addSpot(Spot n) {
		theGrid[spotCounter%size][spotCounter/size] = n;
		spotCounter = spotCounter + 1;
	}

	public Spot getSpot(int x, int y) {
		return theGrid[x][y];
	}
}