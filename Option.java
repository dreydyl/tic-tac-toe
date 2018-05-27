
public abstract class Option extends Button {

	protected boolean selected;
	
	public void select() { selected = true; }
	public void deselect() { selected = false; }
	
	public boolean isSelected() { return selected; }

}
