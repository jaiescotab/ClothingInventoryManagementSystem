import java.util.Scanner;
import java.util.ArrayList;

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
            System.out.println("12. Advanced Search and Filters");
            System.out.println("13. Point-of-Sale");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            choice = getValidChoice(input, 0, 13);

            switch (choice) {

                case 1: {
                    System.out.println("\n===== ADD PRODUCT =====");
                    System.out.println("1. Tops");
                    System.out.println("2. Bottoms");
                    System.out.println("3. Outerwear");

                    int categoryChoice = getValidChoice(input, 1, 3);

                    String id;

                    while (true) {
                        id = getNonEmptyInput(input, "Enter Product ID: ");

                        if (inventory.findProductById(id) == null) {
                            break;
                        }
                        System.out.println("Product ID already exists. Please try another ID.");
                    }
                    String name = getNonEmptyInput(input, "Enter Product Name: ");
                    double unitPrice = getValidPrice(input);
                    int quantity = getValidNumber(input, "Enter Quantity: ");
                    int reorderLevel = getValidNumber(input, "Enter Reorder Level: ");

                    switch (categoryChoice) {

                        case 1: {
                            System.out.println("\n===== TOPS DETAILS =====");

                            String size = getNonEmptyInput(input, "Enter Size: ");
                            String color = getNonEmptyInput(input, "Enter Color: ");
                            String sleeveType = getNonEmptyInput(input, "Enter Sleeve Type: ");

                            Tops product = new Tops(id, name, unitPrice, quantity,
                                    reorderLevel, size, color, sleeveType);

                            inventory.addProduct(product);
                            break;
                        }
                        case 2: {
                            System.out.println("\n===== BOTTOMS DETAILS =====");

                            String waistSize = getNonEmptyInput(input, "Enter Waist Size: ");
                            String color = getNonEmptyInput(input, "Enter Color: ");
                            String length = getNonEmptyInput(input, "Enter Length: ");

                            Bottoms product = new Bottoms(id, name, unitPrice, quantity,
                                    reorderLevel, waistSize, color, length);

                            inventory.addProduct(product);
                            break;
                        }
                        case 3: {
                            System.out.println("\n===== OUTERWEAR DETAILS =====");

                            String size = getNonEmptyInput(input, "Enter Size: ");
                            String color = getNonEmptyInput(input, "Enter Color: ");
                            String garmentType = getNonEmptyInput(input, "Enter Garment Type: ");

                            Outerwear product = new Outerwear(id, name, unitPrice, quantity,
                                    reorderLevel, size, color, garmentType);

                            inventory.addProduct(product);
                            break;
                        }
                    }

                    System.out.println("\nProduct added successfully!");
                    System.out.println("Total Products: " + inventory.getProducts().size());
                    break;
                }
                case 2:
                    System.out.println("\n===== ALL PRODUCTS =====");

                    if (inventory.getProducts().isEmpty()) {
                        System.out.println("No products found.");
                    } else {
                        for (Product product : inventory.getProducts()) {
                            System.out.println("\n-------------------------");
                            System.out.println("Product ID: " + product.getId());
                            System.out.println("Product Name: " + product.getName());
                            System.out.println("Category: " + product.getCategory());
                            System.out.println("Unit Price: " + product.getUnitPrice());
                            System.out.println("Quantity: " + product.getQuantity());
                            System.out.println("Reorder Level: " + product.getReorderLevel());
                            System.out.println("Additional Details: " + product.getProductInfo());
                        }
                    }
                    break;

                case 3: {
                    System.out.println("\n===== SEARCH PRODUCT =====");
                    System.out.println("1. Search by Product ID");
                    System.out.println("2. Search by Product Name");

                    int searchChoice = getValidChoice(input, 1, 2);

                    if (searchChoice == 1) {
                        String id = getNonEmptyInput(input, "Enter Product ID: ");
                        Product product = inventory.findProductById(id);

                        if (product != null) {
                            System.out.println("\n===== PRODUCT FOUND =====");
                            System.out.println("Product ID: " + product.getId());
                            System.out.println("Product Name: " + product.getName());
                            System.out.println("Category: " + product.getCategory());
                            System.out.println("Unit Price: " + product.getUnitPrice());
                            System.out.println("Quantity: " + product.getQuantity());
                            System.out.println("Reorder Level: " + product.getReorderLevel());
                            System.out.println("Additional Details: " + product.getProductInfo());
                        } else {
                            System.out.println("No product found with that ID.");
                        }
                    } else {
                        String name = getNonEmptyInput(input, "Enter Product Name: ");
                        ArrayList<Product> foundProducts =
                                inventory.searchProductsByName(name);

                        if (foundProducts.isEmpty()) {
                            System.out.println("No products found with that name.");
                        } else {
                            System.out.println("\n===== SEARCH RESULTS =====");

                            for (Product product : foundProducts) {
                                System.out.println("\n-------------------------");
                                System.out.println("Product ID: " + product.getId());
                                System.out.println("Product Name: " + product.getName());
                                System.out.println("Category: " + product.getCategory());
                                System.out.println("Unit Price: " + product.getUnitPrice());
                                System.out.println("Quantity: " + product.getQuantity());
                                System.out.println("Reorder Level: " + product.getReorderLevel());
                                System.out.println("Additional Details: " + product.getProductInfo());
                            }
                        }
                    }
                    break;
                }
                case 4: {
                    System.out.println("\n===== UPDATE PRODUCT =====");

                    String id = getNonEmptyInput(input, "Enter Product ID to update: ");
                    Product existingProduct = inventory.findProductById(id);

                    if (existingProduct == null) {
                        System.out.println("Product not found.");
                    } else {
                        System.out.println("\n===== CURRENT PRODUCT DETAILS =====");
                        System.out.println("Product ID: " + existingProduct.getId());
                        System.out.println("Product Name: " + existingProduct.getName());
                        System.out.println("Category: " + existingProduct.getCategory());
                        System.out.println("Unit Price: " + existingProduct.getUnitPrice());
                        System.out.println("Quantity: " + existingProduct.getQuantity());
                        System.out.println("Reorder Level: " + existingProduct.getReorderLevel());
                        System.out.println("Additional Details: " + existingProduct.getProductInfo());
                        System.out.println("\n===== ENTER UPDATED DETAILS =====");

                        String name = getNonEmptyInput(input, "Enter New Product Name: ");
                        double unitPrice = getValidPrice(input);
                        int reorderLevel = getValidNumber(input, "Enter New Reorder Level: ");

                        Product updatedProduct = null;

                        if (existingProduct.getCategory().equals("Tops")) {

                            String size = getNonEmptyInput(input, "Enter New Size: ");
                            String color = getNonEmptyInput(input, "Enter New Color: ");
                            String sleeveType = getNonEmptyInput(input, "Enter New Sleeve Type: ");

                            updatedProduct = new Tops(
                                    id, name, unitPrice, existingProduct.getQuantity(),
                                    reorderLevel, size, color, sleeveType
                            );
                        } else if (existingProduct.getCategory().equals("Bottoms")) {
                            String waistSize = getNonEmptyInput(input, "Enter New Waist Size: ");
                            String color = getNonEmptyInput(input, "Enter New Color: ");
                            String length = getNonEmptyInput(input, "Enter New Length: ");

                            updatedProduct = new Bottoms(
                                    id, name, unitPrice, existingProduct.getQuantity(),
                                    reorderLevel, waistSize, color, length
                            );
                        } else if (existingProduct.getCategory().equals("Outerwear")) {
                            String size = getNonEmptyInput(input, "Enter New Size: ");
                            String color = getNonEmptyInput(input, "Enter New Color: ");
                            String garmentType = getNonEmptyInput(input, "Enter New Garment Type: ");

                            updatedProduct = new Outerwear(
                                    id, name, unitPrice, existingProduct.getQuantity(),
                                    reorderLevel, size, color, garmentType
                            );
                        }
                        if (inventory.updateProduct(id, updatedProduct)) {
                            System.out.println("\nProduct updated successfully!");

                            System.out.println("\n===== UPDATED PRODUCT DETAILS =====");
                            System.out.println("Product ID: " + updatedProduct.getId());
                            System.out.println("Product Name: " + updatedProduct.getName());
                            System.out.println("Category: " + updatedProduct.getCategory());
                            System.out.println("Unit Price: " + updatedProduct.getUnitPrice());
                            System.out.println("Quantity: " + updatedProduct.getQuantity());
                            System.out.println("Reorder Level: " + updatedProduct.getReorderLevel());
                            System.out.println("Additional Details: " + updatedProduct.getProductInfo());
                        } else {
                            System.out.println("Product update failed.");
                        }
                    }
                    break;
                }
                case 5: {
                    System.out.println("\n===== DELETE PRODUCT =====");

                    String id = getNonEmptyInput(input, "Enter Product ID to delete: ");
                    Product product = inventory.findProductById(id);

                    if (product == null) {
                        System.out.println("Product not found.");
                    } else {
                        System.out.println("\n===== PRODUCT DETAILS =====");
                        System.out.println("Product ID: " + product.getId());
                        System.out.println("Product Name: " + product.getName());
                        System.out.println("Category: " + product.getCategory());
                        System.out.println("Unit Price: " + product.getUnitPrice());
                        System.out.println("Quantity: " + product.getQuantity());
                        System.out.println("Reorder Level: " + product.getReorderLevel());
                        System.out.println("Additional Details: " + product.getProductInfo());

                        String confirmation;
                        while (true) {
                            confirmation = getNonEmptyInput(input,
                                    "Are you sure you want to delete this product? (Y/N): ");
                            if (confirmation.equalsIgnoreCase("Y")
                                    || confirmation.equalsIgnoreCase("N")) {
                                break;
                            }
                            System.out.println("Invalid input. Please enter Y or N.");
                        }
                        if (confirmation.equalsIgnoreCase("Y")) {
                            if (inventory.deleteProduct(id)) {
                                System.out.println("Product deleted successfully!");
                                System.out.println("Total Products: " + inventory.getProducts().size());
                            } else {
                                System.out.println("Product deletion failed.");
                            }
                        } else {
                            System.out.println("Product deletion cancelled.");
                        }
                    }
                    break;
                }
                case 6: {
                    System.out.println("\n===== STOCK IN =====");

                    String id = getNonEmptyInput(input, "Enter Product ID: ");
                    Product product = inventory.findProductById(id);

                    if (product == null) {
                        System.out.println("Product not found.");
                    } else {
                        int quantity = getValidNumber(input, "Enter quantity to add: ");

                        while (quantity <= 0) {
                            System.out.println("Quantity must be greater than zero.");
                            quantity = getValidNumber(input, "Enter quantity to add: ");
                        }
                        if (inventory.stockIn(id, quantity)) {
                            System.out.println("Stock added successfully!");
                            System.out.println("Updated Quantity: " + product.getQuantity());
                        } else {
                            System.out.println("Stock in failed.");
                        }
                    }
                    break;
                }
                case 7: {
                    System.out.println("\n===== STOCK OUT =====");

                    String id = getNonEmptyInput(input, "Enter Product ID: ");
                    Product product = inventory.findProductById(id);

                    if (product == null) {
                        System.out.println("Product not found.");
                    } else {
                        int quantity = getValidNumber(input, "Enter quantity to remove: ");
                        while (quantity <= 0) {
                            System.out.println("Quantity must be greater than zero.");
                            quantity = getValidNumber(input, "Enter quantity to remove: ");
                        }

                        if (quantity > product.getQuantity()) {
                            System.out.println("Insufficient stock. Available quantity: "
                                    + product.getQuantity());
                        } else if (inventory.stockOut(id, quantity)) {
                            System.out.println("Stock removed successfully!");
                            System.out.println("Updated Quantity: " + product.getQuantity());

                            if (product.getQuantity() <= product.getReorderLevel()) {
                                System.out.println("WARNING: Product has reached its reorder level!");
                            }
                        } else {
                            System.out.println("Stock out failed.");
                        }
                    }
                    break;
                }
                case 8: {
                    System.out.println("\n===== LOW-STOCK PRODUCTS =====");

                    ArrayList<Product> lowStockProducts = inventory.getLowStockProducts();

                    if (lowStockProducts.isEmpty()) {
                        System.out.println("No low-stock products found.");
                    } else {
                        for (Product product : lowStockProducts) {
                            System.out.println("\n-------------------------");
                            System.out.println("Product ID: " + product.getId());
                            System.out.println("Product Name: " + product.getName());
                            System.out.println("Category: " + product.getCategory());
                            System.out.println("Unit Price: " + product.getUnitPrice());
                            System.out.println("Quantity: " + product.getQuantity());
                            System.out.println("Reorder Level: " + product.getReorderLevel());
                            System.out.println("Additional Details: " + product.getProductInfo());
                        }
                    }
                    break;
                }
                                case 9: {
                    System.out.println("\n===== SORT PRODUCTS =====");
                    System.out.println("1. Sort by Name");
                    System.out.println("2. Sort by Price");
                    System.out.println("3. Sort by Quantity");

                    int sortChoice = getValidChoice(input, 1, 3);

                    System.out.println("\n1. Ascending");
                    System.out.println("2. Descending");

                    int orderChoice = getValidChoice(input, 1, 2);
                    boolean ascending = orderChoice == 1;

                    if (sortChoice == 1) {
                        inventory.sortByName(ascending);
                    } else if (sortChoice == 2) {
                        inventory.sortByPrice(ascending);
                    } else {
                        inventory.sortByQuantity(ascending);
                    }
                    System.out.println("\nProducts sorted successfully!");

                    for (Product product : inventory.getProducts()) {
                        System.out.println("\n-------------------------");
                        System.out.println("Product ID: " + product.getId());
                        System.out.println("Product Name: " + product.getName());
                        System.out.println("Category: " + product.getCategory());
                        System.out.println("Unit Price: " + product.getUnitPrice());
                        System.out.println("Quantity: " + product.getQuantity());
                        System.out.println("Reorder Level: " + product.getReorderLevel());
                        System.out.println("Additional Details: " + product.getProductInfo());
                    }
                    break;
                }
                 case 10:
                    System.out.println(inventory.generateSummaryReport());
                    break;

                 case 11:
                    System.out.println("\n===== TRANSACTION HISTORY =====");

                    ArrayList<StockTransaction> transactions = inventory.getTransactions();

                    if (transactions.isEmpty()) {
                        System.out.println("No transaction history found.");
                    } else {
                        for (StockTransaction transaction : transactions) {
                            System.out.println("\n-------------------------");
                            System.out.println("Transaction Type: " + transaction.getTransactionType());
                            System.out.println("Product ID: " + transaction.getProductId());
                            System.out.println("Quantity: " + transaction.getQuantity());
                            System.out.println("Timestamp: " + transaction.getTimestamp());
                        }
                    }
                    break;
                    case 12:
    System.out.println("\n===== ADVANCED SEARCH AND FILTERS =====");

    System.out.println("1. All Categories");
    System.out.println("2. Tops");
    System.out.println("3. Bottoms");
    System.out.println("4. Outerwear");

    int categoryChoice = getValidChoice(input, 1, 4);
    String category = "";

    switch (categoryChoice) {
        case 1:
            category = "All";
            break;
        case 2:
            category = "Tops";
            break;
        case 3:
            category = "Bottoms";
            break;
        case 4:
            category = "Outerwear";
            break;
    }

    System.out.print("Minimum Price: ");
    double minPrice = getValidPrice(input);

    System.out.print("Maximum Price: ");
    double maxPrice = getValidPrice(input);

    if (maxPrice < minPrice) {
        System.out.println("Maximum price cannot be lower than minimum price.");
        break;
    }

    System.out.println("\nStock Status:");
    System.out.println("1. All");
    System.out.println("2. In Stock");
    System.out.println("3. Low Stock");
    System.out.println("4. Out of Stock");

    int stockChoice = getValidChoice(input, 1, 4);
    String stockStatus = "";

    switch (stockChoice) {
        case 1:
            stockStatus = "All";
            break;
        case 2:
            stockStatus = "In Stock";
            break;
        case 3:
            stockStatus = "Low Stock";
            break;
        case 4:
            stockStatus = "Out of Stock";
            break;
    }

    ArrayList<Product> filteredProducts =
            inventory.filterProducts(category, minPrice, maxPrice, stockStatus);

    if (filteredProducts.isEmpty()) {
        System.out.println("No matching products found.");
    } else {
        System.out.println("\n===== FILTERED PRODUCTS =====");

        for (Product product : filteredProducts) {
            System.out.println("\nID: " + product.getId());
            System.out.println("Name: " + product.getName());
            System.out.println("Category: " + product.getCategory());
            System.out.println("Price: " + product.getUnitPrice());
            System.out.println("Quantity: " + product.getQuantity());
            System.out.println("Details: " + product.getProductInfo());
        }
    }
    break;case 13:
    pointOfSale(input, inventory);
    break;
                          case 0:
                    inventory.saveData();
                    System.out.println("Exiting Inventory Management System...");
                    break;
                default:
                    System.out.println("This feature is not implemented yet.");
            }
        } while (choice != 0);
        input.close();
    }
    private static String getNonEmptyInput(Scanner input, String message) {
        String value;

        while (true) {
            System.out.print(message);
            value = input.nextLine();

            if (!value.isBlank()) {
                return value;
            }
            System.out.println("Input cannot be empty. Please try again.");
        }
    }
    private static int getValidNumber(Scanner input, String message) {
        while (true) {
            try {
                System.out.print(message);
                int number = Integer.parseInt(input.nextLine());

                if (number >= 0) {
                    return number;
                }
                System.out.println("Number cannot be negative.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a whole number.");
            }
        }
    }
    private static double getValidPrice(Scanner input) {
        while (true) {
            try {
                System.out.print("Enter Unit Price: ");
                double price = Double.parseDouble(input.nextLine());

                if (Double.isFinite(price) && price >= 0) {
                    return price;
                }
                System.out.println("Unit price must be a valid non-negative number.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }
        private static int getValidChoice(Scanner input, int minimum, int maximum) {
        while (true) {
            try {
                int choice = Integer.parseInt(input.nextLine());

                if (choice >= minimum && choice <= maximum) {
                    return choice;
                }
                System.out.println("Invalid choice. Please enter a number from "
                        + minimum + " to " + maximum + ".");
                System.out.print("Enter your choice: ");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                System.out.print("Enter your choice: ");
            }
        }
    }

private static void pointOfSale(Scanner input, InventoryManager inventory) {

    ArrayList<CartItem> cart = new ArrayList<>();
    String addMore;

    do {
        System.out.println("\n===== POINT OF SALE =====");
        System.out.print("Enter Product ID: ");
        String id = input.nextLine();

        Product product = inventory.findProductById(id);

        if (product == null) {
            System.out.println("Product not found.");
        } else {
            System.out.println("Product: " + product.getName());
            System.out.println("Available Stock: " + product.getQuantity());
            System.out.println("Unit Price: " + product.getUnitPrice());

            System.out.print("Enter Quantity: ");
            int quantity = Integer.parseInt(input.nextLine());

            if (quantity <= 0 || quantity > product.getQuantity()) {
                System.out.println("Invalid quantity or insufficient stock.");
            } else {
                cart.add(new CartItem(product, quantity));
                System.out.println("Product added to cart.");
            }
        }
        System.out.print("Add another product? (Y/N): ");
        addMore = input.nextLine();

    } while (addMore.equalsIgnoreCase("Y"));
    if (cart.isEmpty()) {
        System.out.println("Cart is empty.");
        return;
    }
    double subtotal = 0;

    System.out.println("\n===== SHOPPING CART =====");

    for (CartItem item : cart) {
        System.out.println(item.getProduct().getName()
                + " x " + item.getQuantity()
                + " = " + item.getSubtotal());

        subtotal += item.getSubtotal();
    }
    System.out.println("Subtotal: " + subtotal);

    System.out.println("\nDiscount:");
    System.out.println("1. No Discount");
    System.out.println("2. Bulk Discount");
    System.out.println("3. Loyalty Discount");

    int discountChoice = Integer.parseInt(input.nextLine());
    double discountRate = 0;

    if (discountChoice == 2) {
        discountRate = 0.10;
    } else if (discountChoice == 3) {
        discountRate = 0.05;
    } else if (discountChoice != 1) {
        System.out.println("Invalid discount choice.");
        return;
    }
    double discount = subtotal * discountRate;
    double total = subtotal - discount;

    System.out.println("Discount: " + discount);
    System.out.println("Total: " + total);

    System.out.print("Enter Payment: ");
    double payment = Double.parseDouble(input.nextLine());

    if (!Double.isFinite(payment) || payment < total) {
        System.out.println("Insufficient or invalid payment.");
        return;
    }
    // Deduct stock only after successful payment.
    for (CartItem item : cart) {
        inventory.stockOut(
                item.getProduct().getId(),
                item.getQuantity()
        );
    }
    double change = payment - total;

    System.out.println("\n========== RECEIPT ==========");

    for (CartItem item : cart) {
        System.out.println(item.getProduct().getName()
                + " x " + item.getQuantity()
                + " = " + item.getSubtotal());
    }
    System.out.println("-----------------------------");
    System.out.println("Subtotal: " + subtotal);
    System.out.println("Discount: " + discount);
    System.out.println("Total: " + total);
    System.out.println("Payment: " + payment);
    System.out.println("Change: " + change);
    System.out.println("=============================");
    System.out.println("Thank you for shopping!");
}
}