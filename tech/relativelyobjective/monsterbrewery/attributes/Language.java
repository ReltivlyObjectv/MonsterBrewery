package tech.relativelyobjective.monsterbrewery.attributes;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import tech.relativelyobjective.monsterbrewery.pieces.FrameMain;

/**
 *
 * @author ReltivlyObjectv
 * Contact: me@relativelyobjective.tech
 * 
 */
public class Language implements Attribute {
	private String lang;
	
	public Language() {
		lang = "";
	}
	public Language(String value) {
		lang = value;
	}
	@Override
	public void editAttribute(FrameMain mainFrame) {
		JDialog editWindow = new JDialog(mainFrame, "Edit Language", true);
			editWindow.setPreferredSize(new Dimension(400,100));
			editWindow.setSize(editWindow.getPreferredSize());
			editWindow.setMaximumSize(editWindow.getPreferredSize());
			editWindow.setMinimumSize(editWindow.getPreferredSize());
			editWindow.setLayout(new GridBagLayout());
			GridBagConstraints constraints = new GridBagConstraints();
			JTextField text = new JTextField(20);
			text.setText(lang);
			constraints.gridy = 0;
			constraints.gridx = 0;
			editWindow.add(text, constraints);
			JButton saveButton = new JButton("Save Language");
			saveButton.addActionListener((ActionEvent e) -> {
				if (!text.getText().isEmpty()) {
					lang = text.getText();
					editWindow.dispose();
				}
			});
			constraints.gridx++;
			
			editWindow.add(saveButton, constraints);
		SwingUtilities.invokeLater(() -> {
			editWindow.setVisible(true);
		});
	}
	@Override
	public String toString() {
		return String.format("Language: %s", lang);
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String l) {
		lang = l;
	}
}
