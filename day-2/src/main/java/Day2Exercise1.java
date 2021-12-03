public class Day2Exercise1 implements Exercise {
    private String[] input;

    @Override
    public void runOnData(String dataString) {
        input = dataString.split(NEW_LINE_MATCHER);
    }

    @Override
    public String execute() {
        int depth = 0, horizontal = 0;
        for (String rawInstruction : input) {
            var instruction = rawInstruction.split("\s");
            var action = Action.valueOf(instruction[0]);
            var amount = Integer.parseInt(instruction[1]);

            switch (action) {
                case up, down -> depth += action.getDirection() * amount;
                case forward -> horizontal += amount;
            }
        }

        return String.valueOf(depth * horizontal);
    }

    @Override
    public String day() {
        return "day-2";
    }
}
