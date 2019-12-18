package baseline;

public class GlobalManager {
    private static String path = "/src/test/resources/baseline/";

    public static String getBaselinePath() {
        return path;
    }

    public static void setBaselinePath(String path) {
        GlobalManager.path = path;
    }
}
