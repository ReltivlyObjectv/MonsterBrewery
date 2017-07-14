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
public class PanelMonsterMiscAddButtons extends JPanel {
	private final PanelMonsterSenses senses;
	private final PanelMonsterLanguages lang;
	private final PanelMonsterSkills skills;
	
	public PanelMonsterMiscAddButtons() {
		super.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		//Labels
		constraints.gridy = 0;
		constraints.gridx = 0;
		super.add(new JLabelBold("Senses"), constraints);
		constraints.gridx = 1;
		super.add(new JLabelBold("Languages"), constraints);
		//Panels
		constraints.gridy++;
		senses = new PanelMonsterSenses();
		constraints.gridx = 0;
		super.add(senses, constraints);
		lang = new PanelMonsterLanguages();
		constraints.gridx = 1;
		super.add(lang, constraints);
		constraints.gridx = 0;
		constraints.gridy++;
		skills = new PanelMonsterSkills();
		constraints.gridy++;
		super.add(skills, constraints);
		JPanel buttonCluster = new JPanel();
			//TODO
		constraints.gridx++;
		super.add(buttonCluster, constraints);
	}
}
