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
			int defaultSpeed = i == 0 ? 5 : 0; //Non-walk speed is 0
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
	public void setCanHover(boolean canH) {
		hover.setSelected(canH);
	}
	public void setWalkSpeed(int s) {
		speeds[0].setValue(s);
	}
	public void setSwimSpeed(int s) {
		speeds[1].setValue(s);
	}
	public void setBurrowSpeed(int s) {
		speeds[2].setValue(s);
	}
	public void setClimbSpeed(int s) {
		speeds[3].setValue(s);
	}
	public void setFlySpeed(int s) {
		speeds[4].setValue(s);
	}
}
