package tech.relativelyobjective.monsterbrewery.pieces;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import tech.relativelyobjective.monsterbrewery.resources.JLabelBold;

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
