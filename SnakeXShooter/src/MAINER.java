import javax.swing.JOptionPane;

import main.Window;

public class MAINER {
	
	public static void main(String args[])
	{
		JOptionPane.showMessageDialog(null, "Tutorial:- \n1)Use Up-Down-Left-Right or W-A-S-D keys to move the snake around \n2)Click anywhere on the screen to shoot bullets\n3) You can shoot bullets after eating 4 apples\n5)Kill all enemies to win\n6)Eat apples to increase your score\n7)Press p to pause\n8)If you crash on enemies you lose,then press any key to continue");
		new Window();
	}

}
