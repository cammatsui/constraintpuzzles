import java.awt.*;
import javax.swing.*;

public class GamePanel extends JPanel {

	private Puzzle thePuzzle;
	private int gameSize;
	private int[] headers;
	private GameButton[] buttonArray;

	public GamePanel(Puzzle p) {
		super();

		this.setBackground(Color.white);

		thePuzzle = p;
		gameSize = thePuzzle.getSize();


		//Get the array of headers from the Puzzle object, hCounter helps us determine where to put the headers in the grid layout.
		headers = thePuzzle.getHeaders();
		int hCounter = 0;


		this.setBorder(BorderFactory.createBevelBorder(1));
		//GridLayout has dimension of gameSize+2 because of headers (which show constraints or other information)
		if (headers.length!=0)
			this.setLayout(new GridLayout(gameSize+2, gameSize+2));
		else
			this.setLayout(new GridLayout(gameSize, gameSize));

		
		

		//Put the first row of headers in.
		while ((hCounter <= gameSize + 2) && (hCounter < headers.length)) {
			JLabel temp = getHeader(hCounter);
			this.add(temp);
			hCounter = hCounter+1;
		}


		//Put the buttons into an array so that they can be easily updated to display a solution
		buttonArray = new GameButton[gameSize*gameSize];
	
		//Put the GameButtons in
		for (int i = 0; i < gameSize*gameSize; i++) {


			GameButton button;
			if (thePuzzle instanceof Kakurasu) {
				button = new KakurasuButton(thePuzzle, i);
			} else if (thePuzzle instanceof Hitori) {
				button = new HitoriButton(thePuzzle, i, thePuzzle.getTemplateValue(i));
			} else {
				button = new GameButton(thePuzzle, i);
			}

			
			button.setBorder(BorderFactory.createLineBorder(Color.black));
			button.setPreferredSize(new Dimension(100, 100));
			this.add(button);

			buttonArray[i] = button;
			//This loop places the headers in the correct spots in the gridlayout.
			if (((i+1)%gameSize==0) && (hCounter < headers.length)) {
				for (int j=0; j<2; j++) {
					JLabel temp = getHeader(hCounter);
					this.add(temp);
					hCounter = hCounter + 1;
				}
			}

		}

		//Put in the last row of headers.
		while (hCounter < headers.length) {
			JLabel temp = getHeader(hCounter);
			this.add(temp);
			hCounter = hCounter+1;
		}
	}

	//Method to create a header (JLabel)
	private JLabel getHeader(int hCounter) {
		JLabel temp = new JLabel();
		temp.setPreferredSize(new Dimension(100, 100));
		if (hCounter != 0 && hCounter != (gameSize+1) && hCounter != (3*gameSize+2) && hCounter!= (4*gameSize+3)) {
			temp.setText(Integer.toString(headers[hCounter]));
			temp.setHorizontalAlignment(SwingConstants.CENTER);
		}
		temp.setBorder(BorderFactory.createLineBorder(Color.red));
		return temp;
	}

	//Allows us to update all of a GamePanel's buttons to display a solution.
	public void updateButtons() {
		for (int i = 0; i < buttonArray.length; i++) {
			buttonArray[i].updateButton();
		}
	}

}