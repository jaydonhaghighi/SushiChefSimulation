Sushi Chef Simulation

Files
1. Main.java: The main entry point. Initializes the supplier and chefs, then starts the simulation.
2. Supplier.java: Manages ingredient production (limited to 20 rounds) and synchronization.
3. Chef.java: Represents chefs who use ingredients to make sushi.

Setup
1. Install Java (JDK 8 or later).
2. Compile all files: javac Main.java Supplier.java Chef.java
3. Run the program: java Main

How It Works
1. The supplier places two random ingredients on a shared table.
2. Chefs wait for the missing ingredients to make sushi.
3. Once sushi is made, the table is cleared for the supplier to add new ingredients.
4. The simulation stops after 20 ingredient sets are produced.