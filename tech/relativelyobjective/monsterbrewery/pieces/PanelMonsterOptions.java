package tech.relativelyobjective.monsterbrewery.pieces;

import java.awt.BorderLayout;
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
	
	public PanelMonsterOptions() {
		super.setLayout(new BorderLayout());
		JPanel overviewElements = new JPanel();
			overviewElements.setLayout(new BorderLayout());
			overview = new PanelMonsterOverview();
			overviewElements.add(overview, BorderLayout.WEST);
			attributes = new PanelMonsterAttributes();
			overviewElements.add(attributes, BorderLayout.EAST);
		super.add(overviewElements, BorderLayout.NORTH);
	}
}
