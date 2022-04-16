package lab8;

import java.util.Random;

/**
 * This class will put averageSearchMissCost() through its paces, and 
 * contain at least one method: 
 */
public class HashMapPlayground {
    public static final int REPEAT_COUNT        = 1000;
    public static final int TABLE_SIZE          = 8192;

    public static void printExperimentalResultsTable() {
        Random rand = new Random(42);
        for(int j = 1; j<10; j++) {
            double lambda = (double) j / 10;
            double totalCosts = 0;
            for(int i=0; i<REPEAT_COUNT; i++) {
                TitanProbeHashMap<Integer, Integer> map = new TitanProbeHashMap<>(TABLE_SIZE);

                int count = (int)(lambda * TABLE_SIZE);
                for(int k=0; k<=count; k++) {
                    map.put(rand.nextInt(), k);
                }
                totalCosts += map.empiricalAverageSearchMissCost();
            }
            System.out.printf("%2.3f\n", totalCosts / REPEAT_COUNT);
        }
    }
}
