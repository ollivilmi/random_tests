package tester.interfaces.sequencer;

import tester.interfaces.sequencer.IntSequence;

public class Sequencer {
    public Sequencer()
    {
        IntSequence sq = IntSequence.getSequence(new int[]{3,1,4,1,5,9});
        while (sq.hasNext())
            System.out.println(sq.next());
    }

    public static void main(String[] args) {
        new Sequencer();
    }
}
