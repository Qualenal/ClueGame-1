package clueGame;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class DetectiveNotes extends JDialog {

	public DetectiveNotes() {
		setTitle("Detective Notes");
		setSize(600, 350);
		getContentPane().setLayout(null);
		fillPanel();
	}

	private void fillPanel() {
		// People and buttons
		JLabel ppl = new JLabel("People");
		ppl.setLocation(20, 0);
		ppl.setSize(150, 25);
		getContentPane().add(ppl);

		JCheckBoxMenuItem pPlum = new JCheckBoxMenuItem("Professor Plum");
		pPlum.setLocation(20, 20);
		pPlum.setSize(125, 25);
		getContentPane().add(pPlum);

		JCheckBoxMenuItem mGreen = new JCheckBoxMenuItem("Mr. Green");
		mGreen.setLocation(20, 40);
		mGreen.setSize(125, 25);
		getContentPane().add(mGreen);

		JCheckBoxMenuItem mWhite = new JCheckBoxMenuItem("Mrs. White");
		mWhite.setLocation(20, 60);
		mWhite.setSize(125, 25);
		getContentPane().add(mWhite);

		JCheckBoxMenuItem mPeacock = new JCheckBoxMenuItem("Mrs. Peacock");
		mPeacock.setLocation(150, 20);
		mPeacock.setSize(125, 25);
		getContentPane().add(mPeacock);

		JCheckBoxMenuItem mScarlet = new JCheckBoxMenuItem("Miss Scarlet");
		mScarlet.setLocation(150, 40);
		mScarlet.setSize(125, 25);
		getContentPane().add(mScarlet);

		JCheckBoxMenuItem cMustard = new JCheckBoxMenuItem("Colonel Mustard");
		cMustard.setLocation(150, 60);
		cMustard.setSize(125, 25);
		getContentPane().add(cMustard);

		JLabel choice = new JLabel("Person Choice");
		choice.setLocation(300, 40);
		choice.setSize(150, 25);
		getContentPane().add(choice);

		JComboBox model = new JComboBox();
		model.addItem("Professor Plum");
		model.addItem("Mr. Green");
		model.addItem("Mrs. White");
		model.addItem("Mrs. Peacock");
		model.addItem("Miss Scarlet");
		model.addItem("ColonelMustard");
		model.setLocation(400, 40);
		model.setSize(150, 25);
		getContentPane().add(model);

		// Weapons and Buttons
		JLabel wpn = new JLabel("Weapons");
		wpn.setLocation(20, 100);
		wpn.setSize(150, 25);
		getContentPane().add(wpn);

		JCheckBoxMenuItem lP = new JCheckBoxMenuItem("Lead Pipe");
		lP.setLocation(20, 120);
		lP.setSize(125, 25);
		getContentPane().add(lP);

		JCheckBoxMenuItem pistol = new JCheckBoxMenuItem("Pistol");
		pistol.setLocation(20, 140);
		pistol.setSize(125, 25);
		getContentPane().add(pistol);

		JCheckBoxMenuItem knife = new JCheckBoxMenuItem("Knife");
		knife.setLocation(20, 160);
		knife.setSize(125, 25);
		getContentPane().add(knife);

		JCheckBoxMenuItem cstick = new JCheckBoxMenuItem("Candlestick");
		cstick.setLocation(150, 120);
		cstick.setSize(125, 25);
		getContentPane().add(cstick);

		JCheckBoxMenuItem wrench = new JCheckBoxMenuItem("Wrench");
		wrench.setLocation(150, 140);
		wrench.setSize(125, 25);
		getContentPane().add(wrench);

		JCheckBoxMenuItem psn = new JCheckBoxMenuItem("Poison");
		psn.setLocation(150, 160);
		psn.setSize(125, 25);
		getContentPane().add(psn);

		JCheckBoxMenuItem bat = new JCheckBoxMenuItem("Bat");
		bat.setLocation(150, 180);
		bat.setSize(125, 25);
		getContentPane().add(bat);

		JCheckBoxMenuItem shk = new JCheckBoxMenuItem("Shuriken");
		shk.setLocation(20, 180);
		shk.setSize(125, 25);
		getContentPane().add(shk);

		JLabel wpnChoice = new JLabel("Weapon Choice");
		wpnChoice.setLocation(300, 140);
		wpnChoice.setSize(150, 25);
		getContentPane().add(wpnChoice);

		JComboBox wpnBox = new JComboBox();
		wpnBox.addItem("Lead Pipe");
		wpnBox.addItem("Pistol");
		wpnBox.addItem("Knife");
		wpnBox.addItem("Candlestick");
		wpnBox.addItem("Shuriken");
		wpnBox.addItem("Wrench");
		wpnBox.addItem("Poison");
		wpnBox.addItem("Bat");
		wpnBox.setLocation(400, 140);
		wpnBox.setSize(150, 25);
		getContentPane().add(wpnBox);

		//Rooms and Buttons
		JLabel rooms = new JLabel("Rooms");
		rooms.setLocation(20, 220);
		rooms.setSize(150, 25);
		getContentPane().add(rooms);

		JCheckBoxMenuItem cons = new JCheckBoxMenuItem("Conservatory");
		cons.setLocation(20, 240);
		cons.setSize(125, 25);
		getContentPane().add(cons);

		JCheckBoxMenuItem ball = new JCheckBoxMenuItem("Ballroom");
		ball.setLocation(20, 260);
		ball.setSize(125, 25);
		getContentPane().add(ball);

		JCheckBoxMenuItem bRoom = new JCheckBoxMenuItem("Billiard Room");
		bRoom.setLocation(20, 280);
		bRoom.setSize(125, 25);
		getContentPane().add(bRoom);

		JCheckBoxMenuItem lib = new JCheckBoxMenuItem("Library");
		lib.setLocation(150, 220);
		lib.setSize(125, 25);
		getContentPane().add(lib);

		JCheckBoxMenuItem study = new JCheckBoxMenuItem("Study");
		study.setLocation(150, 240);
		study.setSize(125, 25);
		getContentPane().add(study);

		JCheckBoxMenuItem dRoom = new JCheckBoxMenuItem("Dining Room");
		dRoom.setLocation(150, 260);
		dRoom.setSize(125, 25);
		getContentPane().add(dRoom);

		JCheckBoxMenuItem lounge = new JCheckBoxMenuItem("Lounge");
		lounge.setLocation(150, 280);
		lounge.setSize(125, 25);
		getContentPane().add(lounge);
		
		JLabel roomChoice = new JLabel("Room Choice");
		roomChoice.setLocation(300, 260);
		roomChoice.setSize(150, 25);
		getContentPane().add(roomChoice);


		JComboBox roomBox = new JComboBox();
		roomBox.addItem("Conservatory");
		roomBox.addItem("Kitchen");
		roomBox.addItem("Ballroom");
		roomBox.addItem("Billiard Room");
		roomBox.addItem("Library");
		roomBox.addItem("Study");
		roomBox.addItem("Dining Room");
		roomBox.addItem("Lounge");
		roomBox.setLocation(400, 260);
		roomBox.setSize(150, 25);
		getContentPane().add(roomBox);
	}

	public static void main(String[] args) {
		DetectiveNotes gui = new DetectiveNotes();
		gui.setVisible(true);
	}

}
