import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Hitori extends Puzzle {

	private File file;
	private int[][] template;


	public Hitori() {
		super(5, 2);
		//Hitori's constraints are read from a file
		file = new File("HitoriTemplate.txt");
		template = new int[5][5];


		try {
			Scanner fromFile = new Scanner(file);

			int r = 0;
			
			//This scanner allows us to take in each row of the template,
			//Split it into integers, and put them into our template array.
			while (fromFile.hasNextLine()) {
				String[] row = fromFile.nextLine().split(" ");
				for (int i = 0; i < row.length; i++) {
					template[i][r] = Integer.parseInt(row[i]);
					
				}
				r++;
			}

		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
		}
		int[] h = new int[0];
		this.setHeaders(h);
		this.setInstructions("Shade the squares black such that no number appears twice in a row or column. Shaded squares cannot touch each other, except for diagonally. Finally, all white squares must create a single continous area. Gray squares are undecided.");
	}

	@Override
	public int getTemplateValue(int index) {
		return template[index%5][index/5];
	}

	@Override
	public void setPuzzle() {
		Grid aGrid = this.getGrid();
		for (int i = 0; i < this.getSize()*this.getSize(); i++) {
			Spot temp = new Spot(this.getButtonLimit(), 1);
			aGrid.addSpot(temp);
		}


	}


	public boolean checkConstraint(int spotNumber) {
		int x = spotNumber%this.getSize();
		int y = spotNumber/this.getSize();
		int[][] markedArray = this.getArray();
		int spotValue = markedArray[x][y];
		int[][] constraint = this.template;
		int spotConstraint = constraint[x][y];
		// We must check that a) the spot's neighbors aren't marked, b) no unmarked (white)
		// number appears more than once, and c) that every white square touches another one.

		if (spotValue == 0)
			return false;
		
		//first check that none of the spot's neighbors are also marked if marked
		if (spotValue == 2) {
			if (x == 4 && y > 0 && y < 4) {
				if (markedArray[x-1][y] == 2) return false;
				if (markedArray[x][y-1] == 2) return false;
				if (markedArray[x][y+1] == 2) return false;
			} else if (x==4 && y==0) {
				if (markedArray[x-1][y] == 2) return false;
				if (markedArray[x][y+1] == 2) return false;
			} else if (x==4 && y==4) {
				if (markedArray[x-1][y] == 2) return false;
				if (markedArray[x][y-1] == 2) return false;
			} else if (x == 0 && y > 0 && y < 4) {
				if (markedArray[x+1][y] == 2) return false;
				if (markedArray[x][y-1] == 2) return false;
				if (markedArray[x][y+1] == 2) return false;
			} else if (x==0 && y==0) {
				if (markedArray[x+1][y] == 2) return false;
				if (markedArray[x][y+1] == 2) return false;
			} else if (x==0 && y==4) {
				if (markedArray[x+1][y] == 2) return false;
				if (markedArray[x][y-1] == 2) return false;
			} else if (y==0) {
				if (markedArray[x-1][y] == 2) return false;
				if (markedArray[x+1][y] == 2) return false;
				if (markedArray[x][y+1] == 2) return false;
			} else if (y==4) {
				if (markedArray[x-1][y] == 2) return false;
				if (markedArray[x+1][y] == 2) return false;
				if (markedArray[x][y-1] == 2) return false;
			} else {
				if (markedArray[x-1][y] == 2) return false;
				if (markedArray[x+1][y] == 2) return false;
				if (markedArray[x][y-1] == 2) return false;
				if (markedArray[x][y+1] == 2) return false;
			}
		}





		//then, must check that no duplicates in our row/column
		//essentially if marked with 1 and another marked with 1 with same number in row/column, return false
		if (spotValue == 1) {
			//first, check row
			for (int i = 0; i < this.getSize(); i++) {
				if (markedArray[i][y] == 1 && constraint[i][y] == spotConstraint && i != x) return false;
			}

			//then check column
			for (int i = 0; i < this.getSize(); i++) {
				if (markedArray[x][i] == 1 && constraint[x][i] == spotConstraint && i != y) return false;
			}

			//then, must check that unmarked cells are connected.
			//if this cell is unmarked, one if its neighbors must be unmarked.
			if (x==4) {
				if (y==4) {
					//bottom right corner
					if (markedArray[x-1][y] == 1) return true;
					if (markedArray[x][y-1] == 1) return true;
				} else if (y==0) {
					//top right corner
					if (markedArray[x-1][y] == 1) return true;
					if (markedArray[x][y+1] == 1) return true;
				} else {
					//everything else in far right column
					if (markedArray[x-1][y] == 1) return true;
					if (markedArray[x][y-1] == 1) return true;
					if (markedArray[x][y+1] == 1) return true;
				}
			} else if (x==0) {
				if (y==4) {
					//bottom left corner
					if (markedArray[x+1][y] == 1) return true;
					if (markedArray[x][y-1] == 1) return true;
				} else if (y==0) {
					//top left corner
					if (markedArray[x+1][y] == 1) return true;
					if (markedArray[x][y+1] == 1) return true;
				} else {
					//everything else in far left column
					if (markedArray[x+1][y] == 1) return true;
					if (markedArray[x][y-1] == 1) return true;
					if (markedArray[x][y+1] == 1) return true;
				}
			} else if (y==0) {
				//everything else in top row
				if (markedArray[x+1][y] == 1) return true;
				if (markedArray[x-1][y] == 1) return true;
				if (markedArray[x][y+1] == 1) return true;
			} else if (y==4) {
				//everything else in bottom row
				if (markedArray[x+1][y] == 1) return true;
				if (markedArray[x-1][y] == 1) return true;
				if (markedArray[x][y-1] == 1) return true;
			} else {
				//all non-edge spots
				if (markedArray[x+1][y] == 1) return true;		
				if (markedArray[x-1][y] == 1) return true;		
				if (markedArray[x][y-1] == 1) return true;
				if (markedArray[x][y+1] == 1) return true;
			}

		}

		return true;
	}

	
}