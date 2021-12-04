public interface Exercise {

    String NEW_LINE_MATCHER = "(\r)?\n";

    void runOnData(String dataString);

    String execute();

    String day();

}
