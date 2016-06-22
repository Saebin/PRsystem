package edu.hkcity.cs;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * The Class CosineSimilarity.
 */
public class CosSimComparar extends Comparar {
    /**
     * Instantiates a new cosine similarity.
     */
    public CosSimComparar() {
        super();
        info = "CosineSimilarity Comparar";
    }

    /**
     * Instantiates a new cosine similarity.
     *
     * @param tar the tar
     * @param ori the ori
     */
    public CosSimComparar(String tar, String ori) {
        super(tar, ori);
        info = "코사인 유사도";
    }

    /* (non-Javadoc)
     * @see edu.hkcity.cs.Comparar#compare(edu.hkcity.cs.Formatter, edu.hkcity.cs.Output)
     */
    @Override
    public String compare(Formatter fmt, Output output) {
        String target = fmt.format(super.getTar());
        String original = fmt.format(super.getOri());
        target = target.replaceAll("\n", " ");
        original = original.replaceAll("\n", " ");
        double res = CosSimliar(target, original);
        String strRes = Double.toString(res);
        output.print(info, res);
        output.cosin = res;
        return strRes;
    }


    /**
     * Pub cos simliar.
     *
     * @param str1 the str1
     * @param str2 the str2
     * @return the double
     * Cosine similarity is a measure of similarity between two vectors
       of an inner product space that measures the cosine of the angle between
       them. The cosine of 0 is 1, and less than 1 for any other angle; the
       lowest
       value of the cosine is -1. The cosine of the angle between two vectors
       thus determines whether two vectors are pointing in roughly the same
       direction.
       input:
       two formatted string, i.e remove redundant spaces with one space
       output:
       cosine similarity of input strings;
     */
    public double PubCosSimliar(String str1, String str2) {
        return CosSimliar(str1, str2);
    }

    /**
     * Cos simliar.
     *
     * @param str1 the str1
     * @param str2 the str2
     * @return the double
     */
    private double CosSimliar(String str1, String str2) {
        Map<String, int[]> vectorSpace = new HashMap<String, int[]>();
        int[] itemCountArray = null;

        // get tokens,split by space
        String strArray[] = str1.split(" ");
        for (int i = 0; i < strArray.length; ++i) {
            if (vectorSpace.containsKey(strArray[i]))
                ++(vectorSpace.get(strArray[i])[0]);
            else {
                itemCountArray = new int[2];
                itemCountArray[0] = 1;
                itemCountArray[1] = 0;
                vectorSpace.put(strArray[i], itemCountArray);
            }
        }

        strArray = str2.split(" ");
        for (int i = 0; i < strArray.length; ++i) {
            if (vectorSpace.containsKey(strArray[i]))
                ++(vectorSpace.get(strArray[i])[1]);
            else {
                itemCountArray = new int[2];
                itemCountArray[0] = 0;
                itemCountArray[1] = 1;
                vectorSpace.put(strArray[i], itemCountArray);
            }
        }

        // calculate
        double vector1Modulo = 0.00;
        double vector2Modulo = 0.00;
        double vectorProduct = 0.00;
        Iterator iter = vectorSpace.entrySet().iterator();

        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            itemCountArray = (int[]) entry.getValue();

            vector1Modulo += itemCountArray[0] * itemCountArray[0];
            vector2Modulo += itemCountArray[1] * itemCountArray[1];

            vectorProduct += itemCountArray[0] * itemCountArray[1];
        }

        vector1Modulo = Math.sqrt(vector1Modulo);
        vector2Modulo = Math.sqrt(vector2Modulo);

        return (vectorProduct / (vector1Modulo * vector2Modulo));
    }

}
