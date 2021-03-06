package tech.relativelyobjective.monsterbrewery.attributes;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import tech.relativelyobjective.monsterbrewery.pieces.FrameMain;

/**
 *
 * @author ReltivlyObjectv
 * Contact: me@relativelyobjective.tech
 * 
 */
public class Sense implements Attribute {
	private String sense;
	private int magnitude;
	
	public Sense(String value, int mag) {
		sense = value;
		magnitude = mag;
	}
	public Sense() {
		sense = "";
		magnitude = 0;
	}
	@Override
	public void editAttribute(FrameMain mainFrame) {
		JDialog editWindow = new JDialog(mainFrame, "Edit Sense", true);
			editWindow.setPreferredSize(new Dimension(400,100));
			editWindow.setSize(editWindow.getPreferredSize());
			editWindow.setMaximumSize(editWindow.getPreferredSize());
			editWindow.setMinimumSize(editWindow.getPreferredSize());
			editWindow.setLayout(new GridBagLayout());
			GridBagConstraints constraints = new GridBagConstraints();
			JTextField text = new JTextField(15);
			text.setText(sense);
			constraints.gridy = 0;
			constraints.gridx = 0;
			editWindow.add(text, constraints);
			JSpinner magSpinner = new JSpinner(new SpinnerNumberModel(0,0,1000,5));
			magSpinner.setValue(magnitude);
			constraints.gridx++;
			editWindow.add(magSpinner, constraints);
			JButton saveButton = new JButton("Save Sense");
			saveButton.addActionListener((ActionEvent e) -> {
				if (!text.getText().isEmpty()) {
					sense = text.getText();
					magnitude = (int) magSpinner.getValue();
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
		return String.format("Sense: %s (%d ft.)", sense, magnitude);
	}
	public String getSense() {
		return sense;
	}
	public int getMagnitude() {
		return magnitude;
	}
	public void setSense(String s) {
		sense = s;
	}
	public void setMagnitude(int m) {
		magnitude = m;
	}
	public void setMagnitude(String m) {
		try {
			setMagnitude(Integer.parseInt(m));
		} catch (NumberFormatException e) {
			//Do Nothing
		}
	}
}
