package tech.relativelyobjective.monsterbrewery.resources;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ReltivlyObjectv
 * Contact: me@relativelyobjective.tech
 * 
 */
public class FontManager {
	public static Font getFontTitle(double fontSize) {
		//TODO
		InputStream istream;
		istream = FontManager.class.getResourceAsStream("/alegreya/AlegreyaSC-Regular.otf");
		Font myFont;
		try {
			myFont = Font.createFont(Font.TRUETYPE_FONT, istream);
		} catch (FontFormatException | IOException ex) {
			Logger.getLogger(FontManager.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}
		myFont = myFont.deriveFont((float) fontSize);
		return myFont;
	}
	public static Font getFontRegularSmallCaps(double fontSize) {
		//TODO
		InputStream istream;
		istream = FontManager.class.getResourceAsStream("/opensans/OpenSans-Regular.ttf");
		Font myFont;
		try {
			myFont = Font.createFont(Font.TRUETYPE_FONT, istream);
		} catch (FontFormatException | IOException ex) {
			Logger.getLogger(FontManager.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}
		myFont = myFont.deriveFont((float) fontSize);
		return myFont;
	}
	public static Font getFontBold(double fontSize) {
		InputStream istream;
		istream = FontManager.class.getResourceAsStream("/opensans/OpenSans-Bold.ttf");
		Font myFont;
		try {
			myFont = Font.createFont(Font.TRUETYPE_FONT, istream);
		} catch (FontFormatException | IOException ex) {
			Logger.getLogger(FontManager.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}
		myFont = myFont.deriveFont((float) fontSize);
		return myFont;
	}
	public static Font getFontBoldItalic(double fontSize) {
		InputStream istream;
		istream = FontManager.class.getResourceAsStream("/opensans/OpenSans-BoldItalic.ttf");
		Font myFont;
		try {
			myFont = Font.createFont(Font.TRUETYPE_FONT, istream);
		} catch (FontFormatException | IOException ex) {
			Logger.getLogger(FontManager.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}
		myFont = myFont.deriveFont((float) fontSize);
		return myFont;
	}
	public static Font getFontExtraBold(double fontSize) {
		InputStream istream;
		istream = FontManager.class.getResourceAsStream("/opensans/OpenSans-ExtraBold.ttf");
		Font myFont;
		try {
			myFont = Font.createFont(Font.TRUETYPE_FONT, istream);
		} catch (FontFormatException | IOException ex) {
			Logger.getLogger(FontManager.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}
		myFont = myFont.deriveFont((float) fontSize);
		return myFont;
	}
	public static Font getFontExtraBoldItalic(double fontSize) {
		InputStream istream;
		istream = FontManager.class.getResourceAsStream("/opensans/OpenSans-ExtraBoldItalic.ttf");
		Font myFont;
		try {
			myFont = Font.createFont(Font.TRUETYPE_FONT, istream);
		} catch (FontFormatException | IOException ex) {
			Logger.getLogger(FontManager.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}
		myFont = myFont.deriveFont((float) fontSize);
		return myFont;
	}
	public static Font getFontItalic(double fontSize) {
		InputStream istream;
		istream = FontManager.class.getResourceAsStream("/opensans/OpenSans-Italic.ttf");
		Font myFont;
		try {
			myFont = Font.createFont(Font.TRUETYPE_FONT, istream);
		} catch (FontFormatException | IOException ex) {
			Logger.getLogger(FontManager.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}
		myFont = myFont.deriveFont((float) fontSize);
		return myFont;
	}
	public static Font getFontLight(double fontSize) {
		InputStream istream;
		istream = FontManager.class.getResourceAsStream("/opensans/OpenSans-Light.ttf");
		Font myFont;
		try {
			myFont = Font.createFont(Font.TRUETYPE_FONT, istream);
		} catch (FontFormatException | IOException ex) {
			Logger.getLogger(FontManager.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}
		myFont = myFont.deriveFont((float) fontSize);
		return myFont;
	}
	public static Font getFontLightItalic(double fontSize) {
		InputStream istream;
		istream = FontManager.class.getResourceAsStream("/opensans/OpenSans-LightItalic.ttf");
		Font myFont;
		try {
			myFont = Font.createFont(Font.TRUETYPE_FONT, istream);
		} catch (FontFormatException | IOException ex) {
			Logger.getLogger(FontManager.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}
		myFont = myFont.deriveFont((float) fontSize);
		return myFont;
	}
	public static Font getFontRegular(double fontSize) {
		InputStream istream;
		istream = FontManager.class.getResourceAsStream("/opensans/OpenSans-Regular.ttf");
		Font myFont;
		try {
			myFont = Font.createFont(Font.TRUETYPE_FONT, istream);
		} catch (FontFormatException | IOException ex) {
			Logger.getLogger(FontManager.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}
		myFont = myFont.deriveFont((float) fontSize);
		return myFont;
	}
	public static Font getFontSemibold(double fontSize) {
		InputStream istream;
		istream = FontManager.class.getResourceAsStream("/opensans/OpenSans-Semibold.ttf");
		Font myFont;
		try {
			myFont = Font.createFont(Font.TRUETYPE_FONT, istream);
		} catch (FontFormatException | IOException ex) {
			Logger.getLogger(FontManager.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}
		myFont = myFont.deriveFont((float) fontSize);
		return myFont;
	}
	public static Font getFontSemiboldItalic(double fontSize) {
		InputStream istream;
		istream = FontManager.class.getResourceAsStream("/opensans/OpenSans-SemiboldItalic.ttf");
		Font myFont;
		try {
			myFont = Font.createFont(Font.TRUETYPE_FONT, istream);
		} catch (FontFormatException | IOException ex) {
			Logger.getLogger(FontManager.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}
		myFont = myFont.deriveFont((float) fontSize);
		return myFont;
	}
}
