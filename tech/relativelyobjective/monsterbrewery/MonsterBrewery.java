package tech.relativelyobjective.monsterbrewery;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import tech.relativelyobjective.monsterbrewery.pieces.FrameMain;

/**
 *
 * @author ReltivlyObjectv
 * Contact: me@relativelyobjective.tech
 * 
 */
public class MonsterBrewery {
	public static void main(String[] args) {
		//Attach JMenu to Apple Menu Bar
		try {
				System.setProperty("apple.laf.useScreenMenuBar", "true");
				System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Test");
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(ClassNotFoundException e) {
				System.out.println("ClassNotFoundException: " + e.getMessage());
        } catch(InstantiationException e) {
				System.out.println("InstantiationException: " + e.getMessage());
        } catch(IllegalAccessException e) {
				System.out.println("IllegalAccessException: " + e.getMessage());
        } catch(UnsupportedLookAndFeelException e) {
				System.out.println("UnsupportedLookAndFeelException: " + e.getMessage());
        }
		//Create main frame
		SwingUtilities.invokeLater(() -> {
			FrameMain mainWindow = new FrameMain();
		});
	}
}
