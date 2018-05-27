import java.util.ArrayList;

import javax.swing.JPanel;

public class SettingsPage extends JPanel {

	private SettingsFrame frame;
	private ArrayList<GameObject> settings = new ArrayList<GameObject>();
	private String title;
	
	public SettingsPage(String title) {
		this.title = title;
	}
	public SettingsPage() {
		this("");
	}
	
	public void add(GameObject setting) {
		setting.setFrame(frame);
		settings.add(setting);
	}
	public GameObject get(int index) {
		return settings.get(index);
	}
	
	public String getTitle() {
		return title;
	}

	public void setFrame(SettingsFrame parent) {
		frame = parent;
	}

}
