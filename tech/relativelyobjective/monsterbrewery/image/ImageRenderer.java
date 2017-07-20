package tech.relativelyobjective.monsterbrewery.image;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import tech.relativelyobjective.monsterbrewery.attributes.*;
import tech.relativelyobjective.monsterbrewery.pieces.FrameMain;
import tech.relativelyobjective.monsterbrewery.resources.Abilities;
import tech.relativelyobjective.monsterbrewery.resources.FontManager;
import tech.relativelyobjective.monsterbrewery.resources.JLabelBrown;
import tech.relativelyobjective.monsterbrewery.resources.Lists;
import tech.relativelyobjective.monsterbrewery.resources.MonsterInformation;

/**
 *
 * @author ReltivlyObjectv
 * Contact: me@relativelyobjective.tech
 * 
 */
public class ImageRenderer {
	private static FrameMain mainFrame;
	
	public static void initialize(FrameMain mainF) {
		mainFrame = mainF;
	}
	public static BufferedImage getImage(JDialog renderWindow) {
		return null;
	}
	public static void renderImage(FrameMain mainFrame) {
		JDialog renderWindow = new JDialog(mainFrame, "Render Monster", true);
		JPanel windowContents = new JPanel();
			windowContents.setLayout(new GridBagLayout());
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			constraints.ipadx = 0;
			constraints.ipady = 0;
			constraints.weightx = 1;
			constraints.fill = GridBagConstraints.NONE;
			windowContents.add(getNewEndCap(), constraints);
			constraints.gridy++;
			windowContents.add(getWindowContents(), constraints);
			constraints.gridy++;
			windowContents.add(getNewEndCap(), constraints);
		renderWindow.add(windowContents);
		renderWindow.setPreferredSize(new Dimension(430, 700));
		renderWindow.setSize(new Dimension(430, 700));
		renderWindow.setVisible(true);
	}
	private static JPanel getNewEndCap() {
		JPanel returnMe = new JPanel();
		returnMe.setPreferredSize(new Dimension(425,5));
		returnMe.setSize(returnMe.getPreferredSize());
		returnMe.setMaximumSize(returnMe.getPreferredSize());
		returnMe.setMinimumSize(returnMe.getPreferredSize());
		returnMe.setBackground(Color.decode("#e49937"));
		returnMe.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		return returnMe;
	}
	private static JPanel getWindowContents() {
		JPanel returnMe = new JPanel();
		returnMe.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.anchor = GridBagConstraints.WEST;
		for (int i = 0; i < 3; i++) {
			constraints.gridx = i;
			JPanel spacer = new JPanel();
			spacer.setOpaque(false);
			spacer.setPreferredSize(new Dimension(9,1));
			returnMe.add(spacer, constraints);
		}
		//Actual content
		String nameData = MonsterInformation.getMonsterName();
		nameData = nameData.equals("") ? "No Name" : nameData;
		JLabel nameLabel = new JLabel(nameData);
		nameLabel.setFont(FontManager.getFontTitle(18));
		constraints.gridy++;
		constraints.gridx = 1;
		returnMe.add(nameLabel, constraints);
		constraints.ipady = 3;
		constraints.gridy++;
		JLabel sizeTypeTag = new JLabel(String.format("%s %s %s, %s",
			Lists.formatUpperCase(MonsterInformation.getMonsterSize()),
			MonsterInformation.getMonsterType().toLowerCase(),
			MonsterInformation.getMonsterTag().toLowerCase(),
			MonsterInformation.getAlignment().toLowerCase()
			));
		sizeTypeTag.setFont(FontManager.getFontItalic(10));
		returnMe.add(sizeTypeTag, constraints);
		constraints.gridy++;
		returnMe.add(getNewSeperator(), constraints);
		constraints.gridy++;
		returnMe.add(getOverview(), constraints);
		constraints.gridy++;
		returnMe.add(getNewSeperator(), constraints);
		constraints.gridy++;
		returnMe.add(getStatArea(), constraints);
		constraints.gridy++;
		returnMe.add(getNewSeperator(), constraints);
		constraints.gridy++;
		returnMe.add(getMiscAttributes(), constraints);
		constraints.gridy++;
		returnMe.add(getNewSeperator(), constraints);
		constraints.gridy++;
		
		
		
		//returnMe.setPreferredSize(new Dimension(420,250));
		//returnMe.setMaximumSize(new Dimension(420,250));
		//returnMe.setMinimumSize(new Dimension(420,0));
		//returnMe.setSize(returnMe.getPreferredSize());
		returnMe.setBackground(Color.decode("#fdf1dd"));
		returnMe.setBorder(BorderFactory.createMatteBorder(0,1,0,1,
			Color.decode("#DCDCDC")));
		return returnMe;
	}
	private static JPanel getOverview() {
		JPanel returnMe = new JPanel();
		returnMe.setOpaque(false);
		returnMe.setLayout(new BoxLayout(returnMe, BoxLayout.Y_AXIS));
		JPanel armorClass = new JPanel();
			armorClass.setOpaque(false);
			armorClass.setLayout(new BoxLayout(armorClass, BoxLayout.X_AXIS));
			JLabelBrown acLabel = new JLabelBrown("Armor Class ");
			acLabel.setFont(FontManager.getFontBold(12));
			armorClass.add(acLabel);
			JLabelBrown acData = new JLabelBrown(String.format("%d %s",
				MonsterInformation.getArmorClass(),
				MonsterInformation.getArmorType()
			));
			acData.setFont(FontManager.getFontRegular(12));
			armorClass.add(acData);
			armorClass.add(Box.createHorizontalGlue());
		returnMe.add(armorClass);
		JPanel hitPoints = new JPanel();
			hitPoints.setOpaque(false);
			hitPoints.setLayout(new BoxLayout(hitPoints, BoxLayout.X_AXIS));
			JLabelBrown hpLabel = new JLabelBrown("Hit Points ");
			hpLabel.setFont(FontManager.getFontBold(12));
			hitPoints.add(hpLabel);
			JLabelBrown hpData = new JLabelBrown(MonsterInformation.getHitPointString());
			hpData.setFont(FontManager.getFontRegular(12));
			hitPoints.add(hpData);
			hitPoints.add(Box.createHorizontalGlue());
		returnMe.add(hitPoints);
		JPanel speed = new JPanel();
			speed.setOpaque(false);
			speed.setLayout(new BoxLayout(speed, BoxLayout.X_AXIS));
			JLabelBrown speedLabel = new JLabelBrown("Speed ");
			speedLabel.setFont(FontManager.getFontBold(12));
			speed.add(speedLabel);
			JLabelBrown speedData = new JLabelBrown(
				String.format("%d ft.",MonsterInformation.getWalkSpeed()
				));
			speedData.setFont(FontManager.getFontRegular(12));
			speed.add(speedData);
			speed.add(Box.createHorizontalGlue());
		returnMe.add(speed);
		return returnMe;
	}
	private static JPanel getMiscAttributes() {
		JPanel returnMe = new JPanel();
		returnMe.setLayout(new BoxLayout(returnMe, BoxLayout.Y_AXIS));
		returnMe.setOpaque(false);
		//Saving Throws
		List<String> savingThrows = new LinkedList<>();
		if (MonsterInformation.getStrengthSave() != 0) {
			savingThrows.add(String.format("%s %s%d",
				"Str",
				MonsterInformation.getStrengthSave() > 0 ? "+" : "",
				MonsterInformation.getStrengthSave()
			));
		}
		if (MonsterInformation.getDexteritySave()!= 0) {
			savingThrows.add(String.format("%s %s%d",
				"Dex",
				MonsterInformation.getDexteritySave() > 0 ? "+" : "",
				MonsterInformation.getDexteritySave()
			));
		}
		if (MonsterInformation.getConstitutionSave()!= 0) {
			savingThrows.add(String.format("%s %s%d",
				"Con",
				MonsterInformation.getConstitutionSave() > 0 ? "+" : "",
				MonsterInformation.getConstitutionSave()
			));
		}
		if (MonsterInformation.getIntelligenceSave()!= 0) {
			savingThrows.add(String.format("%s %s%d",
				"Int",
				MonsterInformation.getIntelligenceSave() > 0 ? "+" : "",
				MonsterInformation.getIntelligenceSave()
			));
		}
		if (MonsterInformation.getWisdomSave()!= 0) {
			savingThrows.add(String.format("%s %s%d",
				"Wis",
				MonsterInformation.getWisdomSave() > 0 ? "+" : "",
				MonsterInformation.getWisdomSave()
			));
		}
		if (MonsterInformation.getCharismaSave()!= 0) {
			savingThrows.add(String.format("%s %s%d",
				"Cha",
				MonsterInformation.getCharismaSave() > 0 ? "+" : "",
				MonsterInformation.getCharismaSave()
			));
		}
		if (savingThrows.size() > 0) {
			JPanel panel = new JPanel();
			panel.setOpaque(false);
			panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
			JLabelBrown label = new JLabelBrown("Saving Throws");
			label.setFont(FontManager.getFontBold(12));
			panel.add(label);
			String string = " ";
			int i = 0;
			for (String s : savingThrows) {
				if (i > 0) {
					string += ", ";
				}
				string += s;
				i++;
			}
			string += " ";
			JLabelBrown data = new JLabelBrown(string);
			data.setFont(FontManager.getFontRegular(12));
			panel.add(data);
			panel.add(Box.createHorizontalGlue());
			returnMe.add(panel);
		}
		//Misc
		List<Skill> skillModifiers = new LinkedList<>(MonsterInformation.getSkills());
		for (Skill s : skillModifiers) {
			if (s.getSkill().toLowerCase().contains("perception")) {
				skillModifiers.remove(s);
			}
		}
		if (skillModifiers.size() > 0) {
			JPanel panel = new JPanel();
			panel.setOpaque(false);
			panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
			JLabelBrown label = new JLabelBrown("Skills");
			label.setFont(FontManager.getFontBold(12));
			panel.add(label);
			String string = " ";
			int i = 0;
			for (Skill s : skillModifiers) {
				if (i > 0) {
					string += ", ";
				}
				string += String.format("%s %s%d",
					s.getSkill(),
					s.getModifier() > 0 ? "+" : "",
					s.getModifier()
					);
				i++;
			}
			string += " ";
			JLabelBrown data = new JLabelBrown(string);
			data.setFont(FontManager.getFontRegular(12));
			panel.add(data);
			panel.add(Box.createHorizontalGlue());
			returnMe.add(panel);
		}
		List<DamageModifier> damageImmunities = new LinkedList<>();
		List<DamageModifier> damageResistances = new LinkedList<>();
		List<DamageModifier> damageVulnerabilities = new LinkedList<>();
		List<DamageModifier> conditionImmunities = new LinkedList<>();
		List<DamageModifier> conditionResistances = new LinkedList<>();
		List<DamageModifier> conditionVulnerabilities = new LinkedList<>();
		for (DamageModifier d : MonsterInformation.getDamageModifiers()) {
			switch (d.getModifier()) {
				case IMMUNE:
					//IMMUNE
					if (d.getType() == DamageModifier.ModifierType.CONDITION) {
						conditionImmunities.add(d);
					} else {
						damageImmunities.add(d);
					}
					break;
				case RESISTANT:
					//Resistant
					if (d.getType() == DamageModifier.ModifierType.CONDITION) {
						conditionResistances.add(d);
					} else {
						damageResistances.add(d);
					}
					break;
				default:
					//Vulnerable
					if (d.getType() == DamageModifier.ModifierType.CONDITION) {
						conditionVulnerabilities.add(d);
					} else {
						damageVulnerabilities.add(d);
					}
					break;
			}
		}
		//Print Modifiers
		if (damageImmunities.size() > 0) {
			JPanel panel = new JPanel();
			panel.setOpaque(false);
			panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
			JLabelBrown label = new JLabelBrown("Damage Immunities");
			label.setFont(FontManager.getFontBold(12));
			panel.add(label);
			String string = " ";
			int i = 0;
			for (DamageModifier d : damageImmunities) {
				if (i > 0) {
					string += ", ";
				}
				string += d.getValue().toLowerCase();
				i++;
			}
			string += " ";
			JLabelBrown data = new JLabelBrown(string);
			data.setFont(FontManager.getFontRegular(12));
			panel.add(data);
			panel.add(Box.createHorizontalGlue());
			returnMe.add(panel);
		}
		if (damageResistances.size() > 0) {
			JPanel panel = new JPanel();
			panel.setOpaque(false);
			panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
			JLabelBrown label = new JLabelBrown("Damage Resistances");
			label.setFont(FontManager.getFontBold(12));
			panel.add(label);
			String string = " ";
			int i = 0;
			for (DamageModifier d : damageResistances) {
				if (i > 0) {
					string += ", ";
				}
				string += d.getValue().toLowerCase();
				i++;
			}
			string += " ";
			JLabelBrown data = new JLabelBrown(string);
			data.setFont(FontManager.getFontRegular(12));
			panel.add(data);
			panel.add(Box.createHorizontalGlue());
			returnMe.add(panel);
		}
		if (damageVulnerabilities.size() > 0) {
			JPanel panel = new JPanel();
			panel.setOpaque(false);
			panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
			JLabelBrown label = new JLabelBrown("Damage Vulnerabilities");
			label.setFont(FontManager.getFontBold(12));
			panel.add(label);
			String string = " ";
			int i = 0;
			for (DamageModifier d : damageVulnerabilities) {
				if (i > 0) {
					string += ", ";
				}
				string += d.getValue().toLowerCase();
				i++;
			}
			string += " ";
			JLabelBrown data = new JLabelBrown(string);
			data.setFont(FontManager.getFontRegular(12));
			panel.add(data);
			panel.add(Box.createHorizontalGlue());
			returnMe.add(panel);
		}
		if (conditionImmunities.size() > 0) {
			JPanel panel = new JPanel();
			panel.setOpaque(false);
			panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
			JLabelBrown label = new JLabelBrown("Condition Immunities");
			label.setFont(FontManager.getFontBold(12));
			panel.add(label);
			String string = " ";
			int i = 0;
			for (DamageModifier d : conditionImmunities) {
				if (i > 0) {
					string += ", ";
				}
				string += d.getValue().toLowerCase();
				i++;
			}
			string += " ";
			JLabelBrown data = new JLabelBrown(string);
			data.setFont(FontManager.getFontRegular(12));
			panel.add(data);
			panel.add(Box.createHorizontalGlue());
			returnMe.add(panel);
		}
		if (conditionResistances.size() > 0) {
			JPanel panel = new JPanel();
			panel.setOpaque(false);
			panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
			JLabelBrown label = new JLabelBrown("Condition Resistances");
			label.setFont(FontManager.getFontBold(12));
			panel.add(label);
			String string = " ";
			int i = 0;
			for (DamageModifier d : conditionResistances) {
				if (i > 0) {
					string += ", ";
				}
				string += d.getValue().toLowerCase();
				i++;
			}
			string += " ";
			JLabelBrown data = new JLabelBrown(string);
			data.setFont(FontManager.getFontRegular(12));
			panel.add(data);
			panel.add(Box.createHorizontalGlue());
			returnMe.add(panel);
		}
		if (conditionVulnerabilities.size() > 0) {
			JPanel panel = new JPanel();
			panel.setOpaque(false);
			panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
			JLabelBrown label = new JLabelBrown("Condition Vulnerabilities");
			label.setFont(FontManager.getFontBold(12));
			panel.add(label);
			String string = " ";
			int i = 0;
			for (DamageModifier d : conditionVulnerabilities) {
				if (i > 0) {
					string += ", ";
				}
				string += d.getValue().toLowerCase();
				i++;
			}
			string += " ";
			JLabelBrown data = new JLabelBrown(string);
			data.setFont(FontManager.getFontRegular(12));
			panel.add(data);
			panel.add(Box.createHorizontalGlue());
			returnMe.add(panel);
		}
		//Senses
		List<Attribute> senses = new LinkedList<>(MonsterInformation.getSenses());
		for (Skill s : MonsterInformation.getSkills()) {
			if (s.getSkill().toLowerCase().contains("perception")) {
				senses.add(s);
				break;
			}
		}
		if (senses.size() > 0) {
			JPanel panel = new JPanel();
			panel.setOpaque(false);
			panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
			JLabelBrown label = new JLabelBrown("Senses");
			label.setFont(FontManager.getFontBold(12));
			panel.add(label);
			String string = " ";
			int i = 0;
			for (Attribute a : senses) {
				if (i > 0) {
					string += ", ";
				}
				if (a instanceof Sense) {
					string += String.format("%s %d ft.",
						((Sense) a).getSense().toLowerCase(),
						((Sense) a).getMagnitude()
						).toLowerCase();
					i++;
				} else if (a instanceof Skill) {
					string += String.format("%s %d",
					((Skill) a).getSkill().toLowerCase(),
					((Skill) a).getModifier()
					);
				}
				i++;
			}
			string += " ";
			JLabelBrown data = new JLabelBrown(string);
			data.setFont(FontManager.getFontRegular(12));
			panel.add(data);
			panel.add(Box.createHorizontalGlue());
			returnMe.add(panel);
		}
		//Languages
		List<Language> languages = new LinkedList<>(MonsterInformation.getLanguages());
		if (languages.size() > 0) {
			JPanel panel = new JPanel();
			panel.setOpaque(false);
			panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
			JLabelBrown label = new JLabelBrown("Languages");
			label.setFont(FontManager.getFontBold(12));
			panel.add(label);
			String string = " ";
			int i = 0;
			for (Language l : languages) {
				if (i > 0) {
					string += ", ";
				}
				string += l.getLang().toLowerCase();
				i++;
			}
			string += " ";
			JLabelBrown data = new JLabelBrown(string);
			data.setFont(FontManager.getFontRegular(12));
			panel.add(data);
			panel.add(Box.createHorizontalGlue());
			returnMe.add(panel);
		}
		if (true) {
			JPanel panel = new JPanel();
			panel.setOpaque(false);
			panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
			JLabelBrown label = new JLabelBrown("Challenge");
			label.setFont(FontManager.getFontBold(12));
			panel.add(label);
			String string = " "; 
			String challengeRatingString = String.format("%s (%d XP)", 
				MonsterInformation.getChallengeRating(), 
				Lists.calculateChallengeXP(MonsterInformation.getChallengeRating()));
			string += challengeRatingString;
			JLabelBrown data = new JLabelBrown(string);
			data.setFont(FontManager.getFontRegular(12));
			panel.add(data);
			panel.add(Box.createHorizontalGlue());
			returnMe.add(panel);
		}
		return returnMe;
	}
	private static JPanel getStatArea() {
		JPanel returnMe = new JPanel();
		returnMe.setOpaque(false);
		returnMe.setPreferredSize(new Dimension(400,50));
		returnMe.setMaximumSize(returnMe.getPreferredSize());
		returnMe.setMinimumSize(returnMe.getPreferredSize());
		returnMe.setSize(returnMe.getPreferredSize());
		returnMe.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.ipadx = 15;
		constraints.ipady = 5;
		constraints.weightx = (1/6);
		for (int i = 0; i < 6; i++) {
			JLabelBrown label = new JLabelBrown(Lists.ABILITIES[i].toUpperCase());
			label.setFont(FontManager.getFontBold(12));
			returnMe.add(label, constraints);
			constraints.gridx++;
		}
		constraints.gridy++;
		constraints.gridx = 0;
		int str = MonsterInformation.getStrength();
		JLabelBrown strL = new JLabelBrown(String.format("%d (%s)",
			str,
			str >= 10 ? "+"+Abilities.calculateModifier(str) : 
			Abilities.calculateModifier(str)
		));
		returnMe.add(strL, constraints);
		int dex = MonsterInformation.getDexterity();
		JLabelBrown dexL = new JLabelBrown(String.format("%d (%s)",
			dex,
			dex >= 10 ? "+"+Abilities.calculateModifier(dex) : 
			Abilities.calculateModifier(dex)
		));
		constraints.gridx++;
		returnMe.add(dexL, constraints);
		int con = MonsterInformation.getConstitution();
		JLabelBrown conL = new JLabelBrown(String.format("%d (%s)",
			con,
			con >= 10 ? "+"+Abilities.calculateModifier(con) : 
			Abilities.calculateModifier(con)
		));
		constraints.gridx++;
		returnMe.add(conL, constraints);
		int intel = MonsterInformation.getIntelligence();
		JLabelBrown intelL = new JLabelBrown(String.format("%d (%s)",
			intel,
			intel >= 10 ? "+"+Abilities.calculateModifier(intel) : 
			Abilities.calculateModifier(intel)
		));
		constraints.gridx++;
		returnMe.add(intelL, constraints);
		int wis = MonsterInformation.getIntelligence();
		JLabelBrown wisL = new JLabelBrown(String.format("%d (%s)",
			wis,
			wis >= 10 ? "+"+Abilities.calculateModifier(wis) : 
			Abilities.calculateModifier(wis)
		));
		constraints.gridx++;
		returnMe.add(wisL, constraints);
		int cha = MonsterInformation.getCharisma();
		JLabelBrown chaL = new JLabelBrown(String.format("%d (%s)",
			cha,
			cha >= 10 ? "+"+Abilities.calculateModifier(cha) : 
			Abilities.calculateModifier(cha)
		));
		constraints.gridx++;
		returnMe.add(chaL, constraints);
		return returnMe;
	}
	private static JLabel getNewSeperator() {
		URL url = ImageRenderer.class.getResource("/separator.png");
		BufferedImage img;
		try {
			img = (BufferedImage) ImageIO.read(url);
		} catch (IOException ex) {
			Logger.getLogger(ImageRenderer.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}
		JLabel returnMe = new JLabel(new ImageIcon(img));
		return returnMe;
	}
}
