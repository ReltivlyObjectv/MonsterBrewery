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
	public void setChallengeRating(double challengeRating) {
		if (challengeRating < (1.0/8.0)) {
			value.setSelectedItem("0");
		} else if (challengeRating < (1.0/4.0)) {
			value.setSelectedItem("1/8");
		} else if (challengeRating < (1.0/2.0)) {
			value.setSelectedItem("1/4");
		} else if (challengeRating < (1)) {
			value.setSelectedItem("1/2");
		} else if (challengeRating < (2)) {
			value.setSelectedItem("1");
		} else if (challengeRating < (3)) {
			value.setSelectedItem("2");
		} else if (challengeRating < (4)) {
			value.setSelectedItem("3");
		} else if (challengeRating < (5)) {
			value.setSelectedItem("4");
		} else if (challengeRating < (6)) {
			value.setSelectedItem("5");
		} else if (challengeRating < (7)) {
			value.setSelectedItem("6");
		} else if (challengeRating < (8)) {
			value.setSelectedItem("7");
		} else if (challengeRating < (9)) {
			value.setSelectedItem("8");
		} else if (challengeRating < (10)) {
			value.setSelectedItem("9");
		} else if (challengeRating < (11)) {
			value.setSelectedItem("10");
		} else if (challengeRating < (12)) {
			value.setSelectedItem("11");
		} else if (challengeRating < (13)) {
			value.setSelectedItem("12");
		} else if (challengeRating < (14)) {
			value.setSelectedItem("13");
		} else if (challengeRating < (15)) {
			value.setSelectedItem("14");
		} else if (challengeRating < (16)) {
			value.setSelectedItem("15");
		} else if (challengeRating < (17)) {
			value.setSelectedItem("16");
		} else if (challengeRating < (18)) {
			value.setSelectedItem("17");
		} else if (challengeRating < (19)) {
			value.setSelectedItem("18");
		} else if (challengeRating < (20)) {
			value.setSelectedItem("19");
		} else if (challengeRating < (21)) {
			value.setSelectedItem("20");
		} else if (challengeRating < (22)) {
			value.setSelectedItem("21");
		} else if (challengeRating < (23)) {
			value.setSelectedItem("22");
		} else if (challengeRating < (24)) {
			value.setSelectedItem("23");
		} else if (challengeRating < (25)) {
			value.setSelectedItem("24");
		} else if (challengeRating < (26)) {
			value.setSelectedItem("25");
		} else if (challengeRating < (27)) {
			value.setSelectedItem("26");
		} else if (challengeRating < (28)) {
			value.setSelectedItem("27");
		} else if (challengeRating < (29)) {
			value.setSelectedItem("28");
		} else if (challengeRating < (30)) {
			value.setSelectedItem("29");
		} else {
			value.setSelectedItem("30");
		}
	}
	private void calculateChallengeRating() {
		openChallengePrompt(MonsterInformation.getMainFrame());
	}
	private void openChallengePrompt(FrameMain mainFrame) {
		JDialog challengePrompt = new JDialog(mainFrame, "Calculate Challenge Rating", true);
			//TODO fix spinners and set calculated values
			JSpinner armorClass = new JSpinner(new SpinnerNumberModel(0,0,1000,1));
			int ac = MonsterInformation.getArmorClass();
			armorClass.setValue(ac);
			JSpinner hitPoints = new JSpinner(new SpinnerNumberModel(0,0,1000,1));
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
			hitPoints.setValue(totalHP);
			JSpinner attackBonus = new JSpinner(new SpinnerNumberModel(0,0,1000,1));
			int attk = ChallengeRatingCalculator.guessAttackBonus();
			attackBonus.setValue(attk);
			JSpinner damagePerRound = new JSpinner(new SpinnerNumberModel(0,0,1000,1));
			int dps = ChallengeRatingCalculator.guessDamagePerRound();
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
