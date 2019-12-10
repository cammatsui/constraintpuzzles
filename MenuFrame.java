import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuFrame extends JFrame {

	public void setUpFrame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container ct = getContentPane();
		JPanel pane = new JPanel();
		
		//GridLayout such that all elements are centered in one column
		pane.setLayout(new GridLayout(0,1));
		Frame toClose = this;

		ct.setBackground(Color.white);
		//Buttons for each game; each button opens a new ProjectFrame for that specific game,
		//And closes the MenuFrame when opening a ProjectFrame.
		JButton kakurasuButton = new JButton("Kakurasu");
		kakurasuButton.setPreferredSize(new Dimension(300, 100));
		kakurasuButton.setBackground(Color.white);
		kakurasuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Kakurasu kakurasu = new Kakurasu();
				ProjectFrame k = new ProjectFrame();
				k.setUpFrame(kakurasu);
				k.pack();
				k.setVisible(true);
				k.setPreferredSize(new Dimension(600, 600));
				k.repaint();
				toClose.dispose();
			}
		});



		JButton skyscraperButton = new JButton("Skyscraper");
		skyscraperButton.setPreferredSize(new Dimension(300, 100));
		skyscraperButton.setBackground(Color.white);
		skyscraperButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Puzzle skyscraper = new Skyscraper();
				ProjectFrame s = new ProjectFrame();
				s.setUpFrame(skyscraper);
				s.pack();
				s.setVisible(true);
				s.setPreferredSize(new Dimension(600, 600));
				s.repaint();
				toClose.dispose();
				}
		});



		JButton hitoriButton = new JButton("Hitori");
		hitoriButton.setPreferredSize(new Dimension(300, 100));
		hitoriButton.setBackground(Color.white);
		hitoriButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Puzzle hitori = new Hitori();
				ProjectFrame h = new ProjectFrame();
				h.setUpFrame(hitori);
				h.pack();
				h.setVisible(true);
				h.setPreferredSize(new Dimension(600, 600));
				h.repaint();
				toClose.dispose();
			}
		});

		//Button to quit the program
		JButton quitButton = new JButton("Quit");
		quitButton.setPreferredSize(new Dimension(300, 100));
		quitButton.setBackground(Color.white);
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		} );

		//Add Information Label, Borders to Buttons, add Elements to Pane then to JFrame.
		JLabel infoLabel = new JLabel("Choose a Game to Play:");
		pane.add(infoLabel);
		infoLabel.setHorizontalAlignment(SwingConstants.CENTER);

		kakurasuButton.setBorder(BorderFactory.createLineBorder(Color.black));

		skyscraperButton.setBorder(BorderFactory.createLineBorder(Color.black));

		hitoriButton.setBorder(BorderFactory.createLineBorder(Color.black));
		
		quitButton.setBorder(BorderFactory.createLineBorder(Color.black));

		pane.add(kakurasuButton);
		pane.add(skyscraperButton);
		pane.add(hitoriButton);
		pane.add(quitButton);
		ct.add(pane);
	}

}