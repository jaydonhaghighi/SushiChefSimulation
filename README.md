# Sushi Chef Simulation

## Description
This project simulates a sushi-making process with a supplier (Agent) and three chefs, each of whom always has one specific ingredient. The supplier provides two random ingredients, and the chefs collaborate to complete the sushi-making process. The simulation demonstrates multithreading in Java, focusing on thread synchronization.

---

## File Descriptions
1. **`Main.java`**:
    - The entry point of the application.
    - Initializes the supplier and chefs.
    - Starts threads for the supplier and the chefs to simulate concurrent behavior.

2. **`Supplier.java`**:
    - Represents the supplier (Agent) responsible for providing two random ingredients at a time.
    - Limits the production to 20 rounds.
    - Ensures synchronization to avoid race conditions while chefs interact with the table.

3. **`Chef.java`**:
    - Represents a chef with a specific ingredient (e.g., rice, nori, or filling).
    - Waits for the other two ingredients from the supplier to make sushi.
    - Notifies the supplier when the table is cleared after making sushi.

---

## Setup Instructions

1. **Requirements**:
    - Java Development Kit (JDK) 8 or later.
    - A text editor or IDE (e.g., IntelliJ IDEA, Eclipse).

2. **Compilation**:
    - Open a terminal or command prompt.
    - Navigate to the directory containing the Java files.
    - Compile all Java files using the following command:
      ```bash
      javac Main.java Supplier.java Chef.java
      ```

3. **Execution**:
    - Run the program using:
      ```bash
      java Main
      ```

---

## Program Flow
1. The supplier starts producing two random ingredients and places them on a shared table.
2. Chefs continuously check the table to see if the two missing ingredients they need are available.
3. Once a chef completes making sushi, they clear the table and notify the supplier to produce new ingredients.
4. The simulation stops once the supplier reaches the maximum production count (20).

---

## Notes
- The program uses thread synchronization (`synchronized`, `wait`, and `notifyAll`) to ensure safe access to shared resources (the table).
- The `Supplier` terminates after 20 rounds of production. Ensure the `productionCount` constant is set correctly in the `Supplier` class.

---