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
		setSize(750, 200);
		JPanel panel = new JPanel();
		getContentPane().setLayout(null);
		fillPanel(panel);

	}

	 private void fillPanel(JPanel panel) {
		 	JLabel nameLabel = new JLabel("Whose Turn is it?");
			nameLabel.setLocation(20,0);
			nameLabel.setSize(150,25);
		 	turnName = new JTextField(20);
			turnName.setLocation(10,23);
			turnName.setSize(150,30);
			getContentPane().add(nameLabel);
			getContentPane().add(turnName);
			
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
	}
	
	public static void main(String[] args) {
		ControlPanel gui = new ControlPanel();	
		gui.setVisible(true);
	}


}