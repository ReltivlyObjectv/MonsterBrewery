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
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import tech.relativelyobjective.monsterbrewery.AttributeHandler;
import tech.relativelyobjective.monsterbrewery.attributes.Sense;
import tech.relativelyobjective.monsterbrewery.resources.Lists;

/**
 *
 * @author ReltivlyObjectv
 * Contact: me@relativelyobjective.tech
 * 
 */

public class PanelMonsterSenses extends JPanel {
	private final JComboBox sense;
	private final JSpinner distance;

	public PanelMonsterSenses() {
		super.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		super.add(new JLabel("Sense"));
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
