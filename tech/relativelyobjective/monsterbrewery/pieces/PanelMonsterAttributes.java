package tech.relativelyobjective.monsterbrewery.pieces;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import tech.relativelyobjective.monsterbrewery.AttributeHandler;

/**
 *
 * @author ReltivlyObjectv
 * Contact: me@relativelyobjective.tech
 * 
 */
public class PanelMonsterAttributes extends JPanel {
	private final JList attribList;
	private final JButton edit;
	private final JButton delete;
	private final JButton up;
	private final JButton down;
	
	public PanelMonsterAttributes() {
		super.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		//List
		constraints.gridx = 0;
		constraints.gridy = 0;
		attribList = new JList();
		attribList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		attribList.setLayoutOrientation(JList.VERTICAL);
		attribList.setVisibleRowCount(-1);
		JScrollPane scroller = new JScrollPane(attribList);
		scroller.setPreferredSize(new Dimension(250,170));
		super.add(scroller, constraints);
		//Options
		JPanel options = new JPanel();
			options.setLayout(new GridBagLayout());
			GridBagConstraints buttonWeight = new GridBagConstraints();
			buttonWeight.weightx = .25;
			buttonWeight.weighty = 1;
			edit = new JButton("Edit");
			edit.addActionListener((ActionEvent e) -> {
				editSelected();
			});
			options.add(edit, buttonWeight);
			delete = new JButton("Delete");
			delete.addActionListener((ActionEvent e) -> {
				deleteSelected();
			});
			options.add(delete, buttonWeight);
			up = new JButton("Up");
			up.addActionListener((ActionEvent e) -> {
				moveSelected(true);
			});
			options.add(up, buttonWeight);
			down = new JButton("Down");
			down.addActionListener((ActionEvent e) -> {
				moveSelected(false);
			});
			options.add(down, buttonWeight);
		constraints.gridy++;
		super.add(options, constraints);
		AttributeHandler.initialize(this);
	}
	public JList getList() {
		return attribList;
	}
	private void deleteSelected() {
		AttributeHandler.deleteAttribute(attribList.getSelectedIndex());
	}
	private void editSelected() {
		AttributeHandler.editItem(attribList.getSelectedIndex());
	}
	private void moveSelected(boolean up) {
		AttributeHandler.shiftItem(attribList.getSelectedIndex(), up);
	}
}
