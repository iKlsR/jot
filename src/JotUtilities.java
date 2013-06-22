public class JotUtilities {
    public static String DIRTY = " *";
    public static String CLEAN = " ";

    // will remove these soon..
    public static int hexToR(String h) { return Integer.parseInt(h.substring(0, 2), 16); };
    public static int hexToG(String h) { return Integer.parseInt(h.substring(2, 4), 16); };
    public static int hexToB(String h) { return Integer.parseInt(h.substring(4, 6), 16); };

    public static int hexTo_(String h, int o, int e) {
        return Integer.parseInt(h.substring(o, e), 16);
    };
}
