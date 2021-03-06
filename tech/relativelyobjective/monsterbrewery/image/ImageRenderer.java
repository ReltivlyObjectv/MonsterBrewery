package tech.relativelyobjective.monsterbrewery.image;

import tech.relativelyobjective.monsterbrewery.resources.JLabelBrownOverview;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
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
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import tech.relativelyobjective.monsterbrewery.attributes.*;
import tech.relativelyobjective.monsterbrewery.filestorage.SaveCreator;
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
	private static JDialog currentRenderWindow = null;
	private static JPanel renderArea = null;
	private static final Color TRANSPARENT_COLOR = new Color(238, 238, 238);
	public static void initialize(FrameMain mainF) {
		mainFrame = mainF;
	}
	public static void renderToFile() {
		renderImage(mainFrame);
		JFileChooser fileChooser = new JFileChooser();
		if (SaveCreator.getFileLocation() == null) {
			fileChooser.setCurrentDirectory(new File("."));
		} else {
			fileChooser.setCurrentDirectory(SaveCreator.getFileLocation().getParentFile());
		}
		FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG Images", "png");
		fileChooser.setFileFilter(filter);
		if (fileChooser.showSaveDialog(currentRenderWindow) == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			if (!file.toString().endsWith(".png")) {
				file = new File(file.toString() + ".png");
			}
			if (file.exists()) {
				int response = JOptionPane.showConfirmDialog(null,
						String.format("The file %s already exists. Do you want to replace it?", file.getName()),
						String.format("%s Exists.", file.getName()),
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE);
				if (response != JOptionPane.YES_OPTION) {
					return;
				} 
			}
			try {
				BufferedImage img = getImage(renderArea);
				for (int i = 0; i < img.getWidth(); i++) {
					for (int j = 0; j < img.getHeight(); j++) {
						if (img.getRGB(i, j) == TRANSPARENT_COLOR.getRGB()) {
							img.setRGB(i, j, new Color(0,0,0,0).getRGB());
						}
					}
				}
				ImageIO.write(removeGrayBackground(img), "png", file);
			} catch (IOException ex) {
				Logger.getLogger(ImageRenderer.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}
	private static BufferedImage removeGrayBackground(BufferedImage img) {
		//TODO Crop
		return img;
	}
	public static BufferedImage getImage(Component panel) {
		BufferedImage img = new BufferedImage(
			panel.getWidth(), panel.getHeight(), 
		BufferedImage.TYPE_INT_ARGB);
		Graphics g = img.createGraphics();
		panel.paint(g);
		return img;
	}
	public static void renderImage(FrameMain mainFrame) {
		if (currentRenderWindow != null) {
			currentRenderWindow.dispose();
			currentRenderWindow = null;
		}
		JDialog renderWindow = new JDialog(mainFrame, "Render Monster", false);
		currentRenderWindow = renderWindow;
		renderWindow.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				currentRenderWindow = null;
			}
		});
		JPanel windowContents = new JPanel();
			windowContents.setBackground(TRANSPARENT_COLOR);
			windowContents.setLayout(new GridBagLayout());
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			constraints.ipadx = 0;
			constraints.ipady = 0;
			constraints.weightx = 1;
			windowContents.add(getNewBuffer(), constraints);
			constraints.gridy++;
			constraints.fill = GridBagConstraints.NONE;
			windowContents.add(getNewEndCap(), constraints);
			constraints.gridy++;
			windowContents.add(getWindowContents(), constraints);
			constraints.gridy++;
			windowContents.add(getNewEndCap(), constraints);
			constraints.gridy++;
			windowContents.add(getNewBuffer(), constraints);
		//renderWindow.setPreferredSize(new Dimension(430, 700));
		renderWindow.setPreferredSize(renderWindow.getPreferredSize());
		Dimension newDimension = windowContents.getPreferredSize();
		newDimension.width = 450;
		newDimension.height += newDimension.height/4;
		newDimension.height -= 10;
		renderWindow.setPreferredSize(newDimension);
		renderWindow.setMinimumSize(new Dimension(newDimension.width, 50));
		renderWindow.setMaximumSize(newDimension);
		//renderWindow.(newDimension);
		//renderWindow.setSize(newDimension);
		windowContents.setPreferredSize(newDimension);
		//windowContents.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		JScrollPane scroller = new JScrollPane(windowContents);
		renderArea = windowContents;
		//scroller.setPreferredSize(newDimension);
		renderWindow.add(scroller);
		//renderWindow.setSize(new Dimension(430, 700));
		renderWindow.setSize(renderWindow.getPreferredSize());
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
		JLabelBrown nameLabel = new JLabelBrown(nameData);
		nameLabel.setFont(FontManager.getFontTitle(20));
		constraints.gridy++;
		constraints.gridx = 1;
		returnMe.add(nameLabel, constraints);
		constraints.ipady = 3;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridy++;
		JLabel sizeTypeTag = new JLabel(String.format("%s %s%s%s, %s",
			Lists.formatUpperCase(MonsterInformation.getMonsterSize()),
			MonsterInformation.getMonsterType().toLowerCase(),
			MonsterInformation.getMonsterTag().equals("") ? "" : " ",
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
		returnMe.add(getAbilities(), constraints);
		constraints.gridy++;
		returnMe.add(getActions(), constraints);
		constraints.gridy++;
		returnMe.add(getReactions(), constraints);
		constraints.gridy++;
		returnMe.add(getLegendaryActions(), constraints);
		returnMe.setBackground(Color.decode("#fdf1dd"));
		returnMe.setBorder(BorderFactory.createMatteBorder(0,1,0,1,
			Color.decode("#DCDCDC")));
		returnMe.setPreferredSize(new Dimension(
			returnMe.getPreferredSize().width,
			returnMe.getPreferredSize().height + 50
		));
		return returnMe;
	}
	private static JPanel getOverview() {
		JPanel returnMe = new JPanel();
		returnMe.setOpaque(false);
		returnMe.setLayout(new BoxLayout(returnMe, BoxLayout.Y_AXIS));
		JPanel armorClass = new JPanel();
			armorClass.setOpaque(false);
			armorClass.setLayout(new BoxLayout(armorClass, BoxLayout.X_AXIS));
			JLabelBrownOverview acLabel;
			acLabel = new JLabelBrownOverview("Armor Class",
				String.format("%d %s",
					MonsterInformation.getArmorClass(),
					MonsterInformation.getArmorType()
					));
			acLabel.setFont(FontManager.getFontRegular(12));
			armorClass.add(acLabel);
			armorClass.add(Box.createHorizontalGlue());
		returnMe.add(armorClass);
		JPanel hitPoints = new JPanel();
			hitPoints.setOpaque(false);
			hitPoints.setLayout(new BoxLayout(hitPoints, BoxLayout.X_AXIS));
			JLabelBrownOverview hpLabel = new JLabelBrownOverview("Hit Points ",
				MonsterInformation.getHitPointString());
			hpLabel.setFont(FontManager.getFontRegular(12));
			hitPoints.add(hpLabel);
			hitPoints.add(Box.createHorizontalGlue());
		returnMe.add(hitPoints);
		JPanel speed = new JPanel();
			//Get Information
			String speedDataString = String.format("%d ft.",
				MonsterInformation.getWalkSpeed());
			int speedSwim = MonsterInformation.getSwimSpeed();
			int burrowSpeed = MonsterInformation.getBurrowSpeed();
			int climbSpeed = MonsterInformation.getClimbSpeed();
			int flySpeed = MonsterInformation.getFlySpeed();
			if (speedSwim > 0) {
				speedDataString += String.format(", swim %d ft.", speedSwim);
			}
			if (burrowSpeed > 0) {
				speedDataString += String.format(", burrow %d ft.", burrowSpeed);
			}
			if (climbSpeed > 0) {
				speedDataString += String.format(", climb %d ft.", climbSpeed);
			}
			if (flySpeed > 0) {
				speedDataString += String.format(", fly %d ft. %s", 
					flySpeed,
					MonsterInformation.canHover() ? "(hover)" : "");
			}
			speedDataString += " ";
			//Finish panel
			speed.setOpaque(false);
			speed.setLayout(new BoxLayout(speed, BoxLayout.X_AXIS));
			JLabelBrownOverview speedLabel = new JLabelBrownOverview("Speed", speedDataString);
			speedLabel.setFont(FontManager.getFontRegular(12));
			speed.add(speedLabel);
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
			JLabelBrownOverview label = new JLabelBrownOverview("Saving Throws", string);
			label.setFont(FontManager.getFontRegular(12));
			panel.add(label);
			panel.add(Box.createHorizontalGlue());
			returnMe.add(panel);
		}
		//Misc
		List<Skill> skillModifiers = new LinkedList<>(MonsterInformation.getSkills());
		for (Skill s : skillModifiers) {
			if (s.getSkill().toLowerCase().contains("passive perception")) {
				skillModifiers.remove(s);
				break;
			}
		}
		if (skillModifiers.size() > 0) {
			JPanel panel = new JPanel();
			panel.setOpaque(false);
			panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
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
			JLabelBrownOverview label = new JLabelBrownOverview("Skills", string);
			label.setFont(FontManager.getFontBold(12));
			panel.add(label);
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
			JLabelBrownOverview data = new JLabelBrownOverview("Damage Immunities", string);
			data.setFont(FontManager.getFontRegular(12));
			panel.add(data);
			panel.add(Box.createHorizontalGlue());
			returnMe.add(panel);
		}
		if (damageResistances.size() > 0) {
			JPanel panel = new JPanel();
			panel.setOpaque(false);
			panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
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
			JLabelBrownOverview data = new JLabelBrownOverview("Damage Resistances", string);
			data.setFont(FontManager.getFontRegular(12));
			panel.add(data);
			panel.add(Box.createHorizontalGlue());
			returnMe.add(panel);
		}
		if (damageVulnerabilities.size() > 0) {
			JPanel panel = new JPanel();
			panel.setOpaque(false);
			panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
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
			JLabelBrownOverview data = new JLabelBrownOverview("Damage Vulnerabilities", string);
			data.setFont(FontManager.getFontRegular(12));
			panel.add(data);
			panel.add(Box.createHorizontalGlue());
			returnMe.add(panel);
		}
		if (conditionImmunities.size() > 0) {
			JPanel panel = new JPanel();
			panel.setOpaque(false);
			panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
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
			JLabelBrownOverview data = new JLabelBrownOverview("Condition Immunities", string);
			data.setFont(FontManager.getFontRegular(12));
			panel.add(data);
			panel.add(Box.createHorizontalGlue());
			returnMe.add(panel);
		}
		if (conditionResistances.size() > 0) {
			JPanel panel = new JPanel();
			panel.setOpaque(false);
			panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
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
			JLabelBrownOverview data = new JLabelBrownOverview("Condition Resistances", string);
			data.setFont(FontManager.getFontRegular(12));
			panel.add(data);
			panel.add(Box.createHorizontalGlue());
			returnMe.add(panel);
		}
		if (conditionVulnerabilities.size() > 0) {
			JPanel panel = new JPanel();
			panel.setOpaque(false);
			panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
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
			JLabelBrownOverview data = new JLabelBrownOverview("Condition Vulnerabilities", string);
			data.setFont(FontManager.getFontRegular(12));
			panel.add(data);
			panel.add(Box.createHorizontalGlue());
			returnMe.add(panel);
		}
		//Senses
		List<Attribute> senses = new LinkedList<>(MonsterInformation.getSenses());
		for (Skill s : MonsterInformation.getSkills()) {
			if (s.getSkill().toLowerCase().contains("passive perception")) {
				senses.add(s);
				break;
			}
		}
		if (senses.size() > 0) {
			JPanel panel = new JPanel();
			panel.setOpaque(false);
			panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
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
			JLabelBrownOverview data = new JLabelBrownOverview("Senses", string);
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
			JLabelBrownOverview data = new JLabelBrownOverview("Languages", string);
			data.setFont(FontManager.getFontRegular(12));
			panel.add(data);
			panel.add(Box.createHorizontalGlue());
			returnMe.add(panel);
		}
		if (true) {
			JPanel panel = new JPanel();
			panel.setOpaque(false);
			panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
			String string = " "; 
			String challengeRatingString = String.format("%s (%d XP)", 
				MonsterInformation.getChallengeRating(), 
				Lists.calculateChallengeXP(MonsterInformation.getChallengeRating()));
			string += challengeRatingString;
			JLabelBrownOverview data = new JLabelBrownOverview("Challenge", string);
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
		int wis = MonsterInformation.getWisdom();
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
	private static JPanel getAbilities() {
		List<Ability> abilities = MonsterInformation.getAbilities();
		List<Spellcaster> spellcasters = MonsterInformation.getSpellcaster();
		JPanel returnMe = new JPanel();
			returnMe.setLayout(new GridBagLayout());
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			constraints.weightx = 1;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			returnMe.setOpaque(false);
			for (Ability a : abilities) {
				String text = String.format("<html><b><i>%s.</i></b> %s<br><br></html>", 
					a.getName(), a.getDescription());
				JLabel label = new JLabel(text);
				//label.setBorder(BorderFactory.createEtchedBorder());
				label.setFont(FontManager.getFontRegular(10));
				returnMe.add(label, constraints);
				constraints.gridy++;
			}
			for (Spellcaster s : spellcasters) {
				String text = "<html>";
				int spellcasterLevel = s.getSpellcasterLevel();
				int toHit = s.getToHit();
				text += String.format(
					"<b><i>Spellcasting.</i></b> %s is a %d%s-level spellcaster. ",
					MonsterInformation.getPronoun().toString().toLowerCase().contains("proper") 
						? MonsterInformation.getMonsterName() 
						: "The "+MonsterInformation.getMonsterName().toLowerCase(),
					spellcasterLevel,
					spellcasterLevel == 1 ? "st" :
					spellcasterLevel == 2 ? "nd" :
					spellcasterLevel == 3 ? "rd" :
					"th"
					);
				text += String.format(
					"%s spellcasting ability is %s (spell save DC %d, %s%d to hit with spell attacks). ",
					MonsterInformation.getPronoun().toString().toLowerCase().contains("female") ? "Her" :
					MonsterInformation.getPronoun().toString().toLowerCase().contains("male") ? "His" :
					MonsterInformation.getPronoun().toString().toLowerCase().contains("it") ? "Its" :
					"ERROR",
					Lists.formatUpperCase(s.getSpellcastingAbility().toString()),
					s.getSpellsaveDC(),
					toHit < 0 ? "" : "+",
					toHit
				);
				text += String.format(
					"%s has the following %s spells prepared:<br><br>",
					MonsterInformation.getPronoun().toString().toLowerCase().contains("proper") 
						? MonsterInformation.getMonsterName()
						: "The "+MonsterInformation.getMonsterName().toLowerCase(),
					s.getSpellClass().toLowerCase()
					);
				List<Spellcaster.Spell> cantrips = new LinkedList<>();
				List<Spellcaster.Spell> lvl1 = new LinkedList<>();
				List<Spellcaster.Spell> lvl2 = new LinkedList<>();
				List<Spellcaster.Spell> lvl3 = new LinkedList<>();
				List<Spellcaster.Spell> lvl4 = new LinkedList<>();
				List<Spellcaster.Spell> lvl5 = new LinkedList<>();
				List<Spellcaster.Spell> lvl6 = new LinkedList<>();
				List<Spellcaster.Spell> lvl7 = new LinkedList<>();
				List<Spellcaster.Spell> lvl8 = new LinkedList<>();
				List<Spellcaster.Spell> lvl9 = new LinkedList<>();
				for (Spellcaster.Spell spell : s.getSpells()) {
					switch (spell.level) {
						case 0:
							cantrips.add(spell);
							break;
						case 1:
							lvl1.add(spell);
							break;
						case 2:
							lvl2.add(spell);
							break;
						case 3:
							lvl3.add(spell);
							break;
						case 4:
							lvl4.add(spell);
							break;
						case 5:
							lvl5.add(spell);
							break;
						case 6:
							lvl6.add(spell);
							break;
						case 7:
							lvl7.add(spell);
							break;
						case 8:
							lvl8.add(spell);
							break;
						case 9:
							lvl9.add(spell);
							break;
						default:
							throw new IndexOutOfBoundsException("Spells can only go 0-9");
					}
				}
				boolean hasBeforeCombat = false;
				if (cantrips.size() > 0) {
					String spellList = "Cantrips (at will): ";
					int i = 0;
					for (Spellcaster.Spell spell : cantrips) {
						if (i > 0) {
							spellList += ", ";
						}
						spellList += spell.spell.toLowerCase();
						if (spell.castOnCombat) {
							spellList += "*";
							hasBeforeCombat = true;
						} 
						i++;
					}
					spellList += "<br>";
					text += spellList;
				}
				if (lvl1.size() > 0) {
					String spellList = String.format("1st level (%d slot%s): ",
						s.getSpellSlots()[0],
						s.getSpellSlots()[0] > 0 ? "s" : ""
						);
					int i = 0;
					for (Spellcaster.Spell spell : lvl1) {
						if (i > 0) {
							spellList += ", ";
						}
						spellList += spell.spell.toLowerCase();
						if (spell.castOnCombat) {
							spellList += "*";
							hasBeforeCombat = true;
						} 
						i++;
					}
					spellList += "<br>";
					text += spellList;
				}
				if (lvl2.size() > 0) {
					String spellList = String.format("2nd level (%d slot%s): ",
						s.getSpellSlots()[1],
						s.getSpellSlots()[1] > 0 ? "s" : ""
						);
					int i = 0;
					for (Spellcaster.Spell spell : lvl2) {
						if (i > 0) {
							spellList += ", ";
						}
						spellList += spell.spell.toLowerCase();
						if (spell.castOnCombat) {
							spellList += "*";
							hasBeforeCombat = true;
						} 
						i++;
					}
					spellList += "<br>";
					text += spellList;
				}
				if (lvl3.size() > 0) {
					String spellList = String.format("3rd level (%d slot%s): ",
						s.getSpellSlots()[2],
						s.getSpellSlots()[2] > 0 ? "s" : ""
						);
					int i = 0;
					for (Spellcaster.Spell spell : lvl3) {
						if (i > 0) {
							spellList += ", ";
						}
						spellList += spell.spell.toLowerCase();
						if (spell.castOnCombat) {
							spellList += "*";
							hasBeforeCombat = true;
						} 
						i++;
					}
					spellList += "<br>";
					text += spellList;
				}
				if (lvl4.size() > 0) {
					String spellList = String.format("4th level (%d slot%s): ",
						s.getSpellSlots()[3],
						s.getSpellSlots()[3] > 0 ? "s" : ""
						);
					int i = 0;
					for (Spellcaster.Spell spell : lvl4) {
						if (i > 0) {
							spellList += ", ";
						}
						spellList += spell.spell.toLowerCase();
						if (spell.castOnCombat) {
							spellList += "*";
							hasBeforeCombat = true;
						} 
						i++;
					}
					spellList += "<br>";
					text += spellList;
				}
				if (lvl5.size() > 0) {
					String spellList = String.format("5th level (%d slot%s): ",
						s.getSpellSlots()[4],
						s.getSpellSlots()[4] > 0 ? "s" : ""
						);
					int i = 0;
					for (Spellcaster.Spell spell : lvl5) {
						if (i > 0) {
							spellList += ", ";
						}
						spellList += spell.spell.toLowerCase();
						if (spell.castOnCombat) {
							spellList += "*";
							hasBeforeCombat = true;
						} 
						i++;
					}
					spellList += "<br>";
					text += spellList;
				}
				if (lvl6.size() > 0) {
					String spellList = String.format("6th level (%d slot%s): ",
						s.getSpellSlots()[5],
						s.getSpellSlots()[5] > 0 ? "s" : ""
						);
					int i = 0;
					for (Spellcaster.Spell spell : lvl6) {
						if (i > 0) {
							spellList += ", ";
						}
						spellList += spell.spell.toLowerCase();
						if (spell.castOnCombat) {
							spellList += "*";
							hasBeforeCombat = true;
						} 
						i++;
					}
					spellList += "<br>";
					text += spellList;
				}
				if (lvl7.size() > 0) {
					String spellList = String.format("7th level (%d slot%s): ",
						s.getSpellSlots()[6],
						s.getSpellSlots()[6] > 0 ? "s" : ""
						);
					int i = 0;
					for (Spellcaster.Spell spell : lvl7) {
						if (i > 0) {
							spellList += ", ";
						}
						spellList += spell.spell.toLowerCase();
						if (spell.castOnCombat) {
							spellList += "*";
							hasBeforeCombat = true;
						} 
						i++;
					}
					spellList += "<br>";
					text += spellList;
				}
				if (lvl8.size() > 0) {
					String spellList = String.format("8th level (%d slot%s): ",
						s.getSpellSlots()[7],
						s.getSpellSlots()[7] > 0 ? "s" : ""
						);
					int i = 0;
					for (Spellcaster.Spell spell : lvl8) {
						if (i > 0) {
							spellList += ", ";
						}
						spellList += spell.spell.toLowerCase();
						if (spell.castOnCombat) {
							spellList += "*";
							hasBeforeCombat = true;
						} 
						i++;
					}
					spellList += "<br>";
					text += spellList;
				}
				if (lvl9.size() > 0) {
					String spellList = String.format("9th level (%d slot%s): ",
						s.getSpellSlots()[8],
						s.getSpellSlots()[8] > 0 ? "s" : ""
						);
					int i = 0;
					for (Spellcaster.Spell spell : lvl9) {
						if (i > 0) {
							spellList += ", ";
						}
						spellList += spell.spell.toLowerCase();
						if (spell.castOnCombat) {
							spellList += "*";
							hasBeforeCombat = true;
						} 
						i++;
					}
					spellList += "<br>";
					text += spellList;
				}
				if (hasBeforeCombat) {
					text += String.format("<br>*%s%s casts these spells on %s before combat.",
						MonsterInformation.getPronoun().toString().toLowerCase().contains("proper") 
							? "" : "The ",
						MonsterInformation.getMonsterName().toLowerCase(),
						MonsterInformation.getPronoun().toString().toLowerCase().contains("female") ? "herself" :
							MonsterInformation.getPronoun().toString().toLowerCase().contains("male") ? "himself" :
							MonsterInformation.getPronoun().toString().toLowerCase().contains("it") ? "itself" :
							"ERROR"
						);
				}
				text += "</html>";
				JLabel label = new JLabel(text);
				//label.setBorder(BorderFactory.createEtchedBorder());
				label.setFont(FontManager.getFontRegular(10));
				returnMe.add(label, constraints);
				constraints.gridy++;
			}
		return returnMe;
	}
	private static JPanel getActions() {
		JPanel returnMe = new JPanel();
			returnMe.setLayout(new GridBagLayout());
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			constraints.weightx = 1;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.anchor = GridBagConstraints.WEST;
			returnMe.setOpaque(false);
			List<Action> actions = MonsterInformation.getActions();
			if (actions.size() > 0) {
				JLabel header = new JLabelBrown("Actions");
				header.setFont(FontManager.getFontRegularSmallCaps(17));
				header.setBorder(BorderFactory.createMatteBorder(
					0, 0, 1, 0, Color.decode("#902717")));
				returnMe.add(header, constraints);
				constraints.gridy++;
			}
			for (Action a : actions) {
				String description = "";
				int toHit = a.getToHit();
				int diceType, diceCount, averageDice;
				diceType = Integer.parseInt(a.getDiceType().replace("d", ""));
				diceCount = a.getDiceCount();
				averageDice = (diceType * diceCount) / 2;
				
				if (a.getActionType() == Lists.ActionType.MELEE) {
					//Melee attack
					description += "<i>Melee Weapon Attack</i>: ";
					description += String.format(
						"%s%d to hit, reach %d ft., %s. ",
						toHit < 0 ? "" : "+",
						toHit,
						a.getMeleeReach(),
						"one target"
						);
					description += String.format("<i>Hit:</i> %d (%dd%d) %s damage.",
						averageDice,
						diceCount,
						diceType,
						a.getDamageType().toLowerCase()
						);
				} else if (a.getActionType() == Lists.ActionType.RANGED) {
					//Ranged attack
					description += String.format("<i>Ranged %s Attack: </i>",
						a.getRangedType() == Lists.RangeType.SPELL ? "Spell" : "Weapon"
						);
					if (a.getRangedDelivery() == Lists.RangeDelivery.FIRE_AND_FORGET) {
						//Fire and Forget (min/max range)
						description += String.format(
							"%s%d to hit, reach %d/%d ft., %s. ",
							toHit < 0 ? "" : "+",
							toHit,
							a.getRangedMin(),
							a.getRangedMax(),
							"one target"
							);
					} else {
						//Shape
						description += String.format("%s%d to hit, %d foot %s. ",
							toHit < 0 ? "" : "+",
							toHit,
							a.getRangedSize(),
							a.getRangedShape().toString().toLowerCase()
							);
					}
					description += String.format("<i>Hit:</i> %d (%dd%d) %s damage.",
						averageDice,
						diceCount,
						diceType,
						a.getDamageType().toLowerCase()
						);
				} else {
					//Misc action
					description += a.getDescription();
				}
				description += "<br><br>";
				JLabel content = new JLabel(String.format("<html><b><i>%s.</i></b> %s</html>",
					a.getName(), description));
				content.setFont(FontManager.getFontRegular(10));
				returnMe.add(content, constraints);
				constraints.gridy++;
			}
		return returnMe;
	}
	private static JPanel getReactions() {
		JPanel returnMe = new JPanel();
			returnMe.setLayout(new GridBagLayout());
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			constraints.weightx = 1;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.anchor = GridBagConstraints.WEST;
			returnMe.setOpaque(false);
			List<Reaction> reactions = MonsterInformation.getReactions();
			if (reactions.isEmpty()) {
				returnMe.setPreferredSize(new Dimension(0,0));
				returnMe.setSize(returnMe.getPreferredSize());
				return returnMe;
			}
			JLabel header = new JLabelBrown("Reactions");
			header.setFont(FontManager.getFontRegularSmallCaps(17));
			header.setBorder(BorderFactory.createMatteBorder(
				0, 0, 1, 0, Color.decode("#902717")));
			returnMe.add(header, constraints);
			constraints.gridy++;
			for (Reaction r : reactions) {
				String reactionText = String.format("<html><b><i>%s. </i></b>%s</html>",
					r.getName(), 
					r.getDescription());
				JLabel reactionLabel = new JLabel(reactionText);
				reactionLabel.setFont(FontManager.getFontRegular(10));
				returnMe.add(reactionLabel, constraints);
				constraints.gridy++;
			}
		return returnMe;
	}
	private static JPanel getLegendaryActions() {
		JPanel returnMe = new JPanel();
			returnMe.setLayout(new GridBagLayout());
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			constraints.weightx = 1;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.anchor = GridBagConstraints.WEST;
			returnMe.setOpaque(false);
			List<LegendaryActions> actions = MonsterInformation.getLegendaryActions();
			if (actions.isEmpty()) {
				returnMe.setPreferredSize(new Dimension(0,0));
				returnMe.setSize(returnMe.getPreferredSize());
				return returnMe;
			}
			for (LegendaryActions l : actions) {
				JLabel header = new JLabelBrown("Legendary Actions");
				header.setFont(FontManager.getFontRegularSmallCaps(17));
				header.setBorder(BorderFactory.createMatteBorder(
					0, 0, 1, 0, Color.decode("#902717")));
				returnMe.add(header, constraints);
				String legendaryOverview = String.format("<html>%s can take %d legendary action%s, "+
					"choosing from the options below. Only one legendary action can be used at a time "+
					"and only at the end of another creature's turn. %s regains spent legendary actions "+
					"at the start of %s turn<br><br></html>",
					MonsterInformation.getPronoun().toString().toLowerCase().contains("proper") 
						? MonsterInformation.getMonsterName() : 
						"The "+MonsterInformation.getMonsterName().toLowerCase(),
					l.getUsesPerCycle(),
					l.getUsesPerCycle() == 1 ? "" : "s",
					MonsterInformation.getPronoun().toString().toLowerCase().contains("proper") 
						? MonsterInformation.getMonsterName() : 
						"The "+MonsterInformation.getMonsterName().toLowerCase(),
					MonsterInformation.getPronoun().toString().toLowerCase().contains("female") ? "her" :
						MonsterInformation.getPronoun().toString().toLowerCase().contains("male") ? "his" :
						MonsterInformation.getPronoun().toString().toLowerCase().contains("it") ? "its" :
						"ERROR"
					);
				constraints.gridy++;
				JLabel legendaryOverviewLabel = new JLabel(legendaryOverview);
				legendaryOverviewLabel.setFont(FontManager.getFontRegular(10));
				returnMe.add(legendaryOverviewLabel, constraints);
				constraints.gridy++;
				for (LegendaryActions.Action a : l.getActions()) {
					String action = String.format("<html><b><i>%s. </i></b>%s<br></html>",
						a.name,
						a.text
						);
					constraints.gridy++;
					JLabel actionLabel = new JLabel(action);
					actionLabel.setFont(FontManager.getFontRegular(10));
					returnMe.add(actionLabel, constraints);
				}
			}
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
	private static JPanel getNewBuffer() {
			JPanel buffer = new JPanel();
			buffer.setOpaque(false);
			buffer.setPreferredSize(new Dimension(100,50));
			buffer.setMinimumSize(buffer.getPreferredSize());
			return buffer;
	}
}
