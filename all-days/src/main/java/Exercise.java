public interface Exercise {

    String NEW_LINE_MATCHER = "\n(\r)?";

    void runOnData(String dataString);

    String execute();

    String day();

}
