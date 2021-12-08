import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
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
            logger.info("\u001b[37m==================== {} ========================\u001b[m", exercise.day());
            logger.info("\tExercise: \u001b[1;34m{}\u001b[m", exercise.getClass().getSimpleName());

            var exerciseInput = locateExerciseInput(exercise);
            var startTime = Instant.now();
            exercise.runOnData(exerciseInput);
            var answer = exercise.execute();
            var endTime = Instant.now();

            logger.info("\tComputation time: \u001b[36m{}ms\u001b[m", Duration.between(startTime, endTime).toMillis());
            logger.info("\tAnswer: \u001b[32m{}\u001b[m", answer);
            logger.info("\u001b[37m===================================================\u001b[m");
            logger.info("");
        }
        logger.info("Executed all found exercises.");
    }

    private String locateExerciseInput(Exercise exercise) throws IOException {
        var resourceStream = getClass().getResourceAsStream(exercise.day() + ".txt");
        return new String(resourceStream.readAllBytes(), StandardCharsets.UTF_8);
    }

}
