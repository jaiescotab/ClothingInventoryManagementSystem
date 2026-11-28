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

           switch (choice) {

    case 1:
        System.out.println("\n===== ADD PRODUCT =====");
        System.out.println("1. Tops");
        System.out.println("2. Bottoms");
        System.out.println("3. Outerwear");
        System.out.print("Choose product category: ");

        int categoryChoice = Integer.parseInt(input.nextLine());

switch (categoryChoice) {

    case 1:
        System.out.println("You selected Tops.");
        break;

    case 2:
        System.out.println("You selected Bottoms.");
        break;

    case 3:
        System.out.println("You selected Outerwear.");
        break;


    default:
        System.out.println("Invalid category choice.");
}
        String id;

       while (true) {

       System.out.print("Enter Product ID: ");
           id = input.nextLine();

       if (!id.isBlank()) {
        break;
    } else {
        System.out.println("Product ID cannot be empty. Please try again.");
    }
}
       String name;

       while (true) {

       System.out.print("Enter Product Name: ");
            name = input.nextLine();

       if (!name.isBlank()) {
        break;
    } else {
        System.out.println("Product name cannot be empty. Please try again.");
    }
}
   double unitPrice;

     while (true) {
    try {
        System.out.print("Enter Unit Price: ");
        unitPrice = Double.parseDouble(input.nextLine());

        if (unitPrice >= 0) {
            break;
        } else {
            System.out.println("Unit price cannot be negative. Please try again.");
        }

    } catch (NumberFormatException e) {
        System.out.println("Invalid input. Please enter a valid number.");
    }
}

int quantity;

while (true) {
    try {
        System.out.print("Enter Quantity: ");
        quantity = Integer.parseInt(input.nextLine());

        if (quantity >= 0) {
            break;
        } else {
            System.out.println("Quantity cannot be negative. Please try again.");
        }

    } catch (NumberFormatException e) {
        System.out.println("Invalid input. Please enter a whole number.");
    }
}
    int reorderLevel=0;

while (true) {

    try {
        System.out.print("Enter Reorder Level: ");
        reorderLevel = Integer.parseInt(input.nextLine());

        if (reorderLevel >= 0) {
            break;
        } else {
            System.out.println("Reorder level cannot be negative. Please try again.");
        }
    } catch (NumberFormatException e) {
        System.out.println("Invalid input. Please enter a whole number.");
    }
}
System.out.println("\n===== PRODUCT DETAILS =====");
System.out.println("Product ID: " + id);
System.out.println("Product Name: " + name);
System.out.println("Unit Price: " + unitPrice);
System.out.println("Quantity: " + quantity);
System.out.println("Reorder Level: " + reorderLevel);
String size;
String color;
String sleeveType;
System.out.print("Enter Size: ");
size = input.nextLine();
while (true) {
    System.out.print("Enter Color: ");
    color = input.nextLine();

    if (!color.isBlank() && !color.matches("[0-9]+")) {
        break;
    } else {
        System.out.println("Invalid color. Please enter text.");
    }
}
while (true) {
    System.out.print("Enter Sleeve Type: ");
    sleeveType = input.nextLine();

    if (!sleeveType.isBlank() && !sleeveType.matches("[0-9]+")) {
        break;
    } else {
        System.out.println("Invalid sleeve type. Please enter text.");
    }
}
Tops product = new Tops(id, name, unitPrice, quantity,
                        reorderLevel, size, color, sleeveType);
inventory.addProduct(product);
System.out.println("Product added successfully!");
System.out.println("\nTotal Products: " + inventory.getProducts().size());
 break;

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