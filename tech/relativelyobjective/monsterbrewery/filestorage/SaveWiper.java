package tech.relativelyobjective.monsterbrewery.filestorage;

import tech.relativelyobjective.monsterbrewery.pieces.FrameMain;
import tech.relativelyobjective.monsterbrewery.resources.AttributeHandler;
import tech.relativelyobjective.monsterbrewery.resources.Lists;
import tech.relativelyobjective.monsterbrewery.resources.MonsterInformation;

/**
 *
 * @author ReltivlyObjectv
 * Contact: me@relativelyobjective.tech
 * 
 */
public class SaveWiper {
	public static void resetProgram(FrameMain mainFrame) {
		mainFrame.setTitle("Monster Brewery");
		SaveCreator.resetFileLocation();
		AttributeHandler.clearAttributes();
		MonsterInformation.setCanHover(false);
		MonsterInformation.setAlignment(Lists.ALIGNMENT[0]);
		MonsterInformation.setArmorClass(0);
		MonsterInformation.setArmorType("");
		MonsterInformation.setBurrowSpeed("0");
		MonsterInformation.setChallengeRating(Lists.CHALLENGE_RATING[0]);
		MonsterInformation.setCharisma("1");
		MonsterInformation.setCharismaSave("0");
		MonsterInformation.setClimbSpeed("1");
		MonsterInformation.setConstitution("1");
		MonsterInformation.setConstitutionSave("0");
		MonsterInformation.setDexterity("1");
		MonsterInformation.setDexteritySave("0");
		MonsterInformation.setFlySpeed("1");
		MonsterInformation.setHitPointDiceCount("1");
		MonsterInformation.setHitPointDiceType(Lists.DICE[0]);
		MonsterInformation.setHitPointString("");
		MonsterInformation.setIntelligence("1");
		MonsterInformation.setIntelligenceSave("0");
		MonsterInformation.setMonsterName("");
		MonsterInformation.setMonsterSize(Lists.SIZE[0]);
		MonsterInformation.setMonsterTag("");
		MonsterInformation.setMonsterType("");
		MonsterInformation.setStrength("1");
		MonsterInformation.setStrengthSave("0");
		MonsterInformation.setSwimSpeed("0");
		MonsterInformation.setWalkSpeed("5");
		MonsterInformation.setWisdom("1");
		MonsterInformation.setWisdomSave("0");
	}
}
