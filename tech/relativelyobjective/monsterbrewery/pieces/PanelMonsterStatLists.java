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
	public void setStrength(int s) {
		abilities.setStrength(s);
	}
	public void setDexterity(int d) {
		abilities.setDexterity(d);
	}
	public void setConstitution(int c) {
		abilities.setConstitution(c);
	}
	public void setIntelligence(int i) {
		abilities.setIntelligence(i);
	}
	public void setWisdom(int w) {
		abilities.setWisdom(w);
	}
	public void setCharisma(int c) {
		abilities.setCharisma(c);
	}
	public void setStrengthSave(int s) {
		savingThrows.setStrengthSave(s);
	}
	public void setDexteritySave(int d) {
		savingThrows.setDexteritySave(d);
	}
	public void setConstitutionSave(int c) {
		savingThrows.setConstitutionSave(c);
	}
	public void setIntelligenceSave(int i) {
		savingThrows.setIntelligenceSave(i);
	}
	public void setWisdomSave(int w) {
		savingThrows.setWisdomSave(w);
	}
	public void setCharismaSave(int c) {
		savingThrows.setCharismaSave(c);
	}
	public void setCanHover(boolean c) {
		speed.setCanHover(c);
	}
	public void setWalkSpeed(int w) {
		speed.setWalkSpeed(w);
	}
	public void setSwimSpeed(int s) {
		speed.setSwimSpeed(s);
	}
	public void setBurrowSpeed(int b) {
		speed.setBurrowSpeed(b);
	}
	public void setClimbSpeed(int c) {
		speed.setClimbSpeed(c);
	}
	public void setFlySpeed(int f) {
		speed.setFlySpeed(f);
	}
}
