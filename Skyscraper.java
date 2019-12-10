public class Skyscraper extends Puzzle {
	
	public Skyscraper() {
		super(4, 4);
		this.setHeaders(this.setupHeaders());
		this.setInstructions("Fill in the grid so that no row or column shares the same number. Then use the hints to make sure you can see the correct number of skyscrapers in each row and column from each direction.");
	}

	public int[] setupHeaders() {
		int[] h = new int[20];
		
		//setting constraints
		h[1] = 1; h[2] = 2; h[3] = 4; h[4] = 2; h[6] = 1; h[7] = 3; h[8] = 2; h[9] = 1; h[10] = 2; h[11] = 3; h[12] = 3; h[13] = 2; h[15] = 4; h[16] = 2; h[17] = 1; h[18] = 2;
		
		return h;
	}

	@Override
	public boolean checkConstraint(int spotNumber) {
		//We can write our constraints as: no non-zero number twice in a row or column, and
		//Starting at each of the four relevant skyscraper headers if row/column is full starting from there, check that
		//number of skyscrapers seen is equal to the constraint. If the row/column is not fulll, our only requirement is
		//that they're less than or equal to the constraint.

		int x = spotNumber%this.getSize();
		int y = spotNumber/this.getSize();
		int[][] markedArray = this.getArray();
		int spotValue = markedArray[x][y];

		//if spot not marked, return false
		if (spotValue == 0) {
			return false;
		}

		int[] headers = this.getHeaders();



		//is this number non-zero and another one is in this row? if so fails constraint.
		for (int i = 0; i < this.getSize(); i++) {
			if (markedArray[i][y] == spotValue && i != x && spotValue != 0) {
				return false;
			} 
			
		}

		for (int j = 0; j < this.getSize(); j++) {
			if (markedArray[x][j] == spotValue && j != y && spotValue != 0) {
				return false;
			} 
			
		}


		// Check row left to right
		int greatest = 0;
		int temp = 0;
		int seen = 0;

		// If row full and skyscrapers seen is less than the constraint, than the constraint fails. If it's not full, there's still a chance of a correct row.
		boolean fullRow = true;
		for (int i = 0; i < this.getSize(); i++) {
			if (markedArray[i][y] == 0) {
				fullRow = false;
				break;
			}
		}

		for (int i = 0; i < this.getSize(); i++) {
			temp = markedArray[i][y];
			if(temp == 0) break;
			if (temp > greatest) {
				seen = seen + 1;
				greatest = temp;
			}
		}

		if (seen > headers[6+2*y]) {
			return false;
		}
		if (fullRow && seen != headers[6+2*y]) {
			return false;
		}


		// Check row right to left
		greatest = 0;
		temp = 0;
		seen = 0;

		for (int i = this.getSize()-1; i >= 0; i--) {
			temp = markedArray[i][y];
			if(temp == 0) break;
			if (temp > greatest) {
				seen = seen + 1;
				greatest = temp;
			}
		}

		if (seen > headers[7+2*y]) {
			return false;
		}
		if (fullRow && seen != headers[7+2*y]) {
			return false;
		}


		// Check column top to bottom
		greatest = 0;
		temp = 0;
		seen = 0;

		boolean fullCol = true;
		for (int i = 0; i < this.getSize(); i++) {
			if (markedArray[x][i] == 0) {
				fullCol = false;
				break;
			}
		}

		for (int i = 0; i < this.getSize(); i++) {
			temp = markedArray[x][i];
			if(temp == 0) break;
			if (temp > greatest) {
				seen = seen + 1;
				greatest = temp;
			}
		}

		if (seen > headers[x+1]) {
			return false;
		}
		if (fullCol && seen != headers[x+1]) {
			return false;
		}


		// Check column top to bottom
		greatest = 0;
		temp = 0;
		seen = 0;

		for (int i = this.getSize()-1; i >= 0; i--) {
			temp = markedArray[x][i];
			if(temp == 0) break;
			if (temp > greatest) {
				seen = seen + 1;
				greatest = temp;
			}
		}

		if (seen > headers[x+15]) {
			return false;
		}
		if (fullCol && seen != headers[x+15]) {
			return false;
		}

		// if all constraints are not violated, return true.
		return true;
	}

}