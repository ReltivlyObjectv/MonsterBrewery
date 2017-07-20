package tech.relativelyobjective.monsterbrewery.pieces;

import java.awt.Event;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import tech.relativelyobjective.monsterbrewery.image.ImageRenderer;

/**
 *
 * @author ReltivlyObjectv
 * Contact: me@relativelyobjective.tech
 * 
 */
public class MenuBar extends JMenuBar {
	private final FileMenu fileMenu;
	
	private class FileMenu extends JMenu {
		private final JMenuItem newFile;
		private final JMenuItem save;
		private final JMenuItem saveAs;
		private final JMenuItem load;
		private final JMenuItem render;
		
		public FileMenu(String title) {
			super(title);
			newFile = new JMenuItem("New Monster");
			newFile.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_N, 
				Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
			newFile.addActionListener((ActionEvent e) -> {
				newMonster();
			});
			super.add(newFile);
			save = new JMenuItem("Save");
			save.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_S, 
				Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
			save.addActionListener((ActionEvent e) -> {
				saveMonster();
			});
			super.add(save);
			saveAs = new JMenuItem("Save As...");
			saveAs.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_S, 
				Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()
				| InputEvent.SHIFT_DOWN_MASK));
			saveAs.addActionListener((ActionEvent e) -> {
				saveMonsterAs();
			});
			super.add(saveAs);
			load = new JMenuItem("Load...");
			load.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_O, 
				Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
			load.addActionListener((ActionEvent e) -> {
				loadMonster();
			});
			super.add(load);
			super.addSeparator();
			render = new JMenuItem("Render...");
			render.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_R, 
				Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
			render.addActionListener((ActionEvent e) -> {
				renderMonster();
			});
			super.add(render);
		}
	}
	
	private final FrameMain mainFrame;
	
	public MenuBar(FrameMain mainF) {
		mainFrame = mainF;
		fileMenu = new FileMenu("File");
		super.add(fileMenu);
	}
	private void newMonster() {
		System.out.printf("Not implemented: New\n");
	}
	private void saveMonster() {
		System.out.printf("Not implemented: Save\n");
	}
	private void saveMonsterAs() {
		System.out.printf("Not implemented: Save As\n");
	}
	private void loadMonster() {
		System.out.printf("Not implemented: Load\n");
	}
	private void renderMonster() {
		ImageRenderer.renderImage(mainFrame);
	}
}
