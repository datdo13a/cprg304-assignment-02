package appDomain;

/**
 * this class is used by Parser
 * literally just used to store tag:string and line:number
 * @author Airzy, Eric, Dat, Sohan
 * @version 1.0
 */
public class TagEntry {
	String tag;
	int line;

	TagEntry(String tag, int line) {
		this.tag = tag;
		this.line = line;
	}

	/**
	 * if tag is self closing
	 * @return true if tag is self closing />
	 */
	boolean isSelfClosing() {
		String t = tag.trim();
		return t.endsWith("/>");
	}

	/**
	 * if tag is self closing
	 * @return true if tag is a end tag </
	 */
	boolean isEndTag() {
		String t = tag.trim();
		return t.startsWith("</");
	}

	/**
	 * if tag is self closing
	 * @return true if tag is a start tag < and not </ and not />
	 */
	boolean isStartTag() {
		String t = tag.trim();
		return t.startsWith("<") && !t.startsWith("</") && !t.endsWith("/>");
	}

	/**
	 * stripping brackets, slashes, and attributes
	 * @return the XML tag name only, 
	 */
	String getTagName() {
		String t = tag.trim();
		if (t.startsWith("</"))
			t = t.substring(2);
		else if (t.startsWith("<"))
			t = t.substring(1);
		if (t.endsWith("/>"))
			t = t.substring(0, t.length() - 2);
		else if (t.endsWith(">"))
			t = t.substring(0, t.length() - 1);

		int space = t.indexOf(' ');
		if (space >= 0)
			t = t.substring(0, space);
		return t;
	}
}