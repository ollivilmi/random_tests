package tester;

import java.util.Random;

public class Yahtzee {

    private static Random random = new Random();
    private int throwCount;
    private Result result;

    private class Result {
        int number;
        int amount;

        Result()
        {
            number = 0;
            amount = 0;
        }

        boolean check() {
            return amount == 5;
        }

        void checkForTriples(int[] dices)
        {
            if (amount < 3)
            {
                for (int i = 0; i<6; i++)
                    if (dices[i] > amount) {
                        number = i;
                        amount = dices[i];
                    }
            }
        }
    }

    public boolean play()
    {
        result = new Result();

        for (throwCount = 0; throwCount <3; throwCount++)
        {
            throwDice(5 - result.amount);

            if (result.check())
                return true;
        }
        return false;
    }

    private void throwDice(int diceCount)
    {
        int[] results = new int[diceCount];
        for (int i = 0; i<diceCount; i++)
            results[i] = random.nextInt(6);

        int[] dices = new int[]{0,0,0,0,0,0};

        for (int i = 0; i<results.length; i++)
            dices[results[i]]++;

        result.amount = result.amount + dices[result.number];

        result.checkForTriples(dices);
    }

    public static void main(String[] args) {
        Yahtzee game = new Yahtzee();

        int sampleSize = 10000;

        int[] tryArray = new int[sampleSize];

        for (int i = 0; i<sampleSize; i++)
        {
            int tryCount = 0;
            while (!game.play())
                ++tryCount;

            tryArray[i] = tryCount;
        }

        double sum = 0;
        for (int i = 0; i<sampleSize; i++)
            sum += tryArray[i];

        System.out.println("Average tries: " + sum/sampleSize);
    }
}
