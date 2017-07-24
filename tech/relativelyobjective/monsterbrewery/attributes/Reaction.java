package tech.relativelyobjective.monsterbrewery.attributes;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import tech.relativelyobjective.monsterbrewery.resources.AttributeHandler;
import tech.relativelyobjective.monsterbrewery.pieces.FrameMain;
import tech.relativelyobjective.monsterbrewery.resources.JLabelBold;

/**
 *
 * @author ReltivlyObjectv
 * Contact: me@relativelyobjective.tech
 * 
 */
public class Reaction implements Attribute {
	private String name;
	private String description;
	
	public Reaction() {
		name = "";
		description = "";
	}
	@Override
	public void editAttribute(FrameMain mainFrame) {
		JDialog editWindow;
			editWindow = new JDialog(mainFrame, "Reaction", true);
			editWindow.setPreferredSize(new Dimension(325,350));
			editWindow.setSize(editWindow.getPreferredSize());
			editWindow.setMaximumSize(editWindow.getPreferredSize());
			editWindow.setMinimumSize(editWindow.getPreferredSize());
			editWindow.setLayout(new GridBagLayout());
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridy = 0;
			constraints.gridx = 0;
			editWindow.add(new JLabelBold("Name"), constraints);
			JTextField nameBox = new JTextField(20);
			nameBox.setText(name);
			constraints.gridy++;
			editWindow.add(nameBox, constraints);
			constraints.gridy++;
			editWindow.add(new JLabelBold("Description"), constraints);
			JTextArea descriptionBox = new JTextArea(10, 10);
			descriptionBox.setText(description);
			descriptionBox.setLineWrap(true);
			descriptionBox.setWrapStyleWord(true);
			descriptionBox.setBorder(nameBox.getBorder());
			JScrollPane scroller = new JScrollPane(descriptionBox);
			scroller.setPreferredSize(new Dimension(275,170));
			constraints.gridy++;
			editWindow.add(scroller, constraints);
			JButton saveButton = new JButton("Save Reaction");
			constraints.gridy++;
			editWindow.add(saveButton, constraints);
			//Listener
			saveButton.addActionListener((ActionEvent e) -> {
				if (!nameBox.getText().equals("") &&
					!descriptionBox.getText().equals("")
					) {
					name = nameBox.getText();
					description = descriptionBox.getText();
					if (!AttributeHandler.contains(this)) {
						AttributeHandler.addAttribute(this);
					}
					editWindow.dispose();
				}
			});
		SwingUtilities.invokeLater(() -> {
			editWindow.setVisible(true);
		});
	}
	@Override
	public String toString() {
		return String.format("Reaction: %s", name);
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public void setName(String n) {
		name = n;
	}
	public void setDescription(String d) {
		description = d;
	}
}
