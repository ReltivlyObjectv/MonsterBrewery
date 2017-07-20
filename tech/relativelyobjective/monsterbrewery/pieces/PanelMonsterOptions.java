package tech.relativelyobjective.monsterbrewery.pieces;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;

/**
 *
 * @author ReltivlyObjectv
 * Contact: me@relativelyobjective.tech
 * 
 */
public class PanelMonsterOptions extends JPanel {
	private final PanelMonsterOverview overview;
	private final PanelMonsterAttributes attributes;
	private final PanelMonsterStatLists statLists;
	private final PanelMonsterMiscAddButtons miscButtons;
	
	public PanelMonsterOptions() {
		super.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		JPanel overviewElements = new JPanel();
			overviewElements.setLayout(new BorderLayout());
			overview = new PanelMonsterOverview();
			overviewElements.add(overview, BorderLayout.WEST);
			attributes = new PanelMonsterAttributes();
			overviewElements.add(attributes, BorderLayout.EAST);
		super.add(overviewElements, constraints);
		statLists = new PanelMonsterStatLists();
		constraints.gridy++;
		super.add(statLists, constraints);
		miscButtons = new PanelMonsterMiscAddButtons();
		constraints.gridy++;
		super.add(miscButtons, constraints);
	}
	//Overview
	public String getMonsterName() {
		return overview.getMonsterName();
	}
	public String getArmorType() {
		return overview.getArmorType();
	}
	public String getMonsterSize() {
		return overview.getMonsterSize();
	}
	public String getMonsterType() {
		return overview.getMonsterType();
	}
	public String getMonsterTag() {
		return overview.getMonsterTag();
	}
	public String getAlignment() {
		return overview.getAlignment();
	}
	public String getHitPointString() {
		return overview.getHitPointString();
	}
	public int getHitPointDiceCount() {
		return overview.getHitPointDiceCount();
	}
	public String getHitPointDiceType() {
		return overview.getHitPointDiceType();
	}
	public int getArmorClass() {
		return overview.getArmorClass();
	}
	//Abilities
	public int getStrength() {
		return statLists.getStrength();
	}
	public int getDexterity() {
		return statLists.getDexterity();
	}
	public int getConstitution() {
		return statLists.getConstitution();
	}
	public int getIntelligence() {
		return statLists.getIntelligence();
	}
	public int getWisdom() {
		return statLists.getWisdom();
	}
	public int getCharisma() {
		return statLists.getCharisma();
	}
	public int getStrengthSave() {
		return statLists.getStrengthSave();
	}
	public int getDexteritySave() {
		return statLists.getDexteritySave();
	}
	public int getConstitutionSave() {
		return statLists.getConstitutionSave();
	}
	public int getIntelligenceSave() {
		return statLists.getIntelligenceSave();
	}
	public int getWisdomSave() {
		return statLists.getWisdomSave();
	}
	public int getCharismaSave() {
		return statLists.getCharismaSave();
	}
	public boolean canHover() {
		return statLists.canHover();
	}
	public int getWalkSpeed() {
		return statLists.getWalkSpeed();
	}
	public int getSwimSpeed() {
		return statLists.getSwimSpeed();
	}
	public int getBurrowSpeed() {
		return statLists.getBurrowSpeed();
	}
	public int getClimbSpeed() {
		return statLists.getClimbSpeed();
	}
	public int getFlySpeed() {
		return statLists.getFlySpeed();
	}
	public String getChallengeRating() {
		return miscButtons.getChallengeRating();
	}
}
