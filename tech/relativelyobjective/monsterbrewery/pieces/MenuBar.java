package tech.relativelyobjective.monsterbrewery.pieces;

import com.apple.mrj.MRJApplicationUtils;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import tech.relativelyobjective.monsterbrewery.MonsterBrewery;
import tech.relativelyobjective.monsterbrewery.filestorage.SaveCreator;
import tech.relativelyobjective.monsterbrewery.filestorage.SaveLoader;
import tech.relativelyobjective.monsterbrewery.filestorage.SaveWiper;
import tech.relativelyobjective.monsterbrewery.image.ImageRenderer;

/**
 *
 * @author ReltivlyObjectv
 * Contact: me@relativelyobjective.tech
 * 
 */
public class MenuBar extends JMenuBar {
	private final FileMenu fileMenu;
	private boolean isAboutOpen = false;
	
	private class FileMenu extends JMenu {
		private final JMenuItem newFile;
		private final JMenuItem save;
		private final JMenuItem saveAs;
		private final JMenuItem load;
		private final JMenuItem render;
		private final JMenuItem renderToFile;
		private final JMenuItem about;
		
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
			render = new JMenuItem("Render");
			render.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_R, 
				Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
			render.addActionListener((ActionEvent e) -> {
				renderMonster();
			});
			super.add(render);
			renderToFile = new JMenuItem("Render to File");
			renderToFile.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_R, 
				Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()
				| InputEvent.SHIFT_DOWN_MASK));
			renderToFile.addActionListener((ActionEvent e) -> {
				renderToFile();
			});
			super.add(renderToFile);
			about = new JMenuItem("About");
			if (MonsterBrewery.isMac()) {
			//OS X About Menu
				try {
					MRJApplicationUtils.registerAboutHandler(() -> {
						showAboutMenu();
					});
				} catch (Exception error) {
					//If an exception is encountered, use default about menu
					about.addActionListener((ActionEvent e) -> {
						showAboutMenu();
					});
					super.addSeparator();
					super.add(about);
				}
			} else {
				//Other About Menu
				about.addActionListener((ActionEvent e) -> {
					showAboutMenu();
				});
				super.addSeparator();
				super.add(about);
			}
		}
	}
	
	private final FrameMain mainFrame;
	
	public MenuBar(FrameMain mainF) {
		mainFrame = mainF;
		fileMenu = new FileMenu("File");
		super.add(fileMenu);
	}
	private void newMonster() {
		SaveWiper.resetProgram(mainFrame);
	}
	private void saveMonster() {
		SaveCreator.saveToLocation(mainFrame);
	}
	private void saveMonsterAs() {
		SaveCreator.openSavePrompt(mainFrame);
	}
	private void loadMonster() {
		SaveLoader.openLoadPrompt(mainFrame);
	}
	private void renderMonster() {
		ImageRenderer.renderImage(mainFrame);
	}
	private void renderToFile() {
		ImageRenderer.renderToFile();
	}
	private void showAboutMenu() {
		if (isAboutOpen) {
			//Window already open
		} else {
			isAboutOpen = true;
			JDialog aboutMenu = new JDialog(mainFrame, "About MonsterBrewery");
			aboutMenu.setAlwaysOnTop(true);
			if (MonsterBrewery.isMac()) {
				aboutMenu.setPreferredSize(new Dimension(350,400));
			} else {
				aboutMenu.setPreferredSize(new Dimension(350,490));
			}
			aboutMenu.setMinimumSize(aboutMenu.getPreferredSize());
			aboutMenu.setMaximumSize(aboutMenu.getPreferredSize());
			aboutMenu.setLayout(new GridBagLayout());
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			//About Image
			URL coffeeURL = ImageRenderer.class.getResource("/coffee-150px.png");
			JLabel img = null;
			try {
				BufferedImage buff = (BufferedImage) ImageIO.read(coffeeURL);
				img = new JLabel(new ImageIcon(buff));
				img.setMaximumSize(new Dimension(50,50));
			} catch (IOException ex) {
				Logger.getLogger(ImageRenderer.class.getName()).log(Level.SEVERE, null, ex);
			}
			if (img != null) {
				aboutMenu.add(img, constraints);
				constraints.gridy++;
				aboutMenu.add(getAboutEmptySpace(), constraints);
				constraints.gridy++;
			}
			//Version
			JLabel versionHeader = new JLabel("Version");
			versionHeader.setFont(new Font(versionHeader.getFont().getFontName(), Font.BOLD, 14));
			aboutMenu.add(versionHeader, constraints);
			constraints.gridy++;
			JLabel version = new JLabel("1.9.1");
			version.setFont(new Font(version.getFont().getFontName(), Font.PLAIN, 12));
			aboutMenu.add(version, constraints);
			constraints.gridy++;
			aboutMenu.add(getAboutEmptySpace(), constraints);
			constraints.gridy++;
			//Creator
			JLabel creatorHeader = new JLabel("Created By");
			creatorHeader.setFont(new Font(creatorHeader.getFont().getFontName(), Font.BOLD, 14));
			aboutMenu.add(creatorHeader, constraints);
			constraints.gridy++;
			JLabel creator = new JLabel("ReltivlyObjectv");
			creator.setFont(new Font(creator.getFont().getFontName(), Font.PLAIN, 12));
			aboutMenu.add(creator, constraints);
			constraints.gridy++;
			aboutMenu.add(getAboutEmptySpace(), constraints);
			constraints.gridy++;
			//Email
			JLabel creatorEmailHeader = new JLabel("Contact Email");
			creatorEmailHeader.setFont(new Font(creatorEmailHeader.getFont().getFontName(), Font.BOLD, 14));
			aboutMenu.add(creatorEmailHeader, constraints);
			constraints.gridy++;
			String creatorEmailAddress = "me@relativelyobjective.tech";
			JLabel creatorEmail = new JLabel(
				"<html><a href=\"mailto:"+creatorEmailAddress+"\">"+creatorEmailAddress+"</a></html>"
			);
			creatorEmail.setCursor(new Cursor(Cursor.HAND_CURSOR));
			creatorEmail.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
						try {
							Desktop.getDesktop().browse(new URI("mailto:"+creatorEmailAddress));
						} catch (URISyntaxException | IOException ex) {
								//It looks like there's a problem
						}
				}
			});
			aboutMenu.add(creatorEmail, constraints);
			constraints.gridy++;
			aboutMenu.add(getAboutEmptySpace(), constraints);
			constraints.gridy++;
			//Project Location
			JLabel projectLocationHeader = new JLabel("Project Location");
			projectLocationHeader.setFont(new Font(projectLocationHeader.getFont().getFontName(), Font.BOLD, 14));
			aboutMenu.add(projectLocationHeader, constraints);
			constraints.gridy++;
			String projectURL = "https://gitlab.com/ReltivlyObjectv/MonsterBrewery";
			JLabel projectLocation = new JLabel(
				"<html><a href=\""+projectURL+"\">"+projectURL+"</a></html>"
			);
			projectLocation.setCursor(new Cursor(Cursor.HAND_CURSOR));
			projectLocation.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
						try {
							Desktop.getDesktop().browse(new URI(projectURL));
						} catch (URISyntaxException | IOException ex) {
								//It looks like there's a problem
						}
				}
			});
			projectLocation.setFont(new Font(projectLocation.getFont().getFontName(), Font.PLAIN, 12));
			aboutMenu.add(projectLocation, constraints);
			constraints.gridy++;
			aboutMenu.add(getAboutEmptySpace(), constraints);
			constraints.gridy++;
			//Donations
			JLabel donationHeader = new JLabel("Donate");
			donationHeader.setFont(new Font(donationHeader.getFont().getFontName(), Font.BOLD, 14));
			aboutMenu.add(donationHeader, constraints);
			constraints.gridy++;
			String donateAddress = "https://gitlab.com/ReltivlyObjectv/MonsterBrewery#user-content-donate";
			JLabel donate = new JLabel(
				"<html><a href=\""+donateAddress+"\">I accept Bitcoin, Dogecoin, and Monero!</a></html>"
			);
			donate.setCursor(new Cursor(Cursor.HAND_CURSOR));
			donate.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
						try {
							Desktop.getDesktop().browse(new URI(donateAddress));
						} catch (URISyntaxException | IOException ex) {
								//It looks like there's a problem
						}
				}
			});
			aboutMenu.add(donate, constraints);
			constraints.gridy++;
			//Finished, add listener and set visible
			aboutMenu.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					isAboutOpen = false;
				}
			});
			aboutMenu.setVisible(true);
		}
	}
	private JPanel getAboutEmptySpace() {
		JPanel returnMe = new JPanel();
		returnMe.setMinimumSize(new Dimension(1,10));
		return returnMe;
	}
}
