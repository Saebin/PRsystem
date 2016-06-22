package edu.hkcity.cs;

/**
 * The Class Comparar.
 * @author HW
 */
public abstract class Comparar {

    /**
     * Instantiates a new comparer.
     */
    public Comparar() {
        info = "Abstract Comparar";
    }

    /**
     * Instantiates a new comparar.
     *
     * @param tar
     *            the tar
     * @param ori
     *            the ori
     */
    public Comparar(final String tar, final String ori) {
        this.setTar(tar);
        this.setOri(ori);
        info = "Abstract Comparar";
    }

    /**
     * Compare.
     *
     * @param fmt
     *            the fmt
     * @param output
     *            the output
     * @return the string
     */
    public abstract String compare(Formatter fmt, Output output);

    /**
     * set target string
     *
     * @param tar
     */
    private void setTar(String tar) {
        this.tar = tar;
    }

    /**
     * get the target string
     *
     * @return the string
     */
    protected String getTar() {
        return tar;
    }

    /**
     * set original string
     *
     * @param ori
     */
    private void setOri(String ori) {
        this.ori = ori;
    }

    /**
     * get original string
     *
     * @return the string
     */
    protected String getOri() {
        return ori;
    }

    private String tar;
    private String ori;
    protected String info;
}
