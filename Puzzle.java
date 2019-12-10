public class Puzzle {

	private int size;
	private Grid theGrid;
	private int buttonLimit;
	private String instructions;
	private int[] headers;
	private int solveOperations;

	// Each puzzle has a size, grid of spots, limit of how much those spots can increment, worded instructions, and clues.
	public Puzzle(int s, int bl) {
		size = s;
		buttonLimit = bl;
		theGrid = new Grid(s);
		instructions = "";
		headers = new int[4*s + 4];
		solveOperations = 0;
	}

	public Puzzle(int s, int bl, int[] headers, String instructions) {
		size = s;
		buttonLimit = bl;
		theGrid = new Grid(s);
		this.instructions = instructions;
		this.headers = headers;
		solveOperations = 0;
	}


	// First, to solve, we set the grid to a completely unset one.
	public boolean solve() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				theGrid.getSpot(i, j).setValue(0);
			}
		}
		// Then we create an array of possible values to be tested in our recursive labeling process.
		int[] possibleValues = new int[buttonLimit+1];
		for (int i = 0; i <= buttonLimit; i++) {
			possibleValues[i] = i;
		}
		boolean solved = label(possibleValues, 0);
		System.out.println("Solving the puzzle took " + solveOperations + " operations.");
		return solved;
	}


	public boolean label(int[] possibleValues, int number) {
		if (number == size*size) {
			// If we've reached the end of our grid, we return whether it's been solved or not.
			return checkSolved();
		}

		// This for loop recursively checks each possible value to see if it has an end solution.
		for (int i = 1; i < possibleValues.length; i++) {
			theGrid.getSpot(number%size, number/size).setValue(possibleValues[i]);
			solveOperations = solveOperations + 1;
			boolean check = checkConstraint(number);
			if (check) {
				int newnumber = number + 1;
				if(label(possibleValues, newnumber)) {
					return true;
				}
			}
		}
		// If we've exited the previous recursion's for loop, i.e., there's no solution following from this spot's possibilites,
		// we unset the spot and move backwards.
		theGrid.getSpot(number%size, number/size).setValue(0);
		solveOperations = solveOperations+1;
		return false;
	}



	// generically set up a puzzle with spots.

	public void setPuzzle() {
		for (int i = 0; i < size*size; i++) {
			Spot temp = new Spot(buttonLimit);
			theGrid.addSpot(temp);
		}
	}

	public int[] getHeaders() {
		return headers;
	}

	public void setHeaders(int[] h) {
		headers = h;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String i) {
		instructions = i;
	}

	// returns an array of all the spotvalues.
	public int[][] getArray() {
		int[][] array = new int[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				array[i][j] = theGrid.getSpot(i,j).getValue();
			}

		}
		return array;
	}


	public int getSize() {
		return size;
	}

	public int getSpotValue(int num) {
		return theGrid.getSpot(num%size, num/size).getValue();
	}


	public int getButtonLimit() {
		return buttonLimit;
	}

	public Grid getGrid() {
		return theGrid;
	}

	public Spot getSpot(int num) {
		return theGrid.getSpot(num%size, num/size);
	}

	public int getPreset(int n) {
		return 0;
	}

	// this method is overriden in all subclasses because each has different constraints.
	public boolean checkConstraint(int spotNumber) {
		System.out.println("Default!");
		return true;
	}

	// this method checks that each spot does not violate the constraints.
	public boolean checkBoardConstraint() {
		for (int i = 0; i < this.getSize()*this.getSize(); i++) {
			if (checkConstraint(i) == false) {
				return false;
			}
		}
		return true;
	}

	// checks if the game is solved by checking first that no constraints are violated 
	// and second that all spots are set.
	public boolean checkSolved() {
		
		int[][] markedArray = this.getArray();
		for (int i = 0; i < this.getSize()*this.getSize(); i++) {
			if (markedArray[i%this.getSize()][i/this.getSize()] == 0) {
				return false;
			}
		}

		for (int i = 0; i < this.getSize()*this.getSize(); i++) {
			if (!checkConstraint(i))
				return false;
		}

		return true;
	}

	// Overridden in the only relevant subclass, Hitori.
	public int getTemplateValue(int index) {
		return 88;
	}

}