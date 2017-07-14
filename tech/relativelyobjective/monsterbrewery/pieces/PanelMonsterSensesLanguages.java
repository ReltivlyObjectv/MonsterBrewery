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
import tech.relativelyobjective.monsterbrewery.attributes.Language;
import tech.relativelyobjective.monsterbrewery.attributes.Sense;
import tech.relativelyobjective.monsterbrewery.resources.JLabelBold;
import tech.relativelyobjective.monsterbrewery.resources.Lists;

/**
 *
 * @author ReltivlyObjectv
 * Contact: me@relativelyobjective.tech
 * 
 */
public class PanelMonsterSensesLanguages extends JPanel {
	private class SensesPanel extends JPanel {
		private final JComboBox sense;
		private final JSpinner distance;
		
		public SensesPanel() {
			super.setLayout(new GridBagLayout());
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			super.add(new JLabel("Sense/Passive Perception"));
			constraints.gridx = 1;
			super.add(new JLabel("Distance"));
			constraints.gridy++;
			sense = new JComboBox(Lists.SENSES);
			sense.setEditable(true);
			constraints.gridx = 0;
			super.add(sense, constraints);
			distance = new JSpinner(new SpinnerNumberModel(0,0,1000,5));
			constraints.gridx = 1;
			super.add(distance, constraints);
			constraints.gridx = 2;
			JButton addButton = new JButton("Add Sense");
			addButton.addActionListener((ActionEvent e) -> {
				saveData();
			});
			super.add(addButton, constraints);
		}
		public String getSense() {
			return (String) sense.getSelectedItem();
		}
		public int getDistance() {
			return (int) distance.getValue();
		}
		private void saveData() {
			if (sense.getSelectedItem() == null) {
				return;
			} else if (getSense().equals("")) {
				return;
			} else if (getDistance() <= 0) {
				return;
			}
			AttributeHandler.addAttribute(new Sense(getSense(), getDistance()));
		}
	}
	private class LanguagesPanel extends JPanel {
		private final JComboBox lang;
		
		public LanguagesPanel() {
			super.setLayout(new GridBagLayout());
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			super.add(new JLabel("Language"));
			constraints.gridy++;
			lang = new JComboBox(Lists.LANGUAGES);
			lang.setEditable(true);
			super.add(lang, constraints);
			JButton addButton = new JButton("Add Language");
			addButton.addActionListener((ActionEvent e) -> {
				saveData();
			});
			constraints.gridx++;
			super.add(addButton, constraints);
		}
		private String getLanguage() {
			return (String) lang.getSelectedItem();
		}
		private void saveData() {
			if (getLanguage() == null) {
				return;
			} else if (getLanguage().equals("")) {
				return;
			} 
			AttributeHandler.addAttribute(new Language(getLanguage()));
		}
	}
	private final SensesPanel senses;
	private final LanguagesPanel lang;
	
	public PanelMonsterSensesLanguages() {
		super.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		//Labels
		constraints.gridy = 0;
		constraints.gridx = 0;
		super.add(new JLabelBold("Senses"), constraints);
		constraints.gridx = 1;
		super.add(new JLabelBold("Languages"), constraints);
		//Panels
		constraints.gridy = 1;
		senses = new SensesPanel();
		constraints.gridx = 0;
		super.add(senses, constraints);
		lang = new LanguagesPanel();
		constraints.gridx = 1;
		super.add(lang, constraints);
	}
}
