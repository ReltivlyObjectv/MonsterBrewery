package tech.relativelyobjective.monsterbrewery.attributes;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import tech.relativelyobjective.monsterbrewery.AttributeHandler;
import tech.relativelyobjective.monsterbrewery.pieces.FrameMain;
import tech.relativelyobjective.monsterbrewery.resources.JLabelBold;
import tech.relativelyobjective.monsterbrewery.resources.Lists;

/**
 *
 * @author ReltivlyObjectv
 * Contact: me@relativelyobjective.tech
 * 
 */
public class Spellcaster implements Attribute {
	private class Spell implements Comparable {
		public int level;
		public String spell;
		public boolean castOnCombat;
		public Spell() {
			level = 0;
			spell = "";
			castOnCombat = false;
		}
		@Override
		public String toString() {
			return String.format("%d: %s%s", level, spell, castOnCombat ? "*":"");
		}
		public void editSpell(JDialog parent, List<Spell> list, JList window) {
			JDialog spellWindow = new JDialog(parent, "Spell", true);
				spellWindow.setPreferredSize(new Dimension(500,200));
				spellWindow.setSize(spellWindow.getPreferredSize());
				spellWindow.setMaximumSize(spellWindow.getPreferredSize());
				spellWindow.setMinimumSize(spellWindow.getPreferredSize());
				spellWindow.setLayout(new GridBagLayout());
				GridBagConstraints constraints = new GridBagConstraints();
				constraints.gridx = 0;
				constraints.gridy = 0;
				spellWindow.add(new JLabel("Level"), constraints);
				JSpinner levelSpinner = new JSpinner(new SpinnerNumberModel(0,0,9,1));
				levelSpinner.setValue(level);
				constraints.gridx++;
				spellWindow.add(levelSpinner, constraints);
				constraints.gridx = 0;
				constraints.gridy++;
				spellWindow.add(new JLabel("Spell"), constraints);
				constraints.gridx++;
				JComboBox spellComboBox = new JComboBox(getLevelSpells(level));
				spellComboBox.setEditable(true);
				spellComboBox.setPrototypeDisplayValue("LLLLLLLLLLLLLLLLLLLLLLLLLL");
				spellComboBox.setSelectedItem(spell);
				spellWindow.add(spellComboBox, constraints);
				constraints.gridx = 0;
				constraints.gridy++;
				spellWindow.add(new JLabel("Cast Before Combat?"), constraints);
				JCheckBox castOnCombatBox = new JCheckBox();
				castOnCombatBox.setSelected(castOnCombat);
				constraints.gridx++;
				spellWindow.add(castOnCombatBox, constraints);
				JButton saveButton = new JButton("Save Spell");
				constraints.gridx = 0;
				constraints.gridy++;
				constraints.gridwidth = 2;
				spellWindow.add(saveButton, constraints);
				//Listeners
				levelSpinner.addChangeListener((ChangeEvent e) -> {
					String currentlySelected = (String) spellComboBox.getSelectedItem();
					spellComboBox.removeAllItems();
					String[] spells = getLevelSpells((int) levelSpinner.getValue());
					for (String s : spells) {
						spellComboBox.addItem(s);
					}
					spellComboBox.setSelectedItem(currentlySelected);
				});
				saveButton.addActionListener((ActionEvent e) -> {
					if (spellComboBox.getSelectedItem() != null 
						&& !((String)spellComboBox.getSelectedItem()).contentEquals("")) {
						level = (int) levelSpinner.getValue();
						spell = (String) spellComboBox.getSelectedItem();
						castOnCombat = castOnCombatBox.isSelected();
						if (!list.contains(this)) {
							list.add(this);
						}
						refreshList(list, window);
						spellWindow.dispose();
					}
				});
			spellWindow.setVisible(true);
		}
		private String[] getLevelSpells(int level) {
			switch (level) {
				case 1:
					return Lists.LEVEL_1_SPELLS;
				case 2:
					return Lists.LEVEL_2_SPELLS;
				case 3:
					return Lists.LEVEL_3_SPELLS;
				case 4:
					return Lists.LEVEL_4_SPELLS;
				case 5:
					return Lists.LEVEL_5_SPELLS;
				case 6:
					return Lists.LEVEL_6_SPELLS;
				case 7:
					return Lists.LEVEL_7_SPELLS;
				case 8:
					return Lists.LEVEL_8_SPELLS;
				case 9:
					return Lists.LEVEL_9_SPELLS;
				default:
					return Lists.CANTRIPS;
			}
		}
		@Override
		public int compareTo(Object o) {
			return toString().compareTo(o.toString());
		}
	}
	private final int[] spellSlots;
	private List<Spell> spells;
	private Lists.Abilities spellcastingAbility;
	private int spellcasterLevel;
	private int spellsaveDC;
	private int toHit;
	private String spellClass;
	
