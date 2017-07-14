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
	
	public FrameMain() {
		super("Monster Brewery");
		super.setLayout(new BorderLayout());
		previewWindow = new PanelPreview();
		monsterOptions = new PanelMonsterOptions();
		super.add(previewWindow, BorderLayout.EAST);
		super.add(monsterOptions, BorderLayout.WEST);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setPreferredSize(new Dimension(1000, 500));
		super.setMinimumSize(super.getPreferredSize());
		super.setMaximumSize(super.getPreferredSize());
		super.setSize(super.getPreferredSize());
		super.setVisible(true);
	}
}
