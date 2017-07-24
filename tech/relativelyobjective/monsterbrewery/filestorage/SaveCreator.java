package tech.relativelyobjective.monsterbrewery.filestorage;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import tech.relativelyobjective.monsterbrewery.attributes.*;
import tech.relativelyobjective.monsterbrewery.pieces.FrameMain;
import tech.relativelyobjective.monsterbrewery.resources.MonsterInformation;

/**
 *
 * @author ReltivlyObjectv
 * Contact: me@relativelyobjective.tech
 * 
 */
public class SaveCreator {
	private static File saveLocation = null;
	
	public static void resetFileLocation() {
		saveLocation = null;
	}
	public static void setFileLocation(File newSaveLocation, FrameMain mainFrame) {
		mainFrame.setTitle(String.format("Monster Brewery (%s)",newSaveLocation.getName()));
		saveLocation = newSaveLocation;
	}
	public static void saveToLocation(FrameMain mainFrame) {
		if (saveLocation == null) {
			openSavePrompt(mainFrame);
		} else {
			saveToLocation(saveLocation);
		}
	}
	public static void openSavePrompt(FrameMain mainFrame) {
		
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("MonsterBrewery Files", "monsterbrewery");
		fileChooser.setFileFilter(filter);
		if (fileChooser.showSaveDialog(mainFrame) == JFileChooser.APPROVE_OPTION) {
			File tempSaveLocation = fileChooser.getSelectedFile();
			if (!tempSaveLocation.toString().contains(".monsterbrewery")) {
				tempSaveLocation = new File(tempSaveLocation.toString() + ".monsterbrewery");
			}
			setFileLocation(tempSaveLocation, mainFrame);
			saveToLocation(saveLocation);
		}
	}
	public static void saveToLocation(File file) {
		try {
			DocumentBuilderFactory dbFactory =
			DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = 
			   dbFactory.newDocumentBuilder();
			Document doc = dBuilder.newDocument();
			//Root
			Element rootElement = doc.createElement("monster");
			doc.appendChild(rootElement);
			//Overview
			Element overview = doc.createElement("overview");
			rootElement.appendChild(overview);
				//Name
				Element monsterName = doc.createElement("name");
				monsterName.appendChild(doc.createTextNode(MonsterInformation.getMonsterName()));
				overview.appendChild(monsterName);
				//Armor Class
				Element ac = doc.createElement("armorclass");
				ac.appendChild(doc.createTextNode(""+MonsterInformation.getArmorClass()));
				overview.appendChild(ac);
				//Armor Type
				Element acType = doc.createElement("armortype");
				acType.appendChild(doc.createTextNode(MonsterInformation.getArmorType()));
				overview.appendChild(acType);
				//HP Dice Type
				Element hpDiceType = doc.createElement("dicetype");
				hpDiceType.appendChild(doc.createTextNode(MonsterInformation.getHitPointDiceType()));
				overview.appendChild(hpDiceType);
				//HP Dice Count
				Element hpDiceCount = doc.createElement("dicecount");
				hpDiceCount.appendChild(doc.createTextNode(""+MonsterInformation.getHitPointDiceCount()));
				overview.appendChild(hpDiceCount);
				//HP Total
				Element hpTotal = doc.createElement("hptotal");
				hpTotal.appendChild(doc.createTextNode(MonsterInformation.getHitPointString()));
				overview.appendChild(hpTotal);
				//Size
				Element size = doc.createElement("size");
				size.appendChild(doc.createTextNode(MonsterInformation.getMonsterSize()));
				overview.appendChild(size);
				//Type
				Element type = doc.createElement("type");
				type.appendChild(doc.createTextNode(MonsterInformation.getMonsterType()));
				overview.appendChild(type);
				//Tag
				Element tag = doc.createElement("tag");
				tag.appendChild(doc.createTextNode(MonsterInformation.getMonsterTag()));
				overview.appendChild(tag);
				//Alignment
				Element alignment = doc.createElement("alignment");
				alignment.appendChild(doc.createTextNode(MonsterInformation.getAlignment()));
				overview.appendChild(alignment);
				//Challenge Rating
				Element challengeRating = doc.createElement("challengerating");
				challengeRating.appendChild(doc.createTextNode(MonsterInformation.getChallengeRating()));
				overview.appendChild(challengeRating);
			//Ability Scores
			Element abilityscores = doc.createElement("abilityscores");
			rootElement.appendChild(abilityscores);
				//Strength
				Element str = doc.createElement("str");
				str.appendChild(doc.createTextNode(""+MonsterInformation.getStrength()));
				abilityscores.appendChild(str);
				//Dexterity
				Element dex = doc.createElement("dex");
				dex.appendChild(doc.createTextNode(""+MonsterInformation.getDexterity()));
				abilityscores.appendChild(dex);
				//Constitution
				Element con = doc.createElement("con");
				con.appendChild(doc.createTextNode(""+MonsterInformation.getConstitution()));
				abilityscores.appendChild(con);
				//Intelligence
				Element intel = doc.createElement("int");
				intel.appendChild(doc.createTextNode(""+MonsterInformation.getIntelligence()));
				abilityscores.appendChild(intel);
				//Wisdom
				Element wis = doc.createElement("wis");
				wis.appendChild(doc.createTextNode(""+MonsterInformation.getWisdom()));
				abilityscores.appendChild(wis);
				//Charisma
				Element cha = doc.createElement("cha");
				cha.appendChild(doc.createTextNode(""+MonsterInformation.getCharisma()));
				abilityscores.appendChild(cha);
			//Saving Throws
			Element saves = doc.createElement("saves");
			rootElement.appendChild(saves);
				//Strength
				Element strSave = doc.createElement("str");
				strSave.appendChild(doc.createTextNode(""+MonsterInformation.getStrengthSave()));
				saves.appendChild(strSave);
				//Dexterity
				Element dexSave = doc.createElement("dex");
				dexSave.appendChild(doc.createTextNode(""+MonsterInformation.getDexteritySave()));
				saves.appendChild(dexSave);
				//Constitution
				Element conSave = doc.createElement("con");
				conSave.appendChild(doc.createTextNode(""+MonsterInformation.getConstitutionSave()));
				saves.appendChild(conSave);
				//Intelligence
				Element intelSave = doc.createElement("int");
				intelSave.appendChild(doc.createTextNode(""+MonsterInformation.getIntelligenceSave()));
				saves.appendChild(intelSave);
				//Wisdom
				Element wisSave = doc.createElement("wis");
				wisSave.appendChild(doc.createTextNode(""+MonsterInformation.getWisdomSave()));
				saves.appendChild(wisSave);
				//Charisma
				Element chaSave = doc.createElement("cha");
				chaSave.appendChild(doc.createTextNode(""+MonsterInformation.getCharismaSave()));
				saves.appendChild(chaSave);
			//Speed
			Element speed = doc.createElement("speed");
			rootElement.appendChild(speed);
				//Walk
				Element walk = doc.createElement("walk");
				walk.appendChild(doc.createTextNode(""+MonsterInformation.getWalkSpeed()));
				speed.appendChild(walk);
				//Swim
				Element swim = doc.createElement("swim");
				swim.appendChild(doc.createTextNode(""+MonsterInformation.getSwimSpeed()));
				speed.appendChild(swim);
				//Burrow
				Element burrow = doc.createElement("burrow");
				burrow.appendChild(doc.createTextNode(""+MonsterInformation.getBurrowSpeed()));
				speed.appendChild(burrow);
				//Climb
				Element climb = doc.createElement("climb");
				climb.appendChild(doc.createTextNode(""+MonsterInformation.getClimbSpeed()));
				speed.appendChild(climb);
				//Fly
				Element fly = doc.createElement("fly");
				fly.appendChild(doc.createTextNode(""+MonsterInformation.getFlySpeed()));
				speed.appendChild(fly);
				//Hover?
				Element hover = doc.createElement("hover");
				hover.appendChild(doc.createTextNode(MonsterInformation.canHover() ? "true" : "false"));
				speed.appendChild(hover);
			//Abilities
			Element abilities = doc.createElement("abilities");
			for (Ability a : MonsterInformation.getAbilities()) {
				Element ability = doc.createElement("ability");
				//Name
				Attr name = doc.createAttribute("name");
				name.setValue(a.getName());
				ability.setAttributeNode(name);
				//Description
				Attr desc = doc.createAttribute("desc");
				desc.setValue(a.getDescription());
				ability.setAttributeNode(desc);
				abilities.appendChild(ability);
			}
			rootElement.appendChild(abilities);
			//Actions
			Element actions = doc.createElement("actions");
			for (Action a : MonsterInformation.getActions()) {
				Element action = doc.createElement("action");
				//Action Type
				Attr actionType = doc.createAttribute("type");
				actionType.setValue(a.getActionType().toString());
				action.setAttributeNode(actionType);
				//Name
				Attr name = doc.createAttribute("name");
				name.setValue(a.getName());
				action.setAttributeNode(name);
				//Dice Type
				Attr diceType = doc.createAttribute("dicetype");
				diceType.setValue(a.getDiceType());
				action.setAttributeNode(diceType);
				//Dice Count
				Attr diceCount = doc.createAttribute("dicecount");
				diceCount.setValue(""+a.getDiceCount());
				action.setAttributeNode(diceCount);
				//To hit
				Attr toHit = doc.createAttribute("tohit");
				toHit.setValue(""+a.getToHit());
				action.setAttributeNode(toHit);
				//Damage Type
				Attr damageType = doc.createAttribute("damagetype");
				damageType.setValue(a.getDamageType());
				action.setAttributeNode(damageType);
				//RangedMin
				Attr rangedMin = doc.createAttribute("rangedmin");
				rangedMin.setValue(""+a.getRangedMin());
				action.setAttributeNode(rangedMin);
				//RangedMax
				Attr rangedMax = doc.createAttribute("rangedmax");
				rangedMax.setValue(""+a.getRangedMax());
				action.setAttributeNode(rangedMax);
				//Ranged Shape
				Attr rangedShape = doc.createAttribute("rangedshape");
				rangedShape.setValue(a.getRangedShape().toString());
				action.setAttributeNode(rangedShape);
				//Ranged Delivery
				Attr rangedDelivery = doc.createAttribute("rangeddelivery");
				rangedDelivery.setValue(a.getRangedDelivery().toString());
				action.setAttributeNode(rangedDelivery);
				//Ranged Size
				Attr rangedSize = doc.createAttribute("rangedsize");
				rangedSize.setValue(""+a.getRangedSize());
				action.setAttributeNode(rangedSize);
				//Melee Reach
				Attr meleeReach = doc.createAttribute("meleereach");
				meleeReach.setValue(""+a.getMeleeReach());
				action.setAttributeNode(meleeReach);
				//Description
				Attr description = doc.createAttribute("description");
				description.setValue(a.getDescription());
				action.setAttributeNode(description);
				actions.appendChild(action);
			}
			rootElement.appendChild(actions);
			//Damage Modifier
			Element damageModifiers = doc.createElement("damagemodifiers");
			for (DamageModifier d : MonsterInformation.getDamageModifiers()) {
				Element damageModifier = doc.createElement("damagemodifier");
				//Modifier
				Attr modifier = doc.createAttribute("modifier");
				modifier.setValue(d.getModifier().toString());
				damageModifier.setAttributeNode(modifier);
				//Type
				Attr damType = doc.createAttribute("damagetype");
				damType.setValue(d.getType().toString());
				damageModifier.setAttributeNode(damType);
				//Value
				Attr value = doc.createAttribute("value");
				value.setValue(d.getValue());
				damageModifier.setAttributeNode(value);
				damageModifiers.appendChild(damageModifier);
			}
			rootElement.appendChild(damageModifiers);
			//Languages
			Element languages = doc.createElement("languages");
			for (Language l : MonsterInformation.getLanguages()) {
				Element language = doc.createElement("language");
				//Language
				language.appendChild(doc.createTextNode(l.getLang()));
				languages.appendChild(language);
			}
			rootElement.appendChild(languages);
			//Legendary Actions
			Element legendaryActions = doc.createElement("legendaryactions");
			for (LegendaryActions l : MonsterInformation.getLegendaryActions()) {
				Element legendaryActionSet = doc.createElement("actionset");
				//Number of moves
				Attr numberOfMoves = doc.createAttribute("numberofmoves");
				numberOfMoves.setValue(""+l.getUsesPerCycle());
				legendaryActionSet.setAttributeNode(numberOfMoves);
				//Individual Actions
				for (LegendaryActions.Action a : l.getActions()) {
					Element action = doc.createElement("action");
					legendaryActionSet.appendChild(action);
					//Name
					Attr name = doc.createAttribute("name");
					name.setValue(a.name);
					action.setAttributeNode(name);
					//Description
					Attr desc = doc.createAttribute("description");
					desc.setValue(a.text);
					action.setAttributeNode(desc);
				}
				legendaryActions.appendChild(legendaryActionSet);
			}
			rootElement.appendChild(legendaryActions);
			//Reactions
			Element reactions = doc.createElement("reactions");
			for (Reaction r : MonsterInformation.getReactions()) {
				Element reaction = doc.createElement("reaction");
				reactions.appendChild(reaction);
				//Name
				Attr name = doc.createAttribute("name");
				name.setValue(r.getName());
				reaction.setAttributeNode(name);
				//Description
				Attr desc = doc.createAttribute("description");
				desc.setValue(r.getDescription());
				reaction.setAttributeNode(desc);
			}
			rootElement.appendChild(reactions);
			//Senses
			Element senses = doc.createElement("senses");
			for (Sense s : MonsterInformation.getSenses()) {
				Element sense = doc.createElement("sense");
				senses.appendChild(sense);
				//Name
				Attr name = doc.createAttribute("name");
				name.setValue(s.getSense());
				sense.setAttributeNode(name);
				//Magnitude
				Attr mag = doc.createAttribute("magnitude");
				mag.setValue(""+s.getMagnitude());
				sense.setAttributeNode(mag);
			}
			rootElement.appendChild(senses);
			//Skill
			Element skills = doc.createElement("skills");
			for (Skill s : MonsterInformation.getSkills()) {
				Element skill = doc.createElement("skill");
				skills.appendChild(skill);
				//Name
				Attr name = doc.createAttribute("name");
				name.setValue(s.getSkill());
				skill.setAttributeNode(name);
				//Magnitude
				Attr mag = doc.createAttribute("magnitude");
				mag.setValue(""+s.getModifier());
				skill.setAttributeNode(mag);
			}
			rootElement.appendChild(skills);
			//Spellcaster
			Element spellcasters = doc.createElement("spellcasters");
			for (Spellcaster s : MonsterInformation.getSpellcaster()) {
				Element spellcaster = doc.createElement("spellcaster");
				for (Spellcaster.Spell sp : s.getSpells()) {
					Element spell = doc.createElement("spell");
					spellcaster.appendChild(spell);
					//Level
					Attr level = doc.createAttribute("level");
					level.setValue(""+sp.level);
					spell.setAttributeNode(level);
					//Spell
					Attr spellName = doc.createAttribute("spellname");
					spellName.setValue(sp.spell);
					spell.setAttributeNode(spellName);
					//Cast before combat
					Attr castOnCombat = doc.createAttribute("castbeforecombat");
					castOnCombat.setValue(sp.castOnCombat ? "true" : "false");
					spell.setAttributeNode(castOnCombat);
				}
				spellcasters.appendChild(spellcaster);
				//Class
				Attr spellClass = doc.createAttribute("class");
				spellClass.setValue(s.getSpellClass());
				spellcaster.setAttributeNode(spellClass);
				//Ability
				Attr spellAbility = doc.createAttribute("ability");
				spellAbility.setValue(s.getSpellcastingAbility().toString());
				spellcaster.setAttributeNode(spellAbility);
				//Spellcasting Level
				Attr spellLevel = doc.createAttribute("level");
				spellLevel.setValue(""+s.getSpellcasterLevel());
				spellcaster.setAttributeNode(spellLevel);
				//Spell Save
				Attr spellSave = doc.createAttribute("savedc");
				spellLevel.setValue(""+s.getSpellsaveDC());
				spellcaster.setAttributeNode(spellSave);
				//To Hit
				Attr toHit = doc.createAttribute("tohit");
				toHit.setValue(""+s.getToHit());
				spellcaster.setAttributeNode(toHit);
				//Spell Levels
				int[] spellLevels = s.getSpellSlots();
				for (int i = 0; i < 9; i++) {
					Attr levelSpells = doc.createAttribute(String.format("level%dspells", i+1));
					levelSpells.setValue(""+spellLevels[i]);
					spellcaster.setAttributeNode(levelSpells);
				}
			}
			rootElement.appendChild(spellcasters);
			//Write XML file (.monsterbrewery)
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(file);
			transformer.transform(source, result);
		} catch (ParserConfigurationException | TransformerException ex) {
			Logger.getLogger(SaveCreator.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
