package dirac;

public interface DiracDice {

    int roll();

    class SimpleDice implements DiracDice {
        private int currentValue;

        @Override
        public int roll() {
            return increase() + increase() + increase();
        }

        private int increase() {
            currentValue++;
            if (currentValue > 100) {
                currentValue -= 100;
            }
            return currentValue;
        }
    }
}
