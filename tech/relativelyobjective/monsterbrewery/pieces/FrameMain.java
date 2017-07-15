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
	private final PanelPreview previewWindow;
	private final PanelMonsterOptions monsterOptions;
	private final MenuBar menu;
	
	public FrameMain() {
		super("Monster Brewery");
		super.setLayout(new BorderLayout());
		previewWindow = new PanelPreview();
		monsterOptions = new PanelMonsterOptions();
		menu = new MenuBar();
		super.add(previewWindow, BorderLayout.CENTER);
		super.add(monsterOptions, BorderLayout.WEST);
		super.add(menu);
		super.setJMenuBar(menu);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setPreferredSize(new Dimension(1000, 550));
		super.setMinimumSize(super.getPreferredSize());
		super.setMaximumSize(super.getPreferredSize());
		super.setSize(super.getPreferredSize());
		super.setVisible(true);
	}
}
