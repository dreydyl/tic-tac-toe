import java.util.ArrayList;

public class Player {

	private SuperGraphics.Pattern pattern;
	private ArrayList<Space> territory = new ArrayList<Space>();

	public void setPattern(SuperGraphics.Pattern pattern) {
		System.out.println(pattern.name());
		this.pattern = pattern;
	}
	
	public void add(Space space) {
		territory.add(space);
	}

	public boolean check(int[][] wa) {
		for(int i = 0;i < wa.length;i++) {
			int row = 0;
			for(int j = 0;j < wa[i].length;j++) {
				for(Space s:territory) {
					if(wa[i][j] == s.getIndex()) {
						row++;
					}
				}
			}
			if(row == 3) return true;
		}
		return false;
	}

	public Player(SuperGraphics.Pattern p) {
		setPattern(p);
	}
	public SuperGraphics.Pattern getPattern() {
		return pattern;
	}

}
