package tester.interfaces.sequencer;

public interface IntSequence {
    boolean hasNext();
    int next();

    static IntSequence getSequence(int... numbers) {
        return new IntSequence() {
            int number = 0;

            {
                for (int i = 0; i < numbers.length; i++) {
                    number *= 10;
                    number += numbers[i];
                }
            }

            @Override
            public boolean hasNext() {
                return number != 0;
            }

            @Override
            public int next() {
                int result = number % 10;
                number /= 10;
                return result;
            }

            public int rest() {
                return number;
            }
        };
    }
}
