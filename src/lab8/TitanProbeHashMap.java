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
        int misses = 0;
        for(int i=0; i<tableSize; i++) {
            misses += getCost(i);
        }
        return (double) misses / tableSize;
    }
    /**
     * Uses a string to caclulate the emperical average search miss cost.
     * 
     * @param str the string to get the values of
     * @return the average search miss cost
     */
    public static double empiricalAverageSearchMissCost(String str) {
        TitanProbeHashMap<Integer, Character> newMap = new TitanProbeHashMap<>(str.length());
        for(int i=0; i<str.length(); i++) {
            char c = str.charAt(i);
            if(c == FILLED) newMap.put(i, c);
        }

        return newMap.empiricalAverageSearchMissCost();
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
    
    /**
     * Gets the miss cost of any position
     * @param index the index to get the cost of
     * @return the actual cost
     */
    private int getCost(int index) {
        for(int i=0; i<tableSize; i++) {
            if(keys[(i + index) % tableSize] == null) return i + 1;
        }
        return size;
    }
    

}