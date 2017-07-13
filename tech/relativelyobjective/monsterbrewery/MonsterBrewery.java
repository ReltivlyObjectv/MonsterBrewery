package tech.relativelyobjective.monsterbrewery;

import javax.swing.SwingUtilities;
import tech.relativelyobjective.monsterbrewery.pieces.FrameMain;

/**
 *
 * @author ReltivlyObjectv
 * Contact: me@relativelyobjective.tech
 * 
 */
public class MonsterBrewery {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			FrameMain mainWindow = new FrameMain();
		});
	}
}
