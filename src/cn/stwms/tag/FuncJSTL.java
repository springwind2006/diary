
package cn.stwms.tag;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.jsp.JspTagException;

public class FuncJSTL {

	// *********************************************************************
	// 字符串大小写转换

	public static String toUpperCase(String input) {
		return input.toUpperCase();
	}

	public static String toLowerCase(String input) {
		return input.toLowerCase();
	}

	// *********************************************************************
	// 子字符串处理

	public static int indexOf(String input, String substring) {
		if (input == null)
			input = "";
		if (substring == null)
			substring = "";
		return input.indexOf(substring);
	}

	public static boolean contains(String input, String substring) {
		return indexOf(input, substring) != -1;
	}

	public static boolean containsIgnoreCase(String input, String substring) {
		if (input == null)
			input = "";
		if (substring == null)
			substring = "";
		String inputUC = input.toUpperCase();
		String substringUC = substring.toUpperCase();
		return indexOf(inputUC, substringUC) != -1;
	}

	public static boolean startsWith(String input, String substring) {
		if (input == null)
			input = "";
		if (substring == null)
			substring = "";
		return input.startsWith(substring);
	}

	public static boolean endsWith(String input, String substring) {
		if (input == null)
			input = "";
		if (substring == null)
			substring = "";
		int index = input.indexOf(substring);
		if (index == -1)
			return false;
		if (index == 0 && substring.length() == 0)
			return true;
		return (index == input.length() - substring.length());
	}

	public static String substring(String input, int beginIndex, int endIndex) {
		if (input == null)
			input = "";
		if (beginIndex >= input.length())
			return "";
		if (beginIndex < 0)
			beginIndex = 0;
		if (endIndex < 0 || endIndex > input.length())
			endIndex = input.length();
		if (endIndex < beginIndex)
			return "";
		return input.substring(beginIndex, endIndex);
	}

	public static String substringAfter(String input, String substring) {
		if (input == null)
			input = "";
		if (input.length() == 0)
			return "";
		if (substring == null)
			substring = "";
		if (substring.length() == 0)
			return input;

		int index = input.indexOf(substring);
		if (index == -1) {
			return "";
		} else {
			return input.substring(index + substring.length());
		}
	}

	public static String substringBefore(String input, String substring) {
		if (input == null)
			input = "";
		if (input.length() == 0)
			return "";
		if (substring == null)
			substring = "";
		if (substring.length() == 0)
			return "";

		int index = input.indexOf(substring);
		if (index == -1) {
			return "";
		} else {
			return input.substring(0, index);
		}
	}

	// *********************************************************************
	// 字符串替换

	public static String escapeXml(String input) {
		if (input == null)
			return "";
		return doEscapeXml(input);
	}

	public static String trim(String input) {
		if (input == null)
			return "";
		return input.trim();
	}

	public static String replace(String input, String substringBefore, String substringAfter) {
		if (input == null)
			input = "";
		if (input.length() == 0)
			return "";
		if (substringBefore == null)
			substringBefore = "";
		if (substringBefore.length() == 0)
			return input;

		StringBuffer buf = new StringBuffer(input.length());
		int startIndex = 0;
		int index;
		while ((index = input.indexOf(substringBefore, startIndex)) != -1) {
			buf.append(input.substring(startIndex, index)).append(substringAfter);
			startIndex = index + substringBefore.length();
		}
		return buf.append(input.substring(startIndex)).toString();
	}

	public static String[] split(String input, String delimiters) {
		String[] array;
		if (input == null)
			input = "";
		if (input.length() == 0) {
			array = new String[1];
			array[0] = "";
			return array;
		}

		if (delimiters == null)
			delimiters = "";

		StringTokenizer tok = new StringTokenizer(input, delimiters);
		int count = tok.countTokens();
		array = new String[count];
		int i = 0;
		while (tok.hasMoreTokens()) {
			array[i++] = tok.nextToken();
		}
		return array;
	}

	// *********************************************************************
	// 集合处理

	public static int length(Object obj) throws JspTagException {
		if (obj == null)
			return 0;

		if (obj instanceof String)
			return ((String) obj).length();
		if (obj instanceof Collection)
			return ((Collection<?>) obj).size();
		if (obj instanceof Map)
			return ((Map<?, ?>) obj).size();

		int count = 0;
		if (obj instanceof Iterator) {
			Iterator<?> iter = (Iterator<?>) obj;
			count = 0;
			while (iter.hasNext()) {
				count++;
				iter.next();
			}
			return count;
		}
		if (obj instanceof Enumeration) {
			Enumeration<?> enum_ = (Enumeration<?>) obj;
			count = 0;
			while (enum_.hasMoreElements()) {
				count++;
				enum_.nextElement();
			}
			return count;
		}
		try {
			count = Array.getLength(obj);
			return count;
		} catch (IllegalArgumentException ex) {
		}
		throw new JspTagException("FOREACH_BAD_ITEMS");
	}

	public static String join(String[] array, String separator) {
		if (array == null)
			return "";
		if (separator == null)
			separator = "";

		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < array.length; i++) {
			buf.append(array[i]);
			if (i < array.length - 1)
				buf.append(separator);
		}

		return buf.toString();
	}

	private static String doEscapeXml(String buffer) {
		int start = 0;
		int length = buffer.length();
		char[] arrayBuffer = buffer.toCharArray();
		StringBuffer escapedBuffer = null;
		int HIGHEST_SPECIAL = '>';
		char[][] specialCharactersRepresentation = new char[HIGHEST_SPECIAL + 1][];

		for (int i = 0; i < length; i++) {
			char c = arrayBuffer[i];
			if (c <= HIGHEST_SPECIAL) {
				char[] escaped = specialCharactersRepresentation[c];
				if (escaped != null) {
					if (start == 0) {
						escapedBuffer = new StringBuffer(length + 5);
					}
					if (start < i) {
						escapedBuffer.append(arrayBuffer, start, i - start);
					}
					start = i + 1;
					escapedBuffer.append(escaped);
				}
			}
		}
		if (start == 0) {
			return buffer;
		}
		if (start < length) {
			escapedBuffer.append(arrayBuffer, start, length - start);
		}
		return escapedBuffer.toString();
	}
}
