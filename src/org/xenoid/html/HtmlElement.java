package org.xenoid.html;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public final class HtmlElement implements HtmlNode {
	
	private final String name;
	
	private final Map<String, String> attributes = new TreeMap<String, String>(String.CASE_INSENSITIVE_ORDER);
	
	private final List<HtmlNode> children = new ArrayList<HtmlNode>();

	public HtmlElement(String name) {
		this.name = name;
	}
}
