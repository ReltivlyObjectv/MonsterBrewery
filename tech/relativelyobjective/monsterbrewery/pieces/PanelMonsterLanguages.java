/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tech.relativelyobjective.monsterbrewery.pieces;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import tech.relativelyobjective.monsterbrewery.AttributeHandler;
import tech.relativelyobjective.monsterbrewery.attributes.Language;
import tech.relativelyobjective.monsterbrewery.resources.Lists;

/**
 *
 * @author ReltivlyObjectv
 * Contact: me@relativelyobjective.tech
 * 
 */
public class PanelMonsterLanguages extends JPanel {
	private final JComboBox lang;

	public PanelMonsterLanguages() {
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
