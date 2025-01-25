import java.util.Arrays;

public class TableMonitor {
    // ingredient constants
    public static final int RICE = 0;
    public static final int NORI = 1;
    public static final int FILLING = 2;

    private int[] table; // holds the ingredients
    private boolean tableAvailable = false; // tracks if ingredients are on the table

    /**
     * supplier places two ingredients on the table
     * chefs wait until the table is cleared
     */
    public synchronized void placeIngredients(int[] ingredients) {
        while (tableAvailable) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }
        table = ingredients;
        tableAvailable = true;
        System.out.println("[supplier] placed: " + Arrays.toString(ingredients));
        notifyAll();
    }

    /**
     * chef takes ingredients and clears the table
     */
    public synchronized void takeIngredients() {
        table = null;
        tableAvailable = false;
        System.out.println("[chef] cleared the table");
        notifyAll();
    }

    /**
     * gets the ingredients on the table
     * @return the ingredients or null if the table is empty
     */
    public synchronized int[] getIngredients() {
        return table;
    }

    /**
     * checks if the table has ingredients
     * @return true if ingredients are available, false otherwise
     */
    public synchronized boolean isTableAvailable() {
        return tableAvailable;
    }
}
