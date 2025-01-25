public class Chef implements Runnable {
    private final int myIngredient;  // the chef's own ingredient
    private final Supplier supplier;

    /**
     * Chef constructor with a specific ingredient and a reference to the shared Supplier.
     *
     * @param myIngredient the ingredient this Chef always has either RICE, NORI, or FILLING
     * @param supplier the shared Supplier responsible for providing ingredients
     */
    public Chef(int myIngredient, Supplier supplier) {
        this.myIngredient = myIngredient;
        this.supplier = supplier;
    }

    /**
     * main execution method of the Chef.
     * each Chef continuously checks if it can make sushi using the ingredients on the table.
     */
    @Override
    public void run() {
        while (true) {
            makeSushi();
        }
    }

    /**
     * checks if the two ingredients on the table match what this Chef needs.
     * if true, the Chef takes the ingredients, makes the sushi, and notifies the Supplier.
     */
    private void makeSushi() {
        synchronized (supplier) {
            // wait until the table has the two missing ingredients
            while (!canMakeSushi(supplier.getTable())) {
                try {
                    supplier.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // the table has what we need:
            System.out.println("[" + Thread.currentThread().getName() + "] Has missing ingredient, making sushi...");

            // simulate making the sushi for 3 seconds
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // after making the sushi, clear the table so Supplier can place new ingredients
            System.out.println("[" + Thread.currentThread().getName() + "] Sushi has been made, notifying supplier...");
            supplier.takeIngredients();
        }
    }

    /**
     * Determines whether the Chef can make sushi based on the ingredients on the table.
     *
     * @param tableIngredients the array of ingredients currently on the table
     * @return true if the table contains exactly the two ingredients this Chef needs, false otherwise
     */
    private boolean canMakeSushi(int[] tableIngredients) {
        // if table empty or not exactly 2 items, can't make sushi
        if (tableIngredients == null || tableIngredients.length != 2) {
            return false;
        }

        // the chef can make sushi if the chef's own ingredient is NOT on the table,
        // which means the table has the other two ingredients.
        for (int ing : tableIngredients) {
            if (ing == myIngredient) {
                return false;
            }
        }
        return true;
    }
}
