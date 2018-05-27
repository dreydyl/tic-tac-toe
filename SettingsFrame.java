import java.util.*;

import javax.swing.JFrame;

public class SettingsFrame extends JFrame {

	private Selector tabs;
	private ArrayList<SettingsPage> pages = new ArrayList<SettingsPage>();

	public SettingsFrame() {
		tabs = new Selector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}

	public void add(SettingsPage page) {
		page.setFrame(this);
		tabs.add(new SettingsTab(page.getTitle()));
		pages.add(page);
	}

}
