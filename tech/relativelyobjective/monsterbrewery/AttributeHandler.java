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
	public static void deleteAttribute(int index) {
		if (index >= allAttributes.size() || index < 0) {
			//Out of bounds
			return;
		}
		int localIndex = 0;
		for(Attribute a : allAttributes) {
			if (localIndex == index) {
				deleteAttribute(a);
				return;
			}
			localIndex++;
		}
	}
	public static void shiftItem (int index, boolean up) {
		//TODO
	}
	public static void editItem(Attribute a) {
		//TODO
	}
	public static void editItem(int index) {
		if (index >= allAttributes.size() || index < 0) {
			//Out of bounds
			return;
		}
		int localIndex = 0;
		for(Attribute a : allAttributes) {
			if (localIndex == index) {
				editItem(a);
				return;
			}
			localIndex++;
		}
	}
	public static void updateList() {
		display.getList().setListData(allAttributes.toArray());
	}
}
