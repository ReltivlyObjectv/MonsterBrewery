package tech.relativelyobjective.monsterbrewery.pieces;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import tech.relativelyobjective.monsterbrewery.resources.Attributes;
import tech.relativelyobjective.monsterbrewery.resources.Lists;

/**
 *
 * @author ReltivlyObjectv
 * Contact: me@relativelyobjective.tech
 * 
 */
public class PanelMonsterStatAbilities extends JPanel {
	
	private class AbilityBox extends JPanel {
		private JSpinner valueSpinner;
		private JLabel modifier;
		public AbilityBox() {
			super.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
			valueSpinner = new JSpinner(new SpinnerNumberModel(1,1,30,1));
			modifier = new JLabel("-5");
			valueSpinner.addChangeListener((ChangeEvent e) -> {
				int mod = Attributes.calculateModifier((int) valueSpinner.getValue());
				String modLabel = mod < 0 ? "" + mod : "+" + mod;
				modifier.setText(modLabel);
			});
			super.add(valueSpinner);
			super.add(modifier);
		}
		public int getValue() {
			return (int) valueSpinner.getValue();
		}
	}
	private final AbilityBox[] abilityScores = new AbilityBox[6];
	public PanelMonsterStatAbilities() {
		super.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridy = 0;
		for (int i = 0; i < Lists.ABILITIES.length; i++) {
			constraints.gridx = i;
			super.add(new JLabel(Lists.ABILITIES[i]), constraints);
		}
		constraints.gridy++;
		for (int i = 0; i < Lists.ABILITIES.length; i++) {
			constraints.gridx = i;
			abilityScores[i] = new AbilityBox();
			super.add(abilityScores[i], constraints);
		}
	}
	public int getStrength() {
		return abilityScores[0].getValue();
	}
	public int getDexterity() {
		return abilityScores[1].getValue();
	}
	public int getConstitution() {
		return abilityScores[2].getValue();
	}
	public int getIntelligence() {
		return abilityScores[3].getValue();
	}
	public int getWisdom() {
		return abilityScores[4].getValue();
	}
	public int getCharisma() {
		return abilityScores[5].getValue();
	}
}
