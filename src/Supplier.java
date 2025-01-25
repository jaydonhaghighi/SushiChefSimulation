import java.util.Random;

public class Supplier implements Runnable {
    private final TableMonitor tableMonitor;
    private final Random random = new Random();
    private static final int MAX_PRODUCTIONS = 20;
    private int productionCount = 0;

    /**
     * supplier constructor
     * @param tableMonitor the shared table monitor
     */
    public Supplier(TableMonitor tableMonitor) {
        this.tableMonitor = tableMonitor;
    }

    /**
     * produces up to max productions
     */
    @Override
    public void run() {
        while (productionCount < MAX_PRODUCTIONS) {
            int ing1 = random.nextInt(3);
            int ing2;
            do {
                ing2 = random.nextInt(3);
            } while (ing2 == ing1);

            tableMonitor.placeIngredients(new int[]{ing1, ing2});
            productionCount++;
        }
        System.out.println("[supplier] reached production limit. stopping.");
    }
}
