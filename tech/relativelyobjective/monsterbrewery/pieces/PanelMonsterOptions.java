package tech.relativelyobjective.monsterbrewery.pieces;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;

/**
 *
 * @author ReltivlyObjectv
 * Contact: me@relativelyobjective.tech
 * 
 */
public class PanelMonsterOptions extends JPanel {
	private final PanelMonsterOverview overview;
	private final PanelMonsterAttributes attributes;
	private final PanelMonsterStatLists statLists;
	private final PanelMonsterMiscAddButtons miscButtons;
	
	public PanelMonsterOptions() {
		super.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		JPanel overviewElements = new JPanel();
			overviewElements.setLayout(new BorderLayout());
			overview = new PanelMonsterOverview();
			overviewElements.add(overview, BorderLayout.WEST);
			attributes = new PanelMonsterAttributes();
			overviewElements.add(attributes, BorderLayout.EAST);
		super.add(overviewElements, constraints);
		statLists = new PanelMonsterStatLists();
		constraints.gridy++;
		super.add(statLists, constraints);
		miscButtons = new PanelMonsterMiscAddButtons();
		constraints.gridy++;
		super.add(miscButtons, constraints);
	}
}
