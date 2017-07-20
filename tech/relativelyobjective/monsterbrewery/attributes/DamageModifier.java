package tech.relativelyobjective.monsterbrewery.attributes;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
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
public class DamageModifier implements Attribute {
	private DamageMods modifier;
	private ModifierType type;
	private String value;
	
	public enum ModifierType {
		CONDITION,
		DAMAGE
	}
	public enum DamageMods {
		IMMUNE,
		RESISTANT,
		VULNERABLE
	}
	public DamageModifier(DamageMods modifier, ModifierType type, String value) {
		if (modifier == null) {
			modifier = DamageMods.IMMUNE;
		} else {
			this.modifier = modifier;
		}
		if (type == null) {
			type = ModifierType.CONDITION;
		} else {
			this.type = type;
		}
		if (value == null) {
			value = "";
		} else {
			this.value = value;
		}
	}
	@Override
	public void editAttribute(FrameMain mainFrame) {
		JDialog editWindow;
			editWindow = new JDialog(mainFrame, "Damage/Condition Modifier", true);
			editWindow.setPreferredSize(new Dimension(500,100));
			editWindow.setSize(editWindow.getPreferredSize());
			editWindow.setMaximumSize(editWindow.getPreferredSize());
			editWindow.setMinimumSize(editWindow.getPreferredSize());
			editWindow.setLayout(new GridBagLayout());
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridy = 0;
			constraints.gridx = 0;
			editWindow.add(new JLabel("Type"));
			constraints.gridx++;
			editWindow.add(new JLabel("Modifier"));
			constraints.gridx++;
			editWindow.add(new JLabel("Value"));
			constraints.gridx = 0;
			constraints.gridy++;
			JComboBox typeBox = new JComboBox(ModifierType.values());
			if (type != null) {
				typeBox.setSelectedItem(type);
			}
			editWindow.add(typeBox, constraints);
			JComboBox modBox = new JComboBox(DamageMods.values());
			constraints.gridx++;
			if (modifier != null) {
				modBox.setSelectedItem(modifier);
			}
			editWindow.add(modBox, constraints);
			JComboBox valueBox;
			if (typeBox.getSelectedItem() == ModifierType.CONDITION) {
				valueBox = new JComboBox(Lists.CONDITIONS);
			} else {
				valueBox = new JComboBox(Lists.DAMAGE_TYPES);
			}
			valueBox.setEditable(true);
			constraints.gridx++;
			if (value != null) {
				valueBox.setSelectedItem(value);
			}
			editWindow.add(valueBox, constraints);
			JButton submitButton = new JButton("Save");
			constraints.gridx++;
			editWindow.add(submitButton, constraints);
			//Listeners
			typeBox.addActionListener((ActionEvent e) -> {
				valueBox.removeAllItems();
				if (typeBox.getSelectedItem() == ModifierType.CONDITION) {
					for (int i = 0; i < Lists.CONDITIONS.length; i++) {
						valueBox.addItem(Lists.CONDITIONS[i]);
					}
				} else {
					for (int i = 0; i < Lists.DAMAGE_TYPES.length; i++) {
						valueBox.addItem(Lists.DAMAGE_TYPES[i]);
					}
				}
			});
			submitButton.addActionListener((ActionEvent e) -> {
				if (valueBox.getSelectedItem() != null 
					&& !((String)valueBox.getSelectedItem()).contentEquals("")) {
					modifier = (DamageMods) modBox.getSelectedItem();
					type = (ModifierType) typeBox.getSelectedItem();
					value = (String) valueBox.getSelectedItem();
					if (!AttributeHandler.contains(this)) {
						AttributeHandler.addAttribute(this);
					}
					editWindow.dispose();
				}
			});
		SwingUtilities.invokeLater(() -> {
			editWindow.setVisible(true);
		});
	}
	@Override
	public String toString() {
		String returnValue;
		//Type
		if (type == ModifierType.CONDITION) {
			returnValue = "Condition ";
		} else {
			returnValue = "Damage ";
		}
		switch (modifier) {
			case IMMUNE:
				returnValue += "Immunity: ";
				break;
			case RESISTANT:
				returnValue += "Resistance: ";
				break;
			default:
				returnValue += "Vulnerability: ";
				break;
		}
		returnValue += value;
		return returnValue;
	}
	public DamageMods getModifier() {
		return modifier;
	}
	public ModifierType getType() {
		return type;
	}
	public String getValue() {
		return value;
	}
	
}
