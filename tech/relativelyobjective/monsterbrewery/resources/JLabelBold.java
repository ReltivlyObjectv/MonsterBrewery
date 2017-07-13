package tech.relativelyobjective.monsterbrewery.resources;

import java.awt.Font;
import javax.swing.JLabel;

/**
 *
 * @author ReltivlyObjectv
 * Contact: me@relativelyobjective.tech
 * 
 */
public class JLabelBold extends JLabel {
	public JLabelBold(String label) {
		super(label);
		Font font = super.getFont();
		Font boldFont = new Font(font.getFontName(), Font.BOLD, font.getSize());
		super.setFont(boldFont);
	}
}
