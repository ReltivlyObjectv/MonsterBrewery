package tech.relativelyobjective.monsterbrewery.pieces;

import java.awt.event.ActionEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author ReltivlyObjectv
 * Contact: me@relativelyobjective.tech
 * 
 */
public class MenuBar extends JMenuBar {
	private final JMenu dropDown;
	private final JMenuItem newFile;
	private final JMenuItem save;
	private final JMenuItem saveAs;
	private final JMenuItem load;
	
	public MenuBar() {
		dropDown = new JMenu("File");
			newFile = new JMenuItem("New Monster");
			newFile.addActionListener((ActionEvent e) -> {
				newMonster();
			});
			dropDown.add(newFile);
			save = new JMenuItem("Save");
			save.addActionListener((ActionEvent e) -> {
				saveMonster();
			});
			dropDown.add(save);
			saveAs = new JMenuItem("Save As...");
			saveAs.addActionListener((ActionEvent e) -> {
				saveMonsterAs();
			});
			dropDown.add(saveAs);
			load = new JMenuItem("Load...");
			load.addActionListener((ActionEvent e) -> {
				loadMonster();
			});
			dropDown.add(load);
		super.add(dropDown);
	}
	private void newMonster() {
		System.out.printf("Not implemented!\n");
	}
	private void saveMonster() {
		System.out.printf("Not implemented!\n");
	}
	private void saveMonsterAs() {
		System.out.printf("Not implemented!\n");
	}
	private void loadMonster() {
		System.out.printf("Not implemented!\n");
	}
}
