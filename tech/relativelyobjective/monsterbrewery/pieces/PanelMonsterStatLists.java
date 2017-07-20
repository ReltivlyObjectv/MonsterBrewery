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
	public int getStrength() {
		return abilities.getStrength();
	}
	public int getDexterity() {
		return abilities.getDexterity();
	}
	public int getConstitution() {
		return abilities.getConstitution();
	}
	public int getIntelligence() {
		return abilities.getIntelligence();
	}
	public int getWisdom() {
		return abilities.getWisdom();
	}
	public int getCharisma() {
		return abilities.getCharisma();
	}
	public int getStrengthSave() {
		return savingThrows.getStrengthSave();
	}
	public int getDexteritySave() {
		return savingThrows.getDexteritySave();
	}
	public int getConstitutionSave() {
		return savingThrows.getConstitutionSave();
	}
	public int getIntelligenceSave() {
		return savingThrows.getIntelligenceSave();
	}
	public int getWisdomSave() {
		return savingThrows.getWisdomSave();
	}
	public int getCharismaSave() {
		return savingThrows.getCharismaSave();
	}
	public boolean canHover() {
		return speed.canHover();
	}
	public int getWalkSpeed() {
		return speed.getWalkSpeed();
	}
	public int getSwimSpeed() {
		return speed.getSwimSpeed();
	}
	public int getBurrowSpeed() {
		return speed.getBurrowSpeed();
	}
	public int getClimbSpeed() {
		return speed.getClimbSpeed();
	}
	public int getFlySpeed() {
		return speed.getFlySpeed();
	}
}
