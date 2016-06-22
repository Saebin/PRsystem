package edu.hkcity.cs;

import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Class Formatter.
 */
public class Formatter {

    /** The formatted string. */
    private String formattedString;

    /**
     * Instantiates a new formatter.
     */
    public Formatter() {
    }

    /**
     * Format.
     *
     * @param str
     *            the str
     * @return the string
     */
    public String format(String str) {
        formattedString = str;
        formattedString = deleteIndent();
        formattedString = simplyMultispace();
        formattedString = deleteComment();
        formattedString = deleteBlankLine();
        return formattedString;
    }

    /**
     * Delete indent.
     *
     * @return the string
     */
    private String deleteIndent() {
        return formattedString.replaceAll("^[ \\t]+", "");
    }

    /**
     * Simply multispace.
     *
     * @return the string
     */
    private String simplyMultispace() {
        return formattedString.replaceAll("[ \t]+", " ");
    }

    /**
     * Delete comment.
     *
     * @return the string
     */
    private String deleteComment() {
        return formattedString
                .replaceAll(
                        "(/\\*([^*]|[\\r\\n]|(\\*+([^*/]|[\\r\\n])))*\\*+/)|(//.*)",
                        "");
    }

    /**
     * Delete blank line.
     *
     * @return the string
     */
    private String deleteBlankLine() {
        return formattedString.replaceAll("^[\r\n]+", "").replaceAll("[\r\n]+",
                "\n");
    }

	/**
	 * Format variable declaration.
	 * 
	 * @param str
	 *            the str
	 * @return the string
	 */
	public String formatVariableDeclaration(String str) {
		ArrayList<String> funcList = Utility.splitFunction(str);
		for (int n = 0; n < funcList.size(); n++) {
			Pattern pattern = Pattern
					.compile("[a-zA-Z_][\\w_:<>]*[*]* [*]*[A-Za-z_][\\w_]*.*?;");
			Matcher matcher = pattern.matcher(funcList.get(n));
			int lastEnd = 0;
			StringBuffer resStr = new StringBuffer();
			while (matcher.find()) {
				int start = matcher.start();
				int end = matcher.end();
				resStr.append(funcList.get(n).substring(lastEnd, start));
				String mstr = funcList.get(n).substring(start, end);
				StringTokenizer st = new StringTokenizer(mstr, ",");
				StringBuffer tmpStr = new StringBuffer();
				while (st.hasMoreTokens()) {
					String s = st.nextToken();
					String sdstr = tmpStr.append(s).toString();
					boolean hasPBuck = hasParallelBucket(sdstr);
					boolean hasAssSym = hasAssignSymbol(sdstr);
					if (hasPBuck && hasAssSym) {
						resStr.append(getRefinedStatement(sdstr));
						tmpStr = new StringBuffer();
					} else if (!hasAssSym)
						tmpStr = new StringBuffer();
					else
						tmpStr.append(',');
				}
				lastEnd = end;
			}
			if (resStr.charAt(resStr.length() - 1) == ',')
				resStr.replace(resStr.length() - 1, resStr.length(), ";");
			resStr.append(funcList.get(n).substring(lastEnd));
			funcList.set(n, resStr.toString());
		}
		StringBuffer res = new StringBuffer();
		for (int n = 0; n < funcList.size(); n++) {
			res.append(funcList.get(n));
		}
		return res.toString();
	}

	/**
	 * Checks for assign symbol.
	 * 
	 * @param str
	 *            the str
	 * @return true, if successful
	 */
	private boolean hasAssignSymbol(String str) {
		int i = str.indexOf('=');
		if (i == -1 || i == 0 || i == str.length() - 1 || (i > 0 && str.charAt(i - 1) == '!') || (i < str.length() - 1 && str.charAt(i + 1) == '='))
			return false;
		return true;
	}
    /**
     * Checks for parallel bucket.
     *
     * @param str
     *            the str
     * @return true, if successful
     */
    private boolean hasParallelBucket(String str) {
        int op = -1, cl = -1;
        int i = 0;
        while (i != -1) {
            op++;
            i = str.indexOf('(', ++i);
        }
        i = 0;
        while (i != -1) {
            cl++;
            i = str.indexOf(')', ++i);
        }
        return op == cl;
    }

    /**
     * Gets the refined statement.
     *
     * @param str
     *            the str
     * @return the refined statement
     */
    private String getRefinedStatement(String str) {
        int i = str.indexOf('=');
        int start = i - 1;
        if (str.charAt(start) == ' ') {
            while (start >= 0 && str.charAt(start) == ' ')
                start--;
        }
        while (start >= 0 && str.charAt(start) != ' ')
            start--;
        String resStr = str.substring(start + 1);
        if (!resStr.endsWith(";"))
            resStr += ",";
        return resStr;
    }
}
