package tech.relativelyobjective.monsterbrewery.pieces;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import tech.relativelyobjective.monsterbrewery.resources.Lists;

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
	public Lists.Pronouns getPronoun() {
		return overview.getPronoun();
	}
	public void setMonsterName(String name) {
		overview.setMonsterName(name);
	}
	public void setArmorType(String type) {
		overview.setArmorType(type);
	}
	public void setMonsterSize(String size) {
		overview.setMonsterSize(size);
	}
	public void setMonsterType(String type) {
		overview.setMonsterType(type);
	}
	public void setMonsterTag(String tag) {
		overview.setMonsterTag(tag);
	}
	public void setAlignment(String alignment) {
		overview.setAlignment(alignment);
	}
	public void setHitPointString(String text) {
		overview.setHitPointString(text);
	}
	public void setHitPointDiceCount(int diceCount) {
		overview.setHitPointDiceCount(diceCount);
	}
	public void setHitPointDiceType(String diceType) {
		overview.setHitPointDiceType(diceType);
	}
	public void setArmorClass(int ac) {
		overview.setArmorClass(ac);
	}
	public void setChallengeRating(double cr) {
		miscButtons.setChallengeRating(cr);
	}
	public void setStrength(int s) {
		statLists.setStrength(s);
	}
	public void setDexterity(int d) {
		statLists.setDexterity(d);
	}
	public void setConstitution(int c) {
		statLists.setConstitution(c);
	}
	public void setIntelligence(int i) {
		statLists.setIntelligence(i);
	}
	public void setWisdom(int w) {
		statLists.setWisdom(w);
	}
	public void setCharisma(int c) {
		statLists.setCharisma(c);
	}
	public void setStrengthSave(int s) {
		statLists.setStrengthSave(s);
	}
	public void setDexteritySave(int d) {
		statLists.setDexteritySave(d);
	}
	public void setConstitutionSave(int c) {
		statLists.setConstitutionSave(c);
	}
	public void setIntelligenceSave(int i) {
		statLists.setIntelligenceSave(i);
	}
	public void setWisdomSave(int w) {
		statLists.setWisdomSave(w);
	}
	public void setCharismaSave(int c) {
		statLists.setCharismaSave(c);
	}
	public void setCanHover(boolean c) {
		statLists.setCanHover(c);
	}
	public void setWalkSpeed(int w) {
		statLists.setWalkSpeed(w);
	}
	public void setSwimSpeed(int s) {
		statLists.setSwimSpeed(s);
	}
	public void setBurrowSpeed(int b) {
		statLists.setBurrowSpeed(b);
	}
	public void setClimbSpeed(int c) {
		statLists.setClimbSpeed(c);
	}
	public void setFlySpeed(int f) {
		statLists.setFlySpeed(f);
	}
	public void setPronoun(Lists.Pronouns newPro) {
		overview.setPronoun(newPro);
	}
}
