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
import tech.relativelyobjective.monsterbrewery.resources.AttributeHandler;
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
		public JComboBox rangeDeliveryBox;
		public JComboBox rangeShapeBox;
		public JComboBox rangeTypeBox;
		
		
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
			rangeShapeBox = new JComboBox(Lists.RangeShape.values());
			rangeShapeBox.setSelectedItem(rangedShape);
			rangeTypeBox = new JComboBox(Lists.RangeType.values());
			rangeTypeBox.setSelectedItem(rangedType);
			rangeDeliveryBox = new JComboBox(Lists.RangeDelivery.values());
			rangeDeliveryBox.setSelectedItem(rangedDelivery);
			super.setLayout(new GridBagLayout());
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			//Range Type
			super.add(new JLabel("Action Type"), constraints);
			constraints.gridx++;
			super.add(rangeTypeBox, constraints);
			//Delivery
			constraints.gridx = 0;
			constraints.gridy++;
			super.add(new JLabel("Delivery"), constraints);
			constraints.gridx++;
			super.add(rangeDeliveryBox, constraints);
			//Damage
			constraints.gridx = 0;
			constraints.gridy++;
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
			JLabel shapeLabel = new JLabel("Shape");
			super.add(shapeLabel, constraints);
			constraints.gridx++;
			super.add(rangeShapeBox, constraints);
			//Shape Size
			constraints.gridx = 0;
			constraints.gridy++;
			JLabel shapeSizeLabel = new JLabel("Shape Size");
			super.add(shapeSizeLabel, constraints);
			constraints.gridx++;
			super.add(shapeSize, constraints);
			//Min Range
			constraints.gridx = 0;
			constraints.gridy++;
			JLabel sRangeLabel = new JLabel("Standard Range");
			super.add(sRangeLabel, constraints);
			constraints.gridx++;
			super.add(minRange, constraints);
			//Max Range
			constraints.gridx = 0;
			constraints.gridy++;
			JLabel lRangeLabel = new JLabel("Long Range");
			super.add(lRangeLabel, constraints);
			constraints.gridx++;
			super.add(maxRange, constraints);
			//To Hit
			constraints.gridx = 0;
			constraints.gridy++;
			super.add(new JLabel("To Hit"), constraints);
			constraints.gridx++;
			super.add(this.toHitBox, constraints);
			//Set visibility
			if ((Lists.RangeDelivery) rangeDeliveryBox.getSelectedItem() == Lists.RangeDelivery.FIRE_AND_FORGET) {
				//An attack with min/max range
				shapeLabel.setVisible(false);
				rangeShapeBox.setVisible(false);
				sRangeLabel.setVisible(true);
				minRange.setVisible(true);
				lRangeLabel.setVisible(true);
				maxRange.setVisible(true);
				shapeSizeLabel.setVisible(false);
				shapeSize.setVisible(false);
			} else {
				//An attack with a cone, box, etc
				shapeLabel.setVisible(true);
				rangeShapeBox.setVisible(true);
				sRangeLabel.setVisible(false);
				minRange.setVisible(false);
				lRangeLabel.setVisible(false);
				maxRange.setVisible(false);
				shapeSizeLabel.setVisible(true);
				shapeSize.setVisible(true);
			}
			//Listener
			rangeDeliveryBox.addActionListener((ActionEvent e) -> {
				if ((Lists.RangeDelivery) rangeDeliveryBox.getSelectedItem() == Lists.RangeDelivery.FIRE_AND_FORGET) {
					//An attack with min/max range
					shapeLabel.setVisible(false);
					rangeShapeBox.setVisible(false);
					sRangeLabel.setVisible(true);
					minRange.setVisible(true);
					lRangeLabel.setVisible(true);
					maxRange.setVisible(true);
					shapeSizeLabel.setVisible(false);
					shapeSize.setVisible(false);
				} else {
					//An attack with a cone, box, etc
					shapeLabel.setVisible(true);
					rangeShapeBox.setVisible(true);
					sRangeLabel.setVisible(false);
					minRange.setVisible(false);
					lRangeLabel.setVisible(false);
					maxRange.setVisible(false);
					shapeSizeLabel.setVisible(true);
					shapeSize.setVisible(true);
				}
			});
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
	private Lists.RangeShape rangedShape;
	private Lists.RangeType rangedType;
	private Lists.RangeDelivery rangedDelivery;
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
		rangedShape = Lists.RangeShape.LINE;
		rangedType = Lists.RangeType.PHYSICAL;
		rangedDelivery = Lists.RangeDelivery.FIRE_AND_FORGET;
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
								rangedShape = (Lists.RangeShape) rangedPanel.rangeShapeBox.getSelectedItem();
								rangedType = (Lists.RangeType) rangedPanel.rangeTypeBox.getSelectedItem();
								rangedSize = (int) rangedPanel.shapeSize.getValue();
								rangedDelivery = (Lists.RangeDelivery) rangedPanel.rangeDeliveryBox.getSelectedItem();
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
				switch (rangedDelivery) {
					case SHAPE:
						return String.format("Ranged %s: %s (%d ft. %s)", 
							rangedType == Lists.RangeType.SPELL ? "Spell" : "Attack",
							name, rangedSize, Lists.formatUpperCase(rangedShape));
					case FIRE_AND_FORGET:
						return String.format("Ranged %s: %s (%d/%d ft.)",
							rangedType == Lists.RangeType.SPELL ? "Spell" : "Attack",
							name, rangedMin, rangedMax);
				}
			default:
				return String.format("Action: %s", name);
		}
	}
	public Lists.ActionType getActionType() {
		return actionType;
	}
	public String getName() {
		return name;
	}
	public String getDiceType() {
		return diceType;
	}
	public int getDiceCount() {
		return diceCount;
	}
	public int getToHit() {
		return toHit;
	}
	public String getDamageType() {
		return damageType;
	}
	public int getRangedMin() {
		return rangedMin;
	}
	public int getRangedMax() {
		return rangedMax;
	}
	public Lists.RangeShape getRangedShape() {
		return rangedShape;
	}
	public Lists.RangeType getRangedType() {
		return rangedType;
	}
	public Lists.RangeDelivery getRangedDelivery() {
		return rangedDelivery;
	}
	public int getRangedSize() {
		return rangedSize;
	}
	public int getMeleeReach() {
		return meleeReach;
	}
	public String getDescription() {
		return description;
	}
}
