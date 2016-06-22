package edu.hkcity.cs;

import java.util.ArrayList;

/**
 * The Class FuncByFuncComparar.
 */
public class FuncByFuncComparar extends Comparar {
    // Constructor
    /**
     * Instantiates a new func by func comparar.
     *
     * @param tar
     *            the tar
     * @param ori
     *            the ori
     */
    public FuncByFuncComparar(String tar, String ori) {
        super(tar, ori);
        info = "함수적 유사도";
    }

    // Overload compare()
    /*
     * (non-Javadoc)
     *
     * @see edu.hkcity.cs.Comparar#compare(edu.hkcity.cs.Formatter,
     * edu.hkcity.cs.Output)
     */
    public String compare(Formatter fmt, Output output) {
        String target = fmt.format(getTar());
        String original = fmt.format(getOri());
        ArrayList<String> targetFuncList = Utility.splitFunction(target);
        ArrayList<String> originalFuncList = Utility.splitFunction(original);

        boolean oriFuncPaired[] = new boolean[originalFuncList.size()];
        double sims[] = new double[Math.max(targetFuncList.size(),
                originalFuncList.size())];
        int lnt[] = new int[sims.length];
        int iSims = 0, jCount = 0;

		for (int i = 0; i < targetFuncList.size(); i++) {
			double maxSim = -1;
			int jMax = -1;
			for (int j = 0; j < originalFuncList.size(); j++) {
				if (oriFuncPaired[j])
					continue;

                String tarFunc = targetFuncList.get(i);
                String oriFunc = originalFuncList.get(j);
                if (tarFunc.length() < oriFunc.length()) {
                    oriFunc = Utility.replace(tarFunc, oriFunc);
                } else {
                    tarFunc = Utility.replace(tarFunc, oriFunc);
                }

                String[] tarFunToken = Utility.getTok(tarFunc);
                String[] oriFunToken = Utility.getTok(oriFunc);

				double sim = Utility.lcs(oriFunToken,tarFunToken);

                if (sim > maxSim) {
                    jMax = j;
                    maxSim = sim;
                }
            }
            if (jMax != -1) {
                jCount++;
                oriFuncPaired[jMax] = true;
                lnt[iSims] = Math.max(targetFuncList.get(i).length(),
                        originalFuncList.get(jMax).length());
                sims[iSims++] = maxSim;
            } else {
                lnt[iSims] = targetFuncList.get(i).length();
                sims[iSims++] = 0;
            }
        }

        if (jCount != originalFuncList.size()) {
            for (int j = 0; j < originalFuncList.size(); j++) {
                if (oriFuncPaired[j])
                    continue;
                sims[iSims] = 0;
                lnt[iSims++] = originalFuncList.get(j).length();
                oriFuncPaired[j] = true;
            }
        }

        Double result = calSimilarity(sims, lnt);

        output.print(info, result);
        output.Function = result;

		return Double.toString(Math.floor(result*10000)/10000);
	}

    /**
     * Cal similarity.
     *
     * @param sims
     *            the sims
     * @param lnt
     *            the lnt
     * @return the double
     */
    private double calSimilarity(double sims[], int lnt[]) {
        double interSim = 0;
        int totalLine = 0;
        for (int i = 0; i < sims.length; i++) {
            interSim += sims[i] * lnt[i];
            totalLine += lnt[i];
        }
        return (double) interSim / totalLine;
    }
}
