package lab8;

/**
 * This subclass of LinearProbingHashTable will define at least 3 
 * methods (you may define others as you deem appropriate): 
 */
public class TitanProbeHashMap<K, V> extends LinearProbingHashTable<K, V> {
    private static final char EMPTY         = 'E';
    private static final char FILLED        = 'O';
    private static final int LOG_LENGTH     = 80;

    public TitanProbeHashMap(int capacity) {
        super(capacity);
    }
    /**
     * gets the emperical average search miss cost
     * @return the average search miss cost
     */
    public double empiricalAverageSearchMissCost() {
        int misses = tableSize;  // total miss cost
        int k = 0;               // running total
        for(int i=0; i<tableSize; i++) {
            if(keys[i] == null) k = 0;
            else misses += (++k);
        }
        return (double) misses / tableSize;
    }
    /**
     * Uses a single for loop to caclulate the emperical average search miss cost.
     * 
     * NOTE: could be worth just creating a dummy map filled with the char values and returning
     * the base AverageSearchMissCost of it, however that is less efficient.
     * @param str the string to get the values of
     * @return the average search miss cost
     */
    public static double empiricalAverageSearchMissCost(String str) {
        int misses = str.length();  // total miss cost
        int k = 0;                  // running total
        for(int i=0; i<str.length(); i++) {
            if(str.charAt(i) == EMPTY) k = 0;
            else misses += (++k);
        }
        return (double) misses / str.length();
    }
    public double _empiricalAverageSearchMissCost() {
        double totalMissCost = 0;

        for (int i = 0; i < tableSize; i++) {
            if (keys[i] == null) {
                totalMissCost++;
            } else {
                int j = 0;
                while (keys[(i + j) % tableSize] != null && j != tableSize) {
                    totalMissCost++;
                    j++;
                }
                totalMissCost++;
            }
        }
        return totalMissCost / tableSize;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int len = 0;
        for(K key : keys) {
            sb.append(key == null ? EMPTY: FILLED);
            if(++len == LOG_LENGTH) {
                len = 0;
                sb.append("\n");
            }
        }
        return sb.toString();
    }
    
    

}