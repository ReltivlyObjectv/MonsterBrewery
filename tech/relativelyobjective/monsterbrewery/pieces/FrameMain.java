package tech.relativelyobjective.monsterbrewery.pieces;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author ReltivlyObjectv
 * Contact: me@relativelyobjective.tech
 * 
 */
public class FrameMain extends JFrame {
	private final PanelMonsterOptions monsterOptions;
	private final MenuBar menu;
	
	public FrameMain() {
		super("Monster Brewery");
		super.setLayout(new BorderLayout());
		monsterOptions = new PanelMonsterOptions();
		menu = new MenuBar(this);
		super.add(monsterOptions, BorderLayout.NORTH);
		super.add(menu);
		super.setJMenuBar(menu);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setPreferredSize(new Dimension(850, 580));
		super.setMinimumSize(super.getPreferredSize());
		super.setMaximumSize(super.getPreferredSize());
		super.setSize(super.getPreferredSize());
		super.setVisible(true);
	}
	public PanelMonsterOptions getMonsterOptions() {
		return monsterOptions;
	}
}
