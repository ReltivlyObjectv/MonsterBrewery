package tech.relativelyobjective.monsterbrewery.pieces;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import tech.relativelyobjective.monsterbrewery.resources.JLabelBold;
import tech.relativelyobjective.monsterbrewery.resources.Lists;

/**
 *
 * @author ReltivlyObjectv
 * Contact: me@relativelyobjective.tech
 * 
 */
public class PanelMonsterChallengeRating extends JPanel {
	private JComboBox value;
	
	public PanelMonsterChallengeRating() {
		super.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 2;
		super.add(new JLabelBold("Challenge Rating"), constraints);
		constraints.gridy++;
		constraints.gridwidth = 1;
		constraints.gridx = 0;
		value = new JComboBox(Lists.CHALLENGE_RATING);
		super.add(value, constraints);
		JButton calculateButton = new JButton("Calculate CR [Beta]");
		calculateButton.addActionListener((ActionEvent e) -> {
			calculateChallengeRating();
		});
		constraints.gridx++;
		super.add(calculateButton, constraints);
		
	}
	public String getChallengeRating() {
		return (String) value.getSelectedItem();
	}
	private void calculateChallengeRating() {
		//TODO
		value.setSelectedItem("15");
	}
	
}
