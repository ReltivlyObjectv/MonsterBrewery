package tech.relativelyobjective.monsterbrewery.resources;

import java.util.LinkedList;
import java.util.List;
import tech.relativelyobjective.monsterbrewery.attributes.*;
import tech.relativelyobjective.monsterbrewery.pieces.FrameMain;

/**
 *
 * @author ReltivlyObjectv
 * Contact: me@relativelyobjective.tech
 * 
 */
public class MonsterInformation {
	private static FrameMain mainFrame = null;
	public static void initialize(FrameMain mainF) {
		mainFrame = mainF;
	}
	//-----GETTERS-----
	public static FrameMain getMainFrame() {
		return mainFrame;
	}
	//Overview
	public static String getMonsterName() {
		return mainFrame.getMonsterOptions().getMonsterName();
	}
	public static String getArmorType() {
		return mainFrame.getMonsterOptions().getArmorType();
	}
	public static String getMonsterSize() {
		return mainFrame.getMonsterOptions().getMonsterSize();
	}
	public static String getMonsterType() {
		return mainFrame.getMonsterOptions().getMonsterType();
	}
	public static String getMonsterTag() {
		return mainFrame.getMonsterOptions().getMonsterTag();
	}
	public static String getAlignment() {
		return mainFrame.getMonsterOptions().getAlignment();
	}
	public static String getHitPointString() {
		return mainFrame.getMonsterOptions().getHitPointString();
	}
	public static int getHitPointDiceCount() {
		return mainFrame.getMonsterOptions().getHitPointDiceCount();
	}
	public static String getHitPointDiceType() {
		return mainFrame.getMonsterOptions().getHitPointDiceType();
	}
	public static int getArmorClass() {
		return mainFrame.getMonsterOptions().getArmorClass();
	}
	public static String getChallengeRating() {
		return mainFrame.getMonsterOptions().getChallengeRating();
	}
	//Attributes
	public static List<Attribute> getAttributesCopy() {
		return AttributeHandler.getAllAttributesCopy();
	}
	public static List<Ability> getAbilities() {
		List<Ability> returnMe = new LinkedList<>();
		for (Attribute a : getAttributesCopy()) {
			if (a instanceof Ability) {
				returnMe.add((Ability) a);
			}
		}
		return returnMe;
	}
	public static List<Action> getActions() {
		List<Action> returnMe = new LinkedList<>();
		for (Attribute a : getAttributesCopy()) {
			if (a instanceof Action) {
				returnMe.add((Action) a);
			}
		}
		return returnMe;
	}
	public static List<DamageModifier> getDamageModifiers() {
		List<DamageModifier> returnMe = new LinkedList<>();
		for (Attribute a : getAttributesCopy()) {
			if (a instanceof DamageModifier) {
				returnMe.add((DamageModifier) a);
			}
		}
		return returnMe;
	}
	public static List<Language> getLanguages() {
		List<Language> returnMe = new LinkedList<>();
		for (Attribute a : getAttributesCopy()) {
			if (a instanceof Language) {
				returnMe.add((Language) a);
			}
		}
		return returnMe;
	}
	public static List<LegendaryActions> getLegendaryActions() {
		List<LegendaryActions> returnMe = new LinkedList<>();
		for (Attribute a : getAttributesCopy()) {
			if (a instanceof LegendaryActions) {
				returnMe.add((LegendaryActions) a);
			}
		}
		return returnMe;
	}
	public static List<Reaction> getReactions() {
		List<Reaction> returnMe = new LinkedList<>();
		for (Attribute a : getAttributesCopy()) {
			if (a instanceof Reaction) {
				returnMe.add((Reaction) a);
			}
		}
		return returnMe;
	}
	public static List<Sense> getSenses() {
		List<Sense> returnMe = new LinkedList<>();
		for (Attribute a : getAttributesCopy()) {
			if (a instanceof Sense) {
				returnMe.add((Sense) a);
			}
		}
		return returnMe;
	}
	public static List<Skill> getSkills() {
		List<Skill> returnMe = new LinkedList<>();
		for (Attribute a : getAttributesCopy()) {
			if (a instanceof Skill) {
				returnMe.add((Skill) a);
			}
		}
		return returnMe;
	}
	public static List<Spellcaster> getSpellcaster() {
		List<Spellcaster> returnMe = new LinkedList<>();
		for (Attribute a : getAttributesCopy()) {
			if (a instanceof Spellcaster) {
				returnMe.add((Spellcaster) a);
			}
		}
		return returnMe;
	}
	//Stats
	public static int getStrength() {
		return mainFrame.getMonsterOptions().getStrength();
	}
	public static int getDexterity() {
		return mainFrame.getMonsterOptions().getDexterity();
	}
	public static int getConstitution() {
		return mainFrame.getMonsterOptions().getConstitution();
	}
	public static int getIntelligence() {
		return mainFrame.getMonsterOptions().getIntelligence();
	}
	public static int getWisdom() {
		return mainFrame.getMonsterOptions().getWisdom();
	}
	public static int getCharisma() {
		return mainFrame.getMonsterOptions().getCharisma();
	}
	public static int getStrengthSave() {
		return mainFrame.getMonsterOptions().getStrengthSave();
	}
	public static int getDexteritySave() {
		return mainFrame.getMonsterOptions().getDexteritySave();
	}
	public static int getConstitutionSave() {
		return mainFrame.getMonsterOptions().getConstitutionSave();
	}
	public static int getIntelligenceSave() {
		return mainFrame.getMonsterOptions().getIntelligenceSave();
	}
	public static int getWisdomSave() {
		return mainFrame.getMonsterOptions().getWisdomSave();
	}
	public static int getCharismaSave() {
		return mainFrame.getMonsterOptions().getCharismaSave();
	}
	public static boolean canHover() {
		return mainFrame.getMonsterOptions().canHover();
	}
	public static int getWalkSpeed() {
		return mainFrame.getMonsterOptions().getWalkSpeed();
	}
	public static int getSwimSpeed() {
		return mainFrame.getMonsterOptions().getSwimSpeed();
	}
	public static int getBurrowSpeed() {
		return mainFrame.getMonsterOptions().getBurrowSpeed();
	}
	public static int getClimbSpeed() {
		return mainFrame.getMonsterOptions().getClimbSpeed();
	}
	public static int getFlySpeed() {
		return mainFrame.getMonsterOptions().getFlySpeed();
	}
	//-----SETTERS-----
	//Overview
	public static void setMonsterName(String name) {
	}
	public static void setArmorType(String type) {
	}
	public static void setMonsterSize(String size) {
	}
	public static void setMonsterType(String type) {
	}
	public static void setMonsterTag(String tag) {
	}
	public static void setAlignment(String alignment) {
	}
	public static void setHitPointString(String hp) {
	}
	public static void setHitPointDiceCount(int diceCount) {
	}
	public static void setHitPointDiceType(String dice) {
	}
	public static void setArmorClass(int ac) {
	}
	public static void setChallengeRating(String cr) {
	}
	//Attributes
	public static void addAttribute(Attribute a) {
	}
	public static void addAbility(Ability a) {
	}
	public static void addAction(Action a) {
	}
	public static void addDamageModifier(DamageModifier d) {
	}
	public static void addLanguage(Language l) {
	}
	public static void addLegendaryActions(LegendaryActions l) {
	}
	public static void addReaction(Reaction r) {
	}
	public static void addSense(Sense s) {
	}
	public static void addSkill(Skill s) {
	}
	public static void addSpellcaster(Spellcaster s) {
	}
	//Stats
	public static void setStrength(int s) {
	}
	public static void getDexterity(int d) {
	}
	public static void getConstitution(int c) {
	}
	public static void getIntelligence(int i) {
	}
	public static void getWisdom(int w) {
	}
	public static void getCharisma(int c) {
	}
	public static void getStrengthSave(int s) {
	}
	public static void getDexteritySave(int d) {
	}
	public static void getConstitutionSave(int c) {
	}
	public static void getIntelligenceSave(int i) {
	}
	public static void getWisdomSave(int s) {
	}
	public static void getCharismaSave(int c) {
	}
	public static void setCanHover(boolean canHover) {
	}
	public static void setWalkSpeed(int speed) {
	}
	public static void getSwimSpeed(int speed) {
	}
	public static void getBurrowSpeed(int speed) {
	}
	public static void getClimbSpeed(int speed) {
	}
	public static void getFlySpeed(int speed) {
	}
}
