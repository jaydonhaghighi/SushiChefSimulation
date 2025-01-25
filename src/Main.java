public class Main {

    /**
     * initializes the simulation by creating a supplier
     * and three Chefs, each containing their specific ingredient.
     * It then starts the agent and chef threads to simulate concurrent behavior.
     */
    public static void main(String[] args) {
        // create supplier
        Supplier supplier = new Supplier();

        // create three Chefs, each have their specific ingredient
        Chef chefWithRice = new Chef(Supplier.RICE, supplier);
        Chef chefWithNori = new Chef(Supplier.NORI, supplier);
        Chef chefWithFilling = new Chef(Supplier.FILLING, supplier);

        // create and start threads for supplier and chefs
        Thread agentThread = new Thread(supplier, "Agent");
        Thread riceChefThread = new Thread(chefWithRice, "RiceChef");
        Thread noriChefThread = new Thread(chefWithNori, "NoriChef");
        Thread fillingChefThread = new Thread(chefWithFilling, "FillingChef");

        agentThread.start();
        riceChefThread.start();
        noriChefThread.start();
        fillingChefThread.start();
    }
}