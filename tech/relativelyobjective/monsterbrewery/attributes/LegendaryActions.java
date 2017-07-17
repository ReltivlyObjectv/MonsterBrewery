package tech.relativelyobjective.monsterbrewery.attributes;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import tech.relativelyobjective.monsterbrewery.AttributeHandler;
import tech.relativelyobjective.monsterbrewery.pieces.FrameMain;
import tech.relativelyobjective.monsterbrewery.resources.JLabelBold;

/**
 *
 * @author ReltivlyObjectv
 * Contact: me@relativelyobjective.tech
 * 
 */
public class LegendaryActions implements Attribute {
	private class Action {
		public String name;
		public String text;
		public Action() {
			name = "";
			text = "";
		}
		public void editAction(JDialog parent, JList actionList, List<Action> tempActions) {
			JDialog editWindow = new JDialog(parent, "Action", true);
				editWindow.setPreferredSize(new Dimension(325,350));
				editWindow.setSize(editWindow.getPreferredSize());
				editWindow.setMaximumSize(editWindow.getPreferredSize());
				editWindow.setMinimumSize(editWindow.getPreferredSize());
				editWindow.setLayout(new GridBagLayout());
				GridBagConstraints constraints = new GridBagConstraints();
				constraints.gridx = 0;
				constraints.gridy = 0;
				constraints.ipady = 5;
				editWindow.add(new JLabelBold("Name"), constraints);
				JTextField nameBox = new JTextField(20);
				nameBox.setText(name);
				constraints.gridy++;
				editWindow.add(nameBox, constraints);
				constraints.gridy++;
				editWindow.add(new JLabelBold("Description"), constraints);
				JTextArea textBox = new JTextArea(10, 10);
				textBox.setText(text);
				textBox.setLineWrap(true);
				textBox.setWrapStyleWord(true);
				textBox.setBorder(nameBox.getBorder());
				JScrollPane scroller = new JScrollPane(textBox);
				scroller.setPreferredSize(new Dimension(275,170));
				constraints.gridy++;
				editWindow.add(scroller, constraints);
				JButton saveButton = new JButton("Save Action");
				constraints.gridy++;
				constraints.ipady = 0;
				editWindow.add(saveButton, constraints);
				//Listener
				saveButton.addActionListener((ActionEvent e) -> {
					if (!nameBox.getText().equals("") &&
						!textBox.getText().equals("")
						) {
						name = nameBox.getText();
						text = textBox.getText();
						if (!tempActions.contains(this)) {
							tempActions.add(this);
						}
						actionList.setListData(tempActions.toArray());
						editWindow.dispose();
					}
				});
			editWindow.setVisible(true);
		}
		@Override
		public String toString() {
			return name;
		}
	}
	private List<Action> actions;
	private int usesPerCycle;
	public LegendaryActions() {
		actions = new LinkedList<>();
		usesPerCycle = 3;
	}
	@Override
	public void editAttribute(FrameMain mainFrame) {
		List<Action> tempActions = new LinkedList<>(actions);
		JDialog editWindow = new JDialog(mainFrame, "Legendary Actions", true);
			editWindow.setPreferredSize(new Dimension(350,350));
			editWindow.setSize(editWindow.getPreferredSize());
			editWindow.setMaximumSize(editWindow.getPreferredSize());
			editWindow.setMinimumSize(editWindow.getPreferredSize());
			editWindow.setLayout(new GridBagLayout());
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			constraints.ipady = 5;
			constraints.gridwidth = 2;
			editWindow.add(new JLabelBold("Actions"), constraints);
			constraints.ipady = 0;
			JList actionList = new JList(actions.toArray());
			actionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			actionList.setLayoutOrientation(JList.VERTICAL);
			actionList.setVisibleRowCount(-1);
			JScrollPane scroller = new JScrollPane(actionList);
			scroller.setPreferredSize(new Dimension(250,170));
			constraints.gridy++;
			editWindow.add(scroller, constraints);
			constraints.gridwidth = 1;
			constraints.weightx = 1;
			JButton addButton = new JButton("Add");
			constraints.gridx = 0;
			constraints.gridy++;
			editWindow.add(addButton, constraints);
			JButton deleteButton = new JButton("Delete");
			constraints.gridx++;
			editWindow.add(deleteButton, constraints);
			constraints.gridx = 0;
			constraints.gridy++;
			constraints.gridwidth = 2;
			JPanel usesPanel = new JPanel();
				GridBagConstraints usesConstr = new GridBagConstraints();
				usesConstr.gridx = 0;
				usesConstr.gridy = 0;
				usesPanel.add(new JLabel("Uses Per Cycle"), usesConstr);
				JSpinner uses = new JSpinner(new SpinnerNumberModel(3,1,50,1));
				uses.setValue(usesPerCycle);
				usesConstr.gridx++;
				usesPanel.add(uses, usesConstr);
			editWindow.add(usesPanel, constraints);
			JButton saveButton = new JButton("Save Legendary");
			constraints.gridx = 0;
			constraints.gridy++;
			constraints.gridwidth = 2;
			editWindow.add(saveButton, constraints);
			//Listeners
			actionList.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					if (event.getClickCount() == 2) {
						// Double-click detected
						Action selected = (Action) actionList.getSelectedValue();
						if (selected != null) {
							selected.editAction(editWindow, actionList, tempActions);
						}
					}
				}
			});
			addButton.addActionListener((ActionEvent e) -> {
				Action addMe = new Action();
				addMe.editAction(editWindow, actionList, tempActions);
			});
			deleteButton.addActionListener((ActionEvent e) -> {
				Action removeMe = (Action) actionList.getSelectedValue();
				tempActions.remove(removeMe);
				actionList.setListData(tempActions.toArray());
			});
			saveButton.addActionListener((ActionEvent e) -> {
				if (tempActions.size() > 0) {
					actions = tempActions;
					usesPerCycle = (int) uses.getValue();
					if (!AttributeHandler.contains(this)) {
						AttributeHandler.addAttribute(this);
					}
					editWindow.dispose();
				}
			});
		editWindow.setVisible(true);
	}
	@Override
	public String toString() {
		return String.format("Legendary: %d %s; %d %s",
			actions.size(),
			actions.size() == 1 ? "Action" : "Actions",
			usesPerCycle,
			usesPerCycle == 1 ? "Use" : "Uses"
		);
	}
	
}
