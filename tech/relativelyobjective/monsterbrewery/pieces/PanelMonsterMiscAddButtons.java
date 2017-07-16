package tech.relativelyobjective.monsterbrewery.pieces;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import tech.relativelyobjective.monsterbrewery.attributes.DamageModifier;
import tech.relativelyobjective.monsterbrewery.attributes.Spellcaster;
import tech.relativelyobjective.monsterbrewery.resources.JLabelBold;

/**
 *
 * @author ReltivlyObjectv
 * Contact: me@relativelyobjective.tech
 * 
 */
public class PanelMonsterMiscAddButtons extends JPanel {
	private final PanelMonsterSenses senses;
	private final PanelMonsterLanguages lang;
	private final PanelMonsterSkills skills;
	
	public PanelMonsterMiscAddButtons() {
		super.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		//Labels
		constraints.gridy = 0;
		constraints.gridx = 0;
		super.add(new JLabelBold("Senses"), constraints);
		constraints.gridx = 1;
		super.add(new JLabelBold("Languages"), constraints);
		//Panels
		constraints.gridy++;
		senses = new PanelMonsterSenses();
		constraints.gridx = 0;
		super.add(senses, constraints);
		lang = new PanelMonsterLanguages();
		constraints.gridx = 1;
		super.add(lang, constraints);
		constraints.gridx = 0;
		constraints.gridy++;
		skills = new PanelMonsterSkills();
		constraints.gridy++;
		super.add(skills, constraints);
		JPanel buttonCluster = new JPanel();
			TitledBorder clusterBorder = BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(EtchedBorder.LOWERED)
			);
			buttonCluster.setBorder(clusterBorder);
			buttonCluster.setLayout(new GridBagLayout());
			GridBagConstraints buttonCons = new GridBagConstraints();
			buttonCons.gridx = 0;
			buttonCons.gridy = 0;
			JButton damageMod = new JButton("Add Damage Modifier");
			damageMod.addActionListener((ActionEvent e) -> {
				addDamageModification();
			});
			buttonCluster.add(damageMod, buttonCons);
			buttonCons.gridx++;
			JButton ability = new JButton("Add Ability");
			ability.addActionListener((ActionEvent e) -> {
				addAbility();
			});
			buttonCluster.add(ability, buttonCons);
			buttonCons.gridy++;
			buttonCons.gridx = 0;
			JButton action = new JButton("Add Action");
			action.addActionListener((ActionEvent e) -> {
				addAction();
			});
			buttonCluster.add(action, buttonCons);
			buttonCons.gridx++;
			JButton spellcaster = new JButton("Add Spellcaster");
			spellcaster.addActionListener((ActionEvent e) -> {
				addSpellcaster();
			});
			buttonCluster.add(spellcaster, buttonCons);
			buttonCons.gridy++;
			buttonCons.gridx = 0;
			buttonCons.gridwidth = 2;
			JButton legendary = new JButton("Add Legendary");
			legendary.addActionListener((ActionEvent e) -> {
				addLegendary();
			});
			buttonCluster.add(legendary, buttonCons);
		constraints.gridx++;
		super.add(buttonCluster, constraints);
	}
	private FrameMain getMainFrame() {
		Container parent = super.getParent();
		while (!(parent instanceof FrameMain)) {
			if (parent == null) {
				return null;
			}
			parent = parent.getParent();
		}
		return (FrameMain) parent;
	}
	private void addDamageModification() {
		DamageModifier addMe = new DamageModifier(null,null,null);
		addMe.editAttribute(getMainFrame());
	}
	private void addAbility() {
		//TODO
	}
	private void addAction() {
		//TODO
	}
	private void addLegendary() {
		//TODO
	}
	private void addSpellcaster() {
		Spellcaster addMe = new Spellcaster();
		addMe.editAttribute(getMainFrame());
	}
}
