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
		mainFrame.getMonsterOptions().setMonsterName(name);
	}
	public static void setArmorType(String type) {
		mainFrame.getMonsterOptions().setArmorType(type);
	}
	public static void setMonsterSize(String size) {
		mainFrame.getMonsterOptions().setMonsterSize(size);
	}
	public static void setMonsterType(String type) {
		mainFrame.getMonsterOptions().setMonsterType(type);
	}
	public static void setMonsterTag(String tag) {
		mainFrame.getMonsterOptions().setMonsterTag(tag);
	}
	public static void setAlignment(String alignment) {
		mainFrame.getMonsterOptions().setAlignment(alignment);
	}
	public static void setHitPointString(String hp) {
		mainFrame.getMonsterOptions().setHitPointString(hp);
	}
	public static void setHitPointDiceCount(String diceCount) {
		try {
			mainFrame.getMonsterOptions().setHitPointDiceCount(Integer.parseInt(diceCount));
		} catch (NumberFormatException e) {
			//Do Nothing
		}
	}
	public static void setHitPointDiceType(String dice) {
		mainFrame.getMonsterOptions().setHitPointDiceType(dice);
	}
	public static void setArmorClass(String ac) {
		try {
			mainFrame.getMonsterOptions().setArmorClass(Integer.parseInt(ac));
		} catch (NumberFormatException e) {
			//Do Nothing
		}
	}
	public static void setArmorClass(int ac) {
		mainFrame.getMonsterOptions().setArmorClass(ac);
	}
	public static void setChallengeRating(String cr) {
		if (cr.equals("0")) {
			mainFrame.getMonsterOptions().setChallengeRating(0.0);
		} else if (cr.equals("1/8")) {
			mainFrame.getMonsterOptions().setChallengeRating(1.0/8.0);
		} else if (cr.equals("1/4")) {
			mainFrame.getMonsterOptions().setChallengeRating(1.0/4.0);
		} else if (cr.equals("1/2")) {
			mainFrame.getMonsterOptions().setChallengeRating(1.0/2.0);
		} else {
			try {
				mainFrame.getMonsterOptions().setChallengeRating(Double.parseDouble(cr));
			} catch (NumberFormatException e) {
				//Do Nothing
			}
		}
	}
	//Stats
	public static void setStrength(String s) {
		try{
			mainFrame.getMonsterOptions().setStrength(Integer.parseInt(s));
		} catch(NumberFormatException e) {
			//Do Nothing
		}
	}
	public static void setDexterity(String d) {
		try{
			mainFrame.getMonsterOptions().setDexterity(Integer.parseInt(d));
		} catch(NumberFormatException e) {
			//Do Nothing
		}
	}
	public static void setConstitution(String c) {
		try{
			mainFrame.getMonsterOptions().setConstitution(Integer.parseInt(c));
		} catch(NumberFormatException e) {
			//Do Nothing
		}
	}
	public static void setIntelligence(String i) {
		try{
			mainFrame.getMonsterOptions().setIntelligence(Integer.parseInt(i));
		} catch(NumberFormatException e) {
			//Do Nothing
		}
	}
	public static void setWisdom(String w) {
		try{
			mainFrame.getMonsterOptions().setWisdom(Integer.parseInt(w));
		} catch(NumberFormatException e) {
			//Do Nothing
		}
	}
	public static void setCharisma(String c) {
		try{
			mainFrame.getMonsterOptions().setCharisma(Integer.parseInt(c));
		} catch(NumberFormatException e) {
			//Do Nothing
		}
	}
	public static void setStrengthSave(String s) {
		try{
			mainFrame.getMonsterOptions().setStrengthSave(Integer.parseInt(s));
		} catch(NumberFormatException e) {
			//Do Nothing
		}
	}
	public static void setDexteritySave(String d) {
		try{
			mainFrame.getMonsterOptions().setDexteritySave(Integer.parseInt(d));
		} catch(NumberFormatException e) {
			//Do Nothing
		}
	}
	public static void setConstitutionSave(String c) {
		try{
			mainFrame.getMonsterOptions().setConstitutionSave(Integer.parseInt(c));
		} catch(NumberFormatException e) {
			//Do Nothing
		}
	}
	public static void setIntelligenceSave(String i) {
		try{
			mainFrame.getMonsterOptions().setIntelligenceSave(Integer.parseInt(i));
		} catch(NumberFormatException e) {
			//Do Nothing
		}
	}
	public static void setWisdomSave(String w) {
		try{
			mainFrame.getMonsterOptions().setWisdomSave(Integer.parseInt(w));
		} catch(NumberFormatException e) {
			//Do Nothing
		}
	}
	public static void setCharismaSave(String c) {
		try{
			mainFrame.getMonsterOptions().setCharismaSave(Integer.parseInt(c));
		} catch(NumberFormatException e) {
			//Do Nothing
		}
	}
	public static void setCanHover(String c) {
		try{
			mainFrame.getMonsterOptions().setCanHover(Boolean.parseBoolean(c));
		} catch(NumberFormatException e) {
			//Do Nothing
		}
	}
	public static void setCanHover(boolean c) {
		mainFrame.getMonsterOptions().setCanHover(false);
	}
	public static void setWalkSpeed(String w) {
		try{
			mainFrame.getMonsterOptions().setWalkSpeed(Integer.parseInt(w));
		} catch(NumberFormatException e) {
			//Do Nothing
		}
	}
	public static void setSwimSpeed(String s) {
		try{
			mainFrame.getMonsterOptions().setSwimSpeed(Integer.parseInt(s));
		} catch(NumberFormatException e) {
			//Do Nothing
		}
	}
	public static void setBurrowSpeed(String b) {
		try{
			mainFrame.getMonsterOptions().setBurrowSpeed(Integer.parseInt(b));
		} catch(NumberFormatException e) {
			//Do Nothing
		}
	}
	public static void setClimbSpeed(String c) {
		try{
			mainFrame.getMonsterOptions().setClimbSpeed(Integer.parseInt(c));
		} catch(NumberFormatException e) {
			//Do Nothing
		}
	}
	public static void setFlySpeed(String f) {
		try{
			mainFrame.getMonsterOptions().setFlySpeed(Integer.parseInt(f));
		} catch(NumberFormatException e) {
			//Do Nothing
		}
	}
}
