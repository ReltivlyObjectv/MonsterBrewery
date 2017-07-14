package tech.relativelyobjective.monsterbrewery.attributes;

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
	@Override
	public String toString() {
		if (sense.compareTo("Passive Perception") == 0) {
			return String.format("Sense: %s %d", sense, magnitude);
		} else {
			return String.format("Sense: %s (%d ft.)", sense, magnitude);
		}
	}
	public String getSense() {
		return sense;
	}
	public void setSense(String sense) {
		this.sense = sense;
	}
	public int getMagnitude() {
		return magnitude;
	}
	public void setMagnitude(int mag) {
		magnitude = mag;
	}
}
