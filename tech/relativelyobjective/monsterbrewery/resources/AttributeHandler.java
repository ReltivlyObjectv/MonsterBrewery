package tech.relativelyobjective.monsterbrewery.resources;

import java.util.LinkedList;
import java.util.List;
import tech.relativelyobjective.monsterbrewery.attributes.Attribute;
import tech.relativelyobjective.monsterbrewery.attributes.LegendaryActions;
import tech.relativelyobjective.monsterbrewery.attributes.Spellcaster;
import tech.relativelyobjective.monsterbrewery.pieces.FrameMain;
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
	public static boolean contains(Attribute a) {
		return allAttributes.contains(a);
	}
	public static void shiftItem (int index, boolean up) {
		if (up && index == 0) {
			return;
		} else if (!up && index >= (allAttributes.size() - 1)) {
			return;
		}
		List<Attribute> newList = new LinkedList<>();
		Attribute top;
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
		if (up) {
			updateList(index - 1);
		} else {
			updateList(index + 1);
		}
	}
	public static List<Attribute> getAllAttributesCopy() {
		return new LinkedList<>(allAttributes);
	}
	public static void editItem(Attribute a, FrameMain mainFrame) {
		a.editAttribute(mainFrame);
	}
	public static void editItem(int index, FrameMain mainFrame) {
		if (index >= allAttributes.size() || index < 0) {
			//Out of bounds
			return;
		}
		int localIndex = 0;
		for(Attribute a : allAttributes) {
			if (localIndex == index) {
				editItem(a, mainFrame);
				return;
			}
			localIndex++;
		}
	}
	public static void updateList(int selected) {
		display.getList().setListData(allAttributes.toArray());
		display.getList().setSelectedIndex(selected);
	}
	public static void updateList() {
		display.getList().setListData(allAttributes.toArray());
	}
	public static LegendaryActions getLegendary() {
		for (Attribute a : allAttributes) {
			if (a instanceof LegendaryActions) {
				return (LegendaryActions) a;
			}
		}
		return null;
	}
	public static Spellcaster getSpellcaster() {
		for (Attribute a : allAttributes) {
			if (a instanceof Spellcaster) {
				return (Spellcaster) a;
			}
		}
		return null;
	}
	public static void clearAttributes() {
		allAttributes.clear();
		updateList();
	}
}
