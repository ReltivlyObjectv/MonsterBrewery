package tech.relativelyobjective.monsterbrewery.pieces;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import tech.relativelyobjective.monsterbrewery.resources.ChallengeRatingCalculator;
import tech.relativelyobjective.monsterbrewery.resources.JLabelBold;
import tech.relativelyobjective.monsterbrewery.resources.Lists;
import tech.relativelyobjective.monsterbrewery.resources.MonsterInformation;

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
		JButton calculateButton = new JButton("Calculate CR");
		calculateButton.addActionListener((ActionEvent e) -> {
			calculateChallengeRating();
		});
		constraints.gridx++;
		super.add(calculateButton, constraints);
		
	}
	public String getChallengeRating() {
		return (String) value.getSelectedItem();
	}
	private void setChallengeRating(double challengeRating) {
		//TODO parse double into string of text, then adjust panel
	}
	private void calculateChallengeRating() {
		//double challengeRating = ChallengeRatingCalculator.getChallengeRating(0, 0, 1, 6);
		//System.out.printf("%f",challengeRating);
		//value.setSelectedItem("15");
		openChallengePrompt(MonsterInformation.getMainFrame());
	}
	private void openChallengePrompt(FrameMain mainFrame) {
		JDialog challengePrompt = new JDialog(mainFrame, "Calculate Challenge Rating", true);
			//TODO fix spinners and set calculated values
			JSpinner armorClass = new JSpinner(new SpinnerNumberModel(1,1,1000,1));
			int ac = MonsterInformation.getArmorClass();
			System.out.printf("Armor Class: %d\n", ac);
			armorClass.setValue(ac);
			JSpinner hitPoints = new JSpinner(new SpinnerNumberModel(1,1,1000,1));
			String hitPointString = MonsterInformation.getHitPointString();
			String totalHitPointString;
			try {
				totalHitPointString = hitPointString.substring(hitPointString.indexOf(")")).replace(")", "").replace(" ", "");
			} catch (IndexOutOfBoundsException e) {
				totalHitPointString = "1";
			}
			int totalHP;
			try {
				totalHP = Integer.parseInt(totalHitPointString);
			} catch (NumberFormatException e) {
				totalHP = 1;
			}
			System.out.printf("Total HP: %d\n", totalHP);
			hitPoints.setValue(totalHP);
			JSpinner attackBonus = new JSpinner(new SpinnerNumberModel(1,1,1000,1));
			int attk = ChallengeRatingCalculator.guessAttackBonus();
			System.out.printf("Attack Bonus: %d\n", attk);
			attackBonus.setValue(attk);
			JSpinner damagePerRound = new JSpinner(new SpinnerNumberModel(1,1,1000,1));
			int dps = ChallengeRatingCalculator.guessDamagePerRound();
			System.out.printf("Damage Per Round: %d\n", attk);
			damagePerRound.setValue(dps);
			JButton calculateButton = new JButton("Calculate");
			challengePrompt.setLayout(new GridBagLayout());
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.anchor = GridBagConstraints.CENTER;
			constraints.gridy = 0;
			constraints.gridx = 0;
			challengePrompt.add(new JLabel("Armor Class"), constraints);
			constraints.gridx++;
			challengePrompt.add(armorClass, constraints);
			constraints.gridy++;
			constraints.gridx = 0;
			challengePrompt.add(new JLabel(String.format(
				"Hit Points: %s",
				MonsterInformation.getHitPointString()
				)), constraints);
			constraints.gridx++;
			challengePrompt.add(hitPoints, constraints);
			constraints.gridy++;
			constraints.gridx = 0;
			challengePrompt.add(new JLabel("Attack Bonus (i.e. To Hit)"), constraints);
			constraints.gridx++;
			challengePrompt.add(attackBonus, constraints);
			constraints.gridy++;
			constraints.gridx = 0;
			challengePrompt.add(new JLabel("Damage Per Round"), constraints);
			constraints.gridx++;
			challengePrompt.add(damagePerRound, constraints);
			constraints.gridy++;
			constraints.gridx = 0;
			constraints.gridwidth = 2;
			challengePrompt.add(calculateButton, constraints);
			//Listeners
			calculateButton.addActionListener((ActionEvent e) -> {
				setChallengeRating(ChallengeRatingCalculator.getChallengeRating(
					(int) armorClass.getValue(), 
					(int) hitPoints.getValue(), 
					(int) attackBonus.getValue(), 
					(int) damagePerRound.getValue()
					));
					challengePrompt.dispose(); 
			});
			//Sizing
			challengePrompt.setPreferredSize(new Dimension(300,200));
			challengePrompt.setSize(challengePrompt.getPreferredSize());
			challengePrompt.setMinimumSize(challengePrompt.getPreferredSize());
			challengePrompt.setMaximumSize(challengePrompt.getPreferredSize());
		SwingUtilities.invokeLater(() -> {
			challengePrompt.setVisible(true);
		});
	}
	
}
