package tech.relativelyobjective.monsterbrewery.pieces;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

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
	private final JComboBox hitPointDiceCount;
	private final JSpinner armorClass;
	private final JSpinner hitPointDice;
	public final String[] sizeList = {
		"Tiny",
		"Small",
		"Medium",
		"Large",
		"Huge",
		"Gargantuan"
	};
	public final String[] alignmentList = {
		"Neutral Good",
		"Neutral Evil",
		"True Neutral",
		"Lawful Good",
		"Lawful Neutral",
		"Lawful Evil",
		"Chaotic Good",
		"Chaotic Neutral",
		"Chaotic Evil"
	};
	public final String[] diceList = {
		"d4",
		"d6",
		"d8",
		"d10",
		"d12",
		"d20"
	};
	public final String[] typeList = {
		"Aberration",
		"Beast",
		"Celestial",
		"Construct",
		"Dragon",
		"Elemental",
		"Fey",
		"Fiend",
		"Giant",
		"Humanoid",
		"Monstrosity",
		"Ooze",
		"Plant",
		"Undead"
	};
	public final String[] tagList = {
		"Aarakocra",
		"Bullywug",
		"Demon",
		"Devil",
		"Dwarf",
		"Elf",
		"Gith",
		"Gnoll",
		"Gnome",
		"Goblinoid",
		"Grimlock",
		"Human",
		"Kenku",
		"Kobold",
		"Kuo-toa",
		"Lizardfolk",
		"Merfolk",
		"Orc",
		"Quaggoth",
		"Sahuagin",
		"Shapechanger",
		"Thri-Kreen",
		"Titan",
		"Troglodyte",
		"Yuan-Ti",
		"yugoloth"
	};
	
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
		super.add(new JLabel("Size"), constraints);
		size = new JComboBox(sizeList);
		size.setMinimumSize(new Dimension(100,100));
		constraints.gridx = 1;
		adjustGridBagAnchor(constraints);
		super.add(size, constraints);
		constraints.gridy++;
		//Type / Tag
		constraints.gridx = 0;
		adjustGridBagAnchor(constraints);
		super.add(new JLabel("Type / Tag"), constraints);
		JPanel typeTagRow = new JPanel();
			type = new JComboBox(typeList);
			type.setEditable(true);
			tag = new JComboBox(tagList);
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
		alignment = new JComboBox(alignmentList);
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
			hitPointDice = new JSpinner(new SpinnerNumberModel(0,0,50,1));
			hitPointRow.add(hitPointDice, BorderLayout.WEST);
			hitPointDiceCount = new JComboBox(diceList);
			hitPointRow.add(hitPointDiceCount, BorderLayout.CENTER);
			hitPointCount = new JTextField(9);
			hitPointRow.add(hitPointCount, BorderLayout.EAST);
		constraints.gridx = 1;
		adjustGridBagAnchor(constraints);
		super.add(hitPointRow, constraints);
		constraints.gridy++;
		//Hit Point Buttons
		JPanel hitPointButtons = new JPanel();
			JButton generateButton = new JButton("Generate HP");
			hitPointButtons.add(generateButton);
			JButton rollButton = new JButton("Roll HP");
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
}
