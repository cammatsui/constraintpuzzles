import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class HitoriButton extends GameButton implements ActionListener {
	
	static Color grey = new Color(180, 180, 180);
	static Color lightBlue = new Color(161, 217, 227);

	//Each button belongs to a puzzle and has a number so that we can solve the puzzle
	//Each button also has a value
	public HitoriButton(Puzzle hitori, int num, int text) {
		super(hitori, num);
		this.setOpaque(true);
		this.setBackground(Color.white);
		this.setText(Integer.toString(text));
	}

	@Override
	public void updateButton() {
		this.setValue(this.getPuzzle().getSpot(this.getButtonNumber()).getValue());
		if (this.getValue() == 0) {
			this.setBackground(grey);
		} else if (this.getValue() == 1) {
			this.setBackground(Color.white);
			this.setForeground(Color.black);
		} else {
			this.setBackground(Color.black);
			this.setForeground(Color.white);
		}
	}
	

	//Increments the spot and updates the color.
	@Override
	public void beenHere() {
		this.getPuzzle().getSpot(this.getButtonNumber()).increment();
		this.setValue(this.getPuzzle().getSpot(this.getButtonNumber()).getValue());
		if (this.getValue() == 0) {
			this.setBackground(grey);
		} else if (this.getValue() == 1) {
			this.setBackground(Color.white);
			this.setForeground(Color.black);
		} else {
			this.setBackground(Color.black);
			this.setForeground(Color.white);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		beenHere();
		
	}
	
}
