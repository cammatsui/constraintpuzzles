import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GameButton extends JButton implements ActionListener {
	
	private int value;
	private Puzzle thePuzzle;
	private int buttonNumber;

	//Each button belongs to a puzzle and has a number so that we can interact the puzzle
	//Each button also has a value which, through an ActionListener, is the same as the spot's value.
	public GameButton(Puzzle p, int num) {
		super();
		this.addActionListener(this);
		thePuzzle = p;
		buttonNumber = num;
		this.setOpaque(true);
		this.setBackground(Color.white);
		value = thePuzzle.getGrid().getSpot(num%thePuzzle.getSize(), num/thePuzzle.getSize()).getValue();
		this.setText(Integer.toString(value));
	}

	public GameButton(Puzzle p, int num, int value) {
		super();
		this.addActionListener(this);
		thePuzzle = p;
		buttonNumber = num;
		this.value = value;
		this.setText(Integer.toString(value));
	}

	//We use this to display our solution.
	public void updateButton() {
		value = thePuzzle.getSpot(buttonNumber).getValue();
		this.setText(Integer.toString(value));
	}
	

	//Increments the spot and updates the text to the new value
	public void beenHere() {
		thePuzzle.getSpot(buttonNumber).increment();
		value = thePuzzle.getSpot(buttonNumber).getValue();
		this.setText(Integer.toString(value));
	}
	
	public void actionPerformed(ActionEvent e) {
		beenHere();
	}

	public Puzzle getPuzzle() {
		return thePuzzle;
	}

	public int getButtonNumber() {
		return buttonNumber;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int v) {
		value = v;
	}
	
}
