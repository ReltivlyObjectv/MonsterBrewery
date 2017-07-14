package tech.relativelyobjective.monsterbrewery;

import java.util.LinkedList;
import java.util.List;
import tech.relativelyobjective.monsterbrewery.attributes.Attribute;
import tech.relativelyobjective.monsterbrewery.pieces.PanelMonsterAttributes;

/**
 *
 * @author ReltivlyObjectv
 * Contact: me@relativelyobjective.tech
 * 
 */
public class AttributeHandler {
	private static List<Attribute> allAttributes;
	private static PanelMonsterAttributes display;
	
	public static void initialize(PanelMonsterAttributes displayArea) {
		allAttributes = new LinkedList<>();
		display = displayArea;
	}
	public static void addAttribute(Attribute a) {
		allAttributes.add(a);
		updateList();
	}
	public static void deleteAttribute(Attribute a) {
		allAttributes.remove(a);
		updateList();
	}
	public static void updateList() {
		display.getList().setListData(allAttributes.toArray());
	}
}
