package tech.relativelyobjective.monsterbrewery.resources;

/**
 *
 * @author ReltivlyObjectv
 * Contact: me@relativelyobjective.tech
 * 
 */

public class JLabelBrownOverview extends JLabelBrown{
	public JLabelBrownOverview(String title, String text) {
		super(String.format("<html><b>%s</b> %s</html>", title, text));
	}
}