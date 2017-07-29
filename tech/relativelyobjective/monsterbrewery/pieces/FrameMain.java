package tech.relativelyobjective.monsterbrewery.pieces;

import com.apple.eawt.Application;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import tech.relativelyobjective.monsterbrewery.image.ImageRenderer;

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
		setIcon();
		super.setVisible(true);
	}
	public PanelMonsterOptions getMonsterOptions() {
		return monsterOptions;
	}
	private void setIcon() {
	
		URL url = ImageRenderer.class.getResource("/coffee.png");
		Image img;
		try {
			img = (Image) ImageIO.read(url);
		} catch (IOException ex) {
			Logger.getLogger(ImageRenderer.class.getName()).log(Level.SEVERE, null, ex);
			return;
		}
		if (System.getProperty("os.name", "generic").toLowerCase().contains("mac")) {
			//Mac
			Application.getApplication().setDockIconImage(img);
		} else {
			//Not Mac
			super.setIconImage(img);
		}
	}
}
