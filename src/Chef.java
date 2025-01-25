public class Chef implements Runnable {
    private final int myIngredient; // chef's specific ingredient
    private final TableMonitor tableMonitor;

    /**
     * chef constructor
     * @param myIngredient the ingredient this chef always has
     * @param tableMonitor the shared table monitor
     */
    public Chef(int myIngredient, TableMonitor tableMonitor) {
        this.myIngredient = myIngredient;
        this.tableMonitor = tableMonitor;
    }

    /**
     * continuously checks for ingredients and makes sushi
     */
    @Override
    public void run() {
        while (true) {
            synchronized (tableMonitor) {
                while (!tableMonitor.isTableAvailable() || !canMakeSushi(tableMonitor.getIngredients())) {
                    try {
                        tableMonitor.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        e.printStackTrace();
                    }
                }

                System.out.println("[" + Thread.currentThread().getName() + "] making sushi...");
                try {
                    Thread.sleep(3000); // simulate making sushi
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }

                tableMonitor.takeIngredients();
                tableMonitor.notifyAll();
            }
        }
    }

    /**
     * checks if the chef can make sushi with the ingredients on the table
     * @param ingredients the ingredients on the table
     * @return true if the chef can make sushi, false otherwise
     */
    private boolean canMakeSushi(int[] ingredients) {
        if (ingredients == null || ingredients.length != 2) return false;
        for (int ing : ingredients) {
            if (ing == myIngredient) return false;
        }
        return true;
    }
}
