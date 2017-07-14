package tech.relativelyobjective.monsterbrewery.pieces;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import tech.relativelyobjective.monsterbrewery.AttributeHandler;
import tech.relativelyobjective.monsterbrewery.attributes.Skill;
import tech.relativelyobjective.monsterbrewery.resources.JLabelBold;
import tech.relativelyobjective.monsterbrewery.resources.Lists;

/**
 *
 * @author ReltivlyObjectv
 * Contact: me@relativelyobjective.tech
 * 
 */
public class PanelMonsterSkills extends JPanel {
	private JComboBox skill;
	private JSpinner bonus;
	
	public PanelMonsterSkills() {
		super.setLayout(new GridBagLayout());
		GridBagConstraints mainConstraints = new GridBagConstraints();
		mainConstraints.gridx = 0;
		mainConstraints.gridy = 0;
		super.add(new JLabelBold("Skills"), mainConstraints);
		JPanel grid = new JPanel();
			grid.setLayout(new GridBagLayout());
			GridBagConstraints constraints = new GridBagConstraints();
			//Labels
			constraints.gridy = 0;
			constraints.gridx = 0;
			grid.add(new JLabel("Skill Selection"), constraints);
			constraints.gridx++;
			grid.add(new JLabel("Bonus"), constraints);
			//Inputs
			constraints.gridy++;
			constraints.gridx = 0;
			skill = new JComboBox(Lists.SKILLS);
			skill.setEditable(true);
			grid.add(skill, constraints);
			bonus = new JSpinner(new SpinnerNumberModel(0,-30,30,1));
			constraints.gridx++;
			grid.add(bonus, constraints);
			JButton addButton = new JButton("Add Skill");
			addButton.addActionListener((ActionEvent e) -> {
				saveData();
			});
			constraints.gridx++;
			grid.add(addButton, constraints);
		mainConstraints.gridy++;
		super.add(grid, mainConstraints);
	}
	private void saveData() {
		Skill newSkill = new Skill((String) skill.getSelectedItem(), (int) bonus.getValue());
		AttributeHandler.addAttribute(newSkill);
	}
}
