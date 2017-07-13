package tech.relativelyobjective.monsterbrewery.pieces;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author ReltivlyObjectv
 * Contact: me@relativelyobjective.tech
 * 
 */
public class PanelMonsterStatLists extends JPanel {
	private final PanelMonsterStatAbilities abilities;
	private final PanelMonsterStatSaving savingThrows;
	private final PanelMonsterStatSpeed speed;
	
	private class JLabelBold extends JLabel {
		public JLabelBold(String label) {
			super(label);
			Font font = super.getFont();
			Font boldFont = new Font(font.getFontName(), Font.BOLD, font.getSize());
			super.setFont(boldFont);
		}
	}
	
	public PanelMonsterStatLists() {
		super.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		//Abilities
		constraints.gridy = 0;
		super.add(new JLabelBold("Ability Scores"), constraints);
		abilities = new PanelMonsterStatAbilities();
		constraints.gridy++;
		super.add(abilities, constraints);
		//Saving Throws
		constraints.gridy++;
		super.add(new JLabelBold("Saving Throws"), constraints);
		savingThrows = new PanelMonsterStatSaving();
		constraints.gridy++;
		super.add(savingThrows, constraints);
		//Speed
		constraints.gridy++;
		super.add(new JLabelBold("Speed"), constraints);
		speed = new PanelMonsterStatSpeed();
		constraints.gridy++;
		super.add(speed, constraints);
	}
}
