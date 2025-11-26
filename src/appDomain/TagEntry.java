package appDomain;

public class TagEntry {
	String tag;
	int line;

	TagEntry(String tag, int line) {
		this.tag = tag;
		this.line = line;
	}

	boolean isSelfClosing() {
		String t = tag.trim();
		return t.endsWith("/>");
	}

	boolean isEndTag() {
		String t = tag.trim();
		return t.startsWith("</");
	}

	boolean isStartTag() {
		String t = tag.trim();
		return t.startsWith("<") && !t.startsWith("</") && !t.endsWith("/>");
	}

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