public class Day2Exercise2 implements Exercise {
    private String[] input;

    @Override
    public void runOnData(String dataString) {
        input = dataString.split(NEW_LINE_MATCHER);
    }

    @Override
    public String execute() {
        var depth = 0;
        var horizontal = 0;
        var aim = 0;

        for (String rawInstruction : input) {
            var instruction = rawInstruction.split("\s");
            var action = Action.valueOf(instruction[0]);
            var amount = Integer.parseInt(instruction[1]);

            switch (action) {
                case up, down -> aim += action.getDirection() * amount;
                case forward -> {
                    depth += aim * amount;
                    horizontal += amount;
                }
            }
        }

        return String.valueOf(depth * horizontal);
    }

    @Override
    public String day() {
        return "day-2";
    }
}
