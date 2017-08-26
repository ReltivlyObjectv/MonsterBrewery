package tech.relativelyobjective.monsterbrewery.filestorage;

import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import tech.relativelyobjective.monsterbrewery.attributes.*;
import static tech.relativelyobjective.monsterbrewery.filestorage.SaveCreator.setFileLocation;
import tech.relativelyobjective.monsterbrewery.pieces.FrameMain;
import tech.relativelyobjective.monsterbrewery.resources.AttributeHandler;
import tech.relativelyobjective.monsterbrewery.resources.MonsterInformation;

/**
 *
 * @author ReltivlyObjectv
 * Contact: me@relativelyobjective.tech
 * 
 */
public class SaveLoader {
	public static void loadSave(File saveFile, FrameMain mainFrame) {
		//Mark as save file
		//Open file
		Document doc;
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(saveFile);
			doc.getDocumentElement().normalize();
			
		} catch (SAXException | IOException | ParserConfigurationException ex) {
			warnAboutLoad(mainFrame);
			return;
		}
		SaveWiper.resetProgram(mainFrame);
		//Note: loops are used as a way to skip over missing sections without error
		//Overview
		NodeList overview = doc.getElementsByTagName("overview");
		for (int i = 0; i < overview.getLength(); i++) {
			Node node = overview.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				MonsterInformation.setMonsterName(element.getElementsByTagName("name").item(0).getTextContent());
				MonsterInformation.setArmorClass(element.getElementsByTagName("armorclass").item(0).getTextContent());
				MonsterInformation.setArmorType(element.getElementsByTagName("armortype").item(0).getTextContent());
				MonsterInformation.setHitPointDiceType(element.getElementsByTagName("dicetype").item(0).getTextContent());
				MonsterInformation.setHitPointDiceCount(element.getElementsByTagName("dicecount").item(0).getTextContent());
				MonsterInformation.setHitPointString(element.getElementsByTagName("hptotal").item(0).getTextContent());
				MonsterInformation.setMonsterSize(element.getElementsByTagName("size").item(0).getTextContent());
				MonsterInformation.setMonsterType(element.getElementsByTagName("type").item(0).getTextContent());
				MonsterInformation.setMonsterTag(element.getElementsByTagName("tag").item(0).getTextContent());
				MonsterInformation.setAlignment(element.getElementsByTagName("alignment").item(0).getTextContent());
				MonsterInformation.setChallengeRating(element.getElementsByTagName("challengerating").item(0).getTextContent());
				MonsterInformation.setPronoun(element.getElementsByTagName("pronoun").item(0).getTextContent());
			}
		}
		//Ability Scores
		NodeList abilityScores = doc.getElementsByTagName("abilityscores");
		for (int i = 0; i < abilityScores.getLength(); i++) {
			Node node = abilityScores.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				MonsterInformation.setStrength(element.getElementsByTagName("str").item(0).getTextContent());
				MonsterInformation.setDexterity(element.getElementsByTagName("dex").item(0).getTextContent());
				MonsterInformation.setConstitution(element.getElementsByTagName("con").item(0).getTextContent());
				MonsterInformation.setIntelligence(element.getElementsByTagName("int").item(0).getTextContent());
				MonsterInformation.setWisdom(element.getElementsByTagName("wis").item(0).getTextContent());
				MonsterInformation.setCharisma(element.getElementsByTagName("cha").item(0).getTextContent());
				
			}
		}
		//Ability Saves
		NodeList abilitySaves = doc.getElementsByTagName("saves");
		for (int i = 0; i < abilitySaves.getLength(); i++) {
			Node node = abilitySaves.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				MonsterInformation.setStrengthSave(element.getElementsByTagName("str").item(0).getTextContent());
				MonsterInformation.setDexteritySave(element.getElementsByTagName("dex").item(0).getTextContent());
				MonsterInformation.setConstitutionSave(element.getElementsByTagName("con").item(0).getTextContent());
				MonsterInformation.setIntelligenceSave(element.getElementsByTagName("int").item(0).getTextContent());
				MonsterInformation.setWisdomSave(element.getElementsByTagName("wis").item(0).getTextContent());
				MonsterInformation.setCharismaSave(element.getElementsByTagName("cha").item(0).getTextContent());
			}
		}
		//Speed
		NodeList speed = doc.getElementsByTagName("speed");
		for (int i = 0; i < speed.getLength(); i++) {
			Node node = speed.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				MonsterInformation.setWalkSpeed(element.getElementsByTagName("walk").item(0).getTextContent());
				MonsterInformation.setSwimSpeed(element.getElementsByTagName("swim").item(0).getTextContent());
				MonsterInformation.setBurrowSpeed(element.getElementsByTagName("burrow").item(0).getTextContent());
				MonsterInformation.setClimbSpeed(element.getElementsByTagName("climb").item(0).getTextContent());
				MonsterInformation.setFlySpeed(element.getElementsByTagName("fly").item(0).getTextContent());
				MonsterInformation.setCanHover(element.getElementsByTagName("hover").item(0).getTextContent());
			}
		}
		//Abilities
		NodeList abilities = doc.getElementsByTagName("abilities");
		for (int i = 0; i < abilities.getLength(); i++) {
			Node node = abilities.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				NodeList abilityList = element.getElementsByTagName("ability");
				for (int j = 0; j < abilityList.getLength(); j++) {
					Node abilityNode = abilityList.item(j);
					if (abilityNode.getNodeType() == Node.ELEMENT_NODE) {
						Element ability = (Element) abilityNode;
						Ability ab = new Ability();
						ab.setDescription(ability.getAttribute("desc"));
						ab.setName(ability.getAttribute("name"));
						AttributeHandler.addAttribute(ab);
					}
				}
			}
		}
		//Actions
		NodeList actions = doc.getElementsByTagName("actions");
		for (int i = 0; i < actions.getLength(); i++) {
			Node node = actions.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				NodeList actionList = element.getElementsByTagName("action");
				for (int j = 0; j < actionList.getLength(); j++) {
					Node actionNode = actionList.item(j);
					if (actionNode.getNodeType() == Node.ELEMENT_NODE) {
						Element action = (Element) actionNode;
						Action a = new Action();
						a.setDamageType(action.getAttribute("damagetype"));
						a.setDescription(action.getAttribute("description"));
						a.setDiceCount(action.getAttribute("dicecount"));
						a.setDiceType(action.getAttribute("dicetype"));
						a.setMeleeReach(action.getAttribute("meleereach"));
						a.setName(action.getAttribute("name"));
						a.setRangedDelivery(action.getAttribute("rangeddelivery"));
						a.setRangedMax(action.getAttribute("rangedmax"));
						a.setRangedMin(action.getAttribute("rangedmin"));
						a.setRangedShape(action.getAttribute("rangedshape"));
						a.setRangedSize(action.getAttribute("rangedsize"));
						a.setToHit(action.getAttribute("tohit"));
						a.setActionType(action.getAttribute("type"));
						AttributeHandler.addAttribute(a);
					}
				}
			}
		}
		//Damage Modifiers
		NodeList modifiers = doc.getElementsByTagName("damagemodifiers");
		for (int i = 0; i < modifiers.getLength(); i++) {
			Node node = modifiers.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				NodeList modifierList = element.getElementsByTagName("damagemodifier");
				for (int j = 0; j < modifierList.getLength(); j++) {
					Node modifierNode = modifierList.item(j);
					if (modifierNode.getNodeType() == Node.ELEMENT_NODE) {
						Element modifier = (Element) modifierNode;
						DamageModifier d = new DamageModifier();
						d.setModifier(modifier.getAttribute("modifier"));
						d.setType(modifier.getAttribute("damagetype"));
						d.setValue(modifier.getAttribute("value"));
						AttributeHandler.addAttribute(d);
					}
				}
			}
		}
		//Language
		NodeList languages = doc.getElementsByTagName("languages");
		for (int i = 0; i < languages.getLength(); i++) {
			Node node = languages.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				NodeList languageList = element.getElementsByTagName("language");
				for (int j = 0; j < languageList.getLength(); j++) {
					Node languageNode = languageList.item(j);
					if (languageNode.getNodeType() == Node.ELEMENT_NODE) {
						Element lang = (Element) languageNode;
						Language l = new Language();
						l.setLang(lang.getTextContent());
						AttributeHandler.addAttribute(l);
					}
				}
			}
		}
		//Legendary Actions
		NodeList legendaries = doc.getElementsByTagName("legendaryactions");
		for (int i = 0; i < legendaries.getLength(); i++) {
			Node node = legendaries.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				NodeList legendaryList = element.getElementsByTagName("actionset");
				for (int j = 0; j < legendaryList.getLength(); j++) {
					Node legendaryNode = legendaryList.item(j);
					if (legendaryNode.getNodeType() == Node.ELEMENT_NODE) {
						Element legendarySet = (Element) legendaryNode;
						LegendaryActions leg = new LegendaryActions();
						leg.setUsesPerCycle(legendarySet.getAttribute("numberofmoves"));
						NodeList actionList = legendarySet.getElementsByTagName("action");
						for (int k = 0; k < actionList.getLength(); k++) {
							Node actionNode = actionList.item(k);
							if (actionNode.getNodeType() == Node.ELEMENT_NODE) {
								Element action = (Element) actionNode;
								LegendaryActions.Action a = leg.new Action();
								a.setName(action.getAttribute("name"));
								a.setText(action.getAttribute("description"));
								leg.addAction(a);
							}
						}
						AttributeHandler.addAttribute(leg);
					}
				}
			}
		}
		//Reaction
		NodeList reactions = doc.getElementsByTagName("reactions");
		for (int i = 0; i < reactions.getLength(); i++) {
			Node node = reactions.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				NodeList reactionList = element.getElementsByTagName("reaction");
				for (int j = 0; j < reactionList.getLength(); j++) {
					Node reactionNode = reactionList.item(j);
					if (reactionNode.getNodeType() == Node.ELEMENT_NODE) {
						Element reaction = (Element) reactionNode;
						Reaction r = new Reaction();
						r.setDescription(reaction.getAttribute("description"));
						r.setName(reaction.getAttribute("name"));
						AttributeHandler.addAttribute(r);
					}
				}
			}
		}
		//Sense
		NodeList senses = doc.getElementsByTagName("senses");
		for (int i = 0; i < senses.getLength(); i++) {
			Node node = senses.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				NodeList senseList = element.getElementsByTagName("sense");
				for (int j = 0; j < senseList.getLength(); j++) {
					Node senseNode = senseList.item(j);
					if (senseNode.getNodeType() == Node.ELEMENT_NODE) {
						Element sense = (Element) senseNode;
						Sense s = new Sense();
						s.setSense(sense.getAttribute("name"));
						s.setMagnitude(sense.getAttribute("magnitude"));
						AttributeHandler.addAttribute(s);
					}
				}
			}
		}
		//Skill
		NodeList skills = doc.getElementsByTagName("skills");
		for (int i = 0; i < skills.getLength(); i++) {
			Node node = skills.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				NodeList senseList = element.getElementsByTagName("skill");
				for (int j = 0; j < senseList.getLength(); j++) {
					Node senseNode = senseList.item(j);
					if (senseNode.getNodeType() == Node.ELEMENT_NODE) {
						Element sense = (Element) senseNode;
						Skill s = new Skill();
						s.setSkill(sense.getAttribute("name"));
						s.setModifier(sense.getAttribute("magnitude"));
						AttributeHandler.addAttribute(s);
					}
				}
			}
		}
		//Spellcaster
		NodeList spellcasters = doc.getElementsByTagName("spellcasters");
		for (int i = 0; i < spellcasters.getLength(); i++) {
			Node node = spellcasters.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				NodeList spellcasterList = element.getElementsByTagName("spellcaster");
				for (int j = 0; j < spellcasterList.getLength(); j++) {
					Node spellcasterNode = spellcasterList.item(j);
					if (spellcasterNode.getNodeType() == Node.ELEMENT_NODE) {
						Element spellcasterSet = (Element) spellcasterNode;
						Spellcaster caster = new Spellcaster();
						caster.setSpellcastingAbility(spellcasterSet.getAttribute("ability"));
						caster.setSpellClass(spellcasterSet.getAttribute("class"));
						caster.setSpellcasterLevel(spellcasterSet.getAttribute("level"));
						caster.setSpellsaveDC(spellcasterSet.getAttribute("savedc"));
						caster.setToHit(spellcasterSet.getAttribute("tohit"));
						for (int k = 0; k < 9; k++) {
							int slots;
							try {
								slots = Integer.parseInt(spellcasterSet.getAttribute("level"+(k+1)+"spells"));
							} catch (NumberFormatException e) {
								continue;
							}
							caster.setSpellSlots(k+1, slots);
						}
						NodeList spellList = spellcasterSet.getElementsByTagName("spell");
						for (int k = 0; k < spellList.getLength(); k++) {
							Node actionNode = spellList.item(k);
							if (actionNode.getNodeType() == Node.ELEMENT_NODE) {
								Element spell = (Element) actionNode;
								Spellcaster.Spell s = caster.new Spell();
								try {
									s.spell = spell.getAttribute("spellname");
									s.castOnCombat = Boolean.parseBoolean(spell.getAttribute("castbeforecombat"));
									s.level = Integer.parseInt(spell.getAttribute("level"));
								} catch (NumberFormatException e) {
									continue;
								}
								caster.addSpell(s);
							}
						}
						AttributeHandler.addAttribute(caster);
					}
				}
			}
		}
		SaveCreator.setFileLocation(saveFile, mainFrame);
	}
	private static void warnAboutLoad(FrameMain mainFrame) {
		JOptionPane.showMessageDialog(mainFrame, "Could not load file! (Have you manually modified it?)");
	}
	public static void openLoadPrompt(FrameMain mainFrame) {
		JFileChooser fileChooser = new JFileChooser();
		if (SaveCreator.getFileLocation() != null) {
			fileChooser.setCurrentDirectory(SaveCreator.getFileLocation().getParentFile());
		}
		FileNameExtensionFilter filter = new FileNameExtensionFilter("MonsterBrewery Files", "monsterbrewery");
		fileChooser.setFileFilter(filter);
		if (fileChooser.showOpenDialog(mainFrame) == JFileChooser.APPROVE_OPTION) {
			File saveLocation = fileChooser.getSelectedFile();
			setFileLocation(saveLocation, mainFrame);
			loadSave(saveLocation, mainFrame);
		}
	}
}
