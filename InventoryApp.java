import java.util.Scanner;

public class InventoryApp {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        InventoryManager inventory = new InventoryManager();

        int choice;

        do {
            System.out.println("\n===== INVENTORY MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Product");
            System.out.println("2. View All Products");
            System.out.println("3. Search Product");
            System.out.println("4. Update Product");
            System.out.println("5. Delete Product");
            System.out.println("6. Stock In");
            System.out.println("7. Stock Out");
            System.out.println("8. View Low-Stock Products");
            System.out.println("9. Sort Products");
            System.out.println("10. Generate Summary Report");
            System.out.println("11. View Transaction History");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

           choice = getValidChoice(input);
           choice = getValidChoice(input);

            switch (choice) {

                case 0:
                    System.out.println("Exiting Inventory Management System...");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 0);

        input.close();
    }
    private static int getValidChoice(Scanner input) {

    while (true) {

        try {
            int choice = Integer.parseInt(input.nextLine());

            if (choice >= 0 && choice <= 11) {
                return choice;
            } else {
                System.out.println("Invalid choice. Please enter a number from 0 to 11.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
        }
    }
}
}