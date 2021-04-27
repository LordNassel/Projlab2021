package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SettingsView extends JPanel {
	JFrame frame;
	public SettingsView()
	{
		frame = new JFrame();
		frame.setVisible(true);
		JPanel buttons = new JPanel();
		JButton proba = new JButton("Proba");
		buttons.add(proba);
		frame.add(buttons);
	}

}
