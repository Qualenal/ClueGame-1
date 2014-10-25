package clueGame;

import javax.swing.JDialog;

public class CustomDialog extends JDialog{
	
	public CustomDialog(){
		setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
		setTitle("Detective Notes");
		setSize(500, 250);
		getContentPane().setLayout(null);
		
	}
	
}
