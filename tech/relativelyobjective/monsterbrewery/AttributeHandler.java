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
		if (up && index == 0) {
			return;
		} else if (!up && index >= (allAttributes.size() - 1)) {
			return;
		}
		List<Attribute> newList = new LinkedList<>();
		Attribute top = null;
		Attribute bottom = null;
		int localIndex = 0;
		for (Attribute a : allAttributes) {
			if (up) {
				if (localIndex == index - 1) {
					bottom = a;
				} else if (localIndex == index) {
					top = a;
					if (top != null) {
						newList.add(top);
					}
					if (bottom != null) {
						newList.add(bottom);
					}
				} else {
					newList.add(a);
				}
			} if (!up) {
				if (localIndex == index) {
					bottom = a;
				} else if (localIndex == index + 1) {
					top = a;
					if (top != null) {
						newList.add(top);
					}
					if (bottom != null) {
						newList.add(bottom);
					}
				} else {
					newList.add(a);
				}
			}
			localIndex++;
		}
		allAttributes = newList;
		updateList();
	}
	public static void editItem(Attribute a) {
		a.editAttribute();
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
