package lab8;

import java.util.Random;

/**
 * This class will put averageSearchMissCost() through its paces, and 
 * contain at least one method: 
 */
public class HashMapPlayground {
    public static final int REPEAT_COUNT        = 1000;
    public static final int TABLE_SIZE          = 8192;

    /**
     * Prints out an example table with values.
     * 
     * TODO:
     * repeat count and table size should not be hard coded.
     * Some things should be split into multiple functions such as generating random maps and logging
     */
    public static void printExperimentalResultsTable() {
        Random rand = new Random(42);
        System.out.printf("%-10s%-12s%5s\n", " L ", "Empirical", "Theoretical");
        System.out.printf("%-10s%-12s%5s\n", "", "  ASMC", "   ASMC");
        for(int j = 1; j<10; j++) {
            double lambda = (double) j / 10;
            double totalCosts = 0;
            for(int i=0; i<REPEAT_COUNT; i++) {
                TitanProbeHashMap<Integer, Integer> map = new TitanProbeHashMap<>(TABLE_SIZE);

                int count = (int)(lambda * TABLE_SIZE);
                for(int k=0; k<count; k++) {
                    map.put(rand.nextInt(), k);
                }
                totalCosts += map.empiricalAverageSearchMissCost();
            }
            double t = 1 - lambda;
            double expected = 0.5 * ( 1 + 1 / (t * t));
            System.out.printf("%1.1f%14.3f%12.3f\n", lambda, totalCosts / REPEAT_COUNT, expected);
        }
    }
}
