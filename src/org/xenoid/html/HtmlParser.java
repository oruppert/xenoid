package org.xenoid.html;

@SuppressWarnings("UnusedAssignment")
public final class HtmlParser {

	private static final String SPACE = " \t\r\n";
	private static final String DIGIT = "1234567890";
	private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
	private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String NAME_START = LOWER + UPPER;
	private static final String NAME_CHAR = LOWER + UPPER + DIGIT;

	private static int parse(String input, int offset, String chars) {
		int pos;
		for (pos = 0; offset + pos < input.length(); pos++)
			if (chars.indexOf(input.charAt(offset + pos)) == -1)
				break;
		return pos;
	}

	private static int parse(String input, int offset, String chars, StringBuilder out) {
		int pos = parse(input, offset, chars);
		out.append(input, offset, offset + pos);
		return pos;
	}

	private static int parseName(String input, int offset, StringBuilder out) {
		int n, p = 0;
		p += n = parse(input, offset, NAME_START, out);
		if (n == 0) return 0;
		p += n = parse(input, offset, NAME_CHAR, out);
		return p;
	}




}
