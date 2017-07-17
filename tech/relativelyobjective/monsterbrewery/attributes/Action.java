package tech.relativelyobjective.monsterbrewery.attributes;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import tech.relativelyobjective.monsterbrewery.AttributeHandler;
import tech.relativelyobjective.monsterbrewery.pieces.FrameMain;
import tech.relativelyobjective.monsterbrewery.resources.Lists;

/**
 *
 * @author ReltivlyObjectv
 * Contact: me@relativelyobjective.tech
 * 
 */
public class Action implements Attribute{
	private class MeleePanel extends JPanel {
		public JSpinner range;
		//Shared JElements
		public JComboBox damageTypeBox;
		public JSpinner toHitBox;
		public JSpinner diceCountBox;
		public JComboBox diceTypeBox;
		
		public MeleePanel() {
			range = new JSpinner(new SpinnerNumberModel(5,5,1000,5));
			range.setValue(meleeReach);
			toHitBox = new JSpinner(new SpinnerNumberModel(0,-30,30,1));
			toHitBox.setValue(toHit);
			diceCountBox = new JSpinner(new SpinnerNumberModel(1,1,50,1));
			diceCountBox.setValue(diceCount);
			diceTypeBox = new JComboBox(Lists.DICE);
			diceTypeBox.setSelectedItem(diceType);
			damageTypeBox = new JComboBox(Lists.DAMAGE_TYPES);
			damageTypeBox.setSelectedItem(damageType);
			damageTypeBox.setEditable(true);
			super.setLayout(new GridBagLayout());
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			//Damage
			super.add(new JLabel("Damage"), constraints);
			JPanel damagePanel = new JPanel();
				damagePanel.setLayout(new GridBagLayout());
				GridBagConstraints damConstr = new GridBagConstraints();
				damConstr.gridx = 0;
				damConstr.gridy = 0;
				damagePanel.add(this.diceCountBox, damConstr);
				damConstr.gridx++;
				damagePanel.add(this.diceTypeBox, damConstr);
			constraints.gridx++;
			super.add(damagePanel, constraints);
			//Damage Type
			constraints.gridx = 0;
			constraints.gridy++;
			super.add(new JLabel("Damage Type"), constraints);
			constraints.gridx++;
			super.add(this.damageTypeBox, constraints);
			//To Hit
			constraints.gridx = 0;
			constraints.gridy++;
			super.add(new JLabel("To Hit"), constraints);
			constraints.gridx++;
			super.add(this.toHitBox, constraints);
			//Reach
			constraints.gridx = 0;
			constraints.gridy++;
			super.add(new JLabel("Range"), constraints);
			constraints.gridx++;
			super.add(this.range, constraints);
		}
	}
	private class RangedPanel extends JPanel {
		public JSpinner minRange;
		public JSpinner maxRange;
		public JSpinner toHitBox;
		public JSpinner diceCountBox;
		public JSpinner shapeSize;
		public JComboBox diceTypeBox;
		public JComboBox damageTypeBox;
		public JComboBox attackShapeBox;
		
		
		public RangedPanel() {
			minRange = new JSpinner(new SpinnerNumberModel(5,5,1000,5));
			minRange.setValue(rangedMin);
			maxRange = new JSpinner(new SpinnerNumberModel(5,5,1000,5));
			maxRange.setValue(rangedMax);
			toHitBox = new JSpinner(new SpinnerNumberModel(0,-30,30,1));
			toHitBox.setValue(toHit);
			diceCountBox = new JSpinner(new SpinnerNumberModel(1,1,50,1));
			diceCountBox.setValue(diceCount);
			shapeSize = new JSpinner(new SpinnerNumberModel(5,5,1000,5));
			shapeSize.setValue(rangedSize);
			diceTypeBox = new JComboBox(Lists.DICE);
			diceTypeBox.setSelectedItem(diceType);
			damageTypeBox = new JComboBox(Lists.DAMAGE_TYPES);
			damageTypeBox.setSelectedItem(damageType);
			damageTypeBox.setEditable(true);
			attackShapeBox = new JComboBox(Lists.RangeType.values());
			attackShapeBox.setSelectedItem(rangeType);
			super.setLayout(new GridBagLayout());
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			//Damage
			super.add(new JLabel("Damage"), constraints);
			JPanel damagePanel = new JPanel();
				damagePanel.setLayout(new GridBagLayout());
				GridBagConstraints damConstr = new GridBagConstraints();
				damConstr.gridx = 0;
				damConstr.gridy = 0;
				damagePanel.add(this.diceCountBox, damConstr);
				damConstr.gridx++;
				damagePanel.add(this.diceTypeBox, damConstr);
			constraints.gridx++;
			super.add(damagePanel, constraints);
			//Damage Type
			constraints.gridx = 0;
			constraints.gridy++;
			super.add(new JLabel("Damage Type"), constraints);
			constraints.gridx++;
			super.add(this.damageTypeBox, constraints);
			//Shape
			constraints.gridx = 0;
			constraints.gridy++;
			super.add(new JLabel("Shape"), constraints);
			constraints.gridx++;
			super.add(attackShapeBox, constraints);
			//Shape Size
			constraints.gridx = 0;
			constraints.gridy++;
			super.add(new JLabel("Shape Size"), constraints);
			constraints.gridx++;
			super.add(shapeSize, constraints);
			//To Hit
			constraints.gridx = 0;
			constraints.gridy++;
			super.add(new JLabel("To Hit"), constraints);
			constraints.gridx++;
			super.add(this.toHitBox, constraints);
			//Min Range
			constraints.gridx = 0;
			constraints.gridy++;
			super.add(new JLabel("Standard Range"), constraints);
			constraints.gridx++;
			super.add(minRange, constraints);
			//Max Range
			constraints.gridx = 0;
			constraints.gridy++;
			super.add(new JLabel("Long Range"), constraints);
			constraints.gridx++;
			super.add(maxRange, constraints);
		}
	}
	private class MiscPanel extends JPanel {
		public JTextArea descBox;
		
