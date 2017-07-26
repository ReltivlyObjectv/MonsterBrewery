package tech.relativelyobjective.monsterbrewery.pieces;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import tech.relativelyobjective.monsterbrewery.resources.Lists;

/**
 *
 * @author ReltivlyObjectv
 * Contact: me@relativelyobjective.tech
 * 
 */
public class PanelMonsterOverview extends JPanel {
	private final JTextField name;
	private final JTextField armorType;
	private final JTextField hitPointCount;
	private final JComboBox size;
	private final JComboBox type;
	private final JComboBox tag;
	private final JComboBox alignment;
	private final JComboBox hitPointDiceType;
	private final JComboBox pronoun;
	private final JSpinner armorClass;
	private final JSpinner hitPointDiceCount;
	
	public PanelMonsterOverview() {
		super.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridy = 0;
		//Name
		constraints.gridx = 0;
		adjustGridBagAnchor(constraints);
		super.add(new JLabel("Name"), constraints);
		name = new JTextField(20);
		constraints.gridx = 1;
		adjustGridBagAnchor(constraints);
		super.add(name, constraints);
		constraints.gridy++;
		//Size
		constraints.gridx = 0;
		adjustGridBagAnchor(constraints);
		super.add(new JLabel("Size/Pronoun"), constraints);
		JPanel sizePronounRow = new JPanel();
			size = new JComboBox(Lists.SIZE);
			//size.setMinimumSize(new Dimension(100,100));
			sizePronounRow.add(size);
			pronoun = new JComboBox(Lists.Pronouns.values());
			sizePronounRow.add(pronoun);
			sizePronounRow.setLayout(new BoxLayout(sizePronounRow, BoxLayout.LINE_AXIS));
			constraints.gridx = 1;
			adjustGridBagAnchor(constraints);
		super.add(sizePronounRow, constraints);
		constraints.gridy++;
		//Type / Tag
		constraints.gridx = 0;
		adjustGridBagAnchor(constraints);
		super.add(new JLabel("Type / Tag"), constraints);
		JPanel typeTagRow = new JPanel();
			type = new JComboBox(Lists.TYPE);
			type.setEditable(true);
			tag = new JComboBox(Lists.TAG);
			tag.setEditable(true);
			typeTagRow.setLayout(new BoxLayout(typeTagRow, BoxLayout.LINE_AXIS));
			typeTagRow.add(type);
			typeTagRow.add(tag);
			constraints.gridx = 1;
			adjustGridBagAnchor(constraints);
		super.add(typeTagRow, constraints);
		constraints.gridy++;
		//Alignment
		constraints.gridx = 0;
		adjustGridBagAnchor(constraints);
		super.add(new JLabel("Alignment"), constraints);
		alignment = new JComboBox(Lists.ALIGNMENT);
		constraints.gridx = 1;
		adjustGridBagAnchor(constraints);
		super.add(alignment, constraints);
		constraints.gridy++;
		//Armor
		constraints.gridx = 0;
		adjustGridBagAnchor(constraints);
		super.add(new JLabel("Armor Class"), constraints);
		JPanel armorRow = new JPanel();
			armorRow.setLayout(new BorderLayout());
			armorClass = new JSpinner(new SpinnerNumberModel(0,0,50,1));
			armorRow.add(armorClass, BorderLayout.WEST);
			armorType = new JTextField(16);
			armorRow.add(armorType, BorderLayout.EAST);
		constraints.gridx = 1;
		adjustGridBagAnchor(constraints);
		super.add(armorRow, constraints);
		constraints.gridy++;
		//Hit Points
		constraints.gridx = 0;
		adjustGridBagAnchor(constraints);
		super.add(new JLabel("Hit Points"), constraints);
		JPanel hitPointRow = new JPanel();
			hitPointRow.setLayout(new BorderLayout());
			hitPointDiceCount = new JSpinner(new SpinnerNumberModel(0,0,50,1));
			hitPointRow.add(hitPointDiceCount, BorderLayout.WEST);
			hitPointDiceType = new JComboBox(Lists.DICE);
			hitPointDiceType.setEditable(true);
			hitPointRow.add(hitPointDiceType, BorderLayout.CENTER);
			hitPointCount = new JTextField(9);
			hitPointRow.add(hitPointCount, BorderLayout.EAST);
		constraints.gridx = 1;
		adjustGridBagAnchor(constraints);
		super.add(hitPointRow, constraints);
		constraints.gridy++;
		//Hit Point Buttons
		JPanel hitPointButtons = new JPanel();
			JButton generateButton = new JButton("Generate HP");
			generateButton.addActionListener((ActionEvent e) -> {
				calculateHitPointString();
			});
			hitPointButtons.add(generateButton);
			JButton rollButton = new JButton("Roll HP");
			rollButton.addActionListener((ActionEvent e) -> {
				calculateRandomHitPointString();
			});
			hitPointButtons.add(rollButton);
		super.add(hitPointButtons, constraints);
	}
	private void adjustGridBagAnchor(GridBagConstraints gridBag) {
		if (gridBag == null) {
			return;
		}
		if (gridBag.gridx == 0) {
			gridBag.anchor = GridBagConstraints.EAST;
		} else {
			gridBag.anchor = GridBagConstraints.WEST;
		}
	}
	public String getMonsterName() {
		return name.getText();
	}
	public String getArmorType() {
		return armorType.getText();
	}
	public String getMonsterSize() {
		return (String) size.getSelectedItem();
	}
	public String getMonsterType() {
		return (String) type.getSelectedItem();
	}
	public String getMonsterTag() {
		return (String) tag.getSelectedItem();
	}
	public String getAlignment() {
		return (String) alignment.getSelectedItem();
	}
	public String getHitPointString() {
		return hitPointCount.getText();
	}
	public int getHitPointDiceCount() {
		return (int) hitPointDiceCount.getValue();
	}
	public String getHitPointDiceType() {
		return (String) hitPointDiceType.getSelectedItem();
	}
	public int getArmorClass() {
		return (int) armorClass.getValue();
	}
	public Lists.Pronouns getPronoun() {
		return (Lists.Pronouns) pronoun.getSelectedItem();
	}
	public void setMonsterName(String name) {
		this.name.setText(name);
	}
	public void setArmorType(String type) {
		this.armorType.setText(type);
	}
	public void setMonsterSize(String size) {
		this.size.setSelectedItem(size);
	}
	public void setMonsterType(String type) {
		this.type.setSelectedItem(type);
	}
	public void setMonsterTag(String tag) {
		this.tag.setSelectedItem(tag);
	}
	public void setAlignment(String alignment) {
		this.alignment.setSelectedItem(alignment);
	}
	public void setHitPointString(String text) {
		this.hitPointCount.setText(text);
	}
	public void setHitPointDiceCount(int diceCount) {
		this.hitPointDiceCount.setValue(diceCount);
	}
	public void setHitPointDiceType(String diceType) {
		for(int i = 0; i < Lists.DICE.length; i++) {
			if (Lists.DICE[i].equalsIgnoreCase(diceType)) {
				this.hitPointDiceType.setSelectedItem(Lists.DICE[i]);
			}
		}
	}
	public void setArmorClass(int ac) {
		this.armorClass.setValue(ac);
	}
	public void setPronoun(Lists.Pronouns newPro) {
		pronoun.setSelectedItem(newPro);
	}
	public void calculateHitPointString() {
		int hitPointDiceCountInt = getHitPointDiceCount();
		String diceTypeString = getHitPointDiceType().replace("d", "");
		Integer diceType;
		try {
			diceType = new Integer(diceTypeString);
		} catch(NumberFormatException e) {
			System.out.printf("Could not parse: %s\n", diceTypeString);
			return;
		}
		int maxValue = (diceType + 1) * hitPointDiceCountInt;
		int averageValue = maxValue / 2;
		String text = String.format("(%dd%d) %s", 
			hitPointDiceCountInt, diceType, averageValue);
		hitPointCount.setText(text);
	}
	public void calculateRandomHitPointString() {
		int hitPointDiceCountInt = getHitPointDiceCount();
		String diceTypeString = getHitPointDiceType().replace("d", "");
		Integer diceType;
		try {
			diceType = new Integer(diceTypeString);
		} catch(NumberFormatException e) {
			System.out.printf("Could not parse: %s\n", diceTypeString);
			return;
		}
		int value = 0;
		for (int i = 0; i < hitPointDiceCountInt; i++) {
			value += 1 + (int) (Math.random() * diceType);
		}
		String text = String.format("(%dd%d) %s", 
			hitPointDiceCountInt, diceType, value);
		hitPointCount.setText(text);
	}
}
