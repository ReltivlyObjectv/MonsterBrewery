package tech.relativelyobjective.monsterbrewery.attributes;

/**
 *
 * @author ReltivlyObjectv
 * Contact: me@relativelyobjective.tech
 * 
 */
public class Language implements Attribute {
	private String lang;
	
	public Language(String value) {
		lang = value;
	}
	public void editAttribute() {
		//TODO
	}
	@Override
	public String toString() {
		return String.format("Language: %s", lang);
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String l) {
		lang = l;
	}
}
