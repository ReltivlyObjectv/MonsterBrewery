package tech.relativelyobjective.monsterbrewery.resources;

/**
 *
 * @author ReltivlyObjectv
 * Contact: me@relativelyobjective.tech
 * 
 */
public class Attributes {
	public static int calculateModifier(int abilityScore) {
		return (abilityScore / 2) - 5;
	}
}
