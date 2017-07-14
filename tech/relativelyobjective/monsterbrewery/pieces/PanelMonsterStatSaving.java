package tech.relativelyobjective.monsterbrewery.pieces;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import tech.relativelyobjective.monsterbrewery.resources.JLabelBold;
import tech.relativelyobjective.monsterbrewery.resources.Lists;

/**
 *
 * @author ReltivlyObjectv
 * Contact: me@relativelyobjective.tech
 * 
 */
public class PanelMonsterStatSaving extends JPanel {
	private final JSpinner[] savingBonuses;
		
	public PanelMonsterStatSaving() {
		super.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridy = 0;
		for (int i = 0; i < Lists.ABILITIES.length; i++) {
			constraints.gridx = i;
			super.add(new JLabelBold(Lists.ABILITIES[i]), constraints);
		}
		constraints.gridy++;
		savingBonuses = new JSpinner[6];
		for (int i = 0; i < Lists.ABILITIES.length; i++) {
			constraints.gridx = i;
			savingBonuses[i] = new JSpinner(new SpinnerNumberModel(0,0,30,1));
			JPanel spaceBuffer = new JPanel();
			spaceBuffer.setLayout(new BoxLayout(spaceBuffer, BoxLayout.LINE_AXIS));
			spaceBuffer.add(savingBonuses[i]);
			spaceBuffer.add(new JLabel("    ")); //4 spaces to approximate the width
			super.add(spaceBuffer, constraints);
		}
	}
	public int getStrengthSave() {
		return (int) savingBonuses[0].getValue();
	}
	public int getDexteritySave() {
		return (int) savingBonuses[1].getValue();
	}
	public int getConstitutionSave() {
		return (int) savingBonuses[2].getValue();
	}
	public int getIntelligenceSave() {
		return (int) savingBonuses[3].getValue();
	}
	public int getWisdomSave() {
		return (int) savingBonuses[4].getValue();
	}
	public int getCharismaSave() {
		return (int) savingBonuses[5].getValue();
	}
}
