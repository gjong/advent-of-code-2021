import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ServiceLoader;

public class Executor {

    private final Logger logger = LoggerFactory.getLogger("exercise");

    public static void main(String[] args) throws IOException {
        new Executor().executeAll();
    }

    public void executeAll() throws IOException {
        var exercises = ServiceLoader.load(Exercise.class);

        logger.info("Starting the execution of all known exercises.");
        for (Exercise exercise : exercises) {
            logger.info("===================================================");
            logger.info("Exercise: {}.", exercise.getClass().getSimpleName());

            exercise.runOnData(locateExerciseInput(exercise));
            var answer = exercise.execute();

            logger.info("Answer: {}", answer);
            logger.info("===================================================");
            logger.info("");
            logger.info("");
        }
        logger.info("Executed all found exercises.");
    }

    private String locateExerciseInput(Exercise exercise) throws IOException {
        var resourceStream = getClass().getResourceAsStream(exercise.day() + ".txt");
        return new String(resourceStream.readAllBytes(), StandardCharsets.UTF_8);
    }

}
