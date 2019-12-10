public class Kakurasu extends Puzzle {
	
	public Kakurasu() {
		super(4, 2);
		int[] temp = this.setupHeaders();
		this.setHeaders(temp);
		this.setInstructions("Mark squares so the rows and columns' totals equal the clues below and to the right. Blue squares are marked, white are unmarked, and gray are undecided.");
	}

	//Set up the clues for the puzzle in an array
	public int[] setupHeaders() {
		int[] h = new int[20];
		for (int i = 1; i < 5; i++) {
			h[i] = i;
		}

		int k = 1;
		for (int j = 6; j<13; j=j+2) {
			h[j] = k;
			k=k+1;
		}

		h[7] = 7;
		h[9] = 7;
		h[11] = 4;
		h[13] = 5;
		h[15] = 6;
		h[16] = 2;
		h[17] = 1;
		h[18] = 10;

		return h;
	}

	//Our contraints are that the marked spots' column and row numbers are less than the corresponding headers
	@Override
	public boolean checkConstraint(int spotNumber) {

		int x = spotNumber%this.getSize();
		int y = spotNumber/this.getSize();
		int[][] markedArray = this.getArray();
		int[] headers = this.getHeaders();
		// We must check that the sums of the row and column the spot belongs to are less than or equal to the clues.

		if (markedArray[x][y] == 0) return false;

		int temp = 0;
		int numMarked = 0;
		int sum = 0;


		// First, we check the row.
		for (int i = 0; i < this.getSize(); i++) {
			temp = markedArray[i][y];
			if (temp != 0) numMarked = numMarked + 1;
			if (temp == 1) sum = sum + i + 1;
			if ((numMarked == 4 && sum != headers[7+2*y]) || (numMarked < 4 && sum > headers[7+2*y])) {
				
				return false;
			}
		}

		numMarked = 0;
		sum = 0;

		// Then, we check the column.
		for (int j = 0; j < this.getSize(); j++) {
			temp = markedArray[x][j];
			if (temp != 0) numMarked = numMarked + 1;
			if (temp == 1) sum = sum + j + 1;
			if ((numMarked == 4 && sum != headers[15+x]) || (numMarked < 4 && sum > headers[15+x])) {
				
				return false;
			}
		}



		return true;

	}

	// set up spots in the puzzle.
	@Override
	public void setPuzzle() {
		Grid aGrid = this.getGrid();
		for (int i = 0; i < this.getSize()*this.getSize(); i++) {
			Spot temp = new Spot(this.getButtonLimit(), 2);
			aGrid.addSpot(temp);
		}


	}

}