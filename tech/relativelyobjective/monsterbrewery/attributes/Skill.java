package tech.relativelyobjective.monsterbrewery.attributes;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import tech.relativelyobjective.monsterbrewery.pieces.FrameMain;

/**
 *
 * @author ReltivlyObjectv
 * Contact: me@relativelyobjective.tech
 * 
 */
public class Skill implements Attribute {
	private String skill;
	private int modifier;
	
	public Skill(String s, int m) {
		skill = s;
		modifier = m;
	}
	public Skill() {
		skill = "";
		modifier = 0;
	}
	@Override
	public void editAttribute(FrameMain mainFrame) {
		JDialog editWindow = new JDialog(mainFrame, "Edit Skill", true);
			editWindow.setPreferredSize(new Dimension(400,100));
			editWindow.setSize(editWindow.getPreferredSize());
			editWindow.setMaximumSize(editWindow.getPreferredSize());
			editWindow.setMinimumSize(editWindow.getPreferredSize());
			editWindow.setLayout(new GridBagLayout());
			GridBagConstraints constraints = new GridBagConstraints();
			JTextField text = new JTextField(15);
			text.setText(skill);
			constraints.gridy = 0;
			constraints.gridx = 0;
			editWindow.add(text, constraints);
			JSpinner magSpinner = new JSpinner(new SpinnerNumberModel(0,-30,30,1));
			magSpinner.setValue(modifier);
			constraints.gridx++;
			editWindow.add(magSpinner, constraints);
			JButton saveButton = new JButton("Save Skill");
			saveButton.addActionListener((ActionEvent e) -> {
				if (!text.getText().isEmpty()) {
					skill = text.getText();
					modifier = (int) magSpinner.getValue();
					editWindow.dispose();
				}
			});
			constraints.gridx++;
			editWindow.add(saveButton, constraints);
		SwingUtilities.invokeLater(() -> {
			editWindow.setVisible(true);
		});
	}
	@Override
	public String toString() {
		if (skill.toLowerCase().contains("passive perception")) {
			return String.format("Passive Perception: %d", modifier);
		} else {
			if (modifier < 0) {
				return String.format("Skill: %s %d", skill, modifier);
			} else {
				return String.format("Skill: %s +%d", skill, modifier);
			}
		}
	}
	public String getSkill() {
		return skill;
	}
	public int getModifier() {
		return modifier;
	}
	public void setSkill(String s) {
		skill = s;
	}
	public void setModifier(int mod) {
		modifier = mod;
	}
	public void setModifier(String mod) {
		try {
			setModifier(Integer.parseInt(mod));
		} catch (NumberFormatException e) {
			//Do Nothing
		}
	}
}