		public MiscPanel() {
			super.setLayout(new GridBagLayout());
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			descBox = new JTextArea(15, 15);
			descBox.setText(description);
			descBox.setLineWrap(true);
			descBox.setWrapStyleWord(true);
			JScrollPane scroller = new JScrollPane(descBox);
			scroller.setPreferredSize(new Dimension(325,300));
			super.add(scroller, constraints);
		}
	}
	//Overall
	private Lists.ActionType actionType;
	private String name;
	private String diceType;
	private int diceCount;
	private int toHit;
	private String damageType;
	//Ranged
	private int rangedMin;
	private int rangedMax;
	private Lists.RangeType rangeType;
	private int rangedSize;
	//Melee
	private int meleeReach;
	//Misc
	private String description;
	
	public Action() {
		actionType = Lists.ActionType.MELEE;
		name = "";
		diceType = Lists.DICE[0];
		diceCount = 1;
		toHit = 5;
		rangedMin = 60;
		rangedMax = 240;
		rangedSize =10;
		rangeType = Lists.RangeType.LINE;
		meleeReach = 5;
		damageType = "Piercing";
		description = "";
	}
	@Override
	public void editAttribute(FrameMain mainFrame) {
		//Create Window
		JDialog editWindow = new JDialog(mainFrame, "Action", true);
			editWindow.setPreferredSize(new Dimension(500,500));
			editWindow.setSize(editWindow.getPreferredSize());
			editWindow.setMaximumSize(editWindow.getPreferredSize());
			editWindow.setMinimumSize(editWindow.getPreferredSize());
			editWindow.setLayout(new GridBagLayout());
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			editWindow.add(new JLabel("Name"), constraints);
			constraints.gridx++;
			JTextField nameBox = new JTextField(20);
			nameBox.setText(name);
			editWindow.add(nameBox, constraints);
			JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
				tabbedPane.setPreferredSize(new Dimension(400,400));
				tabbedPane.setSize(tabbedPane.getPreferredSize());
				tabbedPane.setMaximumSize(tabbedPane.getPreferredSize());
				tabbedPane.setMinimumSize(tabbedPane.getPreferredSize());
				MeleePanel meleePanel = new MeleePanel();
				tabbedPane.addTab("Melee Attack", meleePanel);
				RangedPanel rangedPanel = new RangedPanel();
				tabbedPane.addTab("Ranged Attack", rangedPanel);
				MiscPanel miscPanel = new MiscPanel();
				tabbedPane.addTab("Other Action", miscPanel);
				switch (actionType) {
					case MELEE:
						tabbedPane.setSelectedIndex(0);
						break;
					case RANGED:
						tabbedPane.setSelectedIndex(1);
						break;
					case MISC:
						tabbedPane.setSelectedIndex(2);
						break;
				}
			constraints.gridx = 0;
			constraints.gridy++;
			constraints.gridwidth = 2;
			editWindow.add(tabbedPane, constraints);
			JButton submitButton = new JButton("Save Action");
			constraints.gridy++;
			editWindow.add(submitButton, constraints);
			//Listener
			submitButton.addActionListener((ActionEvent e) -> {
				if (!nameBox.getText().equals("")) {
					switch (tabbedPane.getSelectedIndex()) {
						case 0:
							//Melee
							if (!meleePanel.damageTypeBox.getSelectedItem().equals("")) {
								actionType = Lists.ActionType.MELEE;
								name = nameBox.getText();
								diceType = (String) meleePanel.diceTypeBox.getSelectedItem();
								diceCount = (int) meleePanel.diceCountBox.getValue();
								toHit = (int) meleePanel.toHitBox.getValue();
								meleeReach = (int) meleePanel.range.getValue();
								damageType = (String) meleePanel.damageTypeBox.getSelectedItem();
								if (!AttributeHandler.contains(this)) {
									AttributeHandler.addAttribute(this);
								}
								editWindow.dispose();
							}
							
							break;
						case 1:
							//Ranged
							if (!rangedPanel.damageTypeBox.getSelectedItem().equals("")) {
								actionType = Lists.ActionType.RANGED;
								name = nameBox.getText();
								diceType = (String) rangedPanel.diceTypeBox.getSelectedItem();
								diceCount = (int) rangedPanel.diceCountBox.getValue();
								toHit = (int) rangedPanel.toHitBox.getValue();
								rangedMin = (int) rangedPanel.minRange.getValue();
								rangedMax = (int) rangedPanel.maxRange.getValue();
								rangeType = (Lists.RangeType) rangedPanel.attackShapeBox.getSelectedItem();
								rangedSize = (int) rangedPanel.shapeSize.getValue();
								damageType = (String) rangedPanel.damageTypeBox.getSelectedItem();
								if (!AttributeHandler.contains(this)) {
									AttributeHandler.addAttribute(this);
								}
								editWindow.dispose();
							}
							break;
						case 2:
							//Misc
							if (!miscPanel.descBox.getText().equals("")) {
								actionType = Lists.ActionType.MISC;
								name = nameBox.getText();
								description = miscPanel.descBox.getText();
								if (!AttributeHandler.contains(this)) {
									AttributeHandler.addAttribute(this);
								}
								editWindow.dispose();
							}
							break;
					}
				}
			});
		SwingUtilities.invokeLater(() -> {
			editWindow.setVisible(true);
		});
	}
	@Override
	public String toString() {
		switch(actionType) {
			case MELEE:
				return String.format("Melee Attack: %s", name);
			case RANGED:
				return String.format("Ranged Attack: %s %d/%d", name, rangedMin, rangedMax);
			default:
				return String.format("Action: %s", name);
		}
	}
	
}