	public Spellcaster() {
		this.spells = new LinkedList<>();
		spellcastingAbility = Lists.Abilities.INTELLIGENCE;
		spellcasterLevel = 1;
		this.spellSlots = calculateSpellLevels(spellcasterLevel);
		spellsaveDC = 10;
		toHit = 5;
		spellClass = Lists.CLASSES[11];
	}
	@Override
	public void editAttribute(FrameMain mainFrame) {
		JDialog editWindow = new JDialog(mainFrame, "Spellcaster", true);
			editWindow.setPreferredSize(new Dimension(575,425));
			editWindow.setSize(editWindow.getPreferredSize());
			editWindow.setMaximumSize(editWindow.getPreferredSize());
			editWindow.setMinimumSize(editWindow.getPreferredSize());
			editWindow.setLayout(new GridBagLayout());
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			//Class
			editWindow.add(new JLabel("Spellcasting Class"), constraints);
			JComboBox spellClassBox = new JComboBox(Lists.CLASSES);
			spellClassBox.setEditable(true);
			spellClassBox.setSelectedItem(spellClass);
			constraints.gridx++;
			editWindow.add(spellClassBox, constraints);
			//Ability
			constraints.gridx = 0;
			constraints.gridy++;
			editWindow.add(new JLabel("Spellcasting Ability"), constraints);
			JComboBox ability = new JComboBox(Lists.Abilities.values());
			ability.setSelectedItem(spellcastingAbility);
			constraints.gridx++;
			editWindow.add(ability, constraints);
			//Level
			constraints.gridx = 0;
			constraints.gridy++;
			editWindow.add(new JLabel("Spellcasting Level"), constraints);
			JSpinner level = new JSpinner(new SpinnerNumberModel(1,1,20,1));
			level.setValue(spellcasterLevel);
			constraints.gridx++;
			editWindow.add(level, constraints);
			//Save DC
			constraints.gridx = 0;
			constraints.gridy++;
			editWindow.add(new JLabel("Spell Save DC"), constraints);
			JSpinner save = new JSpinner(new SpinnerNumberModel(1,1,20,1));
			save.setValue(spellsaveDC);
			constraints.gridx++;
			editWindow.add(save, constraints);
			//To Hit
			constraints.gridx = 0;
			constraints.gridy++;
			editWindow.add(new JLabel("To Hit"), constraints);
			JSpinner toHitSpin = new JSpinner(new SpinnerNumberModel(0,-20,20,1));
			toHitSpin.setValue(toHit);
			constraints.gridx++;
			editWindow.add(toHitSpin, constraints);
			//Spell Slots
			JSpinner[] slotsInput = new JSpinner[9];
			JPanel slotsPanel = new JPanel();
				slotsPanel.setLayout(new GridBagLayout());
				GridBagConstraints slotConstr = new GridBagConstraints();
				slotConstr.gridy = 0;
				slotConstr.gridx = 0;
				slotConstr.gridwidth = 3;
				slotsPanel.add(new JLabelBold("Spell Slots"), slotConstr);
				slotConstr.gridy++;
				slotConstr.gridwidth = 1;
				slotConstr.ipadx = 20;
				for (int i = 0; i < 9; i++) {
					slotConstr.gridx = i % 3;
					int shift = ((i / 3) * 2) + 1;
					slotConstr.gridy = shift;
					slotsPanel.add(new JLabel(String.format("Level %d", i + 1)), slotConstr);
					slotConstr.gridy = 1 + shift;
					slotsInput[i] = new JSpinner(new SpinnerNumberModel(0,0,20,1));
					slotsInput[i].setValue(spellSlots[i]);
					slotsPanel.add(slotsInput[i], slotConstr);
				}
				constraints.gridwidth = 2;
				constraints.gridx = 0;
				constraints.gridy++;
			editWindow.add(slotsPanel, constraints);
			//Spell List
			List<Spell> tempSpells = new LinkedList<>(spells);
			constraints.gridx = 2;
			constraints.gridy = 0;
			editWindow.add(new JLabelBold("Spells"), constraints);
			constraints.gridy = 1;
			constraints.gridwidth = 2;
			constraints.gridheight = 5;
			JPanel spellPanel = new JPanel();
				spellPanel.setLayout(new GridBagLayout());
				GridBagConstraints spellConstr = new GridBagConstraints();
				spellConstr.gridx = 0;
				spellConstr.gridy = 0;
				spellConstr.gridwidth = 2;
				JList spellList = new JList();
				spellList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				spellList.setLayoutOrientation(JList.VERTICAL);
				spellList.setVisibleRowCount(-1);
				spellList.setListData(tempSpells.toArray());
				JScrollPane scroller = new JScrollPane(spellList);
				scroller.setPreferredSize(new Dimension(200,250));
				spellPanel.add(scroller, spellConstr);
				spellConstr.gridy = 1;
				spellConstr.gridwidth = 1;
				spellConstr.weightx = .5;
				JButton addSpell = new JButton("Add");
				spellPanel.add(addSpell, spellConstr);
				JButton deleteSpell = new JButton("Delete");
				spellConstr.gridx = 1;
				spellPanel.add(deleteSpell, spellConstr);
			editWindow.add(spellPanel, constraints);
			//Save Button
			JButton saveButton = new JButton("Save Spellcaster");
			constraints.gridx = 0;
			constraints.gridy = 6;
			constraints.gridheight = 1;
			constraints.gridwidth = 4;
			editWindow.add(saveButton, constraints);
			//Listeners
			level.addChangeListener((ChangeEvent e) -> {
				calculateSpellLevels((int) level.getValue(), slotsInput);
			});
			saveButton.addActionListener((ActionEvent e) -> {
				spellClass = (String) spellClassBox.getSelectedItem();
				spellcastingAbility = (Lists.Abilities) ability.getSelectedItem();
				spellcasterLevel = (int) level.getValue();
				spellsaveDC = (int) save.getValue();
				toHit = (int) toHitSpin.getValue();
				for (int i = 0; i < 9; i++) {
					spellSlots[i] = (int) slotsInput[i].getValue();
				}
				spells = tempSpells;
				if (!AttributeHandler.contains(this)) {
					AttributeHandler.addAttribute(this);
				}
				editWindow.dispose();
			});
			deleteSpell.addActionListener((ActionEvent e) -> {
				tempSpells.remove((Spell) spellList.getSelectedValue());
				refreshList(tempSpells, spellList);
			});
			addSpell.addActionListener((ActionEvent e) -> {
				Spell s = new Spell();
				s.editSpell(editWindow, tempSpells, spellList);
			});
			spellList.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					if (event.getClickCount() == 2) {
						Spell selected = (Spell) spellList.getSelectedValue();
						selected.editSpell(editWindow, tempSpells, spellList);
					}
				}
			});
		editWindow.setVisible(true);
	}
	@Override
	public String toString() {
		return String.format("Spellcaster: Level %d %s", spellcasterLevel, spellClass);
	}
	private void calculateSpellLevels(int level, JSpinner[] options) {
		int[] newLevels = calculateSpellLevels(level);
		for (int i = 0; i < 9; i++) {
			options[i].setValue(newLevels[i]);
		}
	}
	private void refreshList(List<Spell> list, JList display) {
		Collections.sort(list);
		display.setListData(list.toArray());
	}
	private int[] calculateSpellLevels(int level) {
		int spellSlots[] = new int[9];
		for (int i = 0; i < 9; i++) {
			spellSlots[i] = 0;
		}
		switch (level) {
			case 1:
				spellSlots[0] = 2;
				break;
			case 2:
				spellSlots[0] = 3;
				break;
			case 3:
				spellSlots[0] = 4;
				spellSlots[1] = 2;
				break;
			case 4:
				spellSlots[0] = 4;
				spellSlots[1] = 3;
				break;
			case 5:
				spellSlots[0] = 4;
				spellSlots[1] = 3;
				spellSlots[2] = 2;
				break;
			case 6:
				spellSlots[0] = 4;
				spellSlots[1] = 3;
				spellSlots[2] = 3;
				break;
			case 7:
				spellSlots[0] = 4;
				spellSlots[1] = 3;
				spellSlots[2] = 3;
				spellSlots[3] = 1;
				break;
			case 8:
				spellSlots[0] = 4;
				spellSlots[1] = 3;
				spellSlots[2] = 3;
				spellSlots[3] = 2;
				break;
			case 9:
				spellSlots[0] = 4;
				spellSlots[1] = 3;
				spellSlots[2] = 3;
				spellSlots[3] = 3;
				spellSlots[4] = 1;
				break;
			case 10:
				spellSlots[0] = 4;
				spellSlots[1] = 3;
				spellSlots[2] = 3;
				spellSlots[3] = 3;
				spellSlots[4] = 2;
				break;
			case 11:
			case 12:
				spellSlots[0] = 4;
				spellSlots[1] = 3;
				spellSlots[2] = 3;
				spellSlots[3] = 3;
				spellSlots[4] = 2;
				spellSlots[5] = 1;
				break;
			case 13:
			case 14:
				spellSlots[0] = 4;
				spellSlots[1] = 3;
				spellSlots[2] = 3;
				spellSlots[3] = 3;
				spellSlots[4] = 2;
				spellSlots[5] = 1;
				spellSlots[6] = 1;
				break;
			case 15:
			case 16:
				spellSlots[0] = 4;
				spellSlots[1] = 3;
				spellSlots[2] = 3;
				spellSlots[3] = 3;
				spellSlots[4] = 2;
				spellSlots[5] = 1;
				spellSlots[6] = 1;
				spellSlots[7] = 1;
				break;
			case 17:
				spellSlots[0] = 4;
				spellSlots[1] = 3;
				spellSlots[2] = 3;
				spellSlots[3] = 3;
				spellSlots[4] = 2;
				spellSlots[5] = 1;
				spellSlots[6] = 1;
				spellSlots[7] = 1;
				spellSlots[8] = 1;
				break;
			case 18:
				spellSlots[0] = 4;
				spellSlots[1] = 3;
				spellSlots[2] = 3;
				spellSlots[3] = 3;
				spellSlots[4] = 3;
				spellSlots[5] = 1;
				spellSlots[6] = 1;
				spellSlots[7] = 1;
				spellSlots[8] = 1;
				break;
			case 19:
				spellSlots[0] = 4;
				spellSlots[1] = 3;
				spellSlots[2] = 3;
				spellSlots[3] = 3;
				spellSlots[4] = 3;
				spellSlots[5] = 2;
				spellSlots[6] = 1;
				spellSlots[7] = 1;
				spellSlots[8] = 1;
				break;
			case 20:
				spellSlots[0] = 4;
				spellSlots[1] = 3;
				spellSlots[2] = 3;
				spellSlots[3] = 3;
				spellSlots[4] = 3;
				spellSlots[5] = 2;
				spellSlots[6] = 2;
				spellSlots[7] = 1;
				spellSlots[8] = 1;
				break;
		}
		return spellSlots;
	}
}
