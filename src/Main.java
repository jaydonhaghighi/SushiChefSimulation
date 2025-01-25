public class Main {

    /**
     * starts the simulation by creating the supplier and chefs, then starts their threads
     */
    public static void main(String[] args) {
        // create shared monitor
        TableMonitor tableMonitor = new TableMonitor();

        // create supplier and chefs
        Supplier supplier = new Supplier(tableMonitor);
        Chef chefWithRice = new Chef(TableMonitor.RICE, tableMonitor);
        Chef chefWithNori = new Chef(TableMonitor.NORI, tableMonitor);
        Chef chefWithFilling = new Chef(TableMonitor.FILLING, tableMonitor);

        // start threads
        new Thread(supplier, "agent").start();
        new Thread(chefWithRice, "riceChef").start();
        new Thread(chefWithNori, "noriChef").start();
        new Thread(chefWithFilling, "fillingChef").start();
    }
}
