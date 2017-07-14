package tech.relativelyobjective.monsterbrewery.pieces;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import tech.relativelyobjective.monsterbrewery.resources.Lists;

/**
 *
 * @author ReltivlyObjectv
 * Contact: me@relativelyobjective.tech
 * 
 */
public class PanelMonsterStatSpeed extends JPanel {
	private final JSpinner[] speeds;
	private final JCheckBox hover;
	
	public PanelMonsterStatSpeed() {
		super.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridy = 0;
		for (int i = 0; i < Lists.SPEED_TYPES.length; i++) {
			constraints.gridx = i;
			super.add(new JLabel(Lists.SPEED_TYPES[i]), constraints);
		}
		constraints.gridy++;
		speeds = new JSpinner[5];
		for(int i = 0; i < Lists.SPEED_TYPES.length - 1; i++) {
			constraints.gridx = i;
			int defaultSpeed = i < 4 ? 5 : 0; //Fly speed of zero
			speeds[i] = new JSpinner(new SpinnerNumberModel(defaultSpeed,0,2500,5));
			super.add(speeds[i], constraints);
		}
		constraints.gridx++;
		hover = new JCheckBox();
		super.add(hover, constraints);
	}
	public boolean canHover() {
		return hover.isSelected();
	}
	public int getWalkSpeed() {
		return (int) speeds[0].getValue();
	}
	public int getSwimSpeed() {
		return (int) speeds[1].getValue();
	}
	public int getBurrowSpeed() {
		return (int) speeds[2].getValue();
	}
	public int getClimbSpeed() {
		return (int) speeds[3].getValue();
	}
	public int getFlySpeed() {
		return (int) speeds[4].getValue();
	}
}
