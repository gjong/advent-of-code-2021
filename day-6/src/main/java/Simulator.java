import java.math.BigInteger;

public class Simulator {
    public static final int MAX_REPRODUCTION_AGE = 9;
    private final int amountOFDays;

    public Simulator(int amountOFDays) {
        this.amountOFDays = amountOFDays;
    }

    public BigInteger simulateWithStart(long[] amountOfFisherPerDay) {
        BigInteger[] fishCycles = new BigInteger[MAX_REPRODUCTION_AGE];
        for (var idx=0; idx < amountOfFisherPerDay.length; idx++) {
            fishCycles[idx] = BigInteger.valueOf(amountOfFisherPerDay[idx]);
        }

        var currentDay = 0;
        while (currentDay < amountOFDays) {
            var newBorn = fishCycles[0];
            System.arraycopy(fishCycles, 1, fishCycles, 0, MAX_REPRODUCTION_AGE - 1);

            fishCycles[6] = fishCycles[6].add(newBorn);
            fishCycles[MAX_REPRODUCTION_AGE - 1] = newBorn;
            currentDay++;
        }

        BigInteger result = BigInteger.ZERO;
        for (BigInteger fishCycle : fishCycles) {
            result = result.add(fishCycle);
        }

        return result;
    }
}
