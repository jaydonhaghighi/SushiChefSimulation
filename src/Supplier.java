import java.util.Random;

public class Supplier implements Runnable {

    // ingredient types
    public static final int RICE = 0;
    public static final int NORI = 1;
    public static final int FILLING = 2;

    // table to hold the two ingredients provided by the Agent. Null means the table is empty.
    private int[] table = null;
    private final Random random = new Random();

    // counter to limit the number of productions
    private int productionCount = 0;
    private static final int MAX_PRODUCTIONS = 20;

    /**
     * main execution method of the Supplier.
     * the Supplier continuously produces two different ingredients and places them on the table.
     * It waits for the table to be cleared by a Chef before producing again.
     */
    @Override
    public void run() {
        while (productionCount < MAX_PRODUCTIONS) {
            produceTwoIngredients();
        }
        System.out.println("[Agent] Reached production limit. Stopping.");
    }

    /**
     * produces two different ingredients and places them on the table.
     * ensures the table is empty before adding new ingredients
     * and notifies all waiting threads once the ingredients are placed.
     */
    private synchronized void produceTwoIngredients() {
        // wait until the table is empty
        while (table != null) {
            try {
                wait(); // wait until a Chef notifies that the table is cleared
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (productionCount >= MAX_PRODUCTIONS) {
            notifyAll(); // Wake up any waiting chefs so they can terminate gracefully
            return;
        }

        // picks two distinct ingredients
        int ing1 = random.nextInt(3);
        int ing2;
        do {
            ing2 = random.nextInt(3);
        } while (ing2 == ing1);

        // places them on the table
        table = new int[]{ing1, ing2};
        productionCount++;

        System.out.println("==================================================");
        System.out.println("[Supplier] Placed: "
                + ingredientName(ing1) + " + " + ingredientName(ing2) +
                " (#" + productionCount + ")");
        System.out.println("==================================================");

        // notify all Chefs that new ingredients are available
        notifyAll();
    }

    /**
     * clears the table once the ingredients are taken by a Chef.
     * This method is called by Chefs to signal that the table is ready for new ingredients.
     */
    public synchronized void takeIngredients() {
        table = null;
        notifyAll();
    }

    /**
     * retrieves the current ingredients on the table.
     *
     * @return an array of integers representing the ingredients on the table,
     *         or null if the table is empty.
     */
    public synchronized int[] getTable() {
        return table;
    }

    /**
     * converts an ingredient constant to its string representation.
     * this is purely for display purposes.
     *
     * @param ing the ingredient constant (RICE, NORI, or FILLING)
     * @return the string name of the ingredient
     */
    private String ingredientName(int ing) {
        return switch (ing) {
            case RICE -> "RICE";
            case NORI -> "NORI";
            case FILLING -> "FILLING";
            default -> "UNKNOWN";
        };
    }
}