import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProjectFrame extends JFrame {

	private GamePanel gPanel;
	private Container ct;
	private Puzzle thePuzzle;

	public void setUpFrame(Puzzle p) {
		thePuzzle = p;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ct = getContentPane();
		ct.setLayout(new FlowLayout());
		Frame toClose = this;
		this.setBackground(Color.white);
		ct.setBackground(Color.white);


		//Each ProjectFrame has a menu panel and a gamePanel. The gamePanel contains
		//the buttons and headers for the game, whereas the menu panel contains buttons
		//to check, solve, switch puzzles, and quit.
		//The menu panel is not to be confused with the MenuFrame; it is not the man menu.
		JPanel menu = new JPanel();
		menu.setBackground(Color.white);

		thePuzzle.setPuzzle();
		GamePanel gamePanel = new GamePanel(thePuzzle);
		gPanel = gamePanel;


		//JLabel for instructions that are contained in the Game object.
		//If the player wishes to check the puzzle, will display result.
		JTextArea instructions = new JTextArea(thePuzzle.getInstructions());
		instructions.setPreferredSize(new Dimension(300, 150));
		instructions.setEditable(false);
		instructions.setLineWrap(true);
		instructions.setWrapStyleWord(true);
		


		//menu panel buttons
		JButton check = new JButton("Check Solution");
		check.setBackground(Color.white);
		check.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (thePuzzle.checkSolved()) {
					instructions.setText("Congratulations! You've solved the puzzle.");
				} else {
					instructions.setText("You have not yet solved the puzzle.");
				}
			}
		} );
		check.setPreferredSize(new Dimension(300, 75));
		check.setBorder(BorderFactory.createLineBorder(Color.black));

		JButton solve = new JButton("Find Solution");
		solve.setBackground(Color.white);
		solve.setPreferredSize(new Dimension(300, 100));
		solve.setBorder(BorderFactory.createLineBorder(Color.black));
		solve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (thePuzzle.solve()) {
					gamePanel.updateButtons();
					instructions.setText("Solution found.");
				} else {
					instructions.setText("No solution found.");
				}
			}
		} );


		//Returns to MenuFrame so that the player can choose a different puzzle to play.
		JButton puzzleChoice = new JButton("Play a Different Puzzle");
		puzzleChoice.setBackground(Color.white);
		puzzleChoice.setPreferredSize(new Dimension(300, 75));
		puzzleChoice.setBorder(BorderFactory.createLineBorder(Color.black));
		puzzleChoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuFrame frame = new MenuFrame();
				frame.setUpFrame();

				frame.pack();
				frame.setVisible(true);
				frame.setPreferredSize(new Dimension(600,600));
				frame.repaint();
				toClose.dispose();
			}
		} );

		//Quits, as in MenuFrame.
		JButton quitButton = new JButton("Quit to Desktop");
		quitButton.setBackground(Color.white);
		quitButton.setPreferredSize(new Dimension(300, 75));
		quitButton.setBorder(BorderFactory.createLineBorder(Color.black));
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		} );

		
		

		//Add elements to menu panel
		menu.add(instructions);
		menu.add(check);
		menu.add(solve);
		menu.add(puzzleChoice);
		menu.add(quitButton);


		menu.setBorder(BorderFactory.createLineBorder(Color.black));
		menu.setLayout(new GridLayout(0,1));
		ct.add(menu);


		//we want our menu panel to be the same height as our game panel.

		int height = (p.getSize() * 100) + 200;

		if (thePuzzle.getHeaders().length == 0)
			height = (p.getSize() * 100);


		menu.setPreferredSize(new Dimension(400, height));

		//add gamePanel to the ProjectFrame.
		
		ct.add(gamePanel);




	}

}