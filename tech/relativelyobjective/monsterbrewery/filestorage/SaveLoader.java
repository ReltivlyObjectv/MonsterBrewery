package tech.relativelyobjective.monsterbrewery.filestorage;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import static tech.relativelyobjective.monsterbrewery.filestorage.SaveCreator.setFileLocation;
import tech.relativelyobjective.monsterbrewery.pieces.FrameMain;

/**
 *
 * @author ReltivlyObjectv
 * Contact: me@relativelyobjective.tech
 * 
 */
public class SaveLoader {
	public static void loadSave(File saveFile, FrameMain mainFrame) {
		//Mark as save file
		SaveCreator.setFileLocation(saveFile, mainFrame);
		//TODO
	}
	public static void openLoadPrompt(FrameMain mainFrame) {
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("MonsterBrewery Files", "monsterbrewery");
		fileChooser.setFileFilter(filter);
		if (fileChooser.showOpenDialog(mainFrame) == JFileChooser.APPROVE_OPTION) {
			File saveLocation = fileChooser.getSelectedFile();
			setFileLocation(saveLocation, mainFrame);
			loadSave(saveLocation, mainFrame);
		}
	}
}
