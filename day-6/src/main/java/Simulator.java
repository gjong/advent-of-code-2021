import java.util.Arrays;

public class Simulator {
    public static final int MAX_REPRODUCTION_AGE = 9;
    private final int amountOFDays;

    public Simulator(int amountOFDays) {
        this.amountOFDays = amountOFDays;
    }

    public long simulateWithStart(long[] amountOfFisherPerDay) {
        var currentDay = 0;
        while (currentDay < amountOFDays) {
            var newBorn = amountOfFisherPerDay[0];

            for (var age = 1; age < MAX_REPRODUCTION_AGE; age++) {
                amountOfFisherPerDay[age - 1] = amountOfFisherPerDay[age];
            }

            amountOfFisherPerDay[6] += newBorn;
            amountOfFisherPerDay[MAX_REPRODUCTION_AGE - 1] = newBorn;
            currentDay++;
        }

        return Arrays.stream(amountOfFisherPerDay).sum();
    }
}
