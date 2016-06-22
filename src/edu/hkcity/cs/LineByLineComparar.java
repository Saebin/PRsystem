package edu.hkcity.cs;

import java.lang.String;

/**
 * The Class LineByLineComparar.
 */
public class LineByLineComparar extends Comparar {
    // Constructors
    /**
     * Instantiates a new line by line comparar.
     *
     * @param tar
     *            the tar
     * @param ori
     *            the ori
     */
    public LineByLineComparar(String tar, String ori) {
        super(tar, ori);
        info = "행 유사도";
    }

    // Overload compare()
    /* (non-Javadoc)
     * @see edu.hkcity.cs.Comparar#compare(edu.hkcity.cs.Formatter, edu.hkcity.cs.Output)
     */
    public String compare(Formatter fmt, Output output) {
        String target = fmt.format(super.getTar());
        String original = fmt.format(super.getOri());

        String[] tarStr = target.split("\n");
        String[] oriStr = original.split("\n");

        double count = 0;

        for (int i = 0; i != tarStr.length; ++i) {
            if (i >= oriStr.length)
                break;
            if (tarStr[i].equals(oriStr[i])) {
                ++count;
            }
        }

        Double result = count / tarStr.length;

        output.print(info, result);
        output.Line = result;
        
        return Double.toString(result);
    }
}
