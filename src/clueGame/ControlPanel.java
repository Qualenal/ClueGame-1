package clueGame;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class ControlPanel extends JFrame {
	private JTextField turnName;

	public ControlPanel()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Player Control Panel");
		setSize(750, 150);
		JPanel panel = new JPanel();
		getContentPane().setLayout(null);
		fillPanel(panel);

	}

	 private void fillPanel(JPanel panel) {
		 	//Name Text/Box Creator
		 	JLabel nameLabel = new JLabel("Whose Turn is it?");
			nameLabel.setLocation(20,0);
			nameLabel.setSize(150,25);
		 	turnName = new JTextField(20);
			turnName.setLocation(10,23);
			turnName.setSize(150,30);
			getContentPane().add(nameLabel);
			getContentPane().add(turnName);
			
			//Next player and Accusation Buttons
			JButton nextPlayer = new JButton("Next Player");
			nextPlayer.setMnemonic('N');
			nextPlayer.setLocation(400,0);
			nextPlayer.setSize(150,40);
			getContentPane().add(nextPlayer);
			JButton accusation = new JButton("Make an Accusation");
			accusation.setMnemonic('A');
			accusation.setLocation(550,0);
			accusation.setSize(200,40);
			getContentPane().add(accusation);
			
			//Die Roll Text/Box Creator
			JLabel dieRoll = new JLabel("Die Roll");
			dieRoll.setLocation(10,50);
			dieRoll.setSize(150,25);
		 	JTextField roll = new JTextField(20);
			roll.setLocation(10,75);
			roll.setSize(50,30);
			roll.setEditable(false);
			getContentPane().add(dieRoll);
			getContentPane().add(roll);
			
			//Guess Text/Box Creator
			JLabel guessLabel = new JLabel("Guess");
			guessLabel.setLocation(175,50);
			guessLabel.setSize(150,25);
		 	JTextField guess = new JTextField(20);
			guess.setLocation(175,75);
			guess.setSize(250,30);
			guess.setEditable(false);
			getContentPane().add(guessLabel);
			getContentPane().add(guess);
			
			//Confirmation Text/Box Creator
			JLabel confirmationLabel = new JLabel("Guess Response");
			confirmationLabel.setLocation(525,50);
			confirmationLabel.setSize(150,25);
		 	JTextField confirmation = new JTextField(20);
			confirmation.setLocation(525,75);
			confirmation.setSize(125,30);
			confirmation.setEditable(false);
			getContentPane().add(confirmationLabel);
			getContentPane().add(confirmation);
	}
	
	public static void main(String[] args) {
		ControlPanel gui = new ControlPanel();	
		gui.setVisible(true);
	}


}