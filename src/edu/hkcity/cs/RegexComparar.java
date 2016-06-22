package edu.hkcity.cs;

import java.util.Arrays;

/**
 * The Class RegexComparar.
 */
public class RegexComparar extends Comparar {
    // Constructor
    /**
     * Instantiates a new regex comparar.
     */
    public RegexComparar() {
        super();
        info = "Comparar based on regex transformation";
    }

    /**
     * Instantiates a new regex comparar.
     *
     * @param tar
     *            the tar
     * @param ori
     *            the ori
     */
    public RegexComparar(String tar, String ori) {
        super(tar, ori);
        info = "정규화 유사도";
    }

    /* (non-Javadoc)
     * @see edu.hkcity.cs.Comparar#compare(edu.hkcity.cs.Formatter, edu.hkcity.cs.Output)
     */
    public String compare(Formatter fmt, Output output) {
        String target = fmt.format(getTar());
        String original = fmt.format(getOri());

        Double result = calRegedSim(target, original);

        output.print(info, result);
        output.regex = result;
        return Double.toString(result);
    }

    //
    /**
     * Cal reged sim.
     *
     * @param tar
     *            the tar
     * @param ori
     *            the ori
     * @return the double
     */
    protected double calRegedSim(String tar, String ori) {

        String[] tarVars = Utility.extractVarNames(tar);
        String[] oriVars = Utility.extractVarNames(ori);

        Arrays.sort(tarVars);
        Arrays.sort(oriVars);

        String[] sortedTar = new String[tarVars.length];
        String[] sortedOri = new String[oriVars.length];

        for (int i = 0; i < tarVars.length; ++i)
            sortedTar[i] = tarVars[tarVars.length - 1 - i];
        for (int i = 0; i < oriVars.length; ++i)
            sortedOri[i] = oriVars[oriVars.length - 1 - i];

        String[] regedTar = tar.replaceAll("\\s|;|" + Utility.join(sortedTar, "|"), "").split("");
        String[] regedOri = ori.replaceAll("\\s|;|" + Utility.join(sortedOri, "|"), "").split("");

        return (Utility.lcs(regedTar, regedOri)+Utility.lcs(regedOri, regedTar))/2;
    }
}
