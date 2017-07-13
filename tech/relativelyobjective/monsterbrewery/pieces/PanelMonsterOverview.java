package tech.relativelyobjective.monsterbrewery.pieces;

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
	private final JTextField size;
	private final JTextField armorType;
	private final JTextField hitPointCount;
	private final JComboBox type;
	private final JComboBox tag;
	private final JComboBox alignment;
	private final JComboBox hitPointDiceCount;
	private final JSpinner armorClass;
	private final JSpinner hitPointDice;

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
	
	public PanelMonsterOverview() {
		super.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridy = 0;
		//Name
		constraints.gridx = 0;
		super.add(new JLabel("Name"), constraints);
		name = new JTextField(20);
		constraints.gridx = 1;
		super.add(name, constraints);
		constraints.gridy++;
		//Size
		constraints.gridx = 0;
		super.add(new JLabel("Size"), constraints);
		size = new JTextField(20);
		constraints.gridx = 1;
		super.add(size, constraints);
		constraints.gridy++;
		//Type / Tag
		constraints.gridx = 0;
		super.add(new JLabel("Type / Tag"), constraints);
		JPanel typeTagRow = new JPanel();
			type = new JComboBox();
			type.setEditable(true);
			tag = new JComboBox();
			tag.setEditable(true);
			typeTagRow.setLayout(new BoxLayout(typeTagRow, BoxLayout.LINE_AXIS));
			typeTagRow.add(type);
			typeTagRow.add(tag);
			constraints.gridx = 1;
		super.add(typeTagRow, constraints);
		constraints.gridy++;
		//Alignment
		constraints.gridx = 0;
		super.add(new JLabel("Alignment"), constraints);
		alignment = new JComboBox(alignmentList);
		constraints.gridx = 1;
		super.add(alignment, constraints);
		constraints.gridy++;
		//Armor
		constraints.gridx = 0;
		super.add(new JLabel("Armor Class"), constraints);
		JPanel armorRow = new JPanel();
			armorRow.setLayout(new BoxLayout(armorRow, BoxLayout.X_AXIS));
			armorClass = new JSpinner(new SpinnerNumberModel(0,0,50,1));
			armorRow.add(armorClass);
			armorType = new JTextField();
			armorRow.add(armorType);
		constraints.gridx = 1;
		super.add(armorRow, constraints);
		constraints.gridy++;
		//Hit Points
		constraints.gridx = 0;
		super.add(new JLabel("Hit Points"), constraints);
		JPanel hitPointRow = new JPanel();
			hitPointRow.setLayout(new BoxLayout(hitPointRow, BoxLayout.X_AXIS));
			hitPointDice = new JSpinner(new SpinnerNumberModel(0,0,100,1));
			hitPointRow.add(hitPointDice);
			hitPointDiceCount = new JComboBox(diceList);
			hitPointRow.add(hitPointDiceCount);
			hitPointCount = new JTextField();
			hitPointRow.add(hitPointCount);
		constraints.gridx = 1;
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
}
